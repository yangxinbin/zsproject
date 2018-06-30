package com.mango.leo.zsproject.login.listener;

import com.mango.leo.zsproject.bean.ErrorBean;
import com.mango.leo.zsproject.login.bean.UserMessageBean;

/**
 * Created by admin on 2018/5/18.
 */

public interface OnUserStateListener {
    void onSuccess(String string);
    void onFailure(String msg, Exception e);
    void getSuccessUserMessage(UserMessageBean bean);
    void getErrorUserMessage(ErrorBean bean);
}
