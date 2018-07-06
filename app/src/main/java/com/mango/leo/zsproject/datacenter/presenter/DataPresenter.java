package com.mango.leo.zsproject.datacenter.presenter;

import android.content.Context;

import com.mango.leo.zsproject.datacenter.bean.ShaiXuanData;
import com.mango.leo.zsproject.eventexhibition.bean.ShaiXuanEvent;

/**
 * Created by admin on 2018/5/22.
 */

public interface DataPresenter {
    void visitData(Context context, int type, int page, ShaiXuanData shaiXuanData);
}
