package com.mango.leo.zsproject.industrialservice.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
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
import com.mango.leo.zsproject.industrialservice.adapte.AllProjectsAdapter;
import com.mango.leo.zsproject.industrialservice.createrequirements.AllAndCreatedPlanActivity;
import com.mango.leo.zsproject.industrialservice.createrequirements.BusinessPlanActivity;
import com.mango.leo.zsproject.industrialservice.createrequirements.bean.AllProjectsBean;
import com.mango.leo.zsproject.industrialservice.createrequirements.presenter.AllProjectsPresenter;
import com.mango.leo.zsproject.industrialservice.createrequirements.presenter.AllProjectsPresenterImpl;
import com.mango.leo.zsproject.industrialservice.createrequirements.view.AllProjectsView;
import com.mango.leo.zsproject.utils.NetUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by admin on 2018/5/21.
 */

public class ProjectsRecyclerviewFragment extends Fragment implements AllProjectsView,SwipeRefreshLayout.OnRefreshListener {
    @Bind(R.id.recycle_items)
    RecyclerView recycleItems;
    @Bind(R.id.refresh_items)
    SwipeRefreshLayout refreshItems;
    private AllProjectsPresenter mNewsPresenter;
    private int mType = AllAndCreatedPlanActivity.PROJECTS_TYPE_DRAFTBOX;
    private AllProjectsAdapter adapter;
    private LinearLayoutManager mLayoutManager;
    private List<AllProjectsBean> mData;

    public static ProjectsRecyclerviewFragment newInstance(int type) {
        Bundle bundle = new Bundle();
        ProjectsRecyclerviewFragment fragment = new ProjectsRecyclerviewFragment();
        bundle.putInt("type", type);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mNewsPresenter = new AllProjectsPresenterImpl(this);
        mType = getArguments().getInt("type");

        Log.v("yyyyy","====mType======"+mType);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.project_items, null);
        ButterKnife.bind(this, view);
        refreshItems.setOnRefreshListener(this);
        recycleItems.setHasFixedSize(true);//固定宽高
        mLayoutManager = new LinearLayoutManager(getActivity());
        recycleItems.setLayoutManager(mLayoutManager);
        recycleItems.setItemAnimator(new DefaultItemAnimator());//设置默认动画
        adapter = new AllProjectsAdapter(getActivity().getApplicationContext());
        adapter.setOnItemnewsClickListener(mOnItemClickListener);
        recycleItems.removeAllViews();
        initHeader();
        recycleItems.setAdapter(adapter);
        //recycleItems.addOnScrollListener(mOnScrollListener);
        mNewsPresenter.visitProjects(getActivity(),mType);
        return view;
    }
    private AllProjectsAdapter.OnItemnewsClickListener mOnItemClickListener = new AllProjectsAdapter.OnItemnewsClickListener() {
        @Override
        public void onItemClick(View view, int position) {
            if (mData.size() <= 0) {
                return;
            }
            Intent intent = new Intent(getActivity(), BusinessPlanActivity.class);
            startActivity(intent);
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

    @Override
    public void addProjectsSuccess(List<AllProjectsBean> projectsList) {
        Log.v("yyyyyyyyyyyyyy","88888888"+projectsList.toString());

        /*if (mData == null) {
            mData = new ArrayList<AllProjectsBean>();
        }
        if (mData != null){
            mData.clear();
        }
        Log.v("yyyyyyyyyyyyyy","88888888"+projectsList.toString());
        mData.addAll(projectsList);
        adapter.setmDate(mData);*/
    }

    @Override
    public void addProjectsFail(String e) {

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @Override
    public void onRefresh() {
        if (mData != null) {
            mData.clear();
        }
        if (NetUtil.isNetConnect(getActivity())){
            mNewsPresenter.visitProjects(getActivity(),mType);
        }else {
           // mNewsPresenter.visitProjects(getActivity(),mType);//缓存
        }
    }
}
