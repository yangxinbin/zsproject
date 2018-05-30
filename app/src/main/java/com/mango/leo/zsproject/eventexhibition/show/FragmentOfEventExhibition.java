package com.mango.leo.zsproject.eventexhibition.show;

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
import com.mango.leo.zsproject.eventexhibition.fragments.CampaignFragment;
import com.mango.leo.zsproject.eventexhibition.fragments.ExhibitionFragment;
import com.mango.leo.zsproject.eventexhibition.fragments.InformationFragment;
import com.mango.leo.zsproject.utils.ViewPageAdapter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;


public class FragmentOfEventExhibition extends Fragment {
    @Bind(R.id.tabLayout4)
    TabLayout tabLayout4;
/*    @Bind(R.id.image_msg4)
    ImageView imageMsg4;*/
    @Bind(R.id.viewPager4)
    ViewPager viewPager4;
    List<Fragment> mfragments = new ArrayList<Fragment>();
    private List<String> mDatas;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.eventexhibition, container, false);
        ButterKnife.bind(this, view);
        initDatas();
        init();
        return view;
    }
    private void initDatas() {
        mDatas = new ArrayList<String>(Arrays.asList("活动", "展会","资讯"));
    }

    private void init() {
        tabLayout4.setTabMode(TabLayout.MODE_FIXED);
        ViewPageAdapter vp = new ViewPageAdapter(getFragmentManager(), mfragments, mDatas);
        tabLayout4.setupWithViewPager(viewPager4);
        mfragments.add(new CampaignFragment());
        mfragments.add(new ExhibitionFragment());
        mfragments.add(new InformationFragment());
        viewPager4.setAdapter(vp);
        viewPager4.setCurrentItem(0);
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
