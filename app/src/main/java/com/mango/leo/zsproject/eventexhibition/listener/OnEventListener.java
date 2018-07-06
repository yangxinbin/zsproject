package com.mango.leo.zsproject.eventexhibition.listener;

import com.mango.leo.zsproject.eventexhibition.bean.EventBean;
import com.mango.leo.zsproject.industrialservice.bean.MatchEventBean;

import java.util.List;

/**
 * Created by admin on 2018/5/22.
 */

public interface OnEventListener {
    void onSuccess(List<EventBean> eventBean);
    void onMatchSuccess(List<MatchEventBean> eventBean);
    void onFailure(String msg, Exception e);
}
