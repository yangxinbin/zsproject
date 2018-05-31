package com.mango.leo.zsproject.industrialservice.fragments;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
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

import com.luck.picture.lib.entity.LocalMedia;
import com.mango.leo.zsproject.R;
import com.mango.leo.zsproject.industrialservice.adapte.AllProjectsAdapter;
import com.mango.leo.zsproject.industrialservice.createrequirements.AllAndCreatedPlanActivity;
import com.mango.leo.zsproject.industrialservice.createrequirements.BusinessPlanActivity;
import com.mango.leo.zsproject.industrialservice.createrequirements.bean.AllProjectsBean;
import com.mango.leo.zsproject.industrialservice.createrequirements.carditems.bean.CardFirstItemBean;
import com.mango.leo.zsproject.industrialservice.createrequirements.carditems.bean.CardFourthItemBean;
import com.mango.leo.zsproject.industrialservice.createrequirements.carditems.bean.CardThirdItemBean;
import com.mango.leo.zsproject.industrialservice.createrequirements.presenter.AllProjectsPresenter;
import com.mango.leo.zsproject.industrialservice.createrequirements.presenter.AllProjectsPresenterImpl;
import com.mango.leo.zsproject.industrialservice.createrequirements.view.AllProjectsView;
import com.mango.leo.zsproject.login.PwdLoginActivity;
import com.mango.leo.zsproject.utils.AppUtils;
import com.mango.leo.zsproject.utils.NetUtil;

import org.greenrobot.eventbus.EventBus;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by admin on 2018/5/21.
 */

