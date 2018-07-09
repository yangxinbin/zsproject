package com.mango.leo.zsproject.eventexhibition.fragments;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
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
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.mango.leo.zsproject.R;
import com.mango.leo.zsproject.ZsActivity;
import com.mango.leo.zsproject.eventexhibition.adapter.EventAdapter;
import com.mango.leo.zsproject.eventexhibition.bean.EventBean;
import com.mango.leo.zsproject.eventexhibition.bean.ShaiXuanEvent;
import com.mango.leo.zsproject.eventexhibition.presenter.EventPresenter;
import com.mango.leo.zsproject.eventexhibition.presenter.EventPresenterImpl;
import com.mango.leo.zsproject.eventexhibition.show.EventDetailActivity;
import com.mango.leo.zsproject.eventexhibition.view.EventView;
import com.mango.leo.zsproject.industrialservice.bean.MatchEventBean;
import com.mango.leo.zsproject.utils.AppUtils;
import com.mango.leo.zsproject.utils.DropDownAdapter;
import com.mango.leo.zsproject.utils.NetUtil;
import com.mango.leo.zsproject.utils.URLEncoderURI;
import com.mango.leo.zsproject.viewutil.DropdownMenuLayout;

import org.greenrobot.eventbus.EventBus;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by admin on 2018/5/11.
 */

