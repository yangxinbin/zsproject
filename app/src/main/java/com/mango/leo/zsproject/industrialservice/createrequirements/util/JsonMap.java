package com.mango.leo.zsproject.industrialservice.createrequirements.util;

import android.util.Log;

import com.google.gson.Gson;
import com.mango.leo.zsproject.industrialservice.createrequirements.carditems.bean.CardSecondItemBeanObj;
import com.mango.leo.zsproject.industrialservice.createrequirements.carditems.bean.ChangYe;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by admin on 2018/6/15.
 */

public class JsonMap {

    /**
     * 将json 数组转换为Map 对象
     *
     * @param jsonString
     * @return
     */

    public static List<ChangYe> getMap(String jsonString) throws Exception
    {
//        try {
        ChangYe changYe = null;
        JSONObject jsonObject = (JSONObject) new JSONObject(jsonString).get("responseObject");
        List<ChangYe> list = new ArrayList<>();
        List<String> listStr = null;
        for (Iterator<String> it = jsonObject.keys(); it.hasNext(); ) {
            String str = it.next();
            listStr = new ArrayList<>();
            JSONArray jsonArray = new JSONArray(jsonObject.get(str).toString());
            for (int i = 0; i < jsonArray.length(); i++) {
                listStr.add(jsonArray.getString(i));
            }
            changYe = new ChangYe(str, listStr);
            list.add(changYe);
        }
        Log.v("nnnnnnnnnnnn","_____"+list.get(0).toString());
        return list;
    }
    /**
     * 把json 转换为ArrayList 形式
     *
     * @return
     */
    public static List<ChangYe> getMapTest(String jsonString) throws JSONException {
        List<Map<String, Object>> list = null;
        JSONObject jsonObject = (JSONObject) new JSONObject(jsonString).get("responseObject");
        Log.v("", jsonObject.toString());
        Gson gson = new Gson();
        ChangYe changYe = gson.fromJson(jsonObject.toString(), ChangYe.class);
        Log.v("nnnn", changYe.toString());
        return null;
    }
}
