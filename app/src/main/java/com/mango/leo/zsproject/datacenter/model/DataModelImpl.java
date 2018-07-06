package com.mango.leo.zsproject.datacenter.model;

import android.content.Context;
import android.util.Log;

import com.mango.leo.zsproject.datacenter.bean.TouZiBean;
import com.mango.leo.zsproject.datacenter.listener.OnDataListener;
import com.mango.leo.zsproject.eventexhibition.bean.EventBean;
import com.mango.leo.zsproject.eventexhibition.listener.OnEventListener;
import com.mango.leo.zsproject.industrialservice.bean.MatchDataBean;
import com.mango.leo.zsproject.industrialservice.bean.MatchEventBean;
import com.mango.leo.zsproject.industrialservice.createrequirements.util.ProjectsJsonUtils;
import com.mango.leo.zsproject.utils.HttpUtils;

import java.io.IOException;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * Created by admin on 2018/5/22.
 */

public class DataModelImpl implements DataModel {

    @Override
    public void visitDataItem(Context context, final int type, final String url, final OnDataListener listener) {
        Log.v("eeeee", "======url======" + url);
        new Thread(new Runnable() {
            @Override
            public void run() {
                if (type == 1) {
                    HttpUtils.doGet(url, new Callback() {
                        @Override
                        public void onFailure(Call call, IOException e) {
                            listener.onFailure("FAILURE", e);
                        }

                        @Override
                        public void onResponse(Call call, Response response) throws IOException {
                            try {
                                List<TouZiBean> beanList = ProjectsJsonUtils.readJsonTouZiBean(response.body().string(), "content");//data是json字段获得data的值即对象数组
                                listener.onSuccess(beanList);
                            } catch (Exception e) {
                                listener.onFailure("FAILURE", e);
//                    Log.e("eeeee", response.body().string()+"Exception = " + e);
                            }
                        }
                    });
                } else if (type == 3) {
                    HttpUtils.doGet(url, new Callback() {
                        @Override
                        public void onFailure(Call call, IOException e) {
                            listener.onFailure("FAILURE", e);
                        }

                        @Override
                        public void onResponse(Call call, Response response) throws IOException {
                            try {
                                List<MatchDataBean> beanList = ProjectsJsonUtils.readJsonMatchDataBean(response.body().string(), "content");//data是json字段获得data的值即对象数组
                                listener.onMatchSuccess(beanList);
                            } catch (Exception e) {
                                listener.onFailure("FAILURE", e);
//                    Log.e("eeeee", response.body().string()+"Exception = " + e);
                            }
                        }
                    });
                }
            }
        }).start();
    }
}
