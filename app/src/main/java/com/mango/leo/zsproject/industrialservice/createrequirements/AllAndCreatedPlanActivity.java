package com.mango.leo.zsproject.industrialservice.createrequirements;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.mango.leo.zsproject.R;
import com.mango.leo.zsproject.base.BaseActivity;
import com.mango.leo.zsproject.industrialservice.createrequirements.bean.AllProjectsBean;
import com.mango.leo.zsproject.industrialservice.createrequirements.view.AllProjectsView;
import com.mango.leo.zsproject.industrialservice.fragments.ProjectsRecyclerviewFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AllAndCreatedPlanActivity extends BaseActivity{

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

    public static final int PROJECTS_TYPE_DRAFTBOX = 0;
    public static final int PROJECTS_TYPE_SUBMISSION = 1;
    public static final int PROJECTS_TYPE_AUDITED = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_and_created_plan);
        ButterKnife.bind(this);
        allAndCreatedViewPager.setOffscreenPageLimit(0);
        setupViewPager(allAndCreatedViewPager);
        initDatas();
        allAndCreatedTabLayout.setupWithViewPager(allAndCreatedViewPager);
    }

    private void initDatas() {
        allAndCreatedTabLayout.addTab(allAndCreatedTabLayout.newTab().setText(R.string.draftbox));
        allAndCreatedTabLayout.addTab(allAndCreatedTabLayout.newTab().setText(R.string.submission));
        allAndCreatedTabLayout.addTab(allAndCreatedTabLayout.newTab().setText(R.string.audited));
    }
    private void setupViewPager(ViewPager viewpager) {
        ProjectsPagerAdapter adapter = new ProjectsPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(ProjectsRecyclerviewFragment.newInstance(PROJECTS_TYPE_DRAFTBOX), getString(R.string.draftbox));
        adapter.addFragment(ProjectsRecyclerviewFragment.newInstance(PROJECTS_TYPE_SUBMISSION), getString(R.string.submission));
        adapter.addFragment(ProjectsRecyclerviewFragment.newInstance(PROJECTS_TYPE_AUDITED), getString(R.string.audited));
        viewpager.setAdapter(adapter);
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
    private class ProjectsPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragments = new ArrayList<>();
        private final List<String> mFragmentTitles = new ArrayList<>();

        public ProjectsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        public void addFragment(Fragment fragment, String string) {
            mFragments.add(fragment);
            mFragmentTitles.add(string);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragments.get(position);
        }

        @Override
        public int getCount() {
            return mFragments.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitles.get(position);
        }
    }
}
