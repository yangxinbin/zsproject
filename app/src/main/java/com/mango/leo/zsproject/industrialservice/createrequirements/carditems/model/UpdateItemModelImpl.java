package com.mango.leo.zsproject.industrialservice.createrequirements.carditems.model;

import android.content.Context;
import android.util.Log;

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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * Created by admin on 2018/5/22.
 */

public class UpdateItemModelImpl implements UpdateItemModel {

    @Override
    public void visitUpdateItem(Context context, int type, Object o, String url, final OnUpdateItemListener listener) {
        //Class<?> o = (Class<?>)Class.forName(String.valueOf(c.getClass())).newInstance();
        if (o instanceof CardFirstItemBean) {
            final HashMap<String, String> mapParams = new HashMap<String, String>();
            mapParams.clear();
            CardFirstItemBean cardFirstItemBean = (CardFirstItemBean) o;
            File[] files = new File[cardFirstItemBean.getItemImagePath().size()];
            Log.v("yyyyyyy", url + "********" + cardFirstItemBean.getProjectId() + "********" + cardFirstItemBean.getItemName() + "*********" + cardFirstItemBean.getItemContent());
            mapParams.put("projectId", "123");
            mapParams.put("name", cardFirstItemBean.getItemName());
            mapParams.put("description", cardFirstItemBean.getItemContent());
            for (int i = 0; i < cardFirstItemBean.getItemImagePath().size(); i++) {
                files[i] = new File(cardFirstItemBean.getItemImagePath().get(i).getPath());
                Log.v("yyyyy", files[i].getName() + "^^^^^getItemImagePath^^^^^" + cardFirstItemBean.getItemImagePath().get(i).getPath());
            }
            HttpUtils.doPostAll(url, mapParams, files, new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    Log.v("doPostAll", "^^^^^onFailure^^^^^");
                    listener.onFailure("SAVE FAILURE", e);
                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    if (String.valueOf(response.code()).startsWith("2")) {
                        listener.onSuccess("SAVE SUCCESS");//异步请求
                    } else {
                        Log.v("doPostAll", response.body().string() + "^^else^^^onFailure^^^^^" + response.code());
                        listener.onSuccess("SAVE FAILURE");
                    }
                }
            });
        }
        if (o instanceof CardSecondItemBean) {
            CardSecondItemBean cardFirstItemBean = (CardSecondItemBean) o;
        }

        if (o instanceof List) {
            final List<CardFourthItemBean> cardFourthItemBean = (List<CardFourthItemBean>) o;
            final HashMap<String, String> mapParams = new HashMap<String, String>();
            mapParams.clear();
            mapParams.put("projectId", "5afe83d0bc2ab975d270096d");
            mapParams.put("contactInfo", buildJson(cardFourthItemBean));
            //Log.v("doPutWithJson", "^^^^^buildJson^^^^^" + buildJson(cardFourthItemBean).toString());
            HttpUtils.doPut(url, mapParams, new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    Log.v("doPutWithJson", "^^^^^onFailure^^^^^");
                    listener.onFailure("SAVE FAILURE", e);
                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    if (String.valueOf(response.code()).startsWith("2")) {
                        listener.onSuccess("SAVE SUCCESS");//异步请求
                    } else {
                        Log.v("doPutWithJson", response.body().string() + "^^else^^^onFailure^^^^^" + response.code());
                        listener.onSuccess("SAVE FAILURE");
                    }
                }
            });
        }
    }

    public String buildJson(List<CardFourthItemBean> cardFourthItemBean) {
        JSONArray json = new JSONArray();
        try {
            for (int i = 0; i < cardFourthItemBean.size(); i++) {
                JSONObject jsonObj = new JSONObject();//一定要new对象
                jsonObj.put("username", cardFourthItemBean.get(i).getName().toString());
                jsonObj.put("department", cardFourthItemBean.get(i).getCompany().toString());
                jsonObj.put("position", cardFourthItemBean.get(i).getPosition().toString());
                jsonObj.put("mobile", cardFourthItemBean.get(i).getPhoneNumber().toString());
                jsonObj.put("phone", cardFourthItemBean.get(i).getPhoneNumber().toString());
                jsonObj.put("email", cardFourthItemBean.get(i).getEmail().toString());
                Log.v("doPutWithJson", cardFourthItemBean.get(i).getName() + "^^cardFourthItemBean.get(i).getName()^^^^" );
                json.put(i,jsonObj);
                continue;
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        //把每个数据当作一对象添加到数组里
        Log.v("doPutWithJson", cardFourthItemBean.size() + "^^cardFourthItemBean.size()^^^^"+json.toString());
        return json.toString();
    }//
}
