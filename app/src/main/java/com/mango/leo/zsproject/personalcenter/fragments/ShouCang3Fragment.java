package com.mango.leo.zsproject.personalcenter.fragments;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
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
import com.mango.leo.zsproject.utils.AppUtils;
import com.mango.leo.zsproject.utils.HttpUtils;
import com.mango.leo.zsproject.utils.NetUtil;
import com.mango.leo.zsproject.utils.SwipeItemLayout;
import com.mango.leo.zsproject.utils.Urls;
import com.mango.leo.zsproject.utils.header.ClassicsHeader;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import org.greenrobot.eventbus.EventBus;

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
    SmartRefreshLayout refresh_cam;
    private LinearLayoutManager mLayoutManager;
    private ShouCangEventAdapter adapter;
    private int page = 0;
    private List<MyEventBean> mData, mDataAll;
    private SharedPreferences sharedPreferences;
    private boolean isFirstEnter = true;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.shou_cang3, container, false);
        ButterKnife.bind(this, view);
        sharedPreferences = getActivity().getSharedPreferences("CIFIT", MODE_PRIVATE);
        vivistEvent(page);
        initView();
        return view;
    }

    private void refreshAndLoadMore() {
        refresh_cam.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull final RefreshLayout refreshLayout) {
                refreshLayout.getLayout().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if (mDataAll != null) {
                            mDataAll.clear();
                        }
                        if (mData != null) {
                            mData.clear();
                        }
                        page = 0;
                        Log.v("zzzzzzzzz", "-------onRefresh-------" + page);
                        vivistEvent(page);
                        refreshLayout.finishRefresh();
                    }
                }, 500);
            }
        });
        refresh_cam.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull final RefreshLayout refreshLayout) {
                refreshLayout.getLayout().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        page++;
                        Log.v("zzzzzzzzz", "-------onLoadMore-------" + page);
                        vivistEvent(page);
                        refreshLayout.finishLoadMore();

                    }
                }, 500);
            }
        });
        refresh_cam.setRefreshHeader(new ClassicsHeader(getActivity()));
        refresh_cam.setHeaderHeight(50);

        //触发自动刷新
        if (isFirstEnter) {
            isFirstEnter = false;
            //refresh.autoRefresh();
        } else {
            //mAdapter.refresh(initData());
        }
    }

    private void vivistEvent(final int page) {
        Log.v("vvvvvv", "__" + Urls.HOST_FAVOURITE_LIST + "?page=" + page + "&token=" + sharedPreferences.getString("token", ""));
        new Thread(new Runnable() {
            @Override
            public void run() {
                HttpUtils.doGet(Urls.HOST_FAVOURITE_LIST + "?page=" + page + "&token=" + sharedPreferences.getString("token", ""), new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        mHandler.sendEmptyMessage(0);
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        try {
                            // Log.v("ssssssssssssss",""+response.body().string());
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
        }).start();
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
                case 2:
                    List<MyEventBean> beanList = (List<MyEventBean>) msg.obj;
                    Log.v("sssssssssss", "----" + beanList.size());
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

    public void addEventsView(final List<MyEventBean> eventBeans) {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (eventBeans == null || eventBeans.size() == 0) {
                    AppUtils.showToast(getActivity(), getResources().getString(R.string.no_more));
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
                    if (mData != null) {
                        adapter.setmDate(mData);
                    }
                } else {
                    if (mDataAll != null) {
                        //加载更多
                        int i;
                        for (i = 0; i < mDataAll.size(); i++) {
                            if (mDataAll == null) {
                                return;//一开始断网报空指针的情况
                            }
                            adapter.addItem(mDataAll.get(i));//addItem里面记得要notifyDataSetChanged 否则第一次加载不会显示数据
                        }
                    }
                }
            }
        });
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
        refreshAndLoadMore();
        Log.v("yyyyy", "====onCreateView======" + page);
        if (mDataAll != null) {
            mDataAll.clear();
        }
        if (mData != null) {
            mData.clear();
        }
    }

    private ShouCangEventAdapter.OnEventnewsClickListener mOnItemClickListener = new ShouCangEventAdapter.OnEventnewsClickListener() {
        @Override
        public void onItemClick(View view, int position) {
            if (mData.size() <= 0) {
                return;
            }
            EventBus.getDefault().postSticky(adapter.getItem(position).getResponseObject().getContent().get(position % 20).getEntity());
            Intent intent = new Intent(getActivity(), EventDetailActivity.class);
            intent.putExtra("id", adapter.getItem(position).getResponseObject().getContent().get(position).getEntity().getId());
            startActivity(intent);
        }

        @Override
        public void onCancelingShouCangClick(View view, final int position) {
            canCelShpuCang(position - 1);
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
                Log.e("eeeee", "eeeee = " + e.getMessage());
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

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    public void noMoreMsg() {
        AppUtils.showToast(getActivity(), getResources().getString(R.string.no_more));
    }
}
