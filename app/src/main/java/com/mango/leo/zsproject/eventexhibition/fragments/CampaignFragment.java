package com.mango.leo.zsproject.eventexhibition.fragments;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
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
import com.mango.leo.zsproject.utils.header.ClassicsHeader;
import com.mango.leo.zsproject.viewutil.DropdownMenuLayout;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

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
    private SmartRefreshLayout refresh_cam;
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
    private boolean isFirstEnter = true;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.campaign, container, false);
        ButterKnife.bind(this, view);
        initViews();
        shaiXuanEvent = new ShaiXuanEvent("", "", "", "");
        eventPresenter = new EventPresenterImpl(this);
        eventPresenter.visitEvent(getActivity(), EVENT1, page, shaiXuanEvent);
        if (mDataAll != null) {
            mDataAll.clear();
        }
        if (mData != null) {
            mData.clear();
        }
        return view;
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
        recycle_cam.setHasFixedSize(true);//固定宽高
        mLayoutManager = new LinearLayoutManager(getActivity());
        recycle_cam.setLayoutManager(mLayoutManager);
        recycle_cam.setItemAnimator(new DefaultItemAnimator());//设置默认动画
        adapter = new EventAdapter(getActivity().getApplicationContext());
        adapter.setOnEventnewsClickListener(mOnItemClickListener);
        recycle_cam.removeAllViews();
        recycle_cam.setAdapter(adapter);
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
        refresh_cam.setOnRefreshListener(new OnRefreshListener() {
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
                        eventPresenter.visitEvent(getActivity(), EVENT1, page, shaiXuanEvent);
                        refreshLayout.finishRefresh();
                    }
                }, 500);
            }
        });
        refresh_cam.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull final RefreshLayout refreshLayout) {
                refreshLayout.getLayout().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        page++;
                        Log.v("zzzzzzzzz", "-------onLoadMore-------" + page);
                        eventPresenter.visitEvent(getActivity(), EVENT1, page, shaiXuanEvent);
                        refreshLayout.finishLoadMore();

                    }
                }, 500);
            }
        });
        refresh_cam.setRefreshHeader(new ClassicsHeader(getActivity()));
        refresh_cam.setHeaderHeight(50);

        //触发自动刷新
        if (isFirstEnter) {
            isFirstEnter = false;
            //refresh.autoRefresh();
        } else {
            //mAdapter.refresh(initData());
        }
    }
    private EventAdapter.OnEventnewsClickListener mOnItemClickListener = new EventAdapter.OnEventnewsClickListener() {
        @Override
        public void onItemClick(View view, int position) {
            if (mData.size() <= 0) {
                return;
            }
            EventBus.getDefault().postSticky(mDataAll.get(position).getResponseObject().getContent().get(position % 20));
            Intent intent = new Intent(getActivity(), EventDetailActivity.class);
            intent.putExtra("id", adapter.getItem(position).getResponseObject().getContent().get(position % 20).getId());
            startActivity(intent);
        }
    };


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
        if (mDataAll != null) {
            mDataAll.clear();
        }
        if (mData != null) {
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
    public void addEventsView(final List<EventBean> eventBeans) {
        Log.v("eeeee", eventBeans.get(0).getResponseObject().getContent().get(0).getName() + "======eventBeans======" + eventBeans.size());
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (eventBeans == null || eventBeans.size() == 0) {

                    AppUtils.showToast(getActivity(), getResources().getString(R.string.no_more));

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
    public void addMatchEventsView(List<MatchEventBean> eventBeans) {
    }

    public void noMoreMsg() {
        AppUtils.showToast(getActivity(), getResources().getString(R.string.no_more));
    }

    @Override
    public void showEventFailMsg(String string) {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Log.v("111111111111111", "====e====");
                AppUtils.showToast(getActivity(), getResources().getString(R.string.load_error));
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

}
