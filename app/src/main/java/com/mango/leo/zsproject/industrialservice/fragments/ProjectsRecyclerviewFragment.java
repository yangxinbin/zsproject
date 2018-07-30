package com.mango.leo.zsproject.industrialservice.fragments;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
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
import com.mango.leo.zsproject.industrialservice.createrequirements.carditems.bean.CardSecondItemBeanObj;
import com.mango.leo.zsproject.industrialservice.createrequirements.carditems.bean.CardThirdItemBean;
import com.mango.leo.zsproject.industrialservice.createrequirements.carditems.bean.ChangYe;
import com.mango.leo.zsproject.industrialservice.createrequirements.presenter.AllProjectsPresenter;
import com.mango.leo.zsproject.industrialservice.createrequirements.presenter.AllProjectsPresenterImpl;
import com.mango.leo.zsproject.industrialservice.createrequirements.util.JsonMap;
import com.mango.leo.zsproject.industrialservice.createrequirements.view.AllProjectsView;
import com.mango.leo.zsproject.login.bean.UserMessageBean;
import com.mango.leo.zsproject.utils.AppUtils;
import com.mango.leo.zsproject.utils.DateUtil;
import com.mango.leo.zsproject.utils.HttpUtils;
import com.mango.leo.zsproject.utils.SwipeItemLayout;
import com.mango.leo.zsproject.utils.Urls;
import com.mango.leo.zsproject.utils.header.ClassicsHeader;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

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

