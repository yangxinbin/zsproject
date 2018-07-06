package com.mango.leo.zsproject.industrialservice.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mango.leo.zsproject.R;
import com.mango.leo.zsproject.datacenter.adapter.TouZiAdapter;
import com.mango.leo.zsproject.datacenter.bean.TouZiBean;
import com.mango.leo.zsproject.utils.NetUtil;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by admin on 2018/5/11.
 */

public class MatchInvestorFragment extends Fragment {

    @Bind(R.id.recycle_touzi)
    RecyclerView recycleTouzi;
    @Bind(R.id.refresh_touzi)
    SwipeRefreshLayout refreshTouzi;
    private LinearLayoutManager mLayoutManager;
    private TouZiAdapter adapter;
    private List<TouZiBean> mData, mDataAll, eventBeans1;
    private int page = 0;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.touzif__items, container, false);
        ButterKnife.bind(this, view);
        //initViews();
        return view;
    }

    private void initViews() {
        recycleTouzi.setHasFixedSize(true);//固定宽高
        mLayoutManager = new LinearLayoutManager(getActivity());
        recycleTouzi.setLayoutManager(mLayoutManager);
        recycleTouzi.setItemAnimator(new DefaultItemAnimator());//设置默认动画
        adapter = new TouZiAdapter(getActivity().getApplicationContext());
        adapter.setOnEventnewsClickListener(mOnItemClickListener);
        recycleTouzi.removeAllViews();
        recycleTouzi.setAdapter(adapter);
        recycleTouzi.addOnScrollListener(mOnScrollListener);
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
                //eventPresenter.visitEvent(getActivity(), EVENT1, page);
                Log.v("yyyy", "***onScrollStateChanged******");
            }
        }
    };

    private TouZiAdapter.OnEventnewsClickListener mOnItemClickListener = new TouZiAdapter.OnEventnewsClickListener() {
        @Override
        public void onItemClick(View view, int position) {
            position = position - 1; //配对headerView
            if (mData.size() <= 0) {
                return;
            }
            //EventBus.getDefault().postSticky(mDataAll.get(position));
            /*Log.v("yxbb","_____"+mDataAll.get(position).getResponseObject().getContent().get(position).getName());
            Intent intent = new Intent(getActivity(), EventDetailActivity.class);
            intent.putExtra("FavouriteId",adapter.getItem(position).getResponseObject().getContent().get(position).getId());
            intent.putExtra("position",position);
            startActivity(intent);*/
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
                        if (mData != null && mDataAll != null) {
                            mDataAll.clear();//一定要加上否则会报越界异常 不执行代码加载的if判断
                            mData.clear();
                        }
                        if (NetUtil.isNetConnect(getActivity())) {
                            adapter.isShowFooter(true);
                            //page = 0;
                            //eventPresenter.visitEvent(getActivity(), EVENT1, page);
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
}
