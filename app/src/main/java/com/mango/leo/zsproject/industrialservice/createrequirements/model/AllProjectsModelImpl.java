package com.mango.leo.zsproject.industrialservice.createrequirements.model;

import android.app.ActivityManager;
import android.content.Context;
import android.util.Log;

import com.mango.leo.zsproject.industrialservice.createrequirements.bean.AllProjectsBean;
import com.mango.leo.zsproject.industrialservice.createrequirements.listener.OnAllProjectsListener;
import com.mango.leo.zsproject.industrialservice.createrequirements.util.ProjectsJsonUtils;
import com.mango.leo.zsproject.utils.HttpUtils;

import java.io.IOException;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * Created by admin on 2018/5/21.
 */

public class AllProjectsModelImpl implements AllProjectsModel {
    @Override
    public void visitProjects(final Context context, int type, String url, final OnAllProjectsListener listener) {
        //Log.v("yyyyyyyyy","*****AllProjectsModelImpl******"+url);

/*        final OkHttpUtils.ResultCallback<String> loadNewsCallback = new OkHttpUtils.ResultCallback<String>() {
            @Override
            public void onSuccess(String response) {
                Log.v("yyyyyyyyy",getCurProcessName(context)+"*****onResponse******"+response);
                List<AllProjectsBean> beanList = ProjectsJsonUtils.readJsonNewsBeans(response,"responseObject");//data是json字段获得data的值即对象数组
                listener.onSuccess(beanList);
            }

            @Override
            public void onFailure(Exception e) {
                listener.onFailure("FAILURE", e);
            }
        };
        OkHttpUtils.get(url, loadNewsCallback);*/
        HttpUtils.doGet(url, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                listener.onFailure("FAILURE", e);
            }
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                try {
               // Log.v("yyyyyyyyy",getCurProcessName(context)+"*****onResponse******"+response.body().string());
                //response.body().string() 只能用一次  java.lang.IllegalStateException异常, 该异常表示，当前对客户端的响应已经结束，不能在响应已经结束（或说消亡）后再向客户端（实际上是缓冲区）输出任何内容。
                List<AllProjectsBean> beanList = ProjectsJsonUtils.readJsonNewsBeans(response.body().string(),"responseObject");//data是json字段获得data的值即对象数组
                listener.onSuccess(beanList);
                } catch (Exception e) {
                    Log.e("yyyyy", "Exception = " + e);
                }
            }
        });
    }
}
