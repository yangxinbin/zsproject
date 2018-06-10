package com.mango.leo.zsproject.personalcenter.fragments;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mango.leo.zsproject.R;
import com.mango.leo.zsproject.eventexhibition.show.EventDetailActivity;
import com.mango.leo.zsproject.industrialservice.createrequirements.util.ProjectsJsonUtils;
import com.mango.leo.zsproject.personalcenter.adapter.ShouCangEventAdapter;
import com.mango.leo.zsproject.personalcenter.bean.MyEventBean;
import com.mango.leo.zsproject.personalcenter.show.AccountSecurityActivity;
import com.mango.leo.zsproject.utils.AppUtils;
import com.mango.leo.zsproject.utils.HttpUtils;
import com.mango.leo.zsproject.utils.NetUtil;
import com.mango.leo.zsproject.utils.SwipeItemLayout;
import com.mango.leo.zsproject.utils.Urls;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by Administrator on 2018/6/5 0005.
 */

public class ShouCang3Fragment extends Fragment {
    @Bind(R.id.recycle_cams)
    RecyclerView recycle_cam;
    @Bind(R.id.refresh_cams)
    SwipeRefreshLayout refresh_cam;
    private LinearLayoutManager mLayoutManager;
    private ShouCangEventAdapter adapter;
    private int page = 0;
    private List<MyEventBean> mData, mDataAll;
    private SharedPreferences sharedPreferences;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.shou_cang3, container, false);
        ButterKnife.bind(this, view);
        initSwipeRefreshLayout();
        sharedPreferences = getActivity().getSharedPreferences("CIFIT", MODE_PRIVATE);
        initView();
        initHeader();
        vivistEvent(page);
        return view;
    }

    private void vivistEvent(int page) {
        Log.v("vvvvvv", "__" + Urls.HOST_FAVOURITE_LIST + "?page=" + page + "&token=" + sharedPreferences.getString("token", ""));
        HttpUtils.doGet(Urls.HOST_FAVOURITE_LIST + "?page=" + page + "&token=" + sharedPreferences.getString("token", ""), new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                mHandler.sendEmptyMessage(0);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                try {
                    List<MyEventBean> beanList = ProjectsJsonUtils.readJsonMyEventBeans(response.body().string(), "content");//data是json字段获得data的值即对象数组
                    Message msg = mHandler.obtainMessage();
                    msg.obj = beanList;
                    msg.what = 2;
                    msg.sendToTarget();
                } catch (Exception e) {
                        mHandler.sendEmptyMessage(0);
//                    Log.e("eeeee", response.body().string()+"Exception = " + e);
                }

            }

        });
    }

    private MyHandler mHandler = new MyHandler();

    private class MyHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 0:
                    AppUtils.showToast(getActivity(), "访问失败");
                    break;
                case 1:
                    AppUtils.showToast(getActivity(), "访问成功");
                    Intent intent = new Intent(getActivity(), AccountSecurityActivity.class);
                    startActivity(intent);
                    break;
                case 2:
                    List<MyEventBean> beanList = (List<MyEventBean>) msg.obj;
                    /*if (beanList.size() == 0){
                        noMoreMsg();
                    }else {
                        adapter.isShowFooter(true);
                    }*/
                    addEventsView(beanList);
                    break;
                case 3:
                    //AppUtils.showToast(getActivity(), "取消收藏成功");
                    if (mDataAll != null && mData != null) {
                        mDataAll.clear();
                        mData.clear();
                    }
                    adapter.notifyDataSetChanged();
                    break;
                case 4:
                    AppUtils.showToast(getActivity(), "取消收藏失败");
                    break;
                case 5:
                    AppUtils.showToast(getActivity(), "已收藏");
                    break;
                default:
                    break;
            }
        }
    }

    public void addEventsView(List<MyEventBean> eventBeans) {
        if (eventBeans == null) {
            Log.v("vvvv", eventBeans.get(0).getResponseObject().getContent().get(0).getEntity().getName() + "======eventBeans======" + eventBeans.size());
            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    AppUtils.showToast(getActivity(), "没有更多活动，请您稍后刷新！");
                }
            });
        }
        if (mData == null && mDataAll == null) {
            mData = new ArrayList<MyEventBean>();
            mDataAll = new ArrayList<MyEventBean>();
        }
        if (mDataAll != null) {
            mDataAll.clear();
        }
        mDataAll.addAll(eventBeans);
        if (page == 0) {
            for (int i = 0; i < mDataAll.size(); i++) {//
                mData.add(mDataAll.get(i)); //一次显示page= ? 20条数据
            }
            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if (mData != null) {
                        adapter.setmDate(mData);
                    }
                }
            });
        } else {
            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if (mDataAll != null) {
                        //加载更多
                        int count = adapter.getItemCount() - 2;//增加item数减去头部和尾部
                        int i;
                        for (i = 0; i < mDataAll.size(); i++) {
                            if (mDataAll == null) {
                                return;//一开始断网报空指针的情况
                            }
                            adapter.addItem(mDataAll.get(i));//addItem里面记得要notifyDataSetChanged 否则第一次加载不会显示数据
                            if (mDataAll != null && i >= mDataAll.size() - 1) {//到最后
                                noMoreMsg();
                                return;
                            }
                        }
                    }
                }
            });
        }
    }

    private void initView() {
        recycle_cam.setHasFixedSize(true);//固定宽高
        mLayoutManager = new LinearLayoutManager(getActivity());
        recycle_cam.setLayoutManager(mLayoutManager);
        recycle_cam.setItemAnimator(new DefaultItemAnimator());//设置默认动画
        adapter = new ShouCangEventAdapter(getActivity().getApplicationContext());
        recycle_cam.addOnItemTouchListener(new SwipeItemLayout.OnSwipeItemTouchListener(getContext()));
        adapter.setOnEventnewsClickListener(mOnItemClickListener);
        recycle_cam.removeAllViews();
        recycle_cam.setAdapter(adapter);
        recycle_cam.addOnScrollListener(mOnScrollListener);
        Log.v("yyyyy", "====onCreateView======" + page);
        if (mDataAll != null && mData != null) {
            mDataAll.clear();
            mData.clear();
        }
    }

    private ShouCangEventAdapter.OnEventnewsClickListener mOnItemClickListener = new ShouCangEventAdapter.OnEventnewsClickListener() {
        @Override
        public void onItemClick(View view, int position) {
            position = position - 1; //配对headerView
            if (mData.size() <= 0) {
                return;
            }
            Log.v("yxbb", "_____" + adapter.getItem(position).getResponseObject().getContent().get(position).getEntity().getName());
            Intent intent = new Intent(getActivity(), EventDetailActivity.class);
            intent.putExtra("FavouriteId", adapter.getItem(position).getResponseObject().getContent().get(position).getId());
            startActivity(intent);
        }

        @Override
        public void onCancelingShouCangClick(View view, final int position) {
            canCelShpuCang(position-1);
            /*+*/
        }
    };

    private void canCelShpuCang(int position) {
        Map<String, String> mapParams = new HashMap<String, String>();
        mapParams.clear();
//        Log.e("eeeee", adapter.getItem(position).getResponseObject().getContent().get(position).getId()+"eeeee = "+sharedPreferences.getString("token", ""));
        mapParams.put("id", adapter.getItem(position).getResponseObject().getContent().get(position).getId());
        mapParams.put("token", sharedPreferences.getString("token", ""));
        HttpUtils.doDelete(Urls.HOST_FAVOURITE, mapParams, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e("eeeee", "eeeee = "+e.getMessage());
                mHandler.sendEmptyMessage(4);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (String.valueOf(response.code()).startsWith("2")) {
                    mHandler.sendEmptyMessage(3);
                    vivistEvent(0);
                } else {
                    Log.e("eeeee", response.body().string() + "Exception = ");
                    mHandler.sendEmptyMessage(4);
                }
            }
        });
    }

    private int lastVisibleItem;
    private RecyclerView.OnScrollListener mOnScrollListener = new RecyclerView.OnScrollListener() {

        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            super.onScrolled(recyclerView, dx, dy);
            lastVisibleItem = mLayoutManager.findLastVisibleItemPosition();//可见的最后一个item
        }

        @Override
        public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
            super.onScrollStateChanged(recyclerView, newState);
            if (newState == RecyclerView.SCROLL_STATE_IDLE
                    && lastVisibleItem + 1 == adapter.getItemCount()
                    && adapter.isShowFooter()) {//加载判断条件 手指离开屏幕 到了footeritem
                page++;
                vivistEvent(page);
                Log.v("yyyy", "***onScrollStateChanged******");
            }
        }
    };

    public void initSwipeRefreshLayout() {
        refresh_cam.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refresh_cam.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        refresh_cam.setRefreshing(false);
                        if (mData != null && mDataAll != null) {
                            mDataAll.clear();//一定要加上否则会报越界异常 不执行代码加载的if判断
                            mData.clear();
                        }
                        if (NetUtil.isNetConnect(getActivity())) {
                            adapter.isShowFooter(true);
                            page = 0;
                            vivistEvent(page);
                        } else {
                            // mNewsPresenter.visitProjects(getActivity(),mType);//缓存
                        }
                    }
                }, 2000);
            }
        });
        refresh_cam.setColorSchemeResources(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);
    }

    private void initHeader() {
        //渲染header布局
        ConstraintLayout h = new ConstraintLayout(getActivity());
        ConstraintLayout.LayoutParams layoutParam = new ConstraintLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, dp2px(1.0f));
        layoutParam.setMargins(0, 0, 0, 20);
        h.setLayoutParams(layoutParam);
        adapter.setHeaderView(h);
    }

    private int dp2px(float v) {
        DisplayMetrics dm = getResources().getDisplayMetrics();
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, v, dm);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    public void noMoreMsg() {
        adapter.isShowFooter(false);
        AppUtils.showToast(getActivity(), getResources().getString(R.string.no_more_s));
    }
}
