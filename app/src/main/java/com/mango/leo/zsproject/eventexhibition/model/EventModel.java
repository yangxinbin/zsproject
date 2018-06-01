package com.mango.leo.zsproject.eventexhibition.model;

import android.content.Context;

import com.mango.leo.zsproject.eventexhibition.listener.OnEventListener;
import com.mango.leo.zsproject.industrialservice.createrequirements.carditems.listener.OnUpdateItemListener;

/**
 * Created by admin on 2018/5/22.
 */

public interface EventModel {
    void visitEventItem(Context context, int type, String url, OnEventListener listener);
}
