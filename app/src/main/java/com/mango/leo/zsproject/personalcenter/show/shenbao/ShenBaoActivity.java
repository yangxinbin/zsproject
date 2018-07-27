package com.mango.leo.zsproject.personalcenter.show.shenbao;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.mango.leo.zsproject.R;
import com.mango.leo.zsproject.industrialservice.createrequirements.bean.AllProjectsBean;
import com.mango.leo.zsproject.industrialservice.createrequirements.presenter.AllProjectsPresenter;
import com.mango.leo.zsproject.industrialservice.createrequirements.presenter.AllProjectsPresenterImpl;
import com.mango.leo.zsproject.industrialservice.createrequirements.view.AllProjectsView;
import com.mango.leo.zsproject.personalcenter.show.shenbao.adapter.ListShenBaoAdapter;
import com.mango.leo.zsproject.personalcenter.show.shenbao.bean.IdBean;
import com.mango.leo.zsproject.personalcenter.show.shenbao.fragments.TouziFragment;
import com.mango.leo.zsproject.personalcenter.show.shenbao.fragments.XiangMuFragment;
import com.mango.leo.zsproject.utils.AppUtils;
import com.mango.leo.zsproject.utils.NetUtil;
import com.mango.leo.zsproject.utils.SwipeItemLayout;
import com.mango.leo.zsproject.utils.ViewPageAdapter;
import com.mango.leo.zsproject.utils.header.ClassicsHeader;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ShenBaoActivity extends FragmentActivity implements AllProjectsView, View.OnClickListener {

    @Bind(R.id.imageView_shengbaoback)
    ImageView imageViewShengbaoback;
    @Bind(R.id.tabLayout_shengbao)
    TabLayout tabLayoutShengbao;
    @Bind(R.id.selete)
    RelativeLayout selete;
    List<Fragment> mfragments = new ArrayList<Fragment>();
    @Bind(R.id.viewPager_shengbao)
    ViewPager viewPagerShengbao;
    @Bind(R.id.textView_project)
    TextView textViewProject;
    private List<String> mDatas;
    private Dialog dialog;
    private ListShenBaoAdapter adapter;
    private AllProjectsPresenter allProjectsPresenter;
    private int page = 0;
    private List<AllProjectsBean> mData;
    private ArrayList<AllProjectsBean> mDataAll;
    private SmartRefreshLayout refreshLayout;
    private RecyclerView recyclerView;
    private LinearLayoutManager mLayoutManager;
    private IdBean idBean;
    private SharedPreferences sharedPreferences;
    private static SharedPreferences.Editor editor;
    private boolean isFirstEnter = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        allProjectsPresenter = new AllProjectsPresenterImpl(this);
        sharedPreferences = getSharedPreferences("CIFIT", MODE_PRIVATE);
        editor = sharedPreferences.edit();
        setContentView(R.layout.activity_sheng_bao);
        ButterKnife.bind(this);
        if (!sharedPreferences.getString("projectName", "").equals("")) {
            textViewProject.setText(sharedPreferences.getString("projectName", ""));
        }
        idBean = new IdBean();
        initDatas();
        init();
    }

    private void initDatas() {
        mDatas = new ArrayList<String>(Arrays.asList("    投资方    ", "    项目    "));
    }

    private void init() {
        tabLayoutShengbao.setTabMode(TabLayout.MODE_FIXED);
        ViewPageAdapter vp = new ViewPageAdapter(getSupportFragmentManager(), mfragments, mDatas);
        tabLayoutShengbao.setupWithViewPager(viewPagerShengbao);
        mfragments.add(new TouziFragment());
        mfragments.add(new XiangMuFragment());
        viewPagerShengbao.setAdapter(vp);
        viewPagerShengbao.setCurrentItem(0);
        viewPagerShengbao.setOffscreenPageLimit(1);
    }

    @OnClick({R.id.imageView_shengbaoback, R.id.selete})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.imageView_shengbaoback:
                finish();
                break;
            case R.id.selete:
                showPopupWindow(this);
                break;
        }
    }

    private void showPopupWindow(Context context) {
        if (mDataAll != null) {
            mDataAll.clear();
        }
        if (mData != null) {
            mData.clear();
        }
        View view = LayoutInflater.from(context).inflate(R.layout.project_items, null);
        //此处可按需求为各控件设置属性
        refreshLayout = view.findViewById(R.id.refresh_items);
        recyclerView = view.findViewById(R.id.recycle_items);
        allProjectsPresenter.visitProjects(this, 2, 0);
        initRecyclerView();
        init();
        dialog = new Dialog(context, R.style.dialog);
        dialog.setOnKeyListener(new DialogInterface.OnKeyListener() {
            @Override
            public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_SEARCH || keyCode == KeyEvent.KEYCODE_BACK) {
                    closeDialog();
                    return true;
                } else {
                    return false; // 默认返回 false
                }
            }
        });
        dialog.setContentView(view);
        Window window = dialog.getWindow();
        //设置弹出窗口大小
        window.setLayout(WindowManager.LayoutParams.FILL_PARENT, dp2px(300));
        //设置显示位置
        window.setGravity(Gravity.BOTTOM);
        dialog.show();
    }

    private int dp2px(float v) {
        DisplayMetrics dm = getResources().getDisplayMetrics();
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, v, dm);
    }

    private void initHeader() {
        //渲染header布局
        View header = LayoutInflater.from(this).inflate(R.layout.header_all, null);
        LinearLayout h = header.findViewById(R.id.project_all);
        LinearLayout.LayoutParams layoutParam = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        //layoutParam.setMargins(dp2px(15), dp2px(15), dp2px(15), dp2px(15));
        h.setLayoutParams(layoutParam);
        h.setOnClickListener(this);
        adapter.setHeaderView(h);
    }

    private void initRecyclerView() {
        recyclerView.setHasFixedSize(true);//固定宽高
        mLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());//设置默认动画
        adapter = new ListShenBaoAdapter(this.getApplicationContext());
        adapter.setOnItemnewsClickListener(mOnItemClickListener);
        recyclerView.removeAllViews();
        recyclerView.addOnItemTouchListener(new SwipeItemLayout.OnSwipeItemTouchListener(this));
        initHeader();
        recyclerView.setAdapter(adapter);
        refreshAndLoadMore();
    }
    private void refreshAndLoadMore() {
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
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
                        allProjectsPresenter.visitProjects(getBaseContext(), 2, page);
                        refreshLayout.finishRefresh();
                    }
                }, 500);
            }
        });
        refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull final RefreshLayout refreshLayout) {
                refreshLayout.getLayout().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        page++;
                        Log.v("zzzzzzzzz", "-------onLoadMore-------" + page);
                        allProjectsPresenter.visitProjects(getBaseContext(), 2, page);
                        refreshLayout.finishLoadMore();

                    }
                }, 500);
            }
        });
        refreshLayout.setRefreshHeader(new ClassicsHeader(this));
        refreshLayout.setHeaderHeight(50);

        //触发自动刷新
        if (isFirstEnter) {
            isFirstEnter = false;
            //refresh.autoRefresh();
        } else {
            //mAdapter.refresh(initData());
        }
    }
    private ListShenBaoAdapter.OnItemnewsClickListener mOnItemClickListener = new ListShenBaoAdapter.OnItemnewsClickListener() {
        @Override
        public void onItemClick(View view, int position) {
            position = position - 1;
            if (mData.size() <= 0) {
                return;
            }
            textViewProject.setText(adapter.getItem(position).getResponseObject().getContent().get(position % 20).getName());
            idBean.setProjectId(adapter.getItem(position).getResponseObject().getContent().get(position % 20).getId());
            EventBus.getDefault().postSticky(idBean);
            editor.putString("projectName", adapter.getItem(position).getResponseObject().getContent().get(position % 20).getName())
                    .putString("projectId", adapter.getItem(position).getResponseObject().getContent().get(position % 20).getId())
                    .commit();
            closeDialog();
        }
    };

    @Override
    public void addProjectsSuccess(final List<AllProjectsBean> projectsList) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (projectsList == null || projectsList.size() == 0) {
                    AppUtils.showToast(getBaseContext(), getResources().getString(R.string.no_more));
                    return;
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
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                AppUtils.showToast(getBaseContext(), getResources().getString(R.string.load_error));
            }
        });
    }
    public void noMoreMsg() {
        AppUtils.showToast(this, getResources().getString(R.string.no_more));
    }
    @Override
    public void onClick(View view) {//选择全部
        textViewProject.setText("全部");
        IdBean idBeanAll = new IdBean();
        idBeanAll.setProjectId("");
        EventBus.getDefault().postSticky(idBeanAll);
        editor.putString("projectName", "全部")
                .putString("projectId", "")
                .commit();
        closeDialog();
    }

    private void closeDialog() {
        dialog.dismiss();
        Intent intent = new Intent(ShenBaoActivity.this, ShenBaoActivity.class);
        startActivity(intent);
        finish();
    }

}
