package com.mango.leo.zsproject.datacenter.show;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.mango.leo.zsproject.R;
import com.mango.leo.zsproject.datacenter.fragments.InvestorFragment;
import com.mango.leo.zsproject.datacenter.fragments.InvestorWithoutSelectFragment;
import com.mango.leo.zsproject.datacenter.fragments.ProjectFragment;
import com.mango.leo.zsproject.utils.ViewPageAdapter;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SearchDataActivity extends AppCompatActivity {

    @Bind(R.id.diary_et_search)
    EditText diaryEtSearch;
    @Bind(R.id.et_delete)
    ImageView etDelete;
    @Bind(R.id.state)
    TextView state;
    @Bind(R.id.clear)
    TextView clear;
    @Bind(R.id.history)
    ListView history;
    @Bind(R.id.tabLayout_s)
    TabLayout tabLayoutS;
    @Bind(R.id.viewPager_s)
    ViewPager viewPagerS;
    private ArrayList<String> mDatas;
    List<Fragment> mfragments = new ArrayList<Fragment>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_data);
        ButterKnife.bind(this);
        initDatas();
        init();
    }
    private void initDatas() {
        mDatas = new ArrayList<String>(Arrays.asList("投资方", "项目"));
    }

    private void init() {
        tabLayoutS.setTabMode(TabLayout.MODE_FIXED);
        ViewPageAdapter vp = new ViewPageAdapter(getSupportFragmentManager(), mfragments, mDatas);
        tabLayoutS.setupWithViewPager(viewPagerS);
        mfragments.add(new InvestorWithoutSelectFragment());
        mfragments.add(new ProjectFragment());
        viewPagerS.setAdapter(vp);
        viewPagerS.setCurrentItem(0);
    }
    @OnClick({R.id.et_delete, R.id.state, R.id.clear})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.et_delete:
                break;
            case R.id.state:
                break;
            case R.id.clear:
                break;
        }
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }
}
