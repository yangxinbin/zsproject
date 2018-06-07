package com.mango.leo.zsproject.industrialservice.createrequirements.carditems.model;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import android.util.Log;

import com.mango.leo.zsproject.industrialservice.createrequirements.carditems.bean.CardFirstItemBean;
import com.mango.leo.zsproject.industrialservice.createrequirements.carditems.bean.CardFourthItemBean;
import com.mango.leo.zsproject.industrialservice.createrequirements.carditems.bean.CardSecondItemBean;
import com.mango.leo.zsproject.industrialservice.createrequirements.carditems.bean.CardThirdItemBean;
import com.mango.leo.zsproject.industrialservice.createrequirements.carditems.bean.ProjectBean;
import com.mango.leo.zsproject.industrialservice.createrequirements.carditems.listener.OnUpdateItemListener;
import com.mango.leo.zsproject.industrialservice.createrequirements.util.ProjectsJsonUtils;
import com.mango.leo.zsproject.login.bean.UserMessageBean;
import com.mango.leo.zsproject.utils.HttpUtils;
import com.mango.leo.zsproject.utils.Urls;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.io.PipedReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by admin on 2018/5/22.
 */

public class UpdateItemModelImpl implements UpdateItemModel {
    private SharedPreferences sharedPreferences;
    private static SharedPreferences.Editor editor;


    @Override
    public void visitUpdateItem(Context context, int type, Object o, String url, final OnUpdateItemListener listener) {
        //Class<?> o = (Class<?>)Class.forName(String.valueOf(c.getClass())).newInstance();
        sharedPreferences = context.getSharedPreferences("CIFIT", MODE_PRIVATE);
        editor = sharedPreferences.edit();
        Log.v("xxxxxxxx", "^^^^^vvvvvvv^^^^^" + sharedPreferences.getString("projectId", ""));
        final HashMap<String, String> mapParams = new HashMap<String, String>();
        if (o instanceof CardFirstItemBean) {
            mapParams.clear();
            CardFirstItemBean cardFirstItemBean = (CardFirstItemBean) o;
            //File[] files = new File[cardFirstItemBean.getItemImagePath().size()];
            if (TextUtils.isEmpty(sharedPreferences.getString("projectId", ""))) {
                Log.v("11111","____1___"+sharedPreferences.getString("token", ""));
                url = Urls.HOST_PROJECT;
                mapParams.put("name", cardFirstItemBean.getItemName());
                mapParams.put("organizerDepartment", cardFirstItemBean.getDepartmentName());
                mapParams.put("totalInvestmentRequired", cardFirstItemBean.getMoney());
                mapParams.put("Summary", cardFirstItemBean.getItemContent());
                mapParams.put("token", sharedPreferences.getString("token", ""));
                HttpUtils.doPost(url, mapParams, new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        Log.v("doPostAll", "^^^^^onFailure^^^^^");
                        listener.onFailure("SAVE FAILURE", e);
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        if (String.valueOf(response.code()).startsWith("2")) {
                            listener.onSuccess("SAVE SUCCESS");//异步请求
                            ProjectBean bean = ProjectsJsonUtils.readJsonProjectBeans(response.body().string());//data是json字段获得data的值即对象
                            Log.v("11111","____1_projectId_"+bean.getResponseObject().getId());
                            editor.putString("projectId",bean.getResponseObject().getId()).commit();
                        } else {
                            Log.v("doPostAll", response.body().string() + "^^else^^^onFailure^^^^^" + response.code());
                            listener.onSuccess("SAVE FAILURE");
                        }
                    }
                });
            } else {
                Log.v("11111","____2___");
                mapParams.put("projectId", sharedPreferences.getString("projectId", ""));
                mapParams.put("name", cardFirstItemBean.getItemName());
                mapParams.put("organizerDepartment", cardFirstItemBean.getDepartmentName());
                mapParams.put("totalInvestmentRequired", String.valueOf(cardFirstItemBean.getMoney()));
                mapParams.put("Summary", cardFirstItemBean.getItemContent());
                mapParams.put("token", sharedPreferences.getString("token", ""));
                HttpUtils.doPut(url, mapParams, new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        Log.v("doPostAll", "^^^^^onFailure^^^^^");
                        listener.onFailure("UPDATE FAILURE", e);
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        if (String.valueOf(response.code()).startsWith("2")) {
                            listener.onSuccess("UPDATE SUCCESS");//异步请求
                        } else {
                            Log.v("doPostAll", response.body().string() + "^^else^^^onFailure^^^^^" + response.code());
                            listener.onSuccess("UPDATE FAILURE");
                        }
                    }
                });
            }

        }
        if (o instanceof CardSecondItemBean) {
            CardSecondItemBean cardFirstItemBean = (CardSecondItemBean) o;
        }
        if (o instanceof CardThirdItemBean) {
            CardThirdItemBean cardThirdItemBean = (CardThirdItemBean) o;
            mapParams.clear();
            if (!TextUtils.isEmpty(sharedPreferences.getString("projectId", ""))) {
                mapParams.put("projectId", sharedPreferences.getString("projectId", ""));
                //mapParams.put("province", cardThirdItemBean.getProvince());
                //mapParams.put("city", cardThirdItemBean.getCity()); //null
                mapParams.put("address", cardThirdItemBean.getAddress());
                mapParams.put("lon", cardThirdItemBean.getLon());
                mapParams.put("lat", cardThirdItemBean.getLat());
            }
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
        if (type == 4) {
            final List<CardFourthItemBean> cardFourthItemBean = (List<CardFourthItemBean>) o;
            // final HashMap<String, String> mapParams = new HashMap<String, String>();
            mapParams.clear();
            if (!TextUtils.isEmpty(sharedPreferences.getString("projectId", ""))) {
                mapParams.put("projectId", sharedPreferences.getString("projectId", ""));
                mapParams.put("contactInfo", buildArrayJson(cardFourthItemBean));
            }
            HttpUtils.doPut(url, mapParams, new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    listener.onFailure("SAVE FAILURE", e);
                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    if (String.valueOf(response.code()).startsWith("2")) {
                        listener.onSuccess("SAVE SUCCESS");//异步请求
                    } else {
                        listener.onSuccess("SAVE FAILURE");
                    }
                }
            });
        }
    }

    public String buildArrayJson(List<CardFourthItemBean> cardFourthItemBean) {
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
                json.put(i, jsonObj);
                continue;
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        //把每个数据当作一对象添加到数组里
        return json.toString();
    }//

   /* public String buildObjectJson(CardThirdItemBean cardThirdItemBean) {
        JSONObject jsonObj = new JSONObject();//一定要new对象
        try {
            jsonObj.put("province", cardThirdItemBean.getProvince().toString());
            jsonObj.put("city", cardThirdItemBean.getCity().toString());
            jsonObj.put("address", cardThirdItemBean.getAddress().toString());
            jsonObj.put("lot", cardThirdItemBean.getLon().toString());
            jsonObj.put("lat", cardThirdItemBean.getLat().toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        //把每个数据当作一对象添加到数组里
        Log.v("doPutWithJson",  "^^cardThirdItemBean^^^^" + jsonObj.toString());
        return jsonObj.toString();
    }*/
}
