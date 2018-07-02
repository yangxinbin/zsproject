package com.mango.leo.zsproject.personalcenter.show.shenbao;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.mango.leo.zsproject.R;
import com.mango.leo.zsproject.adapters.DuoXuanAdapter;
import com.mango.leo.zsproject.adapters.GirdDownAdapter;
import com.mango.leo.zsproject.adapters.ListAndGirdDownAdapter;
import com.mango.leo.zsproject.industrialservice.createrequirements.bean.AllProjectsBean;
import com.mango.leo.zsproject.industrialservice.createrequirements.presenter.AllProjectsPresenter;
import com.mango.leo.zsproject.industrialservice.createrequirements.presenter.AllProjectsPresenterImpl;
import com.mango.leo.zsproject.industrialservice.createrequirements.util.StaggeredGridView;
import com.mango.leo.zsproject.industrialservice.createrequirements.view.AllProjectsView;
import com.mango.leo.zsproject.personalcenter.show.shenbao.adapter.ListShenBaoAdapter;
import com.mango.leo.zsproject.personalcenter.show.shenbao.fragments.TouziFragment;
import com.mango.leo.zsproject.personalcenter.show.shenbao.fragments.XiangMuFragment;
import com.mango.leo.zsproject.utils.ViewPageAdapter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ShenBaoActivity extends FragmentActivity implements AdapterView.OnItemClickListener,AllProjectsView {

    @Bind(R.id.imageView_shengbaoback)
    ImageView imageViewShengbaoback;
    @Bind(R.id.tabLayout_shengbao)
    TabLayout tabLayoutShengbao;
    @Bind(R.id.selete)
    RelativeLayout selete;
    List<Fragment> mfragments = new ArrayList<Fragment>();
    @Bind(R.id.viewPager_shengbao)
    ViewPager viewPagerShengbao;
    private List<String> mDatas;
    private Dialog dialog;
    private ListShenBaoAdapter adapter;
    private AllProjectsPresenter allProjectsPresenter;
    private int page = 0;
    private List<AllProjectsBean> mData = new ArrayList<AllProjectsBean>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        allProjectsPresenter = new AllProjectsPresenterImpl(this);
        setContentView(R.layout.activity_sheng_bao);
        allProjectsPresenter.visitProjects(this, 2, page);
        ButterKnife.bind(this);
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
        viewPagerShengbao.setOffscreenPageLimit(0);
    }

    @OnClick({R.id.imageView_shengbaoback, R.id.selete})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.imageView_shengbaoback:
                finish();
                break;
            case R.id.selete:
                showPopupWindow(this,mData);
                break;
        }
    }

    private void showPopupWindow(Context context, List<AllProjectsBean> listDate) {
        View view = LayoutInflater.from(context).inflate(R.layout.listview_default_down, null);
        //此处可按需求为各控件设置属性
        ListView listView = view.findViewById(R.id.lv);
/*        adapter = new ListShenBaoAdapter(context, listDate);
        listView.setAdapter(adapter);*/
        listView.setOnItemClickListener(this);

        dialog = new Dialog(context, R.style.dialog);
        dialog.setContentView(view);
        Window window = dialog.getWindow();
        //设置弹出窗口大小
        window.setLayout(WindowManager.LayoutParams.FILL_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        //设置显示位置
        window.setGravity(Gravity.BOTTOM);
        //设置动画效果
        window.setWindowAnimations(R.style.AnimBottom);
        dialog.setCanceledOnTouchOutside(true);
        dialog.show();
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

    }

    @Override
    public void addProjectsSuccess(List<AllProjectsBean> projectsList) {

    }

    @Override
    public void addProjectsFail(String e) {

    }
}
