package com.mango.leo.zsproject.eventexhibition.presenter;

import android.content.Context;

import com.mango.leo.zsproject.eventexhibition.bean.EventBean;
import com.mango.leo.zsproject.eventexhibition.bean.ShaiXuanEvent;
import com.mango.leo.zsproject.eventexhibition.listener.OnEventListener;
import com.mango.leo.zsproject.eventexhibition.model.EventModel;
import com.mango.leo.zsproject.eventexhibition.model.EventModelImpl;
import com.mango.leo.zsproject.eventexhibition.view.EventView;
import com.mango.leo.zsproject.utils.Urls;

import java.util.List;

/**
 * Created by admin on 2018/5/22.
 */

public class EventPresenterImpl implements EventPresenter, OnEventListener {

    private EventView eventView;
    private EventModel eventModel;

    public EventPresenterImpl(EventView u) {
        this.eventView = u;
        this.eventModel = new EventModelImpl();
    }

    @Override
    public void visitEvent(Context context, int type , int page, ShaiXuanEvent shaiXuanEvent) {
        //shaiXuanEvent.getTimePast()
        String url = getUrl(type)+"?page="+page+"&city="+shaiXuanEvent.getCity()+"&timePast="+shaiXuanEvent.getTimePast()+shaiXuanEvent.getCity()+"&city="+shaiXuanEvent.getCity();
        eventModel.visitEventItem(context,type,url,this);
    }

    @Override
    public void onSuccess(List<EventBean> eventBeans) {
        eventView.addEventsView(eventBeans);
    }

    @Override
    public void onFailure(String msg, Exception e) {
        eventView.showEventFailMsg(msg);
    }

    private String getUrl(int type) {
        StringBuffer sburl = new StringBuffer();
        switch (type) {
            case 1:
                sburl.append(Urls.HOST_EVENT);
                break;
            case 2:
                //sburl.append(Urls.HOST_PROJECT).append(Urls.UPDATE2);
                break;
            case 3:
               // sburl.append(Urls.HOST_PROJECT).append(Urls.UPDATE3);
                break;
        }
        return sburl.toString();
    }
}
