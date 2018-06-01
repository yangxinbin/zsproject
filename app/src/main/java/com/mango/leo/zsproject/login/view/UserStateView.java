package com.mango.leo.zsproject.login.view;

import com.mango.leo.zsproject.login.bean.TokenFromLonginBean;

/**
 * Created by admin on 2018/5/18.
 */

public interface UserStateView {
    void showStateView(String string);
    void showVisitFailMsg(String string);
    void responeToken(TokenFromLonginBean bean);
}
