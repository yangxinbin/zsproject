package com.mango.leo.zsproject.datacenter.show;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.mango.leo.zsproject.R;
import com.mango.leo.zsproject.datacenter.fragments.ProjectFragment;
import com.mango.leo.zsproject.datacenter.fragments.InvestorFragment;
import com.mango.leo.zsproject.utils.ViewPageAdapter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;


public class FragmentOfDateCenter extends Fragment {
    @Bind(R.id.tabLayout3)
    TabLayout tabLayout3;
    @Bind(R.id.image_msg3)
    ImageView imageMsg3;
    @Bind(R.id.viewPager3)
    ViewPager viewPager3;
    List<Fragment> mfragments = new ArrayList<Fragment>();
    private List<String> mDatas;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.datacenter, container, false);
        ButterKnife.bind(this, view);
        initDatas();
        init();
        return view;
    }
    private void initDatas() {
        mDatas = new ArrayList<String>(Arrays.asList("投资方", "项目"));
    }

    private void init() {
        tabLayout3.setTabMode(TabLayout.MODE_FIXED);
        ViewPageAdapter vp = new ViewPageAdapter(getFragmentManager(), mfragments, mDatas);
        tabLayout3.setupWithViewPager(viewPager3);
        mfragments.add(new InvestorFragment());
        mfragments.add(new ProjectFragment());
        viewPager3.setAdapter(vp);
        viewPager3.setCurrentItem(0);
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
