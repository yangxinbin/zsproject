package com.mango.leo.zsproject.datacenter.model;

import android.content.Context;

import com.mango.leo.zsproject.datacenter.listener.OnDataListener;
import com.mango.leo.zsproject.eventexhibition.listener.OnEventListener;

/**
 * Created by admin on 2018/5/22.
 */

public interface DataModel {
    void visitDataItem(Context context, int type, String url, OnDataListener listener);
}
