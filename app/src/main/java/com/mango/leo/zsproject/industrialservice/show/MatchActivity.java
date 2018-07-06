package com.mango.leo.zsproject.industrialservice.show;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.widget.ImageView;
import android.widget.TextView;

import com.mango.leo.zsproject.R;
import com.mango.leo.zsproject.datacenter.fragments.InvestorFragment;
import com.mango.leo.zsproject.datacenter.fragments.ProjectFragment;
import com.mango.leo.zsproject.eventexhibition.fragments.CampaignFragment;
import com.mango.leo.zsproject.eventexhibition.fragments.ExhibitionFragment;
import com.mango.leo.zsproject.eventexhibition.fragments.InformationFragment;
import com.mango.leo.zsproject.industrialservice.fragments.MatchCampaignFragment;
import com.mango.leo.zsproject.industrialservice.fragments.MatchInvestorFragment;
import com.mango.leo.zsproject.industrialservice.fragments.MatchProjectFragment;
import com.mango.leo.zsproject.utils.ViewPageAdapter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MatchActivity extends AppCompatActivity {

    @Bind(R.id.textView_name)
    TextView textViewName;
    @Bind(R.id.imageView_back)
    ImageView imageViewBack;
    @Bind(R.id.tabLayout)
    TabLayout tabLayout;
    @Bind(R.id.viewPager)
    ViewPager viewPager;
    private ArrayList<String> mDatas;
    List<Fragment> mfragments = new ArrayList<Fragment>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_match);
        ButterKnife.bind(this);
        initDatas();
        init();
    }
    private void initDatas() {
        mDatas = new ArrayList<String>(Arrays.asList("    投资方    ", "    项目    ","    活动    "));
    }
    private void init() {
        tabLayout.setTabMode(TabLayout.MODE_FIXED);
        ViewPageAdapter vp = new ViewPageAdapter(getSupportFragmentManager(), mfragments, mDatas);
        tabLayout.setupWithViewPager(viewPager);
        mfragments.add(new MatchInvestorFragment());
        mfragments.add(new MatchProjectFragment());
        mfragments.add(new MatchCampaignFragment());
        viewPager.setAdapter(vp);
        viewPager.setCurrentItem(0);
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }
    @OnClick(R.id.imageView_back)
    public void onViewClicked() {
        finish();
    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK)) {
            finish();
        }
        return super.onKeyDown(keyCode, event);
    }

}
