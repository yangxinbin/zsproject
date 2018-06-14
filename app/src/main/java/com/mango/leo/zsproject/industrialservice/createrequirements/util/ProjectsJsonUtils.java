package com.mango.leo.zsproject.industrialservice.createrequirements.util;

import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import com.mango.leo.zsproject.eventexhibition.bean.EventBean;
import com.mango.leo.zsproject.industrialpanorama.bean.CityBean;
import com.mango.leo.zsproject.industrialpanorama.bean.ZhaoShangBean;
import com.mango.leo.zsproject.industrialservice.bean.AllItemBean;
import com.mango.leo.zsproject.industrialservice.createrequirements.bean.AllProjectsBean;
import com.mango.leo.zsproject.industrialservice.createrequirements.bean.ChanyLingyuBean;
import com.mango.leo.zsproject.industrialservice.createrequirements.carditems.bean.ChangYe;
import com.mango.leo.zsproject.industrialservice.createrequirements.carditems.bean.ProjectBean;
import com.mango.leo.zsproject.login.bean.UserMessageBean;
import com.mango.leo.zsproject.personalcenter.bean.MyEventBean;
import com.mango.leo.zsproject.utils.ACache;
import com.mango.leo.zsproject.utils.JsonUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Description :
 * Date   : 15/12/19
 */
public class ProjectsJsonUtils {

    private final static String TAG = "NewsJsonUtils";

    /**
     * 将获取到的json转换为列表对象
     *
     * @param res
     * @param
     * @return
     */
    public static List<AllProjectsBean> readJsonNewsBeans(String res, String va) {
        List<AllProjectsBean> beans = new ArrayList<AllProjectsBean>();
        try {
            JsonObject jsonObject = new JsonParser().parse(res).getAsJsonObject();
            JsonObject ob = jsonObject.getAsJsonObject("responseObject");
            JsonArray jsonArray = ob.getAsJsonArray(va);
            for (int i = 0; i < jsonArray.size(); i++) {
                AllProjectsBean news = JsonUtils.deserialize(jsonObject, AllProjectsBean.class);
                beans.add(news);//这里会将所有的json对象转换为bean对象
            }
        } catch (Exception e) {
        }
        return beans;
    }

    public static List<ChanyLingyuBean.ResponseListBean> readJsonCBeans(String res, Context context) {
        ACache.get(context).put("changye", res);
        ACache mCache = ACache.get(context);
        Log.v("222224444","???===="+mCache.getAsString("changye"));
        ChanyLingyuBean chanyLingyuBean = new Gson().fromJson(res, ChanyLingyuBean.class);
        List<ChanyLingyuBean.ResponseListBean> c = chanyLingyuBean.getResponseList();
        return c;
    }
    public static List<ChanyLingyuBean.ResponseListBean> readJsonCBeans(String res) {
        ChanyLingyuBean chanyLingyuBean = new Gson().fromJson(res, ChanyLingyuBean.class);
        List<ChanyLingyuBean.ResponseListBean> c = chanyLingyuBean.getResponseList();
        return c;
    }
    public static UserMessageBean readJsonUserMessageBeans(String res, Context context) {
        ACache.get(context).put("message", res);
        JsonObject jsonObject = new JsonParser().parse(res).getAsJsonObject();
        JsonObject ob = jsonObject.getAsJsonObject("responseObject");
        UserMessageBean bean = JsonUtils.deserialize(jsonObject, UserMessageBean.class);
        return bean;
    }
    public static ChangYe readJsonCMessageBeans(String res) {
        JsonObject jsonObject = new JsonParser().parse(res).getAsJsonObject();
        JsonObject ob = jsonObject.getAsJsonObject("responseObject");
        ChangYe bean = JsonUtils.deserialize(jsonObject, ChangYe.class);
        return bean;
    }
    public static UserMessageBean readJsonUserMessageBeans(String res) {
        JsonObject jsonObject = new JsonParser().parse(res).getAsJsonObject();
        JsonObject ob = jsonObject.getAsJsonObject("responseObject");
        UserMessageBean bean = JsonUtils.deserialize(jsonObject, UserMessageBean.class);
        return bean;
    }

    public static ProjectBean readJsonProjectBeans(String res) {
        JsonObject jsonObject = new JsonParser().parse(res).getAsJsonObject();
        JsonObject ob = jsonObject.getAsJsonObject("responseObject");
        ProjectBean bean = JsonUtils.deserialize(jsonObject, ProjectBean.class);
        return bean;
    }

    public static CityBean readJsonCityBeans(String res) {
        JsonObject jsonObject = new JsonParser().parse(res).getAsJsonObject();
        JsonObject ob = jsonObject.getAsJsonObject("responseObject");
        CityBean bean = JsonUtils.deserialize(jsonObject, CityBean.class);
        return bean;
    }

    public static List<EventBean> readJsonEventBeans(String res, String va) {
        List<EventBean> beans = new ArrayList<EventBean>();
        try {
            JsonObject jsonObject = new JsonParser().parse(res).getAsJsonObject();
            JsonObject ob = jsonObject.getAsJsonObject("responseObject");
            JsonArray jsonArray = ob.getAsJsonArray(va);
            for (int i = 0; i < jsonArray.size(); i++) {
                EventBean event = JsonUtils.deserialize(jsonObject, EventBean.class);
                beans.add(event);//这里会将所有的json对象转换为bean对象
            }
        } catch (Exception e) {
        }
        return beans;
    }

    public static List<ZhaoShangBean> readJsonZhaoShangBeans(String res, String va) {
        List<ZhaoShangBean> beans = new ArrayList<ZhaoShangBean>();
        try {
            JsonObject jsonObject = new JsonParser().parse(res).getAsJsonObject();
            JsonObject ob = jsonObject.getAsJsonObject("responseObject");
            JsonArray jsonArray = ob.getAsJsonArray(va);
            for (int i = 0; i < jsonArray.size(); i++) {
                ZhaoShangBean event = JsonUtils.deserialize(jsonObject, ZhaoShangBean.class);
                beans.add(event);//这里会将所有的json对象转换为bean对象
            }
        } catch (Exception e) {
        }
        return beans;
    }

    public static List<MyEventBean> readJsonMyEventBeans(String res, String va) {
        List<MyEventBean> beans = new ArrayList<MyEventBean>();
        try {
            JsonObject jsonObject = new JsonParser().parse(res).getAsJsonObject();
            JsonObject ob = jsonObject.getAsJsonObject("responseObject");
            JsonArray jsonArray = ob.getAsJsonArray(va);
            for (int i = 0; i < jsonArray.size(); i++) {
                MyEventBean event = JsonUtils.deserialize(jsonObject, MyEventBean.class);
                beans.add(event);//这里会将所有的json对象转换为bean对象
            }
        } catch (Exception e) {
        }
        return beans;
    }
}