public class CampaignFragment extends Fragment implements AdapterView.OnItemClickListener, EventView {
    @Bind(R.id.dropdownmenu)
    DropdownMenuLayout dropdownmenu;
    @Bind(R.id.id)
    LinearLayout id;
    private String headers[] = {"时间", "地区", "类型"};
    private List<View> popViews = new ArrayList<View>();
    private String times[] = {"全部", "近一月", "近三个月", "已过期"};
    private String wheres[] = {"全部", "北京", "上海", "广州", "深圳", "厦门"};
    private String whats[] = {"全部", "免费", "付费"};
    private SwipeRefreshLayout refresh_cam;
    private RecyclerView recycle_cam;
    private DropDownAdapter timeAdapter;
    private DropDownAdapter whereAdapter;
    private DropDownAdapter whatAdapter;
    private EventPresenter eventPresenter;
    private final int EVENT1 = 1;
    private int page = 0;
    private LinearLayoutManager mLayoutManager;
    private EventAdapter adapter;
    private List<EventBean> mData, mDataAll;
    Long timePast, timeFuture;
    Calendar calendar;
    private ShaiXuanEvent shaiXuanEvent;
    private Date da;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.campaign, container, false);
        ButterKnife.bind(this, view);
        initViews();
        shaiXuanEvent = new ShaiXuanEvent("", "", "", "");
        eventPresenter = new EventPresenterImpl(this);
        eventPresenter.visitEvent(getActivity(), EVENT1, page, shaiXuanEvent);
        initHeader();
        if (mDataAll != null || mData != null) {
            mDataAll.clear();
            mData.clear();
        }
        return view;
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

    @SuppressLint("ResourceType")
    private void initViews() {
        ListView lvTime = new ListView(getActivity());
        lvTime.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        lvTime.setId(0);
        lvTime.setDividerHeight(0);
        timeAdapter = new DropDownAdapter(getActivity(), Arrays.asList(times));
        lvTime.setAdapter(timeAdapter);

        ListView lvWhere = new ListView(getActivity());
        lvWhere.setId(1);
        lvWhere.setDividerHeight(0);
        whereAdapter = new DropDownAdapter(getActivity(), Arrays.asList(wheres));
        lvWhere.setAdapter(whereAdapter);

        ListView lvWhat = new ListView(getActivity());
        lvWhat.setId(2);
        lvWhat.setDividerHeight(0);
        whatAdapter = new DropDownAdapter(getActivity(), Arrays.asList(whats));
        lvWhat.setAdapter(whatAdapter);

        popViews.add(lvTime);
        popViews.add(lvWhere);
        popViews.add(lvWhat);

        lvTime.setOnItemClickListener(this);
        lvWhere.setOnItemClickListener(this);
        lvWhat.setOnItemClickListener(this);

        View content = LayoutInflater.from(getActivity()).inflate(R.layout.cam__items, null);
        refresh_cam = content.findViewById(R.id.refresh_cams);
        recycle_cam = content.findViewById(R.id.recycle_cams);
        content.setLayoutParams(new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT));
        dropdownmenu.setDropDownMemu(Arrays.asList(headers), popViews, content);
        initSwipeRefreshLayout();
        recycle_cam.setHasFixedSize(true);//固定宽高
        mLayoutManager = new LinearLayoutManager(getActivity());
        recycle_cam.setLayoutManager(mLayoutManager);
        recycle_cam.setItemAnimator(new DefaultItemAnimator());//设置默认动画
        adapter = new EventAdapter(getActivity().getApplicationContext());
        adapter.setOnEventnewsClickListener(mOnItemClickListener);
        recycle_cam.removeAllViews();
        recycle_cam.setAdapter(adapter);
        recycle_cam.addOnScrollListener(mOnScrollListener);
        Log.v("yyyyy", "====onCreateView======" + page);
        if (mDataAll != null){
            mDataAll.clear();
        }
        if (mData != null){
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
                eventPresenter.visitEvent(getActivity(), EVENT1, page, shaiXuanEvent);
                Log.v("yyyy", "***onScrollStateChanged******");
            }
        }
    };

    private EventAdapter.OnEventnewsClickListener mOnItemClickListener = new EventAdapter.OnEventnewsClickListener() {
        @Override
        public void onItemClick(View view, int position) {
            position = position - 1; //配对headerView
            if (mData.size() <= 0) {
                return;
            }
            EventBus.getDefault().postSticky(mDataAll.get(position).getResponseObject().getContent().get(position%20));
            Intent intent = new Intent(getActivity(), EventDetailActivity.class);
            intent.putExtra("id", adapter.getItem(position).getResponseObject().getContent().get(position%20).getId());
            startActivity(intent);
        }
    };

    public void initSwipeRefreshLayout() {
        refresh_cam.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                        refresh_cam.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        refresh_cam.setRefreshing(false);
                        if (mData != null && mDataAll != null) {
                            mDataAll.clear();//一定要加上否则会报越界异常 不执行代码加载的if判断
                            mData.clear();
                        }
                        if (NetUtil.isNetConnect(getActivity())) {
                            adapter.isShowFooter(true);
                            page = 0;
                            eventPresenter.visitEvent(getActivity(), EVENT1, page, shaiXuanEvent);
                        } else {
                            // mNewsPresenter.visitProjects(getActivity(),mType);//缓存
                        }
                    }
                }, 2000);
            }
        });
        refresh_cam.setColorSchemeResources(android.R.color.holo_blue_bright,
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
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
        if (mDataAll != null){
            mDataAll.clear();
        }
        if (mData != null){
            mData.clear();
        }
        adapter.notifyDataSetChanged();
        switch (adapterView.getId()) {
            case 0://时间
                calendar = Calendar.getInstance();
                //获取当前时间
                da = new Date();
                calendar.setTime(da);//把当前时间赋给日历
                if (position == 0) {
                    shaiXuanEvent.setTimeFuture("");
                    shaiXuanEvent.setTimePast("");
                }
                if (position == 1) {
                    calendar.add(Calendar.MONTH, -1);
                    timePast = calendar.getTimeInMillis();
                    Log.v("zzzzzzzzzz", calendar.getTime() + "----");
                    calendar.add(Calendar.MONTH, 2);
                    timeFuture = calendar.getTimeInMillis();
                    Log.v("zzzzzzzzzz", calendar.getTime() + "----");
                    shaiXuanEvent.setTimePast(String.valueOf(timePast));
                    shaiXuanEvent.setTimeFuture(String.valueOf(timeFuture));
                    Log.v("zzzzzzzzzz", timePast + "----" + timeFuture);
                }
                if (position == 2) {
                    calendar.add(Calendar.MONTH, -3);
                    timePast = calendar.getTimeInMillis();
                    calendar.add(Calendar.MONTH, 6);
                    timeFuture = calendar.getTimeInMillis();
                    shaiXuanEvent.setTimePast(String.valueOf(timePast));
                    shaiXuanEvent.setTimeFuture(String.valueOf(timeFuture));
                    Log.v("zzzzzzzzz", timePast + "----" + timeFuture);
                }
                if (position == 3) {
                }
               // eventPresenter.visitEvent(getActivity(), EVENT1, page, shaiXuanEvent);
                timeAdapter.setCheckItem(position);
                dropdownmenu.setTableTitle(times[position]);
                dropdownmenu.closeMenu();
                Log.v("zzzzzbbbbbb", "----" + shaiXuanEvent.getTimePast());
                break;
            case 1://地区
                if (position == 0) {
                    shaiXuanEvent.setCity("");
                } else {
                    try {
                        shaiXuanEvent.setCity(URLEncoderURI.encode(wheres[position], "UTF-8"));
                        Log.v("zzzzzbbbbbb", "----" + URLEncoderURI.encode(wheres[position], "UTF-8"));
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                }
                whereAdapter.setCheckItem(position);
                dropdownmenu.setTableTitle(wheres[position]);
                dropdownmenu.closeMenu();
                break;
            case 2://类型
                if (position == 0) {
                    shaiXuanEvent.setTypePay("");
                }
                if (position == 1) {
                    shaiXuanEvent.setTypePay("0");
                }
                if (position == 2) {
                    shaiXuanEvent.setTypePay("1");
                }
                whatAdapter.setCheckItem(position);
                dropdownmenu.setTableTitle(whats[position]);
                dropdownmenu.closeMenu();
                break;
        }
        Log.v("zzzzzyyybb", "----" + shaiXuanEvent.toString());
        eventPresenter.visitEvent(getActivity(), EVENT1, 0, shaiXuanEvent);
    }
    @Override
    public void addEventsView(List<EventBean> eventBeans) {
        Log.v("eeeee", eventBeans.get(0).getResponseObject().getContent().get(0).getName() + "======eventBeans======" + eventBeans.size());
        if (eventBeans == null || eventBeans.size() == 0) {
            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    AppUtils.showToast(getActivity(), getResources().getString(R.string.no_more));
                }
            });
        }
        if (mData == null && mDataAll == null) {
            mData = new ArrayList<EventBean>();
            mDataAll = new ArrayList<EventBean>();
        }
        if (mDataAll != null) {
            mDataAll.clear();
        }
        mDataAll.addAll(eventBeans);
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
/*                            if (mDataAll != null && i >= mDataAll.size() - 1) {//到最后
                                noMoreMsg();
                                return;
                            }*/
                        }
                    }
                }
            });
        }
    }

    @Override
    public void addMatchEventsView(List<MatchEventBean> eventBeans) {
    }

    public void noMoreMsg() {
        adapter.isShowFooter(false);
        AppUtils.showToast(getActivity(), getResources().getString(R.string.no_more));
    }

    @Override
    public void showEventFailMsg(String string) {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                AppUtils.showToast(getActivity(), getResources().getString(R.string.no_more));
            }
        });
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
