package com.mango.leo.zsproject.industrialservice.createrequirements.carditems.model;

import android.content.Context;
import android.util.Log;

import com.mango.leo.zsproject.industrialservice.createrequirements.carditems.bean.CardFirstItemBean;
import com.mango.leo.zsproject.industrialservice.createrequirements.carditems.bean.CardFourthItemBean;
import com.mango.leo.zsproject.industrialservice.createrequirements.carditems.bean.CardSecondItemBean;
import com.mango.leo.zsproject.industrialservice.createrequirements.carditems.listener.OnUpdateItemListener;
import com.mango.leo.zsproject.utils.HttpUtils;
import com.mango.leo.zsproject.utils.NetUtil;
import com.mango.leo.zsproject.utils.netutil.Network;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

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

        if (o instanceof CardFourthItemBean) {
            final CardFourthItemBean cardFourthItemBean = (CardFourthItemBean) o;
            /*OkHttpClient okHttpClient = new OkHttpClient();
            Log.v("put",url+"**********"+buildJson(cardFourthItemBean));
            FormBody build = new FormBody.Builder()
                    .add("projectId", "5afe83d0bc2ab975d270096d")
                    //.add("contactInfo","http://192.168.1.166:9999/project-service/project/govt/utilityFee?projectId=5afe83d0bc2ab975d270096d&contactInfo=[{\"username\":\"王小明\",\"department\":\"阿里巴巴\",\"position\":\"经理\",\"mobile\":\"18315536307\",\"phone\":\"18315536307\",\"email\":\"xxxx@hotmail.com\"}]")
                    .add("waterFeeInfo","100")
                    .add("electricityFeeInfo","100")
                    .add("gasFeeInfo","100")
                    .add("landFeeInfo","100")
                    .build();
            String format = String.format("http://192.168.1.147:9999/project-service/project/govt/contacts?projectId=%s&contactInfo=%s","5afe83d0bc2ab975d270096d",buildJson(cardFourthItemBean));
            Request build1 = new Request.Builder()
                    .url("http://192.168.1.166:9999/project-service/project/govt/utilityFee?projectId=5afe83d0bc2ab975d270096d&waterFeeInfo=100&electricityFeeInfo=101&gasFeeInfo=102.3&landFeeInfo=103.2")
                    .put(build)
                    .build();
            okHttpClient.newCall(build1).enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    Log.v("put", "^^^^^onFailure^^^^^");
                    listener.onFailure("SAVE FAILURE", e);
                }
                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    if (String.valueOf(response.code()).startsWith("2")) {
                        listener.onSuccess("SAVE SUCCESS");//异步请求
                    } else {
                        Log.v("put", response.body().string()+"^^else^^^onFailure^^^^^" + response.code());
                        listener.onSuccess("SAVE FAILURE");
                    }
                }
            });*/
//            mapParams.put("waterFeeInfo","100");
//            mapParams.put("electricityFeeInfo","100");
//            mapParams.put("gasFeeInfo","100");
//            mapParams.put("landFeeInfo","100");
            final HashMap<String, String> mapParams = new HashMap<String, String>();
            mapParams.clear();
            mapParams.put("projectId", "5afe83d0bc2ab975d270096d");
            mapParams.put("contactInfo", buildJson(cardFourthItemBean).toString());
            Log.v("network", "^^^^^params^^^^^"+buildJson(cardFourthItemBean));


            // mapParams.put("contactInfo", "[{\"username\":\"王小明\",\"department\":\"阿里巴巴\",\"position\":\"经理\",\"mobile\":\"18315536307\",\"phone\":\"18315536307\",\"email\":\"xxxx@hotmail.com\"}]");
            String[] jsons = new String[1];
            jsons[0] = buildJson(cardFourthItemBean).toString();
            Log.v("doPutWithJson", "^^^^^json^^^^^" + jsons[0]);
            Map<String, String> map = new HashMap<>();
            HttpUtils.doPut("http://192.168.1.166:9999/project-service/project/govt/contacts?projectId=5afe83d0bc2ab975d270096d&contactInfo=%5B%7B%22username%22:%22%E7%8E%8B%E5%B0%8F%E6%98%8E%22,%22department%22:%22%E9%98%BF%E9%87%8C%E5%B7%B4%E5%B7%B4%22,%22position%22:%22%E7%BB%8F%E7%90%86%22,%22mobile%22:%2218315536307%22,%22phone%22:%2218315536307%22,%22email%22:%22xxxx@hotmail.com%22%7D%5D", map, new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    Log.v("doPutWithJson", "^^^^^onFailure^^^^^");
                    listener.onFailure("SAVE FAILURE", e);
                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    if (String.valueOf(response.code()).startsWith("2")) {
                        Log.v("doPutWithJson", response.body().string() + "^^else^^^onFailure^^^^^" + response.code());
                        listener.onSuccess("SAVE SUCCESS");//异步请求
                    } else {
                        Log.v("doPutWithJson", response.body().string() + "^^else^^^onFailure^^^^^" + response.code());
                        listener.onSuccess("SAVE FAILURE");
                    }
                }
            });
            /*Network network = new Network(context,url);
            network.performPost(mapParams, new Network.NetCallBack() {
                @Override
                public void onNetFailure() {

                }

                @Override
                public void onSuccess(String result) {

                }

                @Override
                public void onError(String result) {

                }
            });*/
        }
    }

    public JSONArray buildJson(CardFourthItemBean cardFourthItemBean) {
        JSONArray json = new JSONArray();
        JSONObject jsonObj = new JSONObject();
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
        return json;
    }
}
