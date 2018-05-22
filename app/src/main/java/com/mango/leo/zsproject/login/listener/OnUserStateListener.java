package com.mango.leo.zsproject.login.listener;

/**
 * Created by admin on 2018/5/18.
 */

public interface OnUserStateListener {
    void onSuccess(String string);
    void onFailure(String msg, Exception e);
}
