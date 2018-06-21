package com.mango.leo.zsproject.industrialservice.createrequirements.carditems.model;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import android.util.Log;

import com.mango.leo.zsproject.industrialservice.createrequirements.carditems.bean.CardFirstItemBean;
import com.mango.leo.zsproject.industrialservice.createrequirements.carditems.bean.CardFourthItemBean;
import com.mango.leo.zsproject.industrialservice.createrequirements.carditems.bean.CardNinthItemBean;
import com.mango.leo.zsproject.industrialservice.createrequirements.carditems.bean.CardSecondItemBeanObj;
import com.mango.leo.zsproject.industrialservice.createrequirements.carditems.bean.CardThirdItemBean;
import com.mango.leo.zsproject.industrialservice.createrequirements.carditems.bean.ProjectBean;
import com.mango.leo.zsproject.industrialservice.createrequirements.carditems.listener.OnUpdateItemListener;
import com.mango.leo.zsproject.industrialservice.createrequirements.util.ProjectsJsonUtils;
import com.mango.leo.zsproject.utils.HttpUtils;
import com.mango.leo.zsproject.utils.Urls;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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
        if (type == 1) {
            mapParams.clear();
            CardFirstItemBean cardFirstItemBean = (CardFirstItemBean) o;
            //File[] files = new File[cardFirstItemBean.getItemImagePath().size()];
            if (TextUtils.isEmpty(sharedPreferences.getString("projectId", ""))) {
                Log.v("11111", "____1___" + sharedPreferences.getString("token", ""));
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
                            Log.v("11111", "____1_projectId_" + bean.getResponseObject().getId());
                            editor.putString("projectId", bean.getResponseObject().getId()).commit();
                        } else {
                            Log.v("doPostAll", response.body().string() + "^^else^^^onFailure^^^^^" + response.code());
                            listener.onSuccess("SAVE FAILURE");
                        }
                    }
                });
            } else {
                Log.v("11111", "____2___");
                url = Urls.HOST_PROJECT+Urls.UPDATE1;
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
        if (type == 2) {
            final List<CardSecondItemBeanObj.CardSecondItemBean> cardSecondItemBeans = (List<CardSecondItemBeanObj.CardSecondItemBean>) o;
            mapParams.clear();
            if (!TextUtils.isEmpty(sharedPreferences.getString("projectId", ""))) {
                mapParams.put("token", sharedPreferences.getString("token", ""));
                mapParams.put("projectId", sharedPreferences.getString("projectId", ""));
                mapParams.put("industries", buildArray2Json(cardSecondItemBeans));
                Log.v("2222222222244", cardSecondItemBeans.size()+"---" + buildArray2Json(cardSecondItemBeans).toString());
            }
            final String finalUrl = url;
            new Thread(new Runnable() {
                @Override
                public void run() {
                    HttpUtils.doPut(finalUrl, mapParams, new Callback() {
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
            }).start();


        }
        if (type == 3) {
            CardThirdItemBean cardThirdItemBean = (CardThirdItemBean) o;
            mapParams.clear();
            if (!TextUtils.isEmpty(sharedPreferences.getString("projectId", ""))) {
                mapParams.put("token", sharedPreferences.getString("token", ""));
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
                mapParams.put("token", sharedPreferences.getString("token", ""));
                mapParams.put("projectId", sharedPreferences.getString("projectId", ""));
                mapParams.put("contactInfo", buildArray4Json(cardFourthItemBean));
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
                        Log.v("doPutWithJson", response.body().string() + "^^else^^^onFailure^^^^^" + response.code());
                        listener.onSuccess("SAVE FAILURE");
                    }
                }
            });
        }
        if (type == 9) {
            final CardNinthItemBean cardNinthItemBeans = (CardNinthItemBean) o;
            // final HashMap<String, String> mapParams = new HashMap<String, String>();
            mapParams.clear();
            Log.v("99999", sharedPreferences.getString("projectId", "") + "___" + sharedPreferences.getString("min", "0"));
            if (!TextUtils.isEmpty(sharedPreferences.getString("projectId", ""))) {
                mapParams.put("token", sharedPreferences.getString("token", ""));
                mapParams.put("projectId", sharedPreferences.getString("projectId", ""));
                mapParams.put("cooperationModel", cardNinthItemBeans.getMoshi());
                mapParams.put("min", sharedPreferences.getString("min", "0"));
                mapParams.put("max", sharedPreferences.getString("max", "0"));
                mapParams.put("caption", sharedPreferences.getString("caption", ""));
                StringBuffer wht = new StringBuffer();
                for (int i = 0; i < cardNinthItemBeans.getWhy().size(); i++) {
                    wht.append(cardNinthItemBeans.getWhy().get(i) + ",");
                }
                mapParams.put("cooperationStyles", wht.deleteCharAt(wht.length() - 1).toString());
                StringBuffer mtype = new StringBuffer();
                for (int j = 0; j < cardNinthItemBeans.getType().size(); j++) {
                    mtype.append(cardNinthItemBeans.getType().get(j) + ",");
                }
                mapParams.put("investmentType", mtype.deleteCharAt(mtype.length() - 1).toString());
                mapParams.put("other", cardNinthItemBeans.getQita());
                // mapParams.put("contactInfo", buildArrayJson(cardNinthItemBeans));
            }
            HttpUtils.doPut(url, mapParams, new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    listener.onFailure("SAVE FAILURE", e);
                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    if (String.valueOf(response.code()).startsWith("2")) {
                        Log.v("xxxxx", "" + response.body().string());
                        listener.onSuccess("SAVE SUCCESS");//异步请求
                    } else {
                        Log.v("xxxxx", "" + response.body().string());
                        listener.onSuccess("SAVE FAILURE");
                    }
                }
            });
        }
    }

    public String buildArray4Json(List<CardFourthItemBean> cardFourthItemBean) {
        JSONArray json = new JSONArray();
        try {
            for (int i = 0; i < cardFourthItemBean.size(); i++) {
                JSONObject jsonObj = new JSONObject();//一定要new对象
                jsonObj.put("name", cardFourthItemBean.get(i).getName().toString());
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
    }

    public String buildArray2Json(List<CardSecondItemBeanObj.CardSecondItemBean> cardSecondItemBeans) {
        List<JSONObject> list = new ArrayList<>();
//        String[] strings = new String[cardSecondItemBeans.size()];
//        JSONArray json = new JSONArray();
        try {
            for (int i = 0; i < cardSecondItemBeans.size(); i++) {
                JSONObject jsonObj = null;
                for (int j = 0; j < cardSecondItemBeans.get(i).getLingyuList().size(); j++) {
                    Log.v("22222333333",cardSecondItemBeans.get(i).getLingyuList().get(j)+"----"+cardSecondItemBeans.get(i).getChangye());
                    jsonObj = new JSONObject();//一定要new对象
                    jsonObj.put("parent", cardSecondItemBeans.get(i).getChangye());
                    jsonObj.put("name", cardSecondItemBeans.get(i).getLingyuList().get(j).toString());
//                    json.put(j+i+1, jsonObj);
                    list.add(jsonObj);
//                    strings[]
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        //把每个数据当作一对象添加到数组里
//        for(JSONObject o : list){
//            Log.v("22222222222",o.toString());
//        }
        Log.v("naison",list.toString() + "");
        return list.toString();
    }
   /*public String buildStypeJson(CardNinthItemBean cardNinthItemBean) {
        JSONObject jsonObj = new JSONObject();//一定要new对象
        try {
            for (int i = 0 ; i<cardNinthItemBean.getWhy().size();i++ ){

            }
            jsonObj.put("province", cardNinthItemBean.getProvince().toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        //把每个数据当作一对象添加到数组里
        Log.v("doPutWithJson",  "^^cardNinthItemBean^^^^" + jsonObj.toString());
        return jsonObj.toString();
    }*/
}
