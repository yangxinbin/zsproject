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
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Spinner;

import com.mango.leo.zsproject.R;
import com.mango.leo.zsproject.industrialservice.adapte.DemandManagementAdapter;
import com.mango.leo.zsproject.industrialservice.show.AllAndCreatedPlanActivity;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2018/5/10 0010.
 */

public class DemandManagementFragment extends Fragment {
    @Bind(R.id.recycle_view11)
    RecyclerView recycleView11;
    @Bind(R.id.refresh11)
    SwipeRefreshLayout refresh11;
    private LinearLayoutManager mLayoutManager;
    private DemandManagementAdapter adapter;
    private Spinner mSpinner;
    //private Button createButton;
    private ConstraintLayout h;
    private ConstraintLayout allPlanLayout;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.demandmanagement, container, false);
        ButterKnife.bind(this, view);
        initSwipeRefreshLayout();
        mLayoutManager = new LinearLayoutManager(getActivity());
        recycleView11.setLayoutManager(mLayoutManager);
        recycleView11.setItemAnimator(new DefaultItemAnimator());//设置默认动画
        adapter = new DemandManagementAdapter(getActivity().getApplicationContext());
        adapter.setOnItemnewsClickListener(mOnItemClickListener);
        recycleView11.removeAllViews();
        initHeader();
        initAllPlanButton();
        recycleView11.setAdapter(adapter);
        return view;
    }

    private void initAllPlanButton() {
        /*mSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id){
                TextView tv = (TextView)view;
                tv.setGravity(android.view.Gravity.CENTER);   //设置居中
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent){}
        });*/
        allPlanLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), AllAndCreatedPlanActivity.class);
                ActivityOptionsCompat options =
                        ActivityOptionsCompat.makeSceneTransitionAnimation(getActivity(),
                                allPlanLayout, getString(R.string.transition_news_img));
                ActivityCompat.startActivity(getActivity(), intent, options.toBundle());
            }
        });
    }

    public void initSwipeRefreshLayout() {
        refresh11.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refresh11.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        refresh11.setRefreshing(false);
                    }
                }, 2000);
            }
        });
        refresh11.setColorSchemeResources(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);
    }

    private void initHeader() {
        //渲染header布局
        View header = LayoutInflater.from(getActivity()).inflate(R.layout.header, null);
        h = (ConstraintLayout) header.findViewById(R.id.header);
        allPlanLayout =(ConstraintLayout) header.findViewById(R.id.allPlanLayout);
        //mSpinner = (Spinner) header.findViewById(R.id.spinnerState);
        //String[] arrays = new String[]{"全部招商计划", "项目"};
        //ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(getActivity(),
        //        android.R.layout.simple_spinner_dropdown_item, arrays);
        //mSpinner.setAdapter(arrayAdapter);
        //createButton = (Button) header.findViewById(R.id.create_button);
        //设置banner的高度为手机屏幕的四分之一
        //mBanner.setLayoutParams(new Banner.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, 800));
        ConstraintLayout.LayoutParams layoutParam = new ConstraintLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParam.setMargins(0, 0, 0, 20);
        h.setLayoutParams(layoutParam);
        //设置headerview
        //h.setLayoutParams(new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 1));//除top隐藏headerview
        //ArrayAdapter<String> arrayAdapter1 = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, Arrays.asList(arrays));
        //arrayAdapter1.setDropDownViewResource(R.layout.simple_spinner_dropdown_item);
        //mSpinner.setAdapter(arrayAdapter1);
        adapter.setHeaderView(h);
    }
    private int dp2px(float v) {
        DisplayMetrics dm = getResources().getDisplayMetrics();
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, v, dm);
    }
    private DemandManagementAdapter.OnItemnewsClickListener mOnItemClickListener = new DemandManagementAdapter.OnItemnewsClickListener() {
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

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
