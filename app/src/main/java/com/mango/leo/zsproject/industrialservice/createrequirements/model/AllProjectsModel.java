package com.mango.leo.zsproject.industrialservice.createrequirements.model;

import android.content.Context;

import com.mango.leo.zsproject.industrialservice.createrequirements.listener.OnAllProjectsListener;

/**
 * Created by admin on 2018/5/21.
 */

public interface AllProjectsModel {
    void visitProjects(Context context, int type, String url, OnAllProjectsListener listener);//刷新动作加载新闻数据
}
