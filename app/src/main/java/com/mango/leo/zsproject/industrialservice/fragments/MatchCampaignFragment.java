package com.mango.leo.zsproject.industrialservice.fragments;

import android.annotation.SuppressLint;
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
import com.mango.leo.zsproject.eventexhibition.bean.EventBean;
import com.mango.leo.zsproject.eventexhibition.bean.ShaiXuanEvent;
import com.mango.leo.zsproject.eventexhibition.presenter.EventPresenter;
import com.mango.leo.zsproject.eventexhibition.presenter.EventPresenterImpl;
import com.mango.leo.zsproject.eventexhibition.show.EventDetailActivity;
import com.mango.leo.zsproject.eventexhibition.view.EventView;
import com.mango.leo.zsproject.industrialservice.adapte.MatchEventAdapter;
import com.mango.leo.zsproject.industrialservice.bean.MatchEventBean;
import com.mango.leo.zsproject.utils.AppUtils;
import com.mango.leo.zsproject.utils.header.ClassicsHeader;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

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
    SmartRefreshLayout refreshCams;
    private EventPresenter eventPresenter;
    private int page = 0;
    private LinearLayoutManager mLayoutManager;
    private MatchEventAdapter adapter;
    private List<MatchEventBean> mData, mDataAll;
    private final int MATCHEVENT = 4;
    private ShaiXuanEvent shaiXuanEvent;
    private boolean isFirstEnter = true;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.cam__items, container, false);
        ButterKnife.bind(this, view);
        initViews();
        eventPresenter = new EventPresenterImpl(this);
        shaiXuanEvent = new ShaiXuanEvent("", "", "", "");//显示所有
        eventPresenter.visitEvent(getActivity(), MATCHEVENT, page, shaiXuanEvent);
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
        refreshCams.setOnRefreshListener(new OnRefreshListener() {
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
                        eventPresenter.visitEvent(getActivity(), MATCHEVENT, page, shaiXuanEvent);
                        refreshLayout.finishRefresh();
                    }
                }, 500);
            }
        });
        refreshCams.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull final RefreshLayout refreshLayout) {
                refreshLayout.getLayout().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        page++;
                        Log.v("zzzzzzzzz", "-------onLoadMore-------" + page);
                        eventPresenter.visitEvent(getActivity(), MATCHEVENT, page, shaiXuanEvent);
                        refreshLayout.finishLoadMore();

                    }
                }, 500);
            }
        });
        refreshCams.setRefreshHeader(new ClassicsHeader(getActivity()));
        refreshCams.setHeaderHeight(50);

        //触发自动刷新
        if (isFirstEnter) {
            isFirstEnter = false;
            //refresh.autoRefresh();
        } else {
            //mAdapter.refresh(initData());
        }
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
        Log.v("yyyyy", "====onCreateView======" + page);
        if (mDataAll != null && mData != null) {
            mDataAll.clear();
            mData.clear();
        }
    }

    private MatchEventAdapter.OnEventnewsClickListener mOnItemClickListener = new MatchEventAdapter.OnEventnewsClickListener() {
        @Override
        public void onItemClick(View view, int position) {
            if (mData.size() <= 0) {
                return;
            }
            EventBus.getDefault().postSticky(mDataAll.get(position).getContent().get(position % 20));
            Intent intent = new Intent(getActivity(), EventDetailActivity.class);
            intent.putExtra("id", adapter.getItem(position).getContent().get(position % 20).getId());
            startActivity(intent);
        }
    };

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @Override
    public void addEventsView(List<EventBean> eventBeans) {


    }

    @Override
    public void addMatchEventsView(final List<MatchEventBean> eventBeans) {
        Log.v("eeeee", eventBeans.get(0).getContent().get(0).getName() + "======eventBeans======" + eventBeans.size());
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (eventBeans == null || eventBeans.size() == 0) {

                    AppUtils.showToast(getActivity(), getResources().getString(R.string.no_more));

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
                    if (mData != null) {
                        adapter.setmDate(mData);
                    }
                } else {
                    if (mDataAll != null) {
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

    @Override
    public void showEventFailMsg(String string) {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                AppUtils.showToast(getActivity(), getResources().getString(R.string.load_error));
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
