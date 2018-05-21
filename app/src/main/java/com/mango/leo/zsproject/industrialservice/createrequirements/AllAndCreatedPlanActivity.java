package com.mango.leo.zsproject.industrialservice.createrequirements;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.mango.leo.zsproject.R;
import com.mango.leo.zsproject.base.BaseActivity;
import com.mango.leo.zsproject.industrialservice.createrequirements.bean.AllProjectsBean;
import com.mango.leo.zsproject.industrialservice.createrequirements.view.AllProjectsView;
import com.mango.leo.zsproject.industrialservice.fragments.AuditedFragment;
import com.mango.leo.zsproject.industrialservice.fragments.DraftBoxFragment;
import com.mango.leo.zsproject.industrialservice.fragments.SubmissionFragment;
import com.mango.leo.zsproject.utils.ViewPageAdapter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AllAndCreatedPlanActivity extends BaseActivity implements AllProjectsView {

    @Bind(R.id.allAndCreated_image_back)
    ImageView allAndCreatedImageBack;
    @Bind(R.id.allAndCreated_tabLayout)
    TabLayout allAndCreatedTabLayout;
    @Bind(R.id.allAndCreated_image_add)
    Button allAndCreatedImageAdd;
    @Bind(R.id.allAndCreated_viewPager)
    ViewPager allAndCreatedViewPager;
    List<Fragment> mfragments = new ArrayList<Fragment>();
    private List<String> mDatas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_and_created_plan);
        ButterKnife.bind(this);
        initDatas();
        initFragments();
    }

    private void initDatas() {
        mDatas = new ArrayList<String>(Arrays.asList("草稿箱", "已提交", "已审核"));
    }
    private void initFragments() {
        allAndCreatedTabLayout.setTabMode(TabLayout.MODE_FIXED);
        allAndCreatedTabLayout.setupWithViewPager(allAndCreatedViewPager);
        mfragments.add(new DraftBoxFragment());
        mfragments.add(new SubmissionFragment());
        mfragments.add(new AuditedFragment());
        ViewPageAdapter vp = new ViewPageAdapter(getSupportFragmentManager(), mfragments, mDatas);
        allAndCreatedViewPager.setAdapter(vp);
        allAndCreatedViewPager.setCurrentItem(0);
    }
    @OnClick({R.id.allAndCreated_image_back, R.id.allAndCreated_image_add})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.allAndCreated_image_back:
                finish();
                break;
            case R.id.allAndCreated_image_add:
                Intent intent = new Intent(this, CreatedStyleActivity.class);
                startActivity(intent);
                finish();
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }

    @Override
    public void addProjectsSuccess(List<AllProjectsBean> projectsList) {

    }

    @Override
    public void addProjectsFail() {

    }
}
