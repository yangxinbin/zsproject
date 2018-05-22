package com.mango.leo.zsproject.industrialservice.createrequirements.carditems.model;

import android.content.Context;

import com.mango.leo.zsproject.industrialservice.createrequirements.carditems.listener.OnUpdateItemListener;

/**
 * Created by admin on 2018/5/22.
 */

public interface UpdateItemModel {
    void visitUpdateItem(Context context , int type, Object o, String url, OnUpdateItemListener listener);
}
