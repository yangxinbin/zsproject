package com.mango.leo.zsproject.personalcenter.show.shenbao.model;

import android.content.Context;

import com.mango.leo.zsproject.personalcenter.show.shenbao.listener.OnShenBaoListener;

/**
 * Created by admin on 2018/5/21.
 */

public interface ShenBaoModel {
    void visitProjects(Context context, int type, String url, OnShenBaoListener listener);//刷新动作加载新闻数据
}
