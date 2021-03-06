package com.mango.leo.zsproject.industrialservice.createrequirements.util;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.mango.leo.zsproject.bean.ErrorBean;
import com.mango.leo.zsproject.datacenter.bean.TouZiBean;
import com.mango.leo.zsproject.eventexhibition.bean.EventBean;
import com.mango.leo.zsproject.eventexhibition.bean.WechatPayBean;
import com.mango.leo.zsproject.industrialpanorama.bean.ChooseBean;
import com.mango.leo.zsproject.industrialpanorama.bean.CityBean;
import com.mango.leo.zsproject.industrialpanorama.bean.ZhaoShangBean;
import com.mango.leo.zsproject.industrialservice.bean.DemandManagementBean;
import com.mango.leo.zsproject.industrialservice.bean.MatchDataBean;
import com.mango.leo.zsproject.industrialservice.bean.MatchEventBean;
import com.mango.leo.zsproject.industrialservice.createrequirements.bean.AllProjectsBean;
import com.mango.leo.zsproject.industrialservice.createrequirements.bean.ChanyLingyuBean;
import com.mango.leo.zsproject.industrialservice.createrequirements.carditems.bean.ChangYe;
import com.mango.leo.zsproject.industrialservice.createrequirements.carditems.bean.ProjectBean;
import com.mango.leo.zsproject.login.bean.UserMessageBean;
import com.mango.leo.zsproject.personalcenter.bean.MyEventBean;
import com.mango.leo.zsproject.personalcenter.show.baoming.bean.SingUpBean;
import com.mango.leo.zsproject.personalcenter.show.shenbao.bean.ShenBaoBean;
import com.mango.leo.zsproject.utils.ACache;
import com.mango.leo.zsproject.utils.JsonUtils;

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
        UserMessageBean bean = JsonUtils.deserialize(jsonObject, UserMessageBean.class);
        return bean;
    }
    public static ChangYe readJsonCMessageBeans(String res) {
        JsonObject jsonObject = new JsonParser().parse(res).getAsJsonObject();
        ChangYe bean = JsonUtils.deserialize(jsonObject, ChangYe.class);
        return bean;
    }
    public static UserMessageBean readJsonUserMessageBeans(String res) {
        JsonObject jsonObject = new JsonParser().parse(res).getAsJsonObject();
        UserMessageBean bean = JsonUtils.deserialize(jsonObject, UserMessageBean.class);
        return bean;
    }

    public static ProjectBean readJsonProjectBeans(String res) {
        JsonObject jsonObject = new JsonParser().parse(res).getAsJsonObject();
        ProjectBean bean = JsonUtils.deserialize(jsonObject, ProjectBean.class);
        return bean;
    }

    public static CityBean readJsonCityBeans(String res) {
        JsonObject jsonObject = new JsonParser().parse(res).getAsJsonObject();
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

    public static List<DemandManagementBean> readJsonTenantListBeans(String res, String va) {
        List<DemandManagementBean> beans = new ArrayList<DemandManagementBean>();
        try {
            JsonObject jsonObject = new JsonParser().parse(res).getAsJsonObject();
            //JsonObject ob = jsonObject.getAsJsonObject("responseObject");
            JsonArray jsonArray = jsonObject.getAsJsonArray(va);
            for (int i = 0; i < jsonArray.size(); i++) {
                DemandManagementBean event = JsonUtils.deserialize(jsonObject, DemandManagementBean.class);
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

    public static List<SingUpBean> readJsonSingUpBean(String res, String va) {
        List<SingUpBean> beans = new ArrayList<SingUpBean>();
        try {
            JsonObject jsonObject = new JsonParser().parse(res).getAsJsonObject();
            JsonObject ob = jsonObject.getAsJsonObject("responseObject");
            JsonArray jsonArray = ob.getAsJsonArray(va);
            for (int i = 0; i < jsonArray.size(); i++) {
                SingUpBean event = JsonUtils.deserialize(jsonObject, SingUpBean.class);
                beans.add(event);//这里会将所有的json对象转换为bean对象
            }
        } catch (Exception e) {
        }
        return beans;
    }

    public static List<ChooseBean.ResponseListBean> readChooseBeans(String res) {
        ChooseBean chooseBean = new Gson().fromJson(res, ChooseBean.class);
        List<ChooseBean.ResponseListBean> c = chooseBean.getResponseList();
        return c;
    }
    public static ErrorBean readJsonErrorBean(String res) {
        JsonObject jsonObject = new JsonParser().parse(res).getAsJsonObject();
        ErrorBean bean = JsonUtils.deserialize(jsonObject, ErrorBean.class);
        return bean;
    }
    public static List<ShenBaoBean> readJsonShenBaoBean(String res, String va) {
        List<ShenBaoBean> beans = new ArrayList<ShenBaoBean>();
        try {
            JsonObject jsonObject = new JsonParser().parse(res).getAsJsonObject();
            JsonObject ob = jsonObject.getAsJsonObject("responseObject");
            JsonArray jsonArray = ob.getAsJsonArray(va);
            for (int i = 0; i < jsonArray.size(); i++) {
                ShenBaoBean event = JsonUtils.deserialize(jsonObject, ShenBaoBean.class);
                beans.add(event);//这里会将所有的json对象转换为bean对象
            }
        } catch (Exception e) {
        }
        return beans;
    }
    public static WechatPayBean readJsonWechatPayBean(String res) {
        JsonObject jsonObject = new JsonParser().parse(res).getAsJsonObject();
        WechatPayBean bean = JsonUtils.deserialize(jsonObject, WechatPayBean.class);
        return bean;
    }

    public static List<MatchEventBean> readJsonMatchEventBean(String res, String va) {
        List<MatchEventBean> beans = new ArrayList<MatchEventBean>();
        try {
            JsonObject jsonObject = new JsonParser().parse(res).getAsJsonObject();
            //JsonObject ob = jsonObject.getAsJsonObject("responseObject");
            JsonArray jsonArray = jsonObject.getAsJsonArray(va);
            for (int i = 0; i < jsonArray.size(); i++) {
                MatchEventBean event = JsonUtils.deserialize(jsonObject, MatchEventBean.class);
                beans.add(event);//这里会将所有的json对象转换为bean对象
            }
        } catch (Exception e) {
        }
        return beans;
    }
    public static List<TouZiBean> readJsonTouZiBean(String res, String va) {
        List<TouZiBean> beans = new ArrayList<TouZiBean>();
        try {
            JsonObject jsonObject = new JsonParser().parse(res).getAsJsonObject();
            JsonObject ob = jsonObject.getAsJsonObject("responseObject");
            JsonArray jsonArray = ob.getAsJsonArray(va);
            for (int i = 0; i < jsonArray.size(); i++) {
                TouZiBean event = JsonUtils.deserialize(jsonObject, TouZiBean.class);
                beans.add(event);//这里会将所有的json对象转换为bean对象
            }
        } catch (Exception e) {
        }
        return beans;
    }
    public static List<MatchDataBean> readJsonMatchDataBean(String res, String va) {
        List<MatchDataBean> beans = new ArrayList<MatchDataBean>();
        try {
            JsonObject jsonObject = new JsonParser().parse(res).getAsJsonObject();
            //JsonObject ob = jsonObject.getAsJsonObject("responseObject");
            JsonArray jsonArray = jsonObject.getAsJsonArray(va);
            for (int i = 0; i < jsonArray.size(); i++) {
                MatchDataBean event = JsonUtils.deserialize(jsonObject, MatchDataBean.class);
                beans.add(event);//这里会将所有的json对象转换为bean对象
            }
        } catch (Exception e) {
        }
        return beans;
    }
}