public class ProjectsRecyclerviewFragment extends Fragment implements AllProjectsView {
    @Bind(R.id.recycle_items)
    RecyclerView recycleItems;
    @Bind(R.id.refresh_items)
    SmartRefreshLayout refreshItems;
    private AllProjectsPresenter allProjectsPresenter;
    private int mType = AllAndCreatedPlanActivity.PROJECTS_TYPE_BUSSINESS;
    private AllProjectsAdapter adapter;
    private LinearLayoutManager mLayoutManager;
    private List<AllProjectsBean> mData;
    private List<AllProjectsBean> mDataAll;
    private int page = 0;
    private SharedPreferences sharedPreferences;
    private static SharedPreferences.Editor editor;
    private String nowProvince, nowCity, nowDistrict;
    private boolean isFirstEnter;

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
        recycleItems.setHasFixedSize(true);//固定宽高
        mLayoutManager = new LinearLayoutManager(getActivity());
        recycleItems.setLayoutManager(mLayoutManager);
        recycleItems.setItemAnimator(new DefaultItemAnimator());//设置默认动画
        adapter = new AllProjectsAdapter(getActivity().getApplicationContext(), mType);
        adapter.setOnItemnewsClickListener(mOnItemClickListener);
        recycleItems.removeAllViews();
        recycleItems.addOnItemTouchListener(new SwipeItemLayout.OnSwipeItemTouchListener(getContext()));
        recycleItems.setAdapter(adapter);
        Log.v("yyyyy", "====onCreateView======" + page);
        if (mDataAll != null && mData != null) {
            mDataAll.clear();
            mData.clear();
        }
        page = 0;
        allProjectsPresenter.visitProjects(getActivity(), mType, page);
        refreshAndLoadMore();
        return view;
    }

    private void refreshAndLoadMore() {
        refreshItems.setOnRefreshListener(new OnRefreshListener() {
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
                        allProjectsPresenter.visitProjects(getActivity(), mType, page);
                        refreshLayout.finishRefresh();
                    }
                }, 500);
            }
        });
        refreshItems.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull final RefreshLayout refreshLayout) {
                refreshLayout.getLayout().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        page++;
                        Log.v("zzzzzzzzz", "-------onLoadMore-------" + page);
                        allProjectsPresenter.visitProjects(getActivity(), mType, page);
                        refreshLayout.finishLoadMore();

                    }
                }, 500);
            }
        });
        refreshItems.setRefreshHeader(new ClassicsHeader(getActivity()));
        refreshItems.setHeaderHeight(50);

        //触发自动刷新
        if (isFirstEnter) {
            isFirstEnter = false;
            //refresh.autoRefresh();
        } else {
            //mAdapter.refresh(initData());
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void userMessageEventBus(UserMessageBean bean) {
/*        editor.putString("where",nowProvince+nowCity+nowDistrict).commit();
        Log.v("yxb","-----="+sharedPreferences.getString("where","广东省深圳市南山区"));*/
        nowProvince = String.valueOf(bean.getResponseObject().getLocation().getProvince());
        nowCity = String.valueOf(bean.getResponseObject().getLocation().getCity());
        nowDistrict = String.valueOf(bean.getResponseObject().getLocation().getDistrict());
    }

    private AllProjectsAdapter.OnItemnewsClickListener mOnItemClickListener = new AllProjectsAdapter.OnItemnewsClickListener() {
        @Override
        public void onItemClick(View view, int position) {
            if (mData.size() <= 0) {
                return;
            }
            editor.putString("projectId", adapter.getItem(position).getResponseObject().getContent().get(position).getId()).commit();
            Log.v("yyyyyy", adapter.getItem(position).getResponseObject().getContent().get(position).getId() + "****position*******" + position);
            postStickyAll(position);
            loadChanye(adapter.getItem(position).getResponseObject().getContent().get(position).getId());
            Intent intent = new Intent(getActivity(), BusinessPlanActivity.class);
            intent.putExtra("type", adapter.getItem(position).getResponseObject().getContent().get(position).getStage());
            startActivity(intent);
            //getActivity().finish();
        }

        private void loadChanye(final String id) {
            Log.v("ppppppppppp", "__--" + Urls.HOST + "/business-service/project/project/industries?projectId=" + id);
            new Thread(new Runnable() {
                @Override
                public void run() {
                    HttpUtils.doGet(Urls.HOST + "/business-service/project/project/industries?projectId=" + id, new Callback() {
                        @Override
                        public void onFailure(Call call, IOException e) {
                            Log.v("ppppppppppp", "__IOException_--");
                        }

                        @Override
                        public void onResponse(Call call, Response response) throws IOException {
                            try {
                                List<ChangYe> listC = JsonMap.getMap(response.body().string());
                                Message message = mHandler.obtainMessage();
                                message.obj = listC;
                                message.what = 2;
                                mHandler.sendMessage(message);
                            } catch (Exception e) {
                                Log.v("ppppppppppp", "__eee_--" + response.body().string());
                            }
                        }
                    });
                }
            }).start();
        }

        @Override
        public void onEditClick(View view, int position) {
            editor.putString("projectId", adapter.getItem(position).getResponseObject().getContent().get(position).getId()).commit();
            Log.v("yyyyyy", adapter.getItem(position).getResponseObject().getContent().get(position).getId() + "****position*******" + position);
            postStickyAll(position);
            Intent intent = new Intent(getActivity(), BusinessPlanActivity.class);
            intent.putExtra("xiugai", "xiugai");
            startActivity(intent);

        }

        @Override
        public void onDeleteClick(View view, final int position) {
            deletePlan(position);
        }
    };

    private void deletePlan(int position) {
        adapter.deleteItem(position);//因为有头部
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
        //refreshItems.finishRefresh();
    }

    public void postStickyAll(int position) {
        CardFirstItemBean cardFirstItemBean = new CardFirstItemBean();
        if (adapter.getItem(position).getResponseObject().getContent().get(position % 20).getName() != null) {
            cardFirstItemBean.setItemName(adapter.getItem(position).getResponseObject().getContent().get(position % 20).getName());
            cardFirstItemBean.setDepartmentName(adapter.getItem(position).getResponseObject().getContent().get(position % 20).getOrganizerDepartment());
            long time;
            if ("0".equals(String.valueOf(adapter.getItem(position).getResponseObject().getContent().get(position % 20).getUpdatedOn()))) {
                time = adapter.getItem(position).getResponseObject().getContent().get(position % 20).getCreatedOn();
            } else {
                time = adapter.getItem(position).getResponseObject().getContent().get(position % 20).getUpdatedOn();
            }
            if (adapter.getItem(position).getResponseObject().getContent().get(position % 20) != null) {
                cardFirstItemBean.setMoney(String.valueOf(adapter.getItem(position).getResponseObject().getContent().get(position % 20).getTotalInvestmentRequired()));
            }
            cardFirstItemBean.setTime(DateUtil.getDateToString(time, "yyyy-MM-dd"));
            cardFirstItemBean.setMoney(adapter.getItem(position).getResponseObject().getContent().get(position % 20).getTotalInvestmentRequired());
            cardFirstItemBean.setItemContent(adapter.getItem(position).getResponseObject().getContent().get(position % 20).getSummary());
            //cardFirstItemBean.setItemImagePath((List<LocalMedia>) adapter.getItem(position).getResponseObject().getContent().get(position).getPhotos());
            //cardFirstItemBean.setProjectId(adapter.getItem(position).getResponseObject().getContent().get(position).getId());
            EventBus.getDefault().postSticky(cardFirstItemBean);
        } else {
            EventBus.getDefault().postSticky(cardFirstItemBean);
        }
        CardThirdItemBean cardThirdItemBean = new CardThirdItemBean();
        if (adapter.getItem(position).getResponseObject().getContent().get(position % 20).getLocation() != null) {
            cardThirdItemBean.setProvince(nowProvince);
            cardThirdItemBean.setCity(nowCity);
            cardThirdItemBean.setDistrict(nowDistrict);
            cardThirdItemBean.setAddress(String.valueOf(adapter.getItem(position).getResponseObject().getContent().get(position % 20).getLocation().getAddress()));
            cardThirdItemBean.setLon(String.valueOf(adapter.getItem(position).getResponseObject().getContent().get(position % 20).getLocation().getLon()));
            cardThirdItemBean.setLat(String.valueOf(adapter.getItem(position).getResponseObject().getContent().get(position % 20).getLocation().getLat()));
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
            Log.v("xxxxx", adapter.getItem(position).getResponseObject().getContent().get(position % 20).getContacts().size() + "****position*******");
            for (int i = 0; i < adapter.getItem(position).getResponseObject().getContent().get(position).getContacts().size(); i++) {
                CardFourthItemBean cardFourthItemBean = new CardFourthItemBean();
                cardFourthItemBean.setName(String.valueOf(adapter.getItem(position).getResponseObject().getContent().get(position % 20).getContacts().get(i).getName()));
                cardFourthItemBean.setCompany(adapter.getItem(position).getResponseObject().getContent().get(position % 20).getContacts().get(i).getDepartment());
                cardFourthItemBean.setPhoneNumber(adapter.getItem(position).getResponseObject().getContent().get(position % 20).getContacts().get(i).getPhone());
                cardFourthItemBean.setPosition(adapter.getItem(position).getResponseObject().getContent().get(position % 20).getContacts().get(i).getPosition());
                cardFourthItemBean.setEmail(adapter.getItem(position).getResponseObject().getContent().get(position % 20).getContacts().get(i).getEmail());
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
            Log.v("xxxxx", adapter.getItem(position).getResponseObject().getContent().get(position % 20).getIcr().getCooperationModel() + "****position*****xxxx**");
            cardNinthItemBean.setMoshi(adapter.getItem(position).getResponseObject().getContent().get(position % 20).getIcr().getCooperationModel());
            int min = -1;
            int max = -1;
            if (adapter.getItem(position).getResponseObject().getContent().get(position % 20).getIcr().getInvestmentSize() != null) {
                min = adapter.getItem(position).getResponseObject().getContent().get(position % 20).getIcr().getInvestmentSize().getMin();
                max = adapter.getItem(position).getResponseObject().getContent().get(position % 20).getIcr().getInvestmentSize().getMax();
                Log.v("xxxxx", min + "!!!!!!!" + max);
            } else {
                cardNinthItemBean.setMoney("");
            }
            if (max <= 1000 && 0 <= max) {
                cardNinthItemBean.setMoney("1000万以下");
                Log.v("xxxxx", "fdsf");
            }
            if (min >= 1000 && max <= 5000) {
                Log.v("xxxxx", "fdsf");

                cardNinthItemBean.setMoney("1000万—5000万");
            }
            if (min >= 5000 && max <= 10000) {
                Log.v("xxxxx", "fdsf");

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
            //Log.v("999999",adapter.getItem(position).getResponseObject().getContent().get(position).getIcr().getCooperationStyles()+"***"+max+"===="+min+"*****"+cardNinthItemBean.getMoney());
            cardNinthItemBean.setWhy(adapter.getItem(position).getResponseObject().getContent().get(position % 20).getIcr().getCooperationStyles());
            cardNinthItemBean.setType(adapter.getItem(position).getResponseObject().getContent().get(position % 20).getIcr().getInvestmentType());
            cardNinthItemBean.setQita(String.valueOf(adapter.getItem(position).getResponseObject().getContent().get(position % 20).getIcr().getOther()));
            EventBus.getDefault().postSticky(cardNinthItemBean);
        } else {
            EventBus.getDefault().postSticky(cardNinthItemBean);
        }

    }

    @Override
    public void addProjectsSuccess(final List<AllProjectsBean> projectsList) {
        Log.v("yxb", "" + projectsList.size());
        if (getActivity() != null)
            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if (projectsList == null || projectsList.size() == 0) {
                        AppUtils.showToast(getActivity(), getResources().getString(R.string.no_more));
                    }
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
                        adapter.setmDate(mData);
                    } else {
                        if (mDataAll != null) {
                            //加载更多
                            int i;
                            for (i = 0; i < mDataAll.size(); i++) {
                                if (mDataAll == null) {
                                    return;//一开始断网报空指针的情况
                                }
                                Log.v("rrrrrrrrr", "--adapter--");
                                adapter.addItem(mDataAll.get(i));//addItem里面记得要notifyDataSetChanged 否则第一次加载不会显示数据
                            }
                        }
                    }
                }
            });

    }

    @Override
    public void addProjectsFail(String e) {
        if (getActivity() != null) {
            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    AppUtils.showToast(getActivity(), getResources().getString(R.string.load_error));
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
                    refreshItems.autoRefresh();
                    break;
                case 2:
                    List<ChangYe> changYe = (List<ChangYe>) msg.obj;
                    CardSecondItemBeanObj cardSecondItemBeanObj = new CardSecondItemBeanObj();
                    List<CardSecondItemBeanObj.CardSecondItemBean> cardSecondItemBeanList = new ArrayList<>();
                    for (int i = 0; i < changYe.size(); i++) {
                        cardSecondItemBeanList.add(new CardSecondItemBeanObj.CardSecondItemBean(changYe.get(i).getChanYe(), changYe.get(i).getLingYu()));
                    }
                    cardSecondItemBeanObj.setContent(cardSecondItemBeanList);
                    EventBus.getDefault().postSticky(cardSecondItemBeanObj);
                    break;
                default:
                    break;
            }
        }
    }
}
