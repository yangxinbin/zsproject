package com.mango.leo.zsproject.base;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.mango.leo.zsproject.utils.NetUtil;

/**
 * Created by Administrator on 2018/5/15 0015.
 */

public abstract class BaseActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //定制流程
        setActivityState(this);
        //initView(savedInstanceState);
        //initData();
    }

    //protected abstract void initData();

    //protected abstract void initView(Bundle savedInstanceState);

    /**
     * 设置屏幕只能竖屏
     * @param activity
     *                  activity
     */
    public void setActivityState(Activity activity) {
        activity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
    }

    /**
     * 检测网络是否连接
     */
    public boolean isNetConnect(){
        return NetUtil.isNetConnect(this); // NetUtil 是我自己封装的类
    }
}
