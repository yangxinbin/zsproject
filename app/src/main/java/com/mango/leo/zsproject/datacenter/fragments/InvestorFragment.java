package com.mango.leo.zsproject.datacenter.fragments;

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
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.ListView;

import com.mango.leo.zsproject.R;
import com.mango.leo.zsproject.datacenter.adapter.TouZiAdapter;
import com.mango.leo.zsproject.datacenter.bean.ShaiXuanData;
import com.mango.leo.zsproject.datacenter.bean.TouZiBean;
import com.mango.leo.zsproject.datacenter.presenter.DataPresenter;
import com.mango.leo.zsproject.datacenter.presenter.DataPresenterImpl;
import com.mango.leo.zsproject.datacenter.show.InvestorDetailActivity;
import com.mango.leo.zsproject.datacenter.view.DataView;
import com.mango.leo.zsproject.industrialservice.bean.MatchDataBean;
import com.mango.leo.zsproject.utils.AppUtils;
import com.mango.leo.zsproject.utils.DropDownAdapter;
import com.mango.leo.zsproject.utils.header.ClassicsHeader;
import com.mango.leo.zsproject.viewutil.DropdownMenuLayout;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by admin on 2018/5/11.
 */

public class InvestorFragment extends Fragment implements AdapterView.OnItemClickListener, DataView {
    @Bind(R.id.dropdownmenu)
    DropdownMenuLayout dropdownmenu;
    private String headers[] = {"行业", "资金类型", "投资金额", "合作方式"};
    private List<View> popViews = new ArrayList<View>();
    private String hangye[] = {"行业1", "行业2", "行业3", "行业2"};
    private String ways[] = {"行业sdf1", "行dsf业2", "行asdf业3", "行df业2"};
    private String where[] = {"行gg业1", "行gg业2", "行业s3", "行g业2"};
    private String how[] = {"行cc业1", "行cc业2", "行cc业3", "行cc业2"};
    private SmartRefreshLayout refresh_touzi;
    private RecyclerView recycle_touzi;
    private LinearLayoutManager mLayoutManager;
    private TouZiAdapter adapter;
    private List<TouZiBean> mData, mDataAll, eventBeans1;
    private int page = 0;
    private DataPresenter dataPresenter;
    private ShaiXuanData shaiXuanData;
    private final int DATA = 1;
    private boolean isFirstEnter = true;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.investor, container, false);
        ButterKnife.bind(this, view);
        dataPresenter = new DataPresenterImpl(this);
        //显示所有
        shaiXuanData = new ShaiXuanData("", "", "", "");
        dataPresenter.visitData(getActivity(), DATA, page, shaiXuanData);
        initViews();
        if (mDataAll != null) {
            mDataAll.clear();
        }
        if (mData != null) {
            mData.clear();
        }
        return view;
    }

    private void initViews() {
        ListView lvHangye = new ListView(getActivity());
        lvHangye.setId(0);
        lvHangye.setDividerHeight(0);
        lvHangye.setAdapter(new DropDownAdapter(getActivity(), Arrays.asList(hangye)));

        ListView lvWays = new ListView(getActivity());
        lvWays.setId(1);
        lvWays.setDividerHeight(0);
        lvWays.setAdapter(new DropDownAdapter(getActivity(), Arrays.asList(ways)));

        ListView lvWhere = new ListView(getActivity());
        lvWhere.setId(2);
        lvWhere.setDividerHeight(0);
        lvWhere.setAdapter(new DropDownAdapter(getActivity(), Arrays.asList(where)));

        ListView lvHow = new ListView(getActivity());
        lvHow.setId(3);
        lvHow.setDividerHeight(0);
        lvHow.setAdapter(new DropDownAdapter(getActivity(), Arrays.asList(how)));

        lvHangye.setOnItemClickListener(this);
        lvWays.setOnItemClickListener(this);
        lvWhere.setOnItemClickListener(this);
        lvHow.setOnItemClickListener(this);
        popViews.add(lvHangye);
        popViews.add(lvWays);
        popViews.add(lvWhere);
        popViews.add(lvHow);

        View content = LayoutInflater.from(getActivity()).inflate(R.layout.touzi, null);
        refresh_touzi = content.findViewById(R.id.refresh_touzi);
        recycle_touzi = content.findViewById(R.id.recycle_touzi);
        content.setLayoutParams(new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT));
        dropdownmenu.setDropDownMemu(Arrays.asList(headers), popViews, content);
        recycle_touzi.setHasFixedSize(true);//固定宽高
        mLayoutManager = new LinearLayoutManager(getActivity());
        recycle_touzi.setLayoutManager(mLayoutManager);
        recycle_touzi.setItemAnimator(new DefaultItemAnimator());//设置默认动画
        adapter = new TouZiAdapter(getActivity().getApplicationContext());
        adapter.setOnEventnewsClickListener(mOnItemClickListener);
        recycle_touzi.removeAllViews();
        recycle_touzi.setAdapter(adapter);
        refreshAndLoadMore();
        Log.v("yyyyy", "====onCreateView======" + page);
        if (mDataAll != null) {
            mDataAll.clear();
        }
        if (mData != null) {
            mData.clear();
        }
    }
    private void refreshAndLoadMore() {
        refresh_touzi.setOnRefreshListener(new OnRefreshListener() {
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
                        dataPresenter.visitData(getActivity(), DATA, page, shaiXuanData);
                        refreshLayout.finishRefresh();
                    }
                }, 500);
            }
        });
        refresh_touzi.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull final RefreshLayout refreshLayout) {
                refreshLayout.getLayout().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        page++;
                        Log.v("zzzzzzzzz", "-------onLoadMore-------" + page);
                        dataPresenter.visitData(getActivity(), DATA, page, shaiXuanData);
                        refreshLayout.finishLoadMore();

                    }
                }, 500);
            }
        });
        refresh_touzi.setRefreshHeader(new ClassicsHeader(getActivity()));
        refresh_touzi.setHeaderHeight(50);

        //触发自动刷新
        if (isFirstEnter) {
            isFirstEnter = false;
            //refresh.autoRefresh();
        } else {
            //mAdapter.refresh(initData());
        }
    }
    private TouZiAdapter.OnEventnewsClickListener mOnItemClickListener = new TouZiAdapter.OnEventnewsClickListener() {
        @Override
        public void onItemClick(View view, int position) {
            if (mData.size() <= 0) {
                return;
            }
            Intent intent = new Intent(getActivity(), InvestorDetailActivity.class);
            intent.putExtra("Investor_Id", adapter.getItem(position).getResponseObject().getContent().get(position).getId());
            startActivity(intent);
        }
    };

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
                if (event.getAction() == KeyEvent.ACTION_UP && keyCode == KeyEvent.KEYCODE_BACK) {
                    // handle back button
                    // 处理fragment的返回事件
                    dropdownmenu.closeMenu();
                    return true;
                }
                return false;
            }
        });
    }

    @Override
    public void addDataView(final List<TouZiBean> touZiBeans) {
        Log.v("eeeeeyyyyyy", touZiBeans.get(0).getResponseObject().getContent().get(0).getTitle() + "======eventBeans======" + touZiBeans.size());
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (touZiBeans == null || touZiBeans.size() == 0) {

                    AppUtils.showToast(getActivity(), getResources().getString(R.string.no_more));

                }
                if (mData == null && mDataAll == null) {
                    mData = new ArrayList<TouZiBean>();
                    mDataAll = new ArrayList<TouZiBean>();
                }
                if (mDataAll != null) {
                    mDataAll.clear();
                }
                mDataAll.addAll(touZiBeans);
                if (page == 0) {
                    for (int i = 0; i < mDataAll.size(); i++) {//
                        mData.add(mDataAll.get(i)); //一次显示page= ? 20条数据
                    }
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
    public void addMatchDataView(List<MatchDataBean> matchDataBeans) {

    }

    @Override
    public void showDataFailMsg(String string) {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                AppUtils.showToast(getActivity(), getResources().getString(R.string.no_more));
            }
        });
    }

    public void noMoreMsg() {
        AppUtils.showToast(getActivity(), getResources().getString(R.string.no_more));
    }
}
