package com.mango.leo.zsproject.industrialservice.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
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
import com.mango.leo.zsproject.industrialservice.adapte.MatchTouZiAdapter;
import com.mango.leo.zsproject.industrialservice.bean.MatchDataBean;
import com.mango.leo.zsproject.utils.AppUtils;
import com.mango.leo.zsproject.utils.header.ClassicsHeader;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

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
    SmartRefreshLayout refreshTouzi;
    private LinearLayoutManager mLayoutManager;
    private MatchTouZiAdapter adapter;
    private List<MatchDataBean> mData, mDataAll;
    private int page = 0;
    private DataPresenter dataPresenter;
    private ShaiXuanData shaiXuanData;
    private final int MATCHDATA = 3;
    private boolean isFirstEnter = true;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.touzi, container, false);
        ButterKnife.bind(this, view);
        initViews();
        dataPresenter = new DataPresenterImpl(this);
        //显示所有
        shaiXuanData = new ShaiXuanData("", "", "", "");
        dataPresenter.visitData(getActivity(), MATCHDATA, page, shaiXuanData);
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
        refreshTouzi.setOnRefreshListener(new OnRefreshListener() {
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
                        dataPresenter.visitData(getActivity(), MATCHDATA, page, shaiXuanData);
                        refreshLayout.finishRefresh();
                    }
                }, 500);
            }
        });
        refreshTouzi.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull final RefreshLayout refreshLayout) {
                refreshLayout.getLayout().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        page++;
                        Log.v("zzzzzzzzz", "-------onLoadMore-------" + page);
                        dataPresenter.visitData(getActivity(), MATCHDATA, page, shaiXuanData);
                        refreshLayout.finishLoadMore();

                    }
                }, 500);
            }
        });
        refreshTouzi.setRefreshHeader(new ClassicsHeader(getActivity()));
        refreshTouzi.setHeaderHeight(50);

        //触发自动刷新
        if (isFirstEnter) {
            isFirstEnter = false;
            //refresh.autoRefresh();
        } else {
            //mAdapter.refresh(initData());
        }
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
        Log.v("yyyyy", "====onCreateView======" + page);
        if (mDataAll != null) {
            mDataAll.clear();
        }
        if (mData != null) {
            mData.clear();
        }

    }

    private MatchTouZiAdapter.OnEventnewsClickListener mOnItemClickListener = new MatchTouZiAdapter.OnEventnewsClickListener() {
        @Override
        public void onItemClick(View view, int position) {
            if (mData.size() <= 0) {
                return;
            }
            Intent intent = new Intent(getActivity(), InvestorDetailActivity.class);
            intent.putExtra("Investor_Id", adapter.getItem(position).getContent().get(position).getId());
            startActivity(intent);
        }
    };

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
    public void addMatchDataView(final List<MatchDataBean> matchDataBeans) {
        Log.v("eeeee", matchDataBeans.get(0).getContent().get(0).getTitle() + "======matchDataBeans======" + matchDataBeans.size());
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (matchDataBeans == null || matchDataBeans.size() == 0) {
                    AppUtils.showToast(getActivity(), getResources().getString(R.string.no_more));
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
        AppUtils.showToast(getActivity(), getResources().getString(R.string.no_more));
    }
}
