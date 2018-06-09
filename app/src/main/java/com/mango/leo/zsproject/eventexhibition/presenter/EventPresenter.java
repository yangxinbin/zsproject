package com.mango.leo.zsproject.eventexhibition.presenter;

import android.content.Context;

import com.mango.leo.zsproject.eventexhibition.bean.ShaiXuanEvent;

/**
 * Created by admin on 2018/5/22.
 */

public interface EventPresenter {
    void visitEvent(Context context, int type, int page, ShaiXuanEvent shaiXuanEvent);
}
