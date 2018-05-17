package com.mango.leo.zsproject.industrialservice.createrequirements.carditems.basecard;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;

import com.mango.leo.zsproject.industrialservice.createrequirements.BusinessPlanActivity;
import com.mango.leo.zsproject.utils.NetUtil;

/**
 * Created by Administrator on 2018/5/15 0015.
 */

public abstract class BaseCardActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //定制流程
        setActivityState(this);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK)) {
            Intent intent = new Intent(this, BusinessPlanActivity.class);
            startActivity(intent);
            finish();        }
        return super.onKeyDown(keyCode, event);
    }

    /**
     * 设置屏幕只能竖屏
     *
     * @param activity activity
     */
    public void setActivityState(Activity activity) {
        activity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
    }

    /**
     * 检测网络是否连接
     */
    public boolean isNetConnect() {
        return NetUtil.isNetConnect(this); // NetUtil 是我自己封装的类
    }
}
