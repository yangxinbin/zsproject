package com.mango.leo.zsproject.industrialpanorama.fragments;

import android.content.Intent;
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
import com.mango.leo.zsproject.industrialpanorama.adapter.SmartZhaoShanAdapter;
import com.mango.leo.zsproject.industrialpanorama.bean.CityS;
import com.mango.leo.zsproject.industrialpanorama.bean.ZhaoShangBean;
import com.mango.leo.zsproject.industrialpanorama.show.ZhaoShanDetailActivity;
import com.mango.leo.zsproject.industrialservice.createrequirements.util.ProjectsJsonUtils;
import com.mango.leo.zsproject.utils.AppUtils;
import com.mango.leo.zsproject.utils.HttpUtils;
import com.mango.leo.zsproject.utils.SwipeItemLayout;
import com.mango.leo.zsproject.utils.Urls;
import com.mango.leo.zsproject.utils.header.ClassicsHeader;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

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
    SmartRefreshLayout refreshMes;
    private LinearLayoutManager mLayoutManager;
    private SmartZhaoShanAdapter adapter;
    private ArrayList<ZhaoShangBean> mData, mDataAll;
    private int page = 0;
    private String beanM = "深圳";
    private boolean isFirstEnter = true;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.investmentinformation, container, false);
        ButterKnife.bind(this, view);
        initRecycle();
        loadZhaoShanMes(0);
        EventBus.getDefault().register(this);
        if (mDataAll != null) {
            mDataAll.clear();
        }
        if (mData != null) {
            mData.clear();
        }
        refreshAndLoadMore();
        return view;
    }
    private void refreshAndLoadMore() {
        refreshMes.setOnRefreshListener(new OnRefreshListener() {
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
                        loadZhaoShanMes(page);
                        refreshLayout.finishRefresh();
                    }
                }, 500);
            }
        });
        refreshMes.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull final RefreshLayout refreshLayout) {
                refreshLayout.getLayout().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        page++;
                        Log.v("zzzzzzzzz", "-------onLoadMore-------" + page);
                        loadZhaoShanMes(page);
                        refreshLayout.finishLoadMore();

                    }
                }, 500);
            }
        });
        refreshMes.setRefreshHeader(new ClassicsHeader(getActivity()));
        refreshMes.setHeaderHeight(50);

        //触发自动刷新
        if (isFirstEnter) {
            isFirstEnter = false;
            //refresh.autoRefresh();
        } else {
            //mAdapter.refresh(initData());
        }
    }
    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void card1EventBus(CityS bean) {
        Log.v("yyyyyyyyyy", "------111---" + bean.getCity());
        if (mDataAll != null) {
            mDataAll.clear();
        }
        if (mData != null) {
            mData.clear();
        }
        beanM = bean.getCity();
        loadZhaoShanMes(0);
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
        EventBus.getDefault().unregister(this);
    }

    private void loadZhaoShanMes(final int page) {
        Log.v("zzzzzzzzz", "-----0--------" + Urls.HOST_CITY_MES + "?city=" + beanM + "&page=" + page);
        new Thread(new Runnable() {
            @Override
            public void run() {
                HttpUtils.doGet(Urls.HOST_CITY_MES + "?city=" + beanM + "&page=" + page, new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        mHandler.sendEmptyMessage(0);
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        try {
                            final List<ZhaoShangBean> beanList = ProjectsJsonUtils.readJsonZhaoShangBeans(response.body().string(), "content");//data是json字段获得data的值即对象数组
                            Log.v("zzzzzzzzz", "-----1--------" + beanList.size());
                            if (beanList.size() == 0) {
                                mHandler.sendEmptyMessage(2);
                            } else {
                                mHandler.sendEmptyMessage(1);
                            }
                            getActivity().runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    addZhaoShang(beanList, page);
                                }
                            });
                        } catch (Exception e) {
                            mHandler.sendEmptyMessage(0);
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
        adapter = new SmartZhaoShanAdapter(getActivity().getApplicationContext());
        adapter.setOnZhaoShanClickListener(mOnItemClickListener);
        recycleMes.removeAllViews();
        recycleMes.setAdapter(adapter);
    }

    private SmartZhaoShanAdapter.OnZhaoShanClickListener mOnItemClickListener = new SmartZhaoShanAdapter.OnZhaoShanClickListener() {
        @Override
        public void onItemClick(View view, int position) {
            Log.v("oooooooo", adapter.getItem(position) + "---true---" + position);
            if (mData.size() <= 0) {
                return;
            }
            Intent intent = new Intent(getActivity(), ZhaoShanDetailActivity.class);
            intent.putExtra("FavouriteId", adapter.getItem(position).getResponseObject().getContent().get(position % 20).getId());
            startActivity(intent);
        }
    };

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
                        AppUtils.showToast(getActivity(), getResources().getString(R.string.load_error));
                        break;
                    case 1:
                        //AppUtils.showToast(getActivity(), "获取招商信息成功");
                        break;
                    case 2:
                    default:
                        break;
                }
            }
        }
    }

    private void addZhaoShang(final List<ZhaoShangBean> zhaoShangBeans, final int page) {
        Log.v("zzzzzzzzz", page + "-------3------" + zhaoShangBeans.size());
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (zhaoShangBeans == null || zhaoShangBeans.size() == 0) {
                    AppUtils.showToast(getActivity(), getResources().getString(R.string.no_more));
                    return;
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
                    Log.v("zzzzzzzzz", "----4---------" + mData.size());
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

    public void noMoreMsg() {
        AppUtils.showToast(getActivity(), getResources().getString(R.string.no_more));
    }
}
