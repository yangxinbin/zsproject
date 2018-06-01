package com.mango.leo.zsproject.login.model;

import android.content.Context;
import android.util.Log;

import com.mango.leo.zsproject.industrialservice.createrequirements.carditems.bean.CardFirstItemBean;
import com.mango.leo.zsproject.login.bean.User;
import com.mango.leo.zsproject.login.bean.UserPhone;
import com.mango.leo.zsproject.login.listener.OnUserStateListener;
import com.mango.leo.zsproject.utils.HttpUtils;
import com.mango.leo.zsproject.utils.OkHttpUtils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;


/**
 * Created by admin on 2018/5/18.
 */

public class UserStateModelImpl implements UserStateModel {
    @Override
    public void visitPwdUserState(Context context, String url, int type,Object o, final OnUserStateListener listener) {
/*        List<OkHttpUtils.Param> mapParams = new ArrayList<>();
        mapParams.add(new OkHttpUtils.Param("username", user.getUserName()));
        mapParams.add(new OkHttpUtils.Param("password", user.getUserPwd()));*/
        Log.v("uuuuuuu","___________"+url);
        Map<String, String> mapParams = new HashMap<String, String>();
        if (type == 1){
            User user = (User) o;
            mapParams.put("username",user.getUserName());
            mapParams.put("password",user.getUserPwd());
            HttpUtils.doPost(url, mapParams, new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    listener.onFailure("FAILURE",e);
                }
                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    if (response.code() == 200){
                        listener.onSuccess("SUCCESS");//异步请求
                    }else {
                        listener.onSuccess("NOSUCCESS");
                    }
                }
            });
        }

        if (type == 2){
            UserPhone userPhone = (UserPhone) o;
            //mapParams.put("username",userPhone.getPhoneN());
            HttpUtils.doGet(url+"?username="+userPhone.getPhoneN(), new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    listener.onFailure("FAILURE",e);
                }
                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    if (response.code() == 200){
                        listener.onSuccess("CODE_SUCCESS");//异步请求
                    }else {
                        listener.onSuccess("CODE_FAILURE");
                    }
                }
            });
        }

        if (type == 3){
            UserPhone userPhone = (UserPhone) o;
            mapParams.put("username",userPhone.getPhoneN());
            mapParams.put("code",userPhone.getPhoneC());
            HttpUtils.doPost(url, mapParams, new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    listener.onFailure("FAILURE",e);
                }
                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    if (response.code() == 200){
                        listener.onSuccess("SUCCESS");//异步请求
                    }else {
                        listener.onSuccess("NOSUCCESS");
                    }
                }
            });
        }

/*        final OkHttpUtils.ResultCallback<String> loadNewsCallback = new OkHttpUtils.ResultCallback<String>() {
            @Override
            public void onSuccess(String response) {
                // if (response.code() == 200){
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