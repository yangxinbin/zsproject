package com.mango.leo.zsproject.eventexhibition.view;

import com.mango.leo.zsproject.eventexhibition.bean.EventBean;

import java.util.List;

/**
 * Created by admin on 2018/5/21.
 */

public interface EventView {
    void addEventsView(List<EventBean> eventBeans);
    void showEventFailMsg(String string);
}
