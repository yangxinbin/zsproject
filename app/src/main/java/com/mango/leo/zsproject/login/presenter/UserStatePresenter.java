package com.mango.leo.zsproject.login.presenter;

import android.content.Context;

import com.mango.leo.zsproject.login.bean.User;

/**
 * Created by admin on 2018/5/18.
 */

public interface UserStatePresenter {
    void visitPwdUserState(Context context, User user);
}
