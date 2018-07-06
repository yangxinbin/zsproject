package com.mango.leo.zsproject.eventexhibition.presenter;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.mango.leo.zsproject.eventexhibition.bean.EventBean;
import com.mango.leo.zsproject.eventexhibition.bean.ShaiXuanEvent;
import com.mango.leo.zsproject.eventexhibition.listener.OnEventListener;
import com.mango.leo.zsproject.eventexhibition.model.EventModel;
import com.mango.leo.zsproject.eventexhibition.model.EventModelImpl;
import com.mango.leo.zsproject.eventexhibition.view.EventView;
import com.mango.leo.zsproject.industrialservice.bean.MatchEventBean;
import com.mango.leo.zsproject.utils.Urls;

import java.util.List;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by admin on 2018/5/22.
 */

public class EventPresenterImpl implements EventPresenter, OnEventListener {

    private EventView eventView;
    private EventModel eventModel;
    private SharedPreferences sharedPreferences;

    public EventPresenterImpl(EventView u) {
        this.eventView = u;
        this.eventModel = new EventModelImpl();
    }

    @Override
    public void visitEvent(Context context, int type, int page, ShaiXuanEvent shaiXuanEvent) {
        sharedPreferences = context.getSharedPreferences("CIFIT", MODE_PRIVATE);
        String url = null;
        if (type == 1) {
            if (shaiXuanEvent.getTimeFuture() == null && shaiXuanEvent.getTypePay() == null && shaiXuanEvent.getTimeFuture() == null && shaiXuanEvent.getCity() == null) {
                url = getUrl(type) + "?page=" + page;
            } else {
                url = getUrl(type) + "?page=" + page + "&city=" + shaiXuanEvent.getCity() + "&priceType=" + shaiXuanEvent.getTypePay() + "&timePast=" + shaiXuanEvent.getTimePast() + "&timeFuture=" + shaiXuanEvent.getTimeFuture();
            }
        } else if (type == 4) {
            url = getUrl(type) + "?page=" + page + "&id=" + sharedPreferences.getString("match_id", "");
        }
        Log.v("yyyyy", "----" + url);
        eventModel.visitEventItem(context, type, url, this);
    }

    @Override
    public void onSuccess(List<EventBean> eventBeans) {
        eventView.addEventsView(eventBeans);
    }

    @Override
    public void onMatchSuccess(List<MatchEventBean> eventBean) {
        eventView.addMatchEventsView(eventBean);
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
                break;
            case 3:
                break;
            case 4:
                sburl.append(Urls.HOST_MATCH_EVENT);
                break;
        }
        return sburl.toString();
    }
}
