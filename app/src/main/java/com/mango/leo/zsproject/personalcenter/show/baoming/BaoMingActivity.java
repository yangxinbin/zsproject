package com.mango.leo.zsproject.personalcenter.show.baoming;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.constraint.ConstraintLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.mango.leo.zsproject.R;
import com.mango.leo.zsproject.base.BaseActivity;
import com.mango.leo.zsproject.industrialservice.createrequirements.util.ProjectsJsonUtils;
import com.mango.leo.zsproject.personalcenter.show.baoming.adapter.SingedUpEventAdapter;
import com.mango.leo.zsproject.personalcenter.show.baoming.bean.SingUpBean;
import com.mango.leo.zsproject.utils.AppUtils;
import com.mango.leo.zsproject.utils.HttpUtils;
import com.mango.leo.zsproject.utils.NetUtil;
import com.mango.leo.zsproject.utils.SwipeItemLayout;
import com.mango.leo.zsproject.utils.Urls;

import org.greenrobot.eventbus.EventBus;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class BaoMingActivity extends BaseActivity {

    @Bind(R.id.imageView_baomingback)
    ImageView imageViewBaomingback;
    @Bind(R.id.recycle_baoming)
    RecyclerView recycleBaoming;
    @Bind(R.id.refresh_baoming)
    SwipeRefreshLayout refreshBaoming;
    private SingedUpEventAdapter adapter;
    private int page = 0;
    private List<SingUpBean> mData, mDataAll;
    private SharedPreferences sharedPreferences;
    private LinearLayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bao_ming);
        ButterKnife.bind(this);
        initSwipeRefreshLayout();
        sharedPreferences = this.getSharedPreferences("CIFIT", MODE_PRIVATE);
        initView();
        initHeader();
        vivistEvent(page);
    }

    private void vivistEvent(final int page) {
        Log.v("vvvvvv", "__" + Urls.HOST_BAOMING_LIST + "?page=" + page + "&token=" + sharedPreferences.getString("token", ""));
        new Thread(new Runnable() {
            @Override
            public void run() {
                HttpUtils.doGet(Urls.HOST_BAOMING_LIST + "?page=" + page + "&token=" + sharedPreferences.getString("token", ""), new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        mHandler.sendEmptyMessage(0);
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        try {
                            List<SingUpBean> beanList = ProjectsJsonUtils.readJsonSingUpBean(response.body().string(), "content");//data是json字段获得data的值即对象数组
                            Message msg = mHandler.obtainMessage();
                            msg.obj = beanList;
                            msg.what = 1;
                            msg.sendToTarget();
                        } catch (Exception e) {
                            mHandler.sendEmptyMessage(0);
//                    Log.e("eeeee", response.body().string()+"Exception = " + e);
                        }

                    }

                });
            }
        }).start();

    }

    private BaoMingActivity.MyHandler mHandler = new BaoMingActivity.MyHandler();

    private class MyHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 0:
                    AppUtils.showToast(getBaseContext(), "访问失败");
                    break;
                case 1:
                    List<SingUpBean> beanList = (List<SingUpBean>) msg.obj;
                    /*if (beanList.size() == 0){
                        noMoreMsg();
                    }else {
                        adapter.isShowFooter(true);
                    }*/
                    addEventsView(beanList);
                    break;
                case 3:
                    //AppUtils.showToast(this, "取消收藏成功");
                    if (mDataAll != null && mData != null) {
                        mDataAll.clear();
                        mData.clear();
                    }
                    adapter.notifyDataSetChanged();
                    break;
                default:
                    break;
            }
        }
    }

    private void initView() {
        recycleBaoming.setHasFixedSize(true);//固定宽高
        mLayoutManager = new LinearLayoutManager(this);
        recycleBaoming.setLayoutManager(mLayoutManager);
        recycleBaoming.setItemAnimator(new DefaultItemAnimator());//设置默认动画
        adapter = new SingedUpEventAdapter(this);
        recycleBaoming.addOnItemTouchListener(new SwipeItemLayout.OnSwipeItemTouchListener(this));
        adapter.setOnEventnewsClickListener(mOnItemClickListener);
        recycleBaoming.removeAllViews();
        recycleBaoming.setAdapter(adapter);
        recycleBaoming.addOnScrollListener(mOnScrollListener);
        Log.v("yyyyy", "====onCreateView======" + page);
        if (mDataAll != null && mData != null) {
            mDataAll.clear();
            mData.clear();
        }
    }

    private SingedUpEventAdapter.OnEventnewsClickListener mOnItemClickListener = new SingedUpEventAdapter.OnEventnewsClickListener() {
        @Override
        public void onItemClick(View view, int position) {
            position = position - 1; //配对headerView
            if (mData.size() <= 0) {
                return;
            }
            Log.v("eeeeeeeeeee","e-----"+adapter.getItemCount());
            EventBus.getDefault().postSticky(adapter.getItem(position).getResponseObject().getContent().get(position%20));
            Intent intent = new Intent(getApplicationContext(), SingUpedDetailActivity.class);
            startActivity(intent);
            finish();
        }

    };

    public void addEventsView(List<SingUpBean> eventBeans) {
        if (eventBeans == null || eventBeans.size() == 0) {
            Log.v("vvvv", eventBeans.get(0).getResponseObject().getContent().get(0).getEvent().getName() + "======eventBeans======" + eventBeans.size());
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    AppUtils.showToast(getBaseContext(), getResources().getString(R.string.no_more));
                }
            });
        }
        if (mData == null && mDataAll == null) {
            mData = new ArrayList<SingUpBean>();
            mDataAll = new ArrayList<SingUpBean>();
        }
        if (mDataAll != null) {
            mDataAll.clear();
        }
        mDataAll.addAll(eventBeans);
        if (page == 0) {
            for (int i = 0; i < mDataAll.size(); i++) {//
                mData.add(mDataAll.get(i)); //一次显示page= ? 20条数据
            }
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if (mData != null) {
                        adapter.setmDate(mData);
                    }
                }
            });
        } else {
            runOnUiThread(new Runnable() {
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
                    && adapter.isShowFooter() && lastVisibleItem - 1 > 10) {//加载判断条件 手指离开屏幕 到了footeritem
                page++;
                vivistEvent(page);
                Log.v("yyyy", "***onScrollStateChanged******");
            }
        }
    };

    public void initSwipeRefreshLayout() {
        refreshBaoming.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refreshBaoming.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        refreshBaoming.setRefreshing(false);
                        if (mData != null && mDataAll != null) {
                            Log.v("mmmmmmm", "1");
                            mDataAll.clear();//一定要加上否则会报越界异常 不执行代码加载的if判断
                            mData.clear();
                        }
                        if (NetUtil.isNetConnect(BaoMingActivity.this)) {
                            adapter.isShowFooter(true);
                            page = 0;
                            vivistEvent(page);
                        } else {
                            // mNewsPresenter.visitProjects(getActivity(),mType);//缓存
                        }
                    }
                }, 2000);
            }
        });
        refreshBaoming.setColorSchemeResources(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);
    }

    private void initHeader() {
        //渲染header布局
        ConstraintLayout h = new ConstraintLayout(this);
        ConstraintLayout.LayoutParams layoutParam = new ConstraintLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, dp2px(1.0f));
        layoutParam.setMargins(0, 0, 0, 20);
        h.setLayoutParams(layoutParam);
        adapter.setHeaderView(h);
    }

    private int dp2px(float v) {
        DisplayMetrics dm = getResources().getDisplayMetrics();
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, v, dm);
    }

    @OnClick(R.id.imageView_baomingback)
    public void onViewClicked() {
        finish();
    }

    public void noMoreMsg() {
        adapter.isShowFooter(false);
        AppUtils.showToast(this, getResources().getString(R.string.no_more));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK)) {
            finish();
        }
        return super.onKeyDown(keyCode, event);
    }
}
