package com.mango.leo.zsproject.industrialservice.createrequirements.carditems.model;

import android.content.Context;
import android.util.Log;

import com.mango.leo.zsproject.industrialservice.createrequirements.carditems.CardFirstItemActivity;
import com.mango.leo.zsproject.industrialservice.createrequirements.carditems.bean.CardFirstItemBean;
import com.mango.leo.zsproject.industrialservice.createrequirements.carditems.bean.CardSecondItemBean;
import com.mango.leo.zsproject.industrialservice.createrequirements.carditems.listener.OnUpdateItemListener;
import com.mango.leo.zsproject.utils.HttpUtils;

import org.json.JSONArray;

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
        HashMap<String, Object> mapParams = new HashMap<String, Object>();
        if (o instanceof CardFirstItemBean) {
            CardFirstItemBean cardFirstItemBean = (CardFirstItemBean) o;
            Log.v("yyyyyyy", url + "********" + cardFirstItemBean.getProjectId() + "********" + cardFirstItemBean.getItemName() + "*********" + cardFirstItemBean.getItemContent());
            mapParams.put("projectId", cardFirstItemBean.getProjectId());
            mapParams.put("name", cardFirstItemBean.getItemName());
            mapParams.put("description", cardFirstItemBean.getItemContent());
            for (int i = 0; i < cardFirstItemBean.getItemImagePath().size(); i++) {
            }
            //mapParams.put("photos",cardFirstItemBean.getItemImagePath());
            Log.v("yyyyy", "^^^^^mapParams^^^^^" + mapParams.toString());
        }
        if (o instanceof CardSecondItemBean) {
            CardSecondItemBean cardFirstItemBean = (CardSecondItemBean) o;
        }


        //String s  = "{\"projectId\":\"5afe612abc2ab975d270096b\",\"name\":\"ckjhvb\",\"description\":\"fjvh\"}";


        HttpUtils.doPostAll(url, mapParams, new Callback() {
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
}
