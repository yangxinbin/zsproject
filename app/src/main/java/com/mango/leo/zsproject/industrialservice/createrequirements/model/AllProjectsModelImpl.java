package com.mango.leo.zsproject.industrialservice.createrequirements.model;

import android.app.ActivityManager;
import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import com.mango.leo.zsproject.industrialservice.createrequirements.bean.AllProjectsBean;
import com.mango.leo.zsproject.industrialservice.createrequirements.listener.OnAllProjectsListener;
import com.mango.leo.zsproject.industrialservice.createrequirements.util.ProjectsJsonUtils;
import com.mango.leo.zsproject.utils.HttpUtils;
import com.mango.leo.zsproject.utils.OkHttpUtils;

import java.io.IOException;
import java.util.ArrayList;
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
                Log.v("yyyyyyyyy",getCurProcessName(context)+"*****onResponse******"+response.body().string());
                List<AllProjectsBean> beanList = ProjectsJsonUtils.readJsonNewsBeans(response.body().string(),"responseObject");//data是json字段获得data的值即对象数组
                Log.v("yyyyyyyyy","***b**onResponse******"+beanList.toString());
                listener.onSuccess(beanList);
                } catch (Exception e) {
                    Log.e("yyyyy", "Exception = " + e);
                }
            }
        });
    }
    public static String getCurProcessName(Context context) {
        int pid = android.os.Process.myPid();
        ActivityManager mActivityManager = (ActivityManager) context
                .getSystemService(Context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningAppProcessInfo appProcess : mActivityManager
                .getRunningAppProcesses()) {
            if (appProcess.pid == pid) {
                return appProcess.processName;
            }
        }
        return null;
    }
}
