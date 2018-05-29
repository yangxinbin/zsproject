package com.mango.leo.zsproject;

import android.os.Bundle;

import com.mango.leo.zsproject.base.BaseActivity;
import com.mango.leo.zsproject.datacenter.show.FragmentOfDateCenter;
import com.mango.leo.zsproject.eventexhibition.show.FragmentOfEventExhibition;
import com.mango.leo.zsproject.industrialpanorama.show.FragmentOfIndustrialPanorama;
import com.mango.leo.zsproject.industrialservice.show.FragmentOfIndustrialService;
import com.mango.leo.zsproject.personalcenter.show.FragmentOfPersonalCenter;
import com.mango.leo.zsproject.viewutil.BottomBar;

public class ZsActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomBar bottomBar = (BottomBar) findViewById(R.id.bottom_bar);
        bottomBar.setContainer(R.id.fl_container)
                .setTitleBeforeAndAfterColor("#999999", "#ff5d5e")
                .addItem(FragmentOfIndustrialService.class,
                        "投恰服务",
                        R.drawable.tab_icon_first_normal,
                        R.drawable.tab_icon_first_highlight)
                .addItem(FragmentOfIndustrialPanorama.class,
                        "投恰全景",
                        R.drawable.tab_icon_second_normal,
                        R.drawable.tab_icon_second_highlight)
                .addItem(FragmentOfDateCenter.class,
                        "投恰数据库",
                        R.drawable.tab_icon_third_normal,
                        R.drawable.tab_icon_third_highlight)
                .addItem(FragmentOfEventExhibition.class,
                        "投恰展业",
                        R.drawable.tab_icon_fourth_normal,
                        R.drawable.tab_icon_fourth_highlight)
                .addItem(FragmentOfPersonalCenter.class,
                        "我的",
                        R.drawable.tab_icon_fifth_normal,
                        R.drawable.tab_icon_fifth_highlight)
                .build();
    }
}
