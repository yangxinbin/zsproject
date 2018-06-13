package com.mango.leo.zsproject.industrialpanorama.fragments;

import android.content.Intent;
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
import com.mango.leo.zsproject.industrialpanorama.adapter.ZhaoShanAdapter;
import com.mango.leo.zsproject.industrialpanorama.bean.CityS;
import com.mango.leo.zsproject.industrialpanorama.bean.ZhaoShangBean;
import com.mango.leo.zsproject.industrialpanorama.show.ZhaoShanDetailActivity;
import com.mango.leo.zsproject.industrialservice.createrequirements.util.ProjectsJsonUtils;
import com.mango.leo.zsproject.utils.AppUtils;
import com.mango.leo.zsproject.utils.HttpUtils;
import com.mango.leo.zsproject.utils.SwipeItemLayout;
import com.mango.leo.zsproject.utils.Urls;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.IOException;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * Created by admin on 2018/5/11.
 */

public class InvestmentInformationFragment extends Fragment {
    @Bind(R.id.recycle_mes)
    RecyclerView recycleMes;
    @Bind(R.id.refresh_mes)
    SwipeRefreshLayout refreshMes;
    private LinearLayoutManager mLayoutManager;
    private ZhaoShanAdapter adapter;
    private ArrayList<ZhaoShangBean> mData,mDataAll;
    private int page = 0;
    private String beanM = "深圳";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.investmentinformation, container, false);
        ButterKnife.bind(this, view);
        initRecycle();
        initHeader();
        loadZhaoShanMes(0);
        initSwipeRefreshLayout();
        EventBus.getDefault().register(this);
        if (mDataAll != null && mData != null) {
            mDataAll.clear();
            mData.clear();
        }
        return view;
    }
    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void card1EventBus(CityS bean) {
        Log.v("yyyyyyyyyy","------111---"+bean.getCity());
        beanM = bean.getCity();
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
    public void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
        EventBus.getDefault().unregister(this);
    }

    private void loadZhaoShanMes(final int page) {
        Log.v("zzzzzzzzz","__0_"+Urls.HOST_CITY_MES + "?city=" +beanM+"&page="+page);
        new Thread(new Runnable() {
            @Override
            public void run() {
                HttpUtils.doGet(Urls.HOST_CITY_MES + "?city=" + "深圳"+"&page="+page, new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        mHandler.sendEmptyMessage(0);
                    }
                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        try {
                            final List<ZhaoShangBean> beanList = ProjectsJsonUtils.readJsonZhaoShangBeans(response.body().string(),"content");//data是json字段获得data的值即对象数组
                            if (beanList.size() == 0){
                                mHandler.sendEmptyMessage(2);
                            }else {
                                mHandler.sendEmptyMessage(1);
                            }
                            getActivity().runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    addZhaoShang(beanList,page);
                                }
                            });
                    /*zhaoShangBeanList.clear();
                    zhaoShangBeanList.addAll(beanList);*/
                        } catch (Exception e) {
                            mHandler.sendEmptyMessage(0);
//                    Log.e("eeeee", response.body().string()+"Exception = " + e);
                        }
                    }
                });
            }
        }).start();

    }

    private void initRecycle() {
        mLayoutManager = new LinearLayoutManager(getActivity());
        recycleMes.setLayoutManager(mLayoutManager);
        recycleMes.setItemAnimator(new DefaultItemAnimator());//设置默认动画
        recycleMes.addOnItemTouchListener(new SwipeItemLayout.OnSwipeItemTouchListener(getContext()));
        adapter = new ZhaoShanAdapter(getActivity().getApplicationContext());
        adapter.setOnZhaoShanClickListener(mOnItemClickListener);
        recycleMes.addOnScrollListener(mOnScrollListener);
        //recycleMes.setAdapter(adapter);
        recycleMes.removeAllViews();
        recycleMes.setAdapter(adapter);
    }
    private ZhaoShanAdapter.OnZhaoShanClickListener mOnItemClickListener = new ZhaoShanAdapter.OnZhaoShanClickListener() {
        @Override
        public void onItemClick(View view, int position) {
            position = position - 1; //配对headerView
            if (mData.size() <= 0) {
                return;
            }
            Log.v("yxbb", "_____" + adapter.getItem(position).getResponseObject().getContent().get(position).getName());
            Intent intent = new Intent(getActivity(), ZhaoShanDetailActivity.class);
            intent.putExtra("FavouriteId", adapter.getItem(position).getResponseObject().getContent().get(position).getId());
            startActivity(intent);
        }
    };
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
                Log.v("zzzzzzzzz","__2_"+page);
                loadZhaoShanMes(page);
                Log.v("yyyy", "***onScrollStateChanged******");
            }
        }
    };
    public void initSwipeRefreshLayout() {
        refreshMes.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refreshMes.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if (mData != null && mDataAll != null) {
                            mDataAll.clear();//一定要加上否则会报越界异常 不执行代码加载的if判断
                            mData.clear();
                        }
                        refreshMes.setRefreshing(false);
                        loadZhaoShanMes(0);//请求刷新
                    }
                }, 2000);
            }
        });
        refreshMes.setColorSchemeResources(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    private final InvestmentInformationFragment.MyHandler mHandler = new InvestmentInformationFragment.MyHandler(this);

    private class MyHandler extends Handler {
        private final WeakReference<InvestmentInformationFragment> mActivity;

        public MyHandler(InvestmentInformationFragment activity) {
            mActivity = new WeakReference<InvestmentInformationFragment>(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            InvestmentInformationFragment activity = mActivity.get();
            if (activity != null) {
                switch (msg.what) {
                    case 0:
                        AppUtils.showToast(getActivity(), "获取招商信息失败");
                        break;
                    case 1:
                        AppUtils.showToast(getActivity(), "获取招商信息成功");
                        /*List<IntroductionBean> list = new ArrayList<>();
                        for (int i=0;i<cityBean.getResponseObject().getIntroduction().size();i++){
                            list.add(cityBean.getResponseObject().getIntroduction().get(i));
                        }*/
                        //addZhaoShang();
                        break;
                    case 2:
                        //AppUtils.showToast(getActivity(), "没有更多的招商信息");
                    default:
                        break;
                }
            }
        }
    }

    private void addZhaoShang(List<ZhaoShangBean> zhaoShangBeans,int page) {

        Log.v("zzzzzzzzz",page+"__1_"+zhaoShangBeans.size());

        if (zhaoShangBeans == null) {
            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    AppUtils.showToast(getActivity(), "没有更多招商信息，请您稍后刷新！");
                }
            });
        }
        if (mData == null && mDataAll == null) {
            mData = new ArrayList<ZhaoShangBean>();
            mDataAll = new ArrayList<ZhaoShangBean>();
        }
        if (mDataAll != null) {
            mDataAll.clear();
        }
        mDataAll.addAll(zhaoShangBeans);
        if (page == 0) {
            for (int i = 0; i < mDataAll.size(); i++) {//
                mData.add(mDataAll.get(i)); //一次显示page= ? 20条数据
            }
            Log.v("zzzzzzzzz","_4__"+mData.size());
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
    public void noMoreMsg() {
        adapter.isShowFooter(false);
        AppUtils.showToast(getActivity(), getResources().getString(R.string.no_more));
    }
}
