package com.mango.leo.zsproject.industrialservice.fragments;

import android.annotation.SuppressLint;
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
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mango.leo.zsproject.R;
import com.mango.leo.zsproject.eventexhibition.bean.EventBean;
import com.mango.leo.zsproject.eventexhibition.bean.ShaiXuanEvent;
import com.mango.leo.zsproject.eventexhibition.presenter.EventPresenter;
import com.mango.leo.zsproject.eventexhibition.presenter.EventPresenterImpl;
import com.mango.leo.zsproject.eventexhibition.show.EventDetailActivity;
import com.mango.leo.zsproject.eventexhibition.view.EventView;
import com.mango.leo.zsproject.industrialservice.adapte.MatchEventAdapter;
import com.mango.leo.zsproject.industrialservice.bean.MatchEventBean;
import com.mango.leo.zsproject.utils.AppUtils;
import com.mango.leo.zsproject.utils.NetUtil;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by admin on 2018/5/11.
 */

public class MatchCampaignFragment extends Fragment implements EventView {
    @Bind(R.id.recycle_cams)
    RecyclerView recycleCams;
    @Bind(R.id.refresh_cams)
    SwipeRefreshLayout refreshCams;
    private EventPresenter eventPresenter;
    private int page = 0;
    private LinearLayoutManager mLayoutManager;
    private MatchEventAdapter adapter;
    private List<MatchEventBean> mData, mDataAll;
    private final int MATCHEVENT = 4;
    private ShaiXuanEvent shaiXuanEvent;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.cam__items, container, false);
        ButterKnife.bind(this, view);
        initViews();
        initSwipeRefreshLayout();
        eventPresenter = new EventPresenterImpl(this);
        shaiXuanEvent = new ShaiXuanEvent("", "", "", "");//显示所有
        eventPresenter.visitEvent(getActivity(), MATCHEVENT, page, shaiXuanEvent);
        initHeader();
        if (mDataAll != null || mData != null) {
            mDataAll.clear();
            mData.clear();
        }
        return view;
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

    @SuppressLint("ResourceType")
    private void initViews() {
        recycleCams.setHasFixedSize(true);//固定宽高
        mLayoutManager = new LinearLayoutManager(getActivity());
        recycleCams.setLayoutManager(mLayoutManager);
        recycleCams.setItemAnimator(new DefaultItemAnimator());//设置默认动画
        adapter = new MatchEventAdapter(getActivity().getApplicationContext());
        adapter.setOnEventnewsClickListener(mOnItemClickListener);
        recycleCams.removeAllViews();
        recycleCams.setAdapter(adapter);
        recycleCams.addOnScrollListener(mOnScrollListener);
        Log.v("yyyyy", "====onCreateView======" + page);
        if (mDataAll != null && mData != null) {
            mDataAll.clear();
            mData.clear();
        }
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
                eventPresenter.visitEvent(getActivity(), MATCHEVENT, page, shaiXuanEvent);
                Log.v("yyyy", "***onScrollStateChanged******");
            }
        }
    };

    private MatchEventAdapter.OnEventnewsClickListener mOnItemClickListener = new MatchEventAdapter.OnEventnewsClickListener() {
        @Override
        public void onItemClick(View view, int position) {
            position = position - 1; //配对headerView
            if (mData.size() <= 0) {
                return;
            }
            EventBus.getDefault().postSticky(mDataAll.get(position).getContent().get(position % 20));
            Intent intent = new Intent(getActivity(), EventDetailActivity.class);
            intent.putExtra("id", adapter.getItem(position).getContent().get(position % 20).getId());
            startActivity(intent);
        }
    };

    public void initSwipeRefreshLayout() {
        refreshCams.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refreshCams.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        refreshCams.setRefreshing(false);
                        if (mData != null && mDataAll != null) {
                            mDataAll.clear();//一定要加上否则会报越界异常 不执行代码加载的if判断
                            mData.clear();
                        }
                        if (NetUtil.isNetConnect(getActivity())) {
                            adapter.isShowFooter(true);
                            page = 0;
                            eventPresenter.visitEvent(getActivity(), MATCHEVENT, page, shaiXuanEvent);
                        } else {
                            // mNewsPresenter.visitProjects(getActivity(),mType);//缓存
                        }
                    }
                }, 2000);
            }
        });
        refreshCams.setColorSchemeResources(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @Override
    public void addEventsView(List<EventBean> eventBeans) {

        
    }

    @Override
    public void addMatchEventsView(List<MatchEventBean> eventBeans) {
        Log.v("eeeee", eventBeans.get(0).getContent().get(0).getName() + "======eventBeans======" + eventBeans.size());
        if (eventBeans == null) {
            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    AppUtils.showToast(getActivity(), "没有更多匹配活动，请您稍后刷新！");
                }
            });
        }
        if (mData == null && mDataAll == null) {
            mData = new ArrayList<MatchEventBean>();
            mDataAll = new ArrayList<MatchEventBean>();
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

    public void noMoreMsg() {
        adapter.isShowFooter(false);
        AppUtils.showToast(getActivity(), "没有更多活动，请您稍后刷新！");
    }

    @Override
    public void showEventFailMsg(String string) {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                AppUtils.showToast(getActivity(), "没有更多活动，请您稍后刷新！");
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        getView().setFocusableInTouchMode(true);
        getView().requestFocus();
        getView().setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (event.getAction() == KeyEvent.ACTION_UP && keyCode == KeyEvent.KEYCODE_BACK) {
                    return true;
                }
                return false;
            }
        });
    }

}
