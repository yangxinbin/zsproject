package com.mango.leo.zsproject.login.presenter;

import android.content.Context;

import com.mango.leo.zsproject.login.bean.User;
import com.mango.leo.zsproject.login.listener.OnUserStateListener;
import com.mango.leo.zsproject.login.model.UserStateModel;
import com.mango.leo.zsproject.login.model.UserStateModelImpl;
import com.mango.leo.zsproject.login.view.UserStateView;
import com.mango.leo.zsproject.utils.Urls;


/**
 * Created by admin on 2018/5/18.
 */

public class UserStatePresenterImpl implements UserStatePresenter,OnUserStateListener {
    UserStateView userStateView;
    UserStateModel userStateModel;
    public UserStatePresenterImpl(UserStateView u) {
        this.userStateView = u;
        this.userStateModel = new UserStateModelImpl();
    }
    @Override
    public void visitPwdUserState(Context context, User user) {
        userStateModel.visitPwdUserState(context,getUrl(),user,this);
    }

    @Override
    public void onSuccess(String string) {
        userStateView.showStateView(string);
    }

    @Override
    public void onFailure(String msg, Exception e) {
        userStateView.showVisitFailMsg(msg);
    }
    private String getUrl() {
        return Urls.HOST_AUTH;
    }
}
