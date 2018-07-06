package com.mango.leo.zsproject.datacenter.listener;

import com.mango.leo.zsproject.datacenter.bean.TouZiBean;
import com.mango.leo.zsproject.eventexhibition.bean.EventBean;
import com.mango.leo.zsproject.industrialservice.bean.MatchDataBean;
import com.mango.leo.zsproject.industrialservice.bean.MatchEventBean;

import java.util.List;

/**
 * Created by admin on 2018/5/22.
 */

public interface OnDataListener {
    void onSuccess(List<TouZiBean> touZiBeans);
    void onMatchSuccess(List<MatchDataBean> matchDataBeans);
    void onFailure(String msg, Exception e);
}
