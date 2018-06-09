package com.mango.leo.zsproject.personalcenter.show.shengbao.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.mango.leo.zsproject.R;
import com.mango.leo.zsproject.personalcenter.show.shengbao.adapter.ShengBaoAdapter;
import com.mango.leo.zsproject.utils.SwipeItemLayout;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by admin on 2018/6/9.
 */

public class XiangMuFragment extends Fragment {
    @Bind(R.id.recycle_xiangmu)
    RecyclerView recycleXiangmu;
    @Bind(R.id.refresh_xiangmu)
    SwipeRefreshLayout refreshXiangmu;
    private LinearLayoutManager mLayoutManager;
    private ShengBaoAdapter adapter;
    private RelativeLayout h;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.xiangmu, container, false);
        ButterKnife.bind(this, view);
        initSwipeRefreshLayout();
        initRecycle();
        initHeader();
        LoadShengbao();
        return view;
    }

    private void LoadShengbao() {

    }

    private void initRecycle() {
        mLayoutManager = new LinearLayoutManager(getActivity());
        recycleXiangmu.setLayoutManager(mLayoutManager);
        recycleXiangmu.setItemAnimator(new DefaultItemAnimator());//设置默认动画
        recycleXiangmu.addOnItemTouchListener(new SwipeItemLayout.OnSwipeItemTouchListener(getContext()));
        adapter = new ShengBaoAdapter(getActivity().getApplicationContext());
        recycleXiangmu.removeAllViews();
    }
    private void initHeader() {
        //渲染header布局
        View header = LayoutInflater.from(getActivity()).inflate(R.layout.city_header, null);
        h = (RelativeLayout) header.findViewById(R.id.header_city);
        ConstraintLayout.LayoutParams layoutParam = new ConstraintLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParam.setMargins(0, 0, 0, 20);
        h.setLayoutParams(layoutParam);
        adapter.setHeaderView(h);
    }

    public void initSwipeRefreshLayout() {
        refreshXiangmu.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refreshXiangmu.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        refreshXiangmu.setRefreshing(false);
                        //     mNewsPresenter.visitProjects(getActivity(), mType, page);//请求刷新
                    }
                }, 2000);
            }
        });
        refreshXiangmu.setColorSchemeResources(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}