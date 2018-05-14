package com.mango.leo.zsproject.industrialservice.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mango.leo.zsproject.R;
import com.mango.leo.zsproject.industrialservice.adapte.AllItemAdapter;
import com.mango.leo.zsproject.industrialservice.bean.AllItemBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by admin on 2018/5/14.
 */

public class DraftBoxFragment extends Fragment {
    @Bind(R.id.recycle_draftBox)
    RecyclerView recycleDraftBox;
    @Bind(R.id.refresh_draftBox)
    SwipeRefreshLayout refreshDraftBox;
    private LinearLayoutManager mLayoutManager;
    private AllItemAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.draftbox, container, false);
        ButterKnife.bind(this, view);
        initSwipeRefreshLayout();
        mLayoutManager = new LinearLayoutManager(getActivity());
        recycleDraftBox.setLayoutManager(mLayoutManager);
        recycleDraftBox.setItemAnimator(new DefaultItemAnimator());//设置默认动画
        adapter = new AllItemAdapter(getActivity().getApplicationContext());
        adapter.setOnItemnewsClickListener(mOnItemClickListener);
        recycleDraftBox.removeAllViews();
        initHeader();
        recycleDraftBox.setAdapter(adapter);
        List<AllItemBean> d = new ArrayList<AllItemBean>();
        for (int i =1;i<9;i++){
            AllItemBean de = new AllItemBean(i+"fsdafasd",i+"dssdvcdafdasd");
            d.add(de);
        }
        adapter.setmDate(d);
        return view;
    }
    private void initHeader() {
        //渲染header布局
        ConstraintLayout h =  new ConstraintLayout(getActivity());
        ConstraintLayout.LayoutParams layoutParam = new ConstraintLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, dp2px(1.0f));
        layoutParam.setMargins(0, 0, 0, 20);
        h.setLayoutParams(layoutParam);
        adapter.setHeaderView(h);
    }
    private int dp2px(float v) {
        DisplayMetrics dm = getResources().getDisplayMetrics();
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, v, dm);
    }
    private AllItemAdapter.OnItemnewsClickListener mOnItemClickListener = new AllItemAdapter.OnItemnewsClickListener() {
        @Override
        public void onItemClick(View view, int position) {
/*
            String newsurl = adapter.getItem(opsition).getResult().getData().get(position).getUrl();
            Intent intent = new Intent(getActivity(), DetailActivity.class);
            intent.putExtra("newsurl", newsurl);//传输内容
            View transitionView = view.findViewById(R.id.item_news_img);
            ActivityOptionsCompat options =
                    ActivityOptionsCompat.makeSceneTransitionAnimation(getActivity(),
                            transitionView, getString(R.string.transition_news_img));
            ActivityCompat.startActivity(getActivity(), intent, options.toBundle());*/
        }
    };
    public void initSwipeRefreshLayout() {
        refreshDraftBox.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refreshDraftBox.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        refreshDraftBox.setRefreshing(false);
                    }
                }, 2000);
            }
        });
        refreshDraftBox.setColorSchemeResources(android.R.color.holo_blue_bright,
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
