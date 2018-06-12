package com.mango.leo.zsproject.personalcenter.show.shengbao;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.mango.leo.zsproject.R;
import com.mango.leo.zsproject.base.BaseActivity;
import com.mango.leo.zsproject.personalcenter.show.shengbao.fragments.TouziFragment;
import com.mango.leo.zsproject.personalcenter.show.shengbao.fragments.XiangMuFragment;
import com.mango.leo.zsproject.utils.ViewPageAdapter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ShengBaoActivity extends BaseActivity {

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sheng_bao);
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
    }

    @OnClick({R.id.imageView_shengbaoback, R.id.tabLayout_shengbao, R.id.selete})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.imageView_shengbaoback:
                finish();
                break;
            case R.id.tabLayout_shengbao:
                break;
            case R.id.selete:
                seletePlan();
                break;
        }
    }

    private void seletePlan() {


    }

    @OnClick(R.id.viewPager_shengbao)
    public void onViewClicked() {
    }
}
