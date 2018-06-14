package com.mango.leo.zsproject.eventexhibition.model;

import android.content.Context;
import android.util.Log;

import com.mango.leo.zsproject.eventexhibition.bean.EventBean;
import com.mango.leo.zsproject.eventexhibition.listener.OnEventListener;
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

public class EventModelImpl implements EventModel {

    @Override
    public void visitEventItem(Context context, int type, String url, final OnEventListener listener) {
        Log.v("eeeee", "======url======" + url);

        HttpUtils.doGet(url, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                listener.onFailure("FAILURE", e);
            }
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                try {
                    List<EventBean> beanList = ProjectsJsonUtils.readJsonEventBeans(response.body().string(), "content");//data是json字段获得data的值即对象数组
                    listener.onSuccess(beanList);
                } catch (Exception e) {
                    listener.onFailure("FAILURE", e);
//                    Log.e("eeeee", response.body().string()+"Exception = " + e);
                }
            }
        });

    }
}
