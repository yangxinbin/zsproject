package com.mango.leo.zsproject.industrialservice.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mango.leo.zsproject.R;
import com.mango.leo.zsproject.industrialservice.adapte.AllItemAdapter;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by admin on 2018/5/14.
 */

public class SubmissionFragment extends Fragment {
    @Bind(R.id.recycle_submission)
    RecyclerView recycleSubmission;
    @Bind(R.id.refresh_submission)
    SwipeRefreshLayout refreshSubmission;
    private LinearLayoutManager mLayoutManager;
    private AllItemAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.submission, container, false);
        ButterKnife.bind(this, view);
        initSwipeRefreshLayout();
        mLayoutManager = new LinearLayoutManager(getActivity());
        recycleSubmission.setLayoutManager(mLayoutManager);
        recycleSubmission.setItemAnimator(new DefaultItemAnimator());//设置默认动画
        adapter = new AllItemAdapter(getActivity().getApplicationContext());
        adapter.setOnItemnewsClickListener(mOnItemClickListener);
        recycleSubmission.removeAllViews();
        recycleSubmission.setAdapter(adapter);
        return view;
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
        refreshSubmission.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refreshSubmission.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        refreshSubmission.setRefreshing(false);
                    }
                }, 2000);
            }
        });
        refreshSubmission.setColorSchemeResources(android.R.color.holo_blue_bright,
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
