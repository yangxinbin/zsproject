package com.mango.leo.zsproject.personalcenter.show.shenbao.fragments;

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
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.mango.leo.zsproject.R;
import com.mango.leo.zsproject.personalcenter.show.shenbao.adapter.ShenBaoAdapter;
import com.mango.leo.zsproject.personalcenter.show.shenbao.bean.ShenBaoBean;
import com.mango.leo.zsproject.personalcenter.show.shenbao.presenter.ShenBaoPresenter;
import com.mango.leo.zsproject.personalcenter.show.shenbao.presenter.ShenBaoPresenterImpl;
import com.mango.leo.zsproject.personalcenter.show.shenbao.view.ShenbaoProjectsView;
import com.mango.leo.zsproject.utils.AppUtils;
import com.mango.leo.zsproject.utils.SwipeItemLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by admin on 2018/6/9.
 */

public class XiangMuFragment extends Fragment implements ShenbaoProjectsView{
    @Bind(R.id.recycle_xiangmu)
    RecyclerView recycleXiangmu;
    @Bind(R.id.refresh_xiangmu)
    SwipeRefreshLayout refreshXiangmu;
    private LinearLayoutManager mLayoutManager;
    private ShenBaoAdapter adapter;
    private RelativeLayout h;
    private final int type = 1;
    private ShenBaoPresenter shenBaoPresenter;
    private int page = 0;
    private ArrayList<ShenBaoBean> mData,mDataAll;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.xiangmu, container, false);
        shenBaoPresenter = new ShenBaoPresenterImpl(this);
        ButterKnife.bind(this, view);
        initSwipeRefreshLayout();
        initRecycle();
        initHeader();
        initSwipeRefreshLayout();
        LoadShengbao("");
        return view;
    }

    private void LoadShengbao(String projectId) {
        shenBaoPresenter.visitProjects(getActivity(), type,projectId, page);
    }


    private void initRecycle() {
        mLayoutManager = new LinearLayoutManager(getActivity());
        recycleXiangmu.setLayoutManager(mLayoutManager);
        recycleXiangmu.setItemAnimator(new DefaultItemAnimator());//设置默认动画
        recycleXiangmu.addOnItemTouchListener(new SwipeItemLayout.OnSwipeItemTouchListener(getContext()));
        adapter = new ShenBaoAdapter(getActivity().getApplicationContext());
        recycleXiangmu.removeAllViews();
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

    @Override
    public void addShengbaoSuccess(List<ShenBaoBean> shengBaoBeans) {
        Log.v("zzzzzzzzz",page+"-------3------"+shengBaoBeans.size());
        if (shengBaoBeans == null) {
            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    AppUtils.showToast(getActivity(), getResources().getString(R.string.no_more));
                    return;
                }
            });
        }
        if (mData == null && mDataAll == null) {
            mData = new ArrayList<ShenBaoBean>();
            mDataAll = new ArrayList<ShenBaoBean>();
        }
        if (mDataAll != null) {
            mDataAll.clear();
        }
        mDataAll.addAll(shengBaoBeans);
        if (page == 0) {
            for (int i = 0; i < mDataAll.size(); i++) {//
                mData.add(mDataAll.get(i)); //一次显示page= ? 20条数据
            }
            Log.v("zzzzzzzzz","----4---------"+mData.size());
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
                        int count = adapter.getItemCount() - 2;//增加item数减去头部和尾部
                        int i;
                        for (i = 0; i < mDataAll.size(); i++) {
                            if (mDataAll == null) {
                                return;//一开始断网报空指针的情况
                            }
                            adapter.addItem(mDataAll.get(i));//addItem里面记得要notifyDataSetChanged 否则第一次加载不会显示数据
                            if (mDataAll != null && i >= mDataAll.size() - 1) {//到最后
                                noMoreMsg();
                                return;
                            }
                        }
                    }
                }
            });
        }
        adapter.isShowFooter(true);
    }

    @Override
    public void addShengbaoFail(String e) {
        if (getActivity() != null) {
            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    AppUtils.showToast(getActivity(), "申报项目方加载失败");
                }
            });
        }
    }
    public void noMoreMsg() {
        adapter.isShowFooter(false);
        AppUtils.showToast(getActivity(), getResources().getString(R.string.shenbao));
    }
}