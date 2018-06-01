package com.mango.leo.zsproject.eventexhibition.listener;

import com.mango.leo.zsproject.eventexhibition.bean.EventBean;

import java.util.List;

/**
 * Created by admin on 2018/5/22.
 */

public interface OnEventListener {
    void onSuccess(List<EventBean> eventBean);
    void onFailure(String msg, Exception e);
}
