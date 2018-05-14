package com.mango.leo.zsproject.industrialpanorama.show;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Spinner;

import com.mango.leo.zsproject.R;
import com.mango.leo.zsproject.industrialpanorama.fragments.CustomRequirementsFragment;
import com.mango.leo.zsproject.industrialpanorama.fragments.InvestmentInformationFragment;
import com.mango.leo.zsproject.industrialpanorama.fragments.InvestmentPlanFragment;
import com.mango.leo.zsproject.utils.ViewPageAdapter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;


public class FragmentOfIndustrialPanorama extends Fragment {
    @Bind(R.id.tabLayout2)
    TabLayout tabLayout2;
    @Bind(R.id.image_msg2)
    ImageView imageMsg2;
    @Bind(R.id.viewPager2)
    ViewPager viewPager2;
    List<Fragment> mfragments = new ArrayList<Fragment>();
    @Bind(R.id.spinner)
    Spinner spinner;
    private List<String> mDatas;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.industrialpanorama, container, false);
        ButterKnife.bind(this, view);
        initDatas();
        init();
        return view;
    }

    private void initDatas() {
        mDatas = new ArrayList<String>(Arrays.asList("招商信息", "招商计划", "定制需求"));
    }

    private void init() {
        tabLayout2.setTabMode(TabLayout.MODE_FIXED);
        ViewPageAdapter vp = new ViewPageAdapter(getFragmentManager(), mfragments, mDatas);
        tabLayout2.setupWithViewPager(viewPager2);
        mfragments.add(new InvestmentInformationFragment());
        mfragments.add(new InvestmentPlanFragment());
        mfragments.add(new CustomRequirementsFragment());
        viewPager2.setAdapter(vp);
        viewPager2.setCurrentItem(0);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
