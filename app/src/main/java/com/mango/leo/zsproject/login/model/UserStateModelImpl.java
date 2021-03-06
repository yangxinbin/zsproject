package com.mango.leo.zsproject.login.model;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.mango.leo.zsproject.bean.ErrorBean;
import com.mango.leo.zsproject.industrialservice.createrequirements.util.ProjectsJsonUtils;
import com.mango.leo.zsproject.login.bean.BeForeUserMesBean;
import com.mango.leo.zsproject.login.bean.UserMessageBean;
import com.mango.leo.zsproject.login.bean.User;
import com.mango.leo.zsproject.login.bean.UserPhone;
import com.mango.leo.zsproject.login.listener.OnUserStateListener;
import com.mango.leo.zsproject.utils.HttpUtils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

import static android.content.Context.MODE_PRIVATE;


/**
 * Created by admin on 2018/5/18.
 */

public class UserStateModelImpl implements UserStateModel {
    private SharedPreferences sharedPreferences;

    @Override
    public void visitPwdUserState(final Context context, final String url, int type, Object o, final OnUserStateListener listener) {
        sharedPreferences = context.getSharedPreferences("CIFIT", MODE_PRIVATE);
/*        List<OkHttpUtils.Param> mapParams = new ArrayList<>();
        mapParams.add(new OkHttpUtils.Param("username", user.getUserName()));
        mapParams.add(new OkHttpUtils.Param("password", user.getUserPwd()));*/
        Log.v("uuuuuuu", "___________" + url);
        Map<String, String> mapParams = new HashMap<String, String>();
        if (type == 1) {//密码登录
            User user = (User) o;
            mapParams.put("username", user.getUserName());
            mapParams.put("password", user.getUserPwd());
            Log.v("uuuuuuu", user.getUserName() + "___________" + user.getUserPwd());

            HttpUtils.doPost(url, mapParams, new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    Log.v("uuuuuuu", "____FAILURE_______");
                    listener.onFailure("FAILURE", e);
                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    if (String.valueOf(response.code()).startsWith("2")) {
                        Log.v("uuuuuuu", "______vv_____");
                        listener.onSuccess("SUCCESS");//异步请求
                        UserMessageBean bean = ProjectsJsonUtils.readJsonUserMessageBeans(response.body().string(), context);//data是json字段获得data的值即对象
                        listener.getSuccessUserMessage(bean);
                    } else {
                        listener.onSuccess("FAILURE");
                    }
                }
            });
        }

        if (type == 2) {//获取验证码
            UserPhone userPhone = (UserPhone) o;
            //mapParams.put("username",userPhone.getPhoneN());
            HttpUtils.doGet(url + "?phoneOrEmail=" + userPhone.getPhoneN(), new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    listener.onFailure("CODE_FAILURE", e);
                    listener.onSuccess("CODE_FAILURE");
                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    if (String.valueOf(response.code()).startsWith("2")) {
                        listener.onSuccess("CODE_SUCCESS");//异步请求
                    } else {
                        listener.onSuccess("CODE_FAILURE");
                    }
                }
            });
        }

        if (type == 3) {//验证码登录
            UserPhone userPhone = (UserPhone) o;
            mapParams.put("username", userPhone.getPhoneN());
            mapParams.put("code", userPhone.getPhoneC());
            mapParams.put("stage", "0");
            HttpUtils.doPost(url, mapParams, new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    Log.v("zzzzzzz", "***e***" + e.getMessage());
                    listener.onFailure("FAILURE", e);
                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    if (String.valueOf(response.code()).startsWith("2")) {
                        UserMessageBean bean = ProjectsJsonUtils.readJsonUserMessageBeans(response.body().string(), context);//data是json字段获得data的值即对象
                        listener.getSuccessUserMessage(bean);
                        listener.onSuccess("SUCCESS");//异步请求
                    } else {
                        Log.v("zzzzzzz", response.body().string() + "******" + response.code());
                        listener.onSuccess("FAILURE");
                    }
                }
            });
        }
        if (type == 4) { //注册
            UserPhone userPhone = (UserPhone) o;
            mapParams.put("username", userPhone.getPhoneN());
            mapParams.put("code", userPhone.getPhoneC());
            mapParams.put("password", "");
            mapParams.put("step", "1");
            Log.v("zzzzzzz", userPhone.getPhoneN() + "******" + userPhone.getPhoneC() + "!!!" + url);
            HttpUtils.doPost(url, mapParams, new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    listener.onFailure("RES_FAILURE", e);
                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    if (String.valueOf(response.code()).startsWith("2")) {
                        listener.onSuccess("RES_SUCCESS");//异步请求
                        UserMessageBean bean = ProjectsJsonUtils.readJsonUserMessageBeans(response.body().string());//data是json字段获得data的值即对象
                        listener.getSuccessUserMessage(bean);
                    } else {
                        Log.v("eeeeeeee", "******!!!" + url);
                        ErrorBean bean = ProjectsJsonUtils.readJsonErrorBean(response.body().string());
                        listener.getErrorUserMessage(bean);
                    }
                }
            });
        }
        if (type == 5) {
            UserPhone user = (UserPhone) o;
            mapParams.put("username", user.getPhoneN());
            mapParams.put("password", user.getPhonePwd());
            mapParams.put("code", user.getPhoneC());
            mapParams.put("step", "2");
            HttpUtils.doPost(url, mapParams, new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    listener.onFailure("SET_FAILURE", e);
                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    if (String.valueOf(response.code()).startsWith("2")) {
                        listener.onSuccess("SET_SUCCESS");//异步请求
                        UserMessageBean bean = ProjectsJsonUtils.readJsonUserMessageBeans(response.body().string());//data是json字段获得data的值即对象
                        listener.getSuccessUserMessage(bean);
                    } else {
                        listener.onSuccess("SET_FAILURE");
                        Log.v("zzzzzzz", response.body().string() + "******" + response.code() + url);
                    }
                }
            });
        }
        if (type == 6) { //上传个人信息
            mapParams.clear();
            BeForeUserMesBean userMessageBean = (BeForeUserMesBean) o;
            Log.v("zzzzzzz1", userMessageBean.toString() + "******");
            if (userMessageBean != null) {
                // Log.v("zzzzzzz","**userMessageBean.getToken()****"+userMessageBean.getToken());
                mapParams.put("token", userMessageBean.getToken());
                mapParams.put("username", userMessageBean.getUsername());
                mapParams.put("company", "");
                mapParams.put("name", userMessageBean.getName());
                mapParams.put("department", userMessageBean.getDepartment());
                mapParams.put("email", userMessageBean.getEmail());
                if (userMessageBean.getPosition() != null) {
                    mapParams.put("position", userMessageBean.getPosition());
                }
                mapParams.put("type", "0");
/*                if (userMessageBean.getCountry() != null) {
                    mapParams.put("country", userMessageBean.getCountry());
                }*/
                if (userMessageBean.getProvince() != null) {
                    mapParams.put("province", userMessageBean.getProvince());
                }
                if (userMessageBean.getCity() != null) {
                    mapParams.put("city", userMessageBean.getCity());
                }
                if (userMessageBean.getDistrict() != null) {
                    mapParams.put("district", userMessageBean.getDistrict());
                }
/*                if (userMessageBean.getAddress() != null) {
                    mapParams.put("address", userMessageBean.getAddress());
                }
                if (userMessageBean.getLon() != null) {
                    mapParams.put("lon", String.valueOf(userMessageBean.getLon()));
                }
                if (userMessageBean.getLat() != null) {
                    mapParams.put("lat", String.valueOf(userMessageBean.getLat()));
                }*/
                /*mapParams.put("token", sharedPreferences.getString("token", ""));
                mapParams.put("username", "123");
                mapParams.put("company", "123");
                mapParams.put("name", "123");
                mapParams.put("department", "123");
                mapParams.put("email", "123");
                mapParams.put("position", "123");
                mapParams.put("type", "0");

                mapParams.put("country", "123");
                mapParams.put("province", "123");
                mapParams.put("city", "123");
                mapParams.put("district", "123");
                mapParams.put("address", "123");
                mapParams.put("lon", "123");
                mapParams.put("lat", "123");*/

            }
            HttpUtils.doPost(url, mapParams, new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    listener.onFailure("MES_FAILURE", e);
                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    if (String.valueOf(response.code()).startsWith("2")) {
                        listener.onSuccess("MES_SUCCESS");//异步请求
                        //注册时以及获取Token这里不用重复获取了
                        UserMessageBean bean = ProjectsJsonUtils.readJsonUserMessageBeans(response.body().string(), context);//data是json字段获得data的值即对象
                        listener.getSuccessUserMessage(bean);
                    } else {
                        Log.v("zzzzzzz", response.body().string() + "******" + response.code() + url);
                        listener.onSuccess("MES_FAILURE");
                    }
                }
            });
        }

/*        final OkHttpUtils.ResultCallback<String> loadNewsCallback = new OkHttpUtils.ResultCallback<String>() {
            @Override
            public void onSuccess(String response) {
                // if (String.valueOf(response.code()).startsWith("2")){
                listener.onSuccess("SUCCESS");//异步请求
                // }else {
                //listener.onSuccess("NOSUCCESS");
                // }            }
            }
            @Override
            public void onFailure(Exception e) {
            }
        };
        OkHttpUtils.post(url,loadNewsCallback,mapParams);*/

    }
}