package com.mango.leo.zsproject.personalcenter.show.shenbao.fragments;

import android.content.Intent;
import android.os.Bundle;
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
import com.mango.leo.zsproject.personalcenter.show.shenbao.ShenBaoDetailActivity;
import com.mango.leo.zsproject.personalcenter.show.shenbao.adapter.ShenBaoAdapter;
import com.mango.leo.zsproject.personalcenter.show.shenbao.bean.IdBean;
import com.mango.leo.zsproject.personalcenter.show.shenbao.bean.ShenBaoBean;
import com.mango.leo.zsproject.personalcenter.show.shenbao.presenter.ShenBaoPresenter;
import com.mango.leo.zsproject.personalcenter.show.shenbao.presenter.ShenBaoPresenterImpl;
import com.mango.leo.zsproject.personalcenter.show.shenbao.view.ShenbaoProjectsView;
import com.mango.leo.zsproject.utils.AppUtils;
import com.mango.leo.zsproject.utils.SwipeItemLayout;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by admin on 2018/6/9.
 */

public class TouziFragment extends Fragment implements ShenbaoProjectsView {
    @Bind(R.id.recycle_touzi)
    RecyclerView recycleTouzi;
    @Bind(R.id.refresh_touzi)
    SwipeRefreshLayout refreshTouzi;
    private LinearLayoutManager mLayoutManager;
    private ShenBaoAdapter adapter;
    private ConstraintLayout h;
    private final int type = 0;
    private ShenBaoPresenter shenBaoPresenter;
    private int page = 0;
    private ArrayList<ShenBaoBean> mData, mDataAll;
    private String projectId = "";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.touzi, container, false);
        shenBaoPresenter = new ShenBaoPresenterImpl(this);
        ButterKnife.bind(this, view);
        EventBus.getDefault().register(this);
        initRecycle();
        initHeader();
        initSwipeRefreshLayout();
        if (mDataAll != null) {
            mDataAll.clear();
        }
        if (mData != null) {
            mData.clear();
        }
        LoadShengbao(projectId, 0);
        return view;
    }

    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void IdEventBus(IdBean bean) {
        if (bean == null) {
            return;
        }
        Log.v("zzzzzzzzz", "---" + bean.getProjectId());
        projectId = bean.getProjectId();
        if (mData != null) {
            mData.clear();
        }
        if (mDataAll != null) {
            mDataAll.clear();
        }
        page = 0;//以上初始化
        //LoadShengbao(projectId, page);
    }

    private void LoadShengbao(String projectId, int page) {
        shenBaoPresenter.visitProjects(getActivity(), type, projectId, page);
    }

    private void initRecycle() {
        Log.v("zzzzzzzzz", "--adapter-");
        mLayoutManager = new LinearLayoutManager(getActivity());
        recycleTouzi.setLayoutManager(mLayoutManager);
        recycleTouzi.setItemAnimator(new DefaultItemAnimator());//设置默认动画
        recycleTouzi.addOnItemTouchListener(new SwipeItemLayout.OnSwipeItemTouchListener(getContext()));
        adapter = new ShenBaoAdapter(getActivity().getApplicationContext());
        adapter.setOnClickListener(mOnItemClickListener);
        recycleTouzi.setAdapter(adapter);
        recycleTouzi.addOnScrollListener(mOnScrollListener);
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
            Log.v("yyyy", "***???****" + (newState == RecyclerView.SCROLL_STATE_IDLE) + "==" + (adapter.getItemCount() == lastVisibleItem + 1) + "==" + adapter.isShowFooter());
            if (newState == RecyclerView.SCROLL_STATE_IDLE
                    && lastVisibleItem + 1 == adapter.getItemCount()
                    && adapter.isShowFooter()) {//加载判断条件 手指离开屏幕 到了footeritem
                page++;
                LoadShengbao(projectId, page);
                Log.v("yyyy", "***onScrollStateChanged******" + adapter.getItemCount());
            }
        }
    };
    private ShenBaoAdapter.OnClickListener mOnItemClickListener = new ShenBaoAdapter.OnClickListener() {
        @Override
        public void onItemClick(View view, int position) {
            position = position - 1; //配对headerView
            if (mData.size() <= 0) {
                return;
            }
            Intent intent = new Intent(getActivity(), ShenBaoDetailActivity.class);
            EventBus.getDefault().postSticky(adapter.getItem(position).getResponseObject().getContent().get(position%20));
            startActivity(intent);
            //getActivity().finish();
        }
    };

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

    public void initSwipeRefreshLayout() {
        refreshTouzi.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refreshTouzi.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        refreshTouzi.setRefreshing(false);
                        if (mData != null) {
                            mData.clear();
                        }
                        if (mDataAll != null) {
                            mDataAll.clear();
                        }
                        page = 0;
                        LoadShengbao(projectId, page);//请求刷新
                    }
                }, 2000);
            }
        });
        refreshTouzi.setColorSchemeResources(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void addShengbaoSuccess(List<ShenBaoBean> shengBaoBeans) {
        Log.v("zzzzzzzzz", page + "----1---3------" + shengBaoBeans.size());
        if (shengBaoBeans == null || shengBaoBeans.size() == 0) {
            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    AppUtils.showToast(getActivity(), getResources().getString(R.string.shenbao));
                    return;
                }
            });
        }
        if (mData == null && mDataAll == null) {
            mData = new ArrayList<ShenBaoBean>();
            mDataAll = new ArrayList<ShenBaoBean>();
        }
        if (mDataAll != null) {
            mDataAll.clear();
        }
/*        if (mData != null) {
            mData.clear();
        }*/
        mDataAll.addAll(shengBaoBeans);
        if (page == 0) {
            for (int i = 0; i < mDataAll.size(); i++) {//
                mData.add(mDataAll.get(i)); //一次显示page= ? 20条数据
            }
            Log.v("zzzzzzzzz", "--1--4---------" + mData.size());
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
        adapter.isShowFooter(true);
    }

    @Override
    public void addShengbaoFail(String e) {
        if (getActivity() != null) {
            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    AppUtils.showToast(getActivity(), "申报投资方加载失败");
                }
            });
        }
    }

    public void noMoreMsg() {
        adapter.isShowFooter(false);
        AppUtils.showToast(getActivity(), getResources().getString(R.string.shenbao));
    }
}

