package com.mango.leo.zsproject.industrialservice.fragments;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
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
import com.mango.leo.zsproject.industrialservice.adapte.AllProjectsAdapter;
import com.mango.leo.zsproject.industrialservice.createrequirements.AllAndCreatedPlanActivity;
import com.mango.leo.zsproject.industrialservice.createrequirements.BusinessPlanActivity;
import com.mango.leo.zsproject.industrialservice.createrequirements.bean.AllProjectsBean;
import com.mango.leo.zsproject.industrialservice.createrequirements.carditems.bean.CardFirstItemBean;
import com.mango.leo.zsproject.industrialservice.createrequirements.carditems.bean.CardFourthItemBean;
import com.mango.leo.zsproject.industrialservice.createrequirements.carditems.bean.CardNinthItemBean;
import com.mango.leo.zsproject.industrialservice.createrequirements.carditems.bean.CardThirdItemBean;
import com.mango.leo.zsproject.industrialservice.createrequirements.presenter.AllProjectsPresenter;
import com.mango.leo.zsproject.industrialservice.createrequirements.presenter.AllProjectsPresenterImpl;
import com.mango.leo.zsproject.industrialservice.createrequirements.view.AllProjectsView;
import com.mango.leo.zsproject.login.bean.UserMessageBean;
import com.mango.leo.zsproject.personalcenter.show.AccountSecurityActivity;
import com.mango.leo.zsproject.utils.AppUtils;
import com.mango.leo.zsproject.utils.DateUtil;
import com.mango.leo.zsproject.utils.HttpUtils;
import com.mango.leo.zsproject.utils.NetUtil;
import com.mango.leo.zsproject.utils.SwipeItemLayout;
import com.mango.leo.zsproject.utils.Urls;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by admin on 2018/5/21.
 */

