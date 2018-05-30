package com.mango.leo.zsproject.industrialservice.show;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mango.leo.zsproject.R;
import com.mango.leo.zsproject.utils.ViewPageAdapter;
import com.mango.leo.zsproject.industrialservice.fragments.DemandManagementFragment;
import com.mango.leo.zsproject.industrialservice.fragments.IndustrialDatabaseFragment;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;


public class FragmentOfIndustrialService extends Fragment {
    @Bind(R.id.tabLayout1)
    TabLayout mTableLayout;
    @Bind(R.id.viewPager1)
    ViewPager mViewPager;
    List<Fragment> mfragments = new ArrayList<Fragment>();
    private List<String> mDatas;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.industrialservice, container, false);
        ButterKnife.bind(this, view);
        initDatas();
        init();
        return view;
    }
    private void initDatas() {
        mDatas = new ArrayList<String>(Arrays.asList("需求管理"/*, "产业数据库", "明星投资人"*/));
    }

    private void init() {
        mTableLayout.setTabMode(TabLayout.MODE_FIXED);
        ViewPageAdapter vp = new ViewPageAdapter(getFragmentManager(), mfragments, mDatas);
        mTableLayout.setupWithViewPager(mViewPager);
        mfragments.add(new DemandManagementFragment());
        //mfragments.add(new IndustrialDatabaseFragment());
       // mfragments.add(new StarInvestorFragment());
        mViewPager.setAdapter(vp);
        mViewPager.setCurrentItem(0);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
