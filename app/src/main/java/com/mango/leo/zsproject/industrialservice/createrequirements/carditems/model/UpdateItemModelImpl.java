package com.mango.leo.zsproject.industrialservice.createrequirements.carditems.model;

import android.content.Context;
import android.util.Log;

import com.mango.leo.zsproject.industrialservice.createrequirements.carditems.CardFirstItemActivity;
import com.mango.leo.zsproject.industrialservice.createrequirements.carditems.bean.CardFirstItemBean;
import com.mango.leo.zsproject.industrialservice.createrequirements.carditems.bean.CardFourthItemBean;
import com.mango.leo.zsproject.industrialservice.createrequirements.carditems.bean.CardSecondItemBean;
import com.mango.leo.zsproject.industrialservice.createrequirements.carditems.listener.OnUpdateItemListener;
import com.mango.leo.zsproject.utils.HttpUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by admin on 2018/5/22.
 */

public class UpdateItemModelImpl implements UpdateItemModel {

    @Override
    public void visitUpdateItem(Context context, int type, Object o, String url, final OnUpdateItemListener listener) {
        //Class<?> o = (Class<?>)Class.forName(String.valueOf(c.getClass())).newInstance();
        HashMap<String, String> mapParams = new HashMap<String, String>();
        if (o instanceof CardFirstItemBean) {
            CardFirstItemBean cardFirstItemBean = (CardFirstItemBean) o;
            File[] files = new File[cardFirstItemBean.getItemImagePath().size()];
            Log.v("yyyyyyy", url + "********" + cardFirstItemBean.getProjectId() + "********" + cardFirstItemBean.getItemName() + "*********" + cardFirstItemBean.getItemContent());
            mapParams.put("projectId", cardFirstItemBean.getProjectId());
            mapParams.put("name", cardFirstItemBean.getItemName());
            mapParams.put("description", cardFirstItemBean.getItemContent());
            for (int i = 0; i < cardFirstItemBean.getItemImagePath().size(); i++) {
                files[i] = new File(cardFirstItemBean.getItemImagePath().get(i).getPath());
                Log.v("yyyyy", files[i].getName() + "^^^^^getItemImagePath^^^^^" + cardFirstItemBean.getItemImagePath().get(i).getPath());
            }
            HttpUtils.doPostAll(url, mapParams, files, new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    Log.v("yyyyy", "^^^^^onFailure^^^^^");
                    listener.onFailure("FAILURE", e);
                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    if (response.code() == 200) {
                        listener.onSuccess("SUCCESS");//异步请求
                    } else {
                        Log.v("yyyyy", "^^else^^^onFailure^^^^^" + response.code());
                        listener.onSuccess("FAILURE");
                    }
                }
            });
        }
        if (o instanceof CardSecondItemBean) {
            CardSecondItemBean cardFirstItemBean = (CardSecondItemBean) o;
        }

        if (o instanceof CardFourthItemBean) {
            final CardFourthItemBean cardFourthItemBean = (CardFourthItemBean) o;
            /*OkHttpClient okHttpClient = new OkHttpClient();
            Log.v("put",url+"**********"+buildJson(cardFourthItemBean));
            FormBody build = new FormBody.Builder()
                    .add("projectId", "123")
                    .add("contactInfo", buildJson(cardFourthItemBean))
                    .build();
            String format = String.format("http://192.168.1.147:9999/project-service/project/govt/contacts?projectId=%s&contactInfo=%s","123",buildJson(cardFourthItemBean));
            Request build1 = new Request.Builder()
                    .url(format)
                    .put(build)
                    .build();
            okHttpClient.newCall(build1).enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    Log.v("put", "^^^^^onFailure^^^^^");
                    listener.onFailure("FAILURE", e);
                }
                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    if (response.code() == 200) {
                        listener.onSuccess("SUCCESS");//异步请求
                    } else {
                        Log.v("put", "^^else^^^onFailure^^^^^" + response.code());
                        listener.onSuccess("FAILURE");
                    }
                }
            });*/
            mapParams.put("projectId", "5afe83d0bc2ab975d270096d");
            mapParams.put("contactInfo",buildJson(cardFourthItemBean));
            HttpUtils.doPut(url, mapParams, new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    Log.v("put", "^^^^^onFailure^^^^^");
                    listener.onFailure("FAILURE", e);
                }
                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    if (response.code() == 200) {
                        listener.onSuccess("SUCCESS");//异步请求
                    } else {
                        Log.v("put", response.body().string()+"^^else^^^onFailure^^^^^" + response.code());
                        listener.onSuccess("FAILURE");
                    }
                }
            });
        }
        //String s  = "{\"projectId\":\"5afe612abc2ab975d270096b\",\"name\":\"ckjhvb\",\"description\":\"fjvh\"}";
    }
    public String buildJson(CardFourthItemBean cardFourthItemBean){
        JSONArray json=new JSONArray();
        JSONObject jsonObj=new JSONObject();
        try {
            jsonObj.put("username", cardFourthItemBean.getName().toString());
            jsonObj.put("department", cardFourthItemBean.getCompany().toString());
            jsonObj.put("position", cardFourthItemBean.getPosition().toString());
            jsonObj.put("mobile", cardFourthItemBean.getPhoneNumber().toString());
            jsonObj.put("phone", cardFourthItemBean.getPhoneNumber().toString());
            jsonObj.put("email", cardFourthItemBean.getEmail().toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        //把每个数据当作一对象添加到数组里
        json.put(jsonObj);
        return json.toString();
    }
}
