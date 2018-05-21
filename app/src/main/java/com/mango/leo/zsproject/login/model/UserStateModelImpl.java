package com.mango.leo.zsproject.login.model;

import android.content.Context;

import com.mango.leo.zsproject.login.bean.User;
import com.mango.leo.zsproject.login.listener.OnPwdUserListener;
import com.mango.leo.zsproject.utils.HttpUtils;

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
    public void visitPwdUserState(Context context, String url, User user, final OnPwdUserListener listener) {
        Map<String, String> mapParams = new HashMap<String, String>();
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
}