public class ProjectsRecyclerviewFragment extends Fragment implements AllProjectsView {
    @Bind(R.id.recycle_items)
    RecyclerView recycleItems;
    @Bind(R.id.refresh_items)
    SwipeRefreshLayout refreshItems;
    private AllProjectsPresenter mNewsPresenter;
    private int mType = AllAndCreatedPlanActivity.PROJECTS_TYPE_DRAFTBOX;
    private AllProjectsAdapter adapter;
    private LinearLayoutManager mLayoutManager;
    private List<AllProjectsBean> mData;
    private List<AllProjectsBean> mDataAll;
    private int lastVisibleItem;
    private int page = 0;
    private SharedPreferences sharedPreferences;
    private static SharedPreferences.Editor editor;

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
        sharedPreferences = getActivity().getSharedPreferences("CIFIT",MODE_PRIVATE);
        editor = sharedPreferences.edit();
        Log.v("yyyyy", "====mType======" + mType);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.project_items, null);
        ButterKnife.bind(this, view);
        initSwipeRefreshLayout();
        recycleItems.setHasFixedSize(true);//固定宽高
        mLayoutManager = new LinearLayoutManager(getActivity());
        recycleItems.setLayoutManager(mLayoutManager);
        recycleItems.setItemAnimator(new DefaultItemAnimator());//设置默认动画
        adapter = new AllProjectsAdapter(getActivity().getApplicationContext());
        adapter.setOnItemnewsClickListener(mOnItemClickListener);
        recycleItems.removeAllViews();
        initHeader();
        recycleItems.setAdapter(adapter);
        recycleItems.addOnScrollListener(mOnScrollListener);
        Log.v("yyyyy", "====onCreateView======" + page);
        if (mDataAll != null && mData != null) {
            mDataAll.clear();
            mData.clear();
        }
        page = 0;
        mNewsPresenter.visitProjects(getActivity(), mType, page);
        return view;
    }

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
                mNewsPresenter.visitProjects(getActivity(), mType, page);
                Log.v("yyyy", "***onScrollStateChanged******");
            }
        }
    };

    public void initSwipeRefreshLayout() {
        refreshItems.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refreshItems.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        refreshItems.setRefreshing(false);
                        if (mData != null && mDataAll != null) {
                            mDataAll.clear();//一定要加上否则会报越界异常 不执行代码加载的if判断
                            mData.clear();
                        }
                        if (NetUtil.isNetConnect(getActivity())) {
                            adapter.isShowFooter(true);
                            page = 0;
                            mNewsPresenter.visitProjects(getActivity(), mType, page);
                        } else {
                            // mNewsPresenter.visitProjects(getActivity(),mType);//缓存
                        }
                    }
                }, 2000);
            }
        });
        refreshItems.setColorSchemeResources(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);
    }

    private AllProjectsAdapter.OnItemnewsClickListener mOnItemClickListener = new AllProjectsAdapter.OnItemnewsClickListener() {
        @Override
        public void onItemClick(View view, int position) {
            position = position - 1; //配对headerView
            if (mData.size() <= 0) {
                return;
            }
            editor.putString("projectId",adapter.getItem(position).getResponseObject().getContent().get(position).getId()).commit();
            Log.v("yyyyyy", adapter.getItem(position).getResponseObject().getContent().get(position).getId()+"****position*******" + position);
            postStickyAll(position);
            Intent intent = new Intent(getActivity(), BusinessPlanActivity.class);
            startActivity(intent);
            getActivity().finish();
        }
    };

    public void postStickyAll(int position) {
        CardFirstItemBean cardFirstItemBean = new CardFirstItemBean();
        if (adapter.getItem(position).getResponseObject().getContent().get(position).getName() != null) {
            cardFirstItemBean.setItemName(adapter.getItem(position).getResponseObject().getContent().get(position).getName());
            cardFirstItemBean.setItemContent(adapter.getItem(position).getResponseObject().getContent().get(position).getDescription());
            cardFirstItemBean.setItemImagePath((List<LocalMedia>) adapter.getItem(position).getResponseObject().getContent().get(position).getPhotos());
            //cardFirstItemBean.setProjectId(adapter.getItem(position).getResponseObject().getContent().get(position).getId());
            EventBus.getDefault().postSticky(cardFirstItemBean);
        }else {
            EventBus.getDefault().postSticky(cardFirstItemBean);
        }
        CardThirdItemBean cardThirdItemBean = new CardThirdItemBean();
        if (adapter.getItem(position).getResponseObject().getContent().get(position).getLocation() != null) {
            cardThirdItemBean.setProvince("广东省");
            cardThirdItemBean.setCity("深圳市");
            cardThirdItemBean.setAddress(adapter.getItem(position).getResponseObject().getContent().get(position).getLocation().getAddress());
            cardThirdItemBean.setLon(String.valueOf(adapter.getItem(position).getResponseObject().getContent().get(position).getLocation().getLon()));
            cardThirdItemBean.setLat(String.valueOf(adapter.getItem(position).getResponseObject().getContent().get(position).getLocation().getLat()));
            Log.v("33333", "______!= null_____");
            EventBus.getDefault().postSticky(cardThirdItemBean);
        }else {
            EventBus.getDefault().postSticky(cardThirdItemBean);
        }

        List<CardFourthItemBean> beans4 = new ArrayList<>();
        if (beans4 != null){
            beans4.clear();//清空刷新
        }
        if (adapter.getItem(position).getResponseObject().getContent().get(position).getContacts() != null) {
            Log.v("xxxxx", adapter.getItem(position).getResponseObject().getContent().get(position).getContacts().size()+"****position*******");
            for (int i= 0;i<adapter.getItem(position).getResponseObject().getContent().get(position).getContacts().size();i++){
                CardFourthItemBean cardFourthItemBean = new CardFourthItemBean();
                cardFourthItemBean.setName(adapter.getItem(position).getResponseObject().getContent().get(position).getContacts().get(i).getUsername());
                cardFourthItemBean.setCompany(adapter.getItem(position).getResponseObject().getContent().get(position).getContacts().get(i).getDepartment());
                cardFourthItemBean.setPhoneNumber(adapter.getItem(position).getResponseObject().getContent().get(position).getContacts().get(i).getPhone());
                cardFourthItemBean.setPosition(adapter.getItem(position).getResponseObject().getContent().get(position).getContacts().get(i).getPosition());
                cardFourthItemBean.setEmail(adapter.getItem(position).getResponseObject().getContent().get(position).getContacts().get(i).getEmail());
                beans4.add(cardFourthItemBean);
            }
            EventBus.getDefault().postSticky(beans4);
        }else {
            Log.v("xxxxx", "****position*****xxxx**");
            EventBus.getDefault().postSticky(beans4);//替换残留事件
           // EventBus.getDefault().removeStickyEvent(new ArrayList<CardFourthItemBean>()) ;//移除事件传递
        }
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

    @Override
    public void addProjectsSuccess(List<AllProjectsBean> projectsList) {
        if (mData == null && mDataAll == null) {
            mData = new ArrayList<AllProjectsBean>();
            mDataAll = new ArrayList<AllProjectsBean>();
        }
        if (mDataAll != null) {
            mDataAll.clear();
        }
        mDataAll.addAll(projectsList);
        if (page == 0) {
            for (int i = 0; i < mDataAll.size(); i++) {//
                mData.add(mDataAll.get(i)); //一次显示page= ? 20条数据
            }
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
                            if (mDataAll != null && i >= mDataAll.size()-1) {//到最后
                                noMoreMsg();
                                return;
                            }
                        }
                    }
                }
            });
        }
    }

    @Override
    public void addProjectsFail(String e) {

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    public void noMoreMsg() {
        adapter.isShowFooter(false);
        AppUtils.showToast(getActivity(), getResources().getString(R.string.no_more));
    }
}
