package com.mango.leo.zsproject.datacenter.view;

import com.mango.leo.zsproject.datacenter.bean.TouZiBean;
import com.mango.leo.zsproject.eventexhibition.bean.EventBean;
import com.mango.leo.zsproject.industrialservice.bean.MatchDataBean;
import com.mango.leo.zsproject.industrialservice.bean.MatchEventBean;

import java.util.List;

/**
 * Created by admin on 2018/5/21.
 */

public interface DataView {
    void addDataView(List<TouZiBean> touZiBeans);
    void addMatchDataView(List<MatchDataBean> matchDataBeans);
    void showDataFailMsg(String string);
}
