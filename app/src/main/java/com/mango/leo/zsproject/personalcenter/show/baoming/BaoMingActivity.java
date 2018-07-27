package com.mango.leo.zsproject.personalcenter.show.baoming;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
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
import com.mango.leo.zsproject.utils.header.ClassicsHeader;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

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
    SmartRefreshLayout refreshBaoming;
    private SingedUpEventAdapter adapter;
    private int page = 0;
    private List<SingUpBean> mData, mDataAll;
    private SharedPreferences sharedPreferences;
    private LinearLayoutManager mLayoutManager;
    private boolean isFirstEnter = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bao_ming);
        ButterKnife.bind(this);
        sharedPreferences = this.getSharedPreferences("CIFIT", MODE_PRIVATE);
        initView();
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
                    addEventsView(beanList);
                    break;
                case 3:
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
        refreshBaoming.setOnRefreshListener(new OnRefreshListener() {
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
                        vivistEvent(page);
                        refreshLayout.finishRefresh();
                    }
                }, 500);
            }
        });
        refreshBaoming.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull final RefreshLayout refreshLayout) {
                refreshLayout.getLayout().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        page++;
                        Log.v("zzzzzzzzz", "-------onLoadMore-------" + page);
                        vivistEvent(page);
                        refreshLayout.finishLoadMore();

                    }
                }, 500);
            }
        });
        refreshBaoming.setRefreshHeader(new ClassicsHeader(this));
        refreshBaoming.setHeaderHeight(50);

        //触发自动刷新
        if (isFirstEnter) {
            isFirstEnter = false;
            //refresh.autoRefresh();
        } else {
            //mAdapter.refresh(initData());
        }
    }
    private SingedUpEventAdapter.OnEventnewsClickListener mOnItemClickListener = new SingedUpEventAdapter.OnEventnewsClickListener() {
        @Override
        public void onItemClick(View view, int position) {
            if (mData.size() <= 0) {
                return;
            }
            Log.v("eeeeeeeeeee", "e-----" + adapter.getItemCount());
            EventBus.getDefault().postSticky(adapter.getItem(position).getResponseObject().getContent().get(position % 20));
            Intent intent = new Intent(getApplicationContext(), SingUpedDetailActivity.class);
            startActivity(intent);
            finish();
        }

    };

    public void addEventsView(final List<SingUpBean> eventBeans) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (eventBeans == null || eventBeans.size() == 0) {

                    AppUtils.showToast(getBaseContext(), getResources().getString(R.string.no_more));

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

    @OnClick(R.id.imageView_baomingback)
    public void onViewClicked() {
        finish();
    }

    public void noMoreMsg() {
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
