package com.mango.leo.zsproject.datacenter.presenter;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.mango.leo.zsproject.datacenter.bean.ShaiXuanData;
import com.mango.leo.zsproject.datacenter.bean.TouZiBean;
import com.mango.leo.zsproject.datacenter.listener.OnDataListener;
import com.mango.leo.zsproject.datacenter.model.DataModel;
import com.mango.leo.zsproject.datacenter.model.DataModelImpl;
import com.mango.leo.zsproject.datacenter.view.DataView;
import com.mango.leo.zsproject.industrialservice.bean.MatchDataBean;
import com.mango.leo.zsproject.utils.Urls;

import java.util.List;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by admin on 2018/5/22.
 */

public class DataPresenterImpl implements DataPresenter, OnDataListener {

    private DataView dataView;
    private DataModel dataModel;
    private SharedPreferences sharedPreferences;

    public DataPresenterImpl(DataView u) {
        this.dataView = u;
        this.dataModel = new DataModelImpl();
    }

    @Override
    public void visitData(Context context, int type, int page, ShaiXuanData shaiXuanData) {
        sharedPreferences = context.getSharedPreferences("CIFIT", MODE_PRIVATE);
        String url = null;
        if (type == 1) {
            /*if (shaiXuanData.getTimeFuture() == null && shaiXuanData.getTypePay() == null && shaiXuanData.getTimeFuture() == null && shaiXuanData.getCity() == null) {
                url = getUrl(type) + "?page=" + page;
            } else {
                url = getUrl(type) + "?page=" + page + "&city=" + shaiXuanData.getCity() + "&priceType=" + shaiXuanData.getTypePay() + "&timePast=" + shaiXuanData.getTimePast() + "&timeFuture=" + shaiXuanData.getTimeFuture();
            }*/
        } else if (type == 3) {
            url = getUrl(type) + "?page=" + page + "&id=" + sharedPreferences.getString("match_id", "");
        }
        Log.v("yyyyy", "----" + url);
        dataModel.visitDataItem(context, type, url, this);
    }

    @Override
    public void onSuccess(List<TouZiBean> touZiBeans) {
        dataView.addDataView(touZiBeans);
    }

    @Override
    public void onMatchSuccess(List<MatchDataBean> matchDataBeans) {
        dataView.addMatchDataView(matchDataBeans);
    }

    @Override
    public void onFailure(String msg, Exception e) {
        dataView.showDataFailMsg(msg);
    }

    private String getUrl(int type) {
        StringBuffer sburl = new StringBuffer();
        switch (type) {
            case 1:
                sburl.append(Urls.HOST_INVESTOR_FILTER);
                break;
            case 2:
                break;
            case 3:
                sburl.append(Urls.HOST_MATCH_DATA);
                break;
        }
        return sburl.toString();
    }
}