public class ProjectsRecyclerviewFragment extends Fragment implements AllProjectsView, SwipeRefreshLayout.OnRefreshListener {
    @Bind(R.id.recycle_items)
    RecyclerView recycleItems;
    @Bind(R.id.refresh_items)
    SwipeRefreshLayout refreshItems;
    private AllProjectsPresenter allProjectsPresenter;
    private int mType = AllAndCreatedPlanActivity.PROJECTS_TYPE_BUSSINESS;
    private AllProjectsAdapter adapter;
    private LinearLayoutManager mLayoutManager;
    private List<AllProjectsBean> mData;
    private List<AllProjectsBean> mDataAll;
    private int lastVisibleItem;
    private int page = 0;
    private SharedPreferences sharedPreferences;
    private static SharedPreferences.Editor editor;
    private String nowProvince, nowCity, nowDistrict;

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
        allProjectsPresenter = new AllProjectsPresenterImpl(this);
        mType = getArguments().getInt("type");
        sharedPreferences = getActivity().getSharedPreferences("CIFIT", MODE_PRIVATE);
        editor = sharedPreferences.edit();
        Log.v("yyyyy", "====mType======" + mType);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.project_items, null);
        ButterKnife.bind(this, view);
        EventBus.getDefault().register(this);
        initSwipeRefreshLayout();
        recycleItems.setHasFixedSize(true);//固定宽高
        mLayoutManager = new LinearLayoutManager(getActivity());
        recycleItems.setLayoutManager(mLayoutManager);
        recycleItems.setItemAnimator(new DefaultItemAnimator());//设置默认动画
        adapter = new AllProjectsAdapter(getActivity().getApplicationContext(), mType);
        adapter.setOnItemnewsClickListener(mOnItemClickListener);
        recycleItems.removeAllViews();
        recycleItems.addOnItemTouchListener(new SwipeItemLayout.OnSwipeItemTouchListener(getContext()));
        initHeader();
        recycleItems.setAdapter(adapter);
        recycleItems.addOnScrollListener(mOnScrollListener);
        Log.v("yyyyy", "====onCreateView======" + page);
        if (mDataAll != null && mData != null) {
            mDataAll.clear();
            mData.clear();
        }
        page = 0;
        allProjectsPresenter.visitProjects(getActivity(), mType, page);
        return view;
    }

    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void userMessageEventBus(UserMessageBean bean) {
/*        editor.putString("where",nowProvince+nowCity+nowDistrict).commit();
        Log.v("yxb","-----="+sharedPreferences.getString("where","广东省深圳市南山区"));*/
        nowProvince = String.valueOf(bean.getResponseObject().getLocation().getProvince());
        nowCity = String.valueOf(bean.getResponseObject().getLocation().getCity());
        nowDistrict = String.valueOf(bean.getResponseObject().getLocation().getDistrict());
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
                allProjectsPresenter.visitProjects(getActivity(), mType, page);
                Log.v("yyyy", "***onScrollStateChanged******");
            }
        }
    };

    public void initSwipeRefreshLayout() {
        refreshItems.setOnRefreshListener(this);
        refreshItems.setColorSchemeResources(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);
    }

    private AllProjectsAdapter.OnItemnewsClickListener mOnItemClickListener = new AllProjectsAdapter.OnItemnewsClickListener() {

        private int state;

        @Override
        public void onItemClick(View view, int position) {
            position = position - 1; //配对headerView
            if (mData.size() <= 0) {
                return;
            }
            editor.putString("projectId", adapter.getItem(position).getResponseObject().getContent().get(position).getId()).commit();
            Log.v("yyyyyy", adapter.getItem(position).getResponseObject().getContent().get(position).getId() + "****position*******" + position);
            postStickyAll(position);
            Intent intent = new Intent(getActivity(), BusinessPlanActivity.class);
            intent.putExtra("type", adapter.getItem(position).getResponseObject().getContent().get(position).getStage());
            startActivity(intent);
            //getActivity().finish();
        }

        @Override
        public void onEditClick(View view, int position) {
            position = position - 1; //配对headerView
            editor.putString("projectId", adapter.getItem(position).getResponseObject().getContent().get(position).getId()).commit();
            Log.v("yyyyyy", adapter.getItem(position).getResponseObject().getContent().get(position).getId() + "****position*******" + position);
            postStickyAll(position);
            Intent intent = new Intent(getActivity(), BusinessPlanActivity.class);
            intent.putExtra("xiugai", "xiugai");
            startActivity(intent);

        }

        @Override
        public void onDeleteClick(View view, final int position) {
            deletePlan(position - 1);
            /*AlertDialog dialog = new AlertDialog.Builder(getActivity())
                    .setIcon(R.drawable.icon)//设置标题的图片
                    .setTitle("招商信息")//设置对话框的标题
                    .setMessage("确定删除此项目草稿吗？")//设置对话框的内容
                    //设置对话框的按钮
                    .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    })
                    .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            deletePlan(position - 1);
                            dialog.dismiss();
                        }
                    }).create();
            dialog.show();*/
        }
    };

    private void deletePlan(int position) {
        adapter.deleteItem(position);//因为有头部
/*        if (mDataAll != null && mData != null) {
            mDataAll.clear();
            mData.clear();
        }
        adapter.notifyDataSetChanged();
        allProjectsPresenter.visitProjects(getActivity(), mType, 0);*/
        Map<String, String> mapParams = new HashMap<String, String>();
        mapParams.clear();
        mapParams.put("projectId", adapter.getItem(position).getResponseObject().getContent().get(position).getId());
        mapParams.put("token", sharedPreferences.getString("token", ""));
        HttpUtils.doDelete(Urls.HOST_PROJECT, mapParams, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e("eeeee", "eeeee = " + e.getMessage());
                mHandler.sendEmptyMessage(0);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (String.valueOf(response.code()).startsWith("2")) {
                    mHandler.sendEmptyMessage(1);
                    // vivistEvent(0);
                } else {
                    Log.e("eeeee", response.body().string() + "Exception = ");
                    mHandler.sendEmptyMessage(0);
                }
            }
        });
        refreshItems.setOnRefreshListener(this);
        refreshItems.postDelayed(new Runnable() {
            @Override
            public void run() {
                // TODO Auto-generated method stub
                //refreshItems.setRefreshing(true);
                //refreshItems.setRefreshing(false);
                if (mData != null && mDataAll != null) {
                    mDataAll.clear();//一定要加上否则会报越界异常 不执行代码加载的if判断
                    mData.clear();
                }
                if (NetUtil.isNetConnect(getActivity())) {
                    adapter.isShowFooter(true);
                    page = 0;
                    allProjectsPresenter.visitProjects(getActivity(), mType, page);
                } else {
                    // allProjectsPresenter.visitProjects(getActivity(),mType);//缓存
                }
            }
        }, 2000);
        //getActivity().onRefresh();
    }

    public void postStickyAll(int position) {
        CardFirstItemBean cardFirstItemBean = new CardFirstItemBean();
        if (adapter.getItem(position).getResponseObject().getContent().get(position).getName() != null) {
            cardFirstItemBean.setItemName(adapter.getItem(position).getResponseObject().getContent().get(position).getName());
            cardFirstItemBean.setDepartmentName(adapter.getItem(position).getResponseObject().getContent().get(position).getOrganizerDepartment());
            long time;
            if ("0".equals(String.valueOf(adapter.getItem(position).getResponseObject().getContent().get(position).getUpdatedOn()))) {
                time = adapter.getItem(position).getResponseObject().getContent().get(position).getCreatedOn();
            } else {
                time = adapter.getItem(position).getResponseObject().getContent().get(position).getUpdatedOn();
            }
            if (adapter.getItem(position).getResponseObject().getContent().get(position) != null) {
                cardFirstItemBean.setMoney(String.valueOf(adapter.getItem(position).getResponseObject().getContent().get(position).getTotalInvestmentRequired()));
            }
            cardFirstItemBean.setTime(DateUtil.getDateToString(time, "yyyy-MM-dd"));
            cardFirstItemBean.setItemContent(adapter.getItem(position).getResponseObject().getContent().get(position).getSummary());
            //cardFirstItemBean.setItemImagePath((List<LocalMedia>) adapter.getItem(position).getResponseObject().getContent().get(position).getPhotos());
            //cardFirstItemBean.setProjectId(adapter.getItem(position).getResponseObject().getContent().get(position).getId());
            EventBus.getDefault().postSticky(cardFirstItemBean);
        } else {
            EventBus.getDefault().postSticky(cardFirstItemBean);
        }
        CardThirdItemBean cardThirdItemBean = new CardThirdItemBean();
        if (adapter.getItem(position).getResponseObject().getContent().get(position).getLocation() != null) {
            cardThirdItemBean.setProvince(nowProvince);
            cardThirdItemBean.setCity(nowCity);
            cardThirdItemBean.setDistrict(nowDistrict);
            cardThirdItemBean.setLon(String.valueOf(adapter.getItem(position).getResponseObject().getContent().get(position).getLocation().getLon()));
            cardThirdItemBean.setLat(String.valueOf(adapter.getItem(position).getResponseObject().getContent().get(position).getLocation().getLat()));
            Log.v("33333", "______!= null_____");
            EventBus.getDefault().postSticky(cardThirdItemBean);
        } else {
            EventBus.getDefault().postSticky(cardThirdItemBean);
        }

        List<CardFourthItemBean> beans4 = new ArrayList<>();
        if (beans4 != null) {
            beans4.clear();//清空刷新
        }
        if (adapter.getItem(position).getResponseObject().getContent().get(position).getContacts() != null) {
            Log.v("xxxxx", adapter.getItem(position).getResponseObject().getContent().get(position).getContacts().size() + "****position*******");
            for (int i = 0; i < adapter.getItem(position).getResponseObject().getContent().get(position).getContacts().size(); i++) {
                CardFourthItemBean cardFourthItemBean = new CardFourthItemBean();
                cardFourthItemBean.setName(String.valueOf(adapter.getItem(position).getResponseObject().getContent().get(position).getContacts().get(i).getName()));
                cardFourthItemBean.setCompany(adapter.getItem(position).getResponseObject().getContent().get(position).getContacts().get(i).getDepartment());
                cardFourthItemBean.setPhoneNumber(adapter.getItem(position).getResponseObject().getContent().get(position).getContacts().get(i).getPhone());
                cardFourthItemBean.setPosition(adapter.getItem(position).getResponseObject().getContent().get(position).getContacts().get(i).getPosition());
                cardFourthItemBean.setEmail(adapter.getItem(position).getResponseObject().getContent().get(position).getContacts().get(i).getEmail());
                beans4.add(cardFourthItemBean);
            }
            EventBus.getDefault().postSticky(beans4);
        } else {
            Log.v("xxxxx", "****position*****xxxx**");
            EventBus.getDefault().postSticky(beans4);//替换残留事件
            // EventBus.getDefault().removeStickyEvent(new ArrayList<CardFourthItemBean>()) ;//移除事件传递
        }
        CardNinthItemBean cardNinthItemBean = new CardNinthItemBean();
        if (adapter.getItem(position).getResponseObject().getContent().get(position).getIcr() != null) {
            Log.v("xxxxx", adapter.getItem(position).getResponseObject().getContent().get(position).getIcr().getCooperationModel() + "****position*****xxxx**");
            cardNinthItemBean.setMoshi(String.valueOf(adapter.getItem(position).getResponseObject().getContent().get(position).getIcr().getCooperationModel()));
            int min = adapter.getItem(position).getResponseObject().getContent().get(position).getIcr().getInvestmentSize().getMin();
            int max = adapter.getItem(position).getResponseObject().getContent().get(position).getIcr().getInvestmentSize().getMax();
            if (max <= 1000) {
                cardNinthItemBean.setMoney("1000万以下");
            }
            if (min >= 1000 && max <= 5000) {
                cardNinthItemBean.setMoney("1000万—5000万");
            }
            if (min >= 5000 && max <= 10000) {
                cardNinthItemBean.setMoney("（含）5000万—1亿");
            }
            if (min >= 10000 && max <= 100000) {
                cardNinthItemBean.setMoney("（含）1亿—10亿");
            }
            if (min >= 100000 && max <= 500000) {
                cardNinthItemBean.setMoney("（含）10亿—50亿");
            }
            if (min >= 500000 && max <= 1000000) {
                cardNinthItemBean.setMoney("（含）50亿—100亿");
            }
            if (min >= 1000000 && max <= 5000000) {
                cardNinthItemBean.setMoney("（含）100亿—500亿");
            }
            if (min >= 5000000 && max <= 10000000) {
                cardNinthItemBean.setMoney("（含）500亿—1000亿");
            }
            if (max >= 10000000) {
                cardNinthItemBean.setMoney("（含）1000亿以上");
            }
/*            List<String> whyList = new ArrayList<>();
            for (int i = 0; i < adapter.getItem(position).getResponseObject().getContent().get(position).getIcr().getCooperationStyles().size(); i++) {
                whyList.add(adapter.getItem(position).getResponseObject().getContent().get(position).getIcr().getCooperationStyles().get(i));
                Log.v("99999", "___bus_" + adapter.getItem(position).getResponseObject().getContent().get(position).getIcr().getCooperationStyles());

            }*/

            cardNinthItemBean.setWhy(adapter.getItem(position).getResponseObject().getContent().get(position).getIcr().getCooperationStyles());
/*            List<String> typeList = new ArrayList<>();
            for (int j = 0; j < adapter.getItem(position).getResponseObject().getContent().get(position).getIcr().getInvestmentType().size(); j++) {
                typeList.add(adapter.getItem(position).getResponseObject().getContent().get(position).getIcr().getInvestmentType().get(j));
                Log.v("888888",adapter.getItem(position).getResponseObject().getContent().get(position).getIcr().getInvestmentType().get(j)+"___bus_");
            }*/
            Log.v("888888",adapter.getItem(position).getResponseObject().getContent().get(position).getIcr().getInvestmentType()+"___bus_");
            cardNinthItemBean.setType(adapter.getItem(position).getResponseObject().getContent().get(position).getIcr().getInvestmentType());
            // cardNinthItemBean.setMoney((String) adapter.getItem(position).getResponseObject().getContent().get(position).getIcr().getCooperationStyles());
            cardNinthItemBean.setQita((String) adapter.getItem(position).getResponseObject().getContent().get(position).getIcr().getOther());
           // EventBus.getDefault().postSticky(cardNinthItemBean);
        } else {
          //  EventBus.getDefault().postSticky(cardNinthItemBean);
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
            if (getActivity() != null) {
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (mData != null) {
                            adapter.setmDate(mData);
                        }
                    }
                });
            }

        } else {
            if (getActivity() != null) {
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
                                Log.v("rrrrrrrrr", "--adapter--");

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
        }
    }

    @Override
    public void addProjectsFail(String e) {
        if (getActivity() != null) {
            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    AppUtils.showToast(getActivity(), "加载失败");
                }
            });
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
        EventBus.getDefault().unregister(this);
    }

    public void noMoreMsg() {
        adapter.isShowFooter(false);
        AppUtils.showToast(getActivity(), getResources().getString(R.string.no_more));
    }


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
                    allProjectsPresenter.visitProjects(getActivity(), mType, page);
                } else {
                    // allProjectsPresenter.visitProjects(getActivity(),mType);//缓存
                }
            }
        }, 2000);
    }

    private ProjectsRecyclerviewFragment.MyHandler mHandler = new ProjectsRecyclerviewFragment.MyHandler();

    private class MyHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 0:
                    AppUtils.showToast(getActivity(), "项目删除失败");
                    break;
                case 1:
                    AppUtils.showToast(getActivity(), "项目删除成功");
                    refreshItems.setRefreshing(false);
                    /*refreshItems.setOnRefreshListener(this);
                    refreshItems.post(new Runnable() {
                        @Override
                        public void run() {
                            // TODO Auto-generated method stub
                            refreshItems.setRefreshing(true);
                        }
                    });*/
                    break;
                default:
                    break;
            }
        }
    }
}
