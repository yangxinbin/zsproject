package com.mango.leo.zsproject.login.presenter;

import android.content.Context;

import com.mango.leo.zsproject.login.bean.UserMessageBean;
import com.mango.leo.zsproject.login.bean.User;
import com.mango.leo.zsproject.login.listener.OnUserStateListener;
import com.mango.leo.zsproject.login.model.UserStateModel;
import com.mango.leo.zsproject.login.model.UserStateModelImpl;
import com.mango.leo.zsproject.login.view.UserStateView;
import com.mango.leo.zsproject.utils.Urls;


/**
 * Created by admin on 2018/5/18.
 */

public class UserStatePresenterImpl implements UserStatePresenter, OnUserStateListener {
    UserStateView userStateView;
    UserStateModel userStateModel;

    public UserStatePresenterImpl(UserStateView u) {
        this.userStateView = u;
        this.userStateModel = new UserStateModelImpl();
    }

    @Override
    public void visitPwdUserState(Context context, int type, Object user) {
        userStateModel.visitPwdUserState(context, getUrl(type),type, user, this);
    }

    @Override
    public void onSuccess(String string) {
        userStateView.showStateView(string);
    }

    @Override
    public void onFailure(String msg, Exception e) {
        userStateView.showVisitFailMsg(msg);
    }

    @Override
    public void getSuccessToken(UserMessageBean bean) {
        userStateView.responeToken(bean);
    }

    private String getUrl(int type) {
        String url = null;
        switch (type) {
            case 1:
                url = Urls.HOST_AUTH;
                break;
            case 2:
                url = Urls.HOST_CODE;
                break;
            case 3:
                url = Urls.HOST_CODELOGIN;
                break;
            case 4:
                url = Urls.HOST_REGIST;
                break;
            case 5:
                url = Urls.HOST_REGIST;
                break;
            case 6:
                url = Urls.HOST_MES;
                break;
        }
        return url;
    }
}
