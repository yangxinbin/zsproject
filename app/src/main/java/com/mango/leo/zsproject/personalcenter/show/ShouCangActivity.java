package com.mango.leo.zsproject.personalcenter.show;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.mango.leo.zsproject.R;
import com.mango.leo.zsproject.industrialpanorama.fragments.CityIntroductionFragment;
import com.mango.leo.zsproject.industrialpanorama.fragments.InvestmentInformationFragment;
import com.mango.leo.zsproject.personalcenter.fragments.ShouCang1Fragment;
import com.mango.leo.zsproject.personalcenter.fragments.ShouCang2Fragment;
import com.mango.leo.zsproject.personalcenter.fragments.ShouCang3Fragment;
import com.mango.leo.zsproject.utils.ViewPageAdapter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ShouCangActivity extends AppCompatActivity {

    @Bind(R.id.imageView_b)
    ImageView imageViewB;
    @Bind(R.id.viewPager2)
    ViewPager viewPager2;
    @Bind(R.id.tabLayout2)
    TabLayout tabLayout2;
    List<Fragment> mfragments = new ArrayList<Fragment>();
    private List<String> mDatas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shou_cang);
        ButterKnife.bind(this);
        initDatas();
        init();
    }

    private void initDatas() {
        mDatas = new ArrayList<String>(Arrays.asList("投资方", "项目", "活动"/*, "定制需求"*/));
    }

    private void init() {
        tabLayout2.setTabMode(TabLayout.MODE_FIXED);
        ViewPageAdapter vp = new ViewPageAdapter(getSupportFragmentManager(), mfragments, mDatas);
        tabLayout2.setupWithViewPager(viewPager2);
        mfragments.add(new ShouCang1Fragment());
        mfragments.add(new ShouCang2Fragment());
        mfragments.add(new ShouCang3Fragment());
        // mfragments.add(new CustomRequirementsFragment());
        viewPager2.setAdapter(vp);
        viewPager2.setCurrentItem(0);
    }

    @OnClick(R.id.imageView_b)
    public void onViewClicked() {
        finish();
    }
}
