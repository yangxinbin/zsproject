package com.mango.leo.zsproject.industrialservice.fragments;

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
import com.mango.leo.zsproject.datacenter.bean.ShaiXuanData;
import com.mango.leo.zsproject.datacenter.bean.TouZiBean;
import com.mango.leo.zsproject.datacenter.presenter.DataPresenter;
import com.mango.leo.zsproject.datacenter.presenter.DataPresenterImpl;
import com.mango.leo.zsproject.datacenter.show.InvestorDetailActivity;
import com.mango.leo.zsproject.datacenter.view.DataView;
import com.mango.leo.zsproject.industrialservice.bean.MatchDataBean;
import com.mango.leo.zsproject.industrialservice.adapte.MatchTouZiAdapter;
import com.mango.leo.zsproject.utils.AppUtils;
import com.mango.leo.zsproject.utils.NetUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by admin on 2018/5/11.
 */

public class MatchInvestorFragment extends Fragment implements DataView {

    @Bind(R.id.recycle_touzi)
    RecyclerView recycleTouzi;
    @Bind(R.id.refresh_touzi)
    SwipeRefreshLayout refreshTouzi;
    private LinearLayoutManager mLayoutManager;
    private MatchTouZiAdapter adapter;
    private List<MatchDataBean> mData, mDataAll;
    private int page = 0;
    private DataPresenter dataPresenter;
    private ShaiXuanData shaiXuanData;
    private final int MATCHDATA = 3;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.touzi, container, false);
        ButterKnife.bind(this, view);
        initViews();
        initSwipeRefreshLayout();
        dataPresenter = new DataPresenterImpl(this);
        //显示所有
        shaiXuanData = new ShaiXuanData("", "", "", "");
        dataPresenter.visitData(getActivity(), MATCHDATA, page, shaiXuanData);
        initHeader();
        if (mDataAll != null){
            mDataAll.clear();
        }
        if (mData != null){
            mData.clear();
        }
        return view;
    }

    private void initViews() {
        recycleTouzi.setHasFixedSize(true);//固定宽高
        mLayoutManager = new LinearLayoutManager(getActivity());
        recycleTouzi.setLayoutManager(mLayoutManager);
        recycleTouzi.setItemAnimator(new DefaultItemAnimator());//设置默认动画
        adapter = new MatchTouZiAdapter(getActivity().getApplicationContext());
        adapter.setOnEventnewsClickListener(mOnItemClickListener);
        recycleTouzi.removeAllViews();
        recycleTouzi.setAdapter(adapter);
        recycleTouzi.addOnScrollListener(mOnScrollListener);
        Log.v("yyyyy", "====onCreateView======" + page);
        if (mDataAll != null){
            mDataAll.clear();
        }
        if (mData != null){
            mData.clear();
        }

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
                    && adapter.isShowFooter() && lastVisibleItem - 1 > 10) {//加载判断条件 手指离开屏幕 到了footeritem
                page++;
                dataPresenter.visitData(getActivity(), MATCHDATA, page, shaiXuanData);
                Log.v("yyyy", "***onScrollStateChanged******");
            }
        }
    };

    private MatchTouZiAdapter.OnEventnewsClickListener mOnItemClickListener = new MatchTouZiAdapter.OnEventnewsClickListener() {
        @Override
        public void onItemClick(View view, int position) {
            position = position - 1; //配对headerView
            if (mData.size() <= 0) {
                return;
            }
            Intent intent = new Intent(getActivity(), InvestorDetailActivity.class);
            intent.putExtra("Investor_Id",adapter.getItem(position).getContent().get(position).getId());
            startActivity(intent);
        }
    };

    public void initSwipeRefreshLayout() {
        refreshTouzi.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refreshTouzi.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        refreshTouzi.setRefreshing(false);
                        if (mDataAll != null){
                            mDataAll.clear();
                        }
                        if (mData != null){
                            mData.clear();
                        }
                        if (NetUtil.isNetConnect(getActivity())) {
                            adapter.isShowFooter(true);
                            page = 0;
                            dataPresenter.visitData(getActivity(), MATCHDATA, page, shaiXuanData);
                        } else {
                            // mNewsPresenter.visitProjects(getActivity(),mType);//缓存
                        }
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
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
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

    @Override
    public void addDataView(List<TouZiBean> touZiBeans) {

    }

    @Override
    public void addMatchDataView(List<MatchDataBean> matchDataBeans) {
        Log.v("eeeee", matchDataBeans.get(0).getContent().get(0).getTitle() + "======matchDataBeans======" + matchDataBeans.size());
        if (matchDataBeans == null || matchDataBeans.size() == 0) {
            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    AppUtils.showToast(getActivity(), getResources().getString(R.string.no_more));
                }
            });
        }
        if (mData == null && mDataAll == null) {
            mData = new ArrayList<MatchDataBean>();
            mDataAll = new ArrayList<MatchDataBean>();
        }
        if (mDataAll != null) {
            mDataAll.clear();
        }
        mDataAll.addAll(matchDataBeans);
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
                        int i;
                        for (i = 0; i < mDataAll.size(); i++) {
                            if (mDataAll == null) {
                                return;//一开始断网报空指针的情况
                            }
                            adapter.addItem(mDataAll.get(i));//addItem里面记得要notifyDataSetChanged 否则第一次加载不会显示数据
/*                            if (mDataAll != null && i >= mDataAll.size() - 1) {//到最后
                                noMoreMsg();
                                return;
                            }*/
                        }
                    }
                }
            });
        }
    }

    @Override
    public void showDataFailMsg(String string) {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                AppUtils.showToast(getActivity(), getResources().getString(R.string.load_error));
            }
        });
    }
    public void noMoreMsg() {
        adapter.isShowFooter(false);
        AppUtils.showToast(getActivity(), getResources().getString(R.string.no_more));
    }
}
