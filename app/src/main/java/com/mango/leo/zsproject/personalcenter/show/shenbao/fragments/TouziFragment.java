package com.mango.leo.zsproject.personalcenter.show.shenbao.fragments;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
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

import com.mango.leo.zsproject.R;
import com.mango.leo.zsproject.personalcenter.show.shenbao.ShenBaoDetailActivity;
import com.mango.leo.zsproject.personalcenter.show.shenbao.adapter.ShenBaoAdapter;
import com.mango.leo.zsproject.personalcenter.show.shenbao.bean.ShenBaoBean;
import com.mango.leo.zsproject.personalcenter.show.shenbao.presenter.ShenBaoPresenter;
import com.mango.leo.zsproject.personalcenter.show.shenbao.presenter.ShenBaoPresenterImpl;
import com.mango.leo.zsproject.personalcenter.show.shenbao.view.ShenbaoProjectsView;
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

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by admin on 2018/6/9.
 */

public class TouziFragment extends Fragment implements ShenbaoProjectsView {
    @Bind(R.id.recycle_touzi)
    RecyclerView recycleTouzi;
    @Bind(R.id.refresh_touzi)
    SmartRefreshLayout refreshTouzi;
    private LinearLayoutManager mLayoutManager;
    private ShenBaoAdapter adapter;
    private ConstraintLayout h;
    private final int type = 0;
    private ShenBaoPresenter shenBaoPresenter;
    private int page = 0;
    private ArrayList<ShenBaoBean> mData, mDataAll;
    private SharedPreferences sharedPreferences;
    private boolean isFirstEnter = true;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.touzi, container, false);
        shenBaoPresenter = new ShenBaoPresenterImpl(this);
        sharedPreferences = getActivity().getSharedPreferences("CIFIT", MODE_PRIVATE);
        ButterKnife.bind(this, view);
        initRecycle();
        if (mDataAll != null) {
            mDataAll.clear();
        }
        if (mData != null) {
            mData.clear();
        }
        LoadShengbao(sharedPreferences.getString("projectId", ""), 0);
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
                        LoadShengbao(sharedPreferences.getString("projectId", ""), page);
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
                        LoadShengbao(sharedPreferences.getString("projectId", ""), page);
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

    private void LoadShengbao(String projectId, int page) {
        shenBaoPresenter.visitProjects(getActivity(), type, projectId, page);
    }

    private void initRecycle() {
        Log.v("zzzzzzzzz", "--adapter-");
        mLayoutManager = new LinearLayoutManager(getActivity());
        recycleTouzi.setLayoutManager(mLayoutManager);
        recycleTouzi.setItemAnimator(new DefaultItemAnimator());//设置默认动画
        adapter = new ShenBaoAdapter(getActivity().getApplicationContext());
        adapter.setOnClickListener(mOnItemClickListener);
        recycleTouzi.setAdapter(adapter);
    }

    private ShenBaoAdapter.OnClickListener mOnItemClickListener = new ShenBaoAdapter.OnClickListener() {
        @Override
        public void onItemClick(View view, int position) {
            if (mData.size() <= 0) {
                return;
            }
            Intent intent = new Intent(getActivity(), ShenBaoDetailActivity.class);
            EventBus.getDefault().postSticky(adapter.getItem(position).getResponseObject().getContent().get(position % 20));
            startActivity(intent);
        }
    };
    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void addShengbaoSuccess(final List<ShenBaoBean> shengBaoBeans) {
        Log.v("zzzzzzzzz", page + "----t-----" + shengBaoBeans.size());
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (shengBaoBeans == null || shengBaoBeans.size() == 0) {
                    AppUtils.showToast(getActivity(), getResources().getString(R.string.no_more));
                    return;
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
                    Log.v("zzzzzzzzz", "--t--------" + mData.size());
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
    public void addShengbaoFail(String e) {
        if (getActivity() != null) {
            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    AppUtils.showToast(getActivity(), getResources().getString(R.string.load_error));
                }
            });
        }
    }

    public void noMoreMsg() {
        AppUtils.showToast(getActivity(), getResources().getString(R.string.no_more));
    }
}

