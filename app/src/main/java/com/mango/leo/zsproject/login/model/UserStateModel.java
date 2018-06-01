package com.mango.leo.zsproject.login.model;

import android.content.Context;

import com.mango.leo.zsproject.login.bean.User;
import com.mango.leo.zsproject.login.listener.OnUserStateListener;

/**
 * Created by admin on 2018/5/18.
 */

public interface UserStateModel {
    void visitPwdUserState(Context context, String url, int type, Object user,OnUserStateListener listener);//isRefresh 刷新重新写入缓存
}
