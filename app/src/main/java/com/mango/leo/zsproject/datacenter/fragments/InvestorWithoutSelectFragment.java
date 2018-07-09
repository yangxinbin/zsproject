package com.mango.leo.zsproject.datacenter.fragments;

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
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.ListView;

import com.mango.leo.zsproject.R;
import com.mango.leo.zsproject.datacenter.adapter.TouZiAdapter;
import com.mango.leo.zsproject.datacenter.bean.TouZiBean;
import com.mango.leo.zsproject.utils.DropDownAdapter;
import com.mango.leo.zsproject.utils.NetUtil;
import com.mango.leo.zsproject.viewutil.DropdownMenuLayout;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by admin on 2018/5/11.
 */

public class InvestorWithoutSelectFragment extends Fragment implements AdapterView.OnItemClickListener {
    @Bind(R.id.dropdownmenu)
    DropdownMenuLayout dropdownmenu;
    private SwipeRefreshLayout refresh_touzi;
    private RecyclerView recycle_touzi;
    private LinearLayoutManager mLayoutManager;
    private TouZiAdapter adapter;
    private List<TouZiBean> mData, mDataAll, eventBeans1;
    private int page = 0;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.investor, container, false);
        ButterKnife.bind(this, view);
        dropdownmenu.setVisibility(View.GONE);
        initViews();
        return view;
    }

    private void initViews() {
        View content = LayoutInflater.from(getActivity()).inflate(R.layout.touzi, null);
        refresh_touzi = content.findViewById(R.id.refresh_touzi);
        recycle_touzi = content.findViewById(R.id.recycle_touzi);
        content.setLayoutParams(new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT));
        initSwipeRefreshLayout();
        recycle_touzi.setHasFixedSize(true);//固定宽高
        mLayoutManager = new LinearLayoutManager(getActivity());
        recycle_touzi.setLayoutManager(mLayoutManager);
        recycle_touzi.setItemAnimator(new DefaultItemAnimator());//设置默认动画
        adapter = new TouZiAdapter(getActivity().getApplicationContext());
        adapter.setOnEventnewsClickListener(mOnItemClickListener);
        recycle_touzi.removeAllViews();
        recycle_touzi.setAdapter(adapter);
        recycle_touzi.addOnScrollListener(mOnScrollListener);
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
        refresh_touzi.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refresh_touzi.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        refresh_touzi.setRefreshing(false);
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
        refresh_touzi.setColorSchemeResources(android.R.color.holo_blue_bright,
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
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        switch (adapterView.getId()) {
            case 0://行业
                dropdownmenu.closeMenu();
                break;
            case 1://类型
                dropdownmenu.closeMenu();
                break;
            case 2://金额
                dropdownmenu.closeMenu();
                break;
            case 3://方式
                dropdownmenu.closeMenu();
                break;
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        getView().setFocusableInTouchMode(true);
        getView().requestFocus();
        getView().setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (event.getAction() == KeyEvent.ACTION_UP && keyCode == KeyEvent.KEYCODE_BACK){
                    // handle back button
                    // 处理fragment的返回事件
                    dropdownmenu.closeMenu();
                    return true;
                }
                return false;
            }
        });
    }
}
