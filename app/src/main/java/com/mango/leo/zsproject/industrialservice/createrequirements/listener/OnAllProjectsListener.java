package com.mango.leo.zsproject.industrialservice.createrequirements.listener;

import com.mango.leo.zsproject.industrialservice.createrequirements.bean.AllProjectsBean;

import java.util.List;

/**
 * Created by admin on 2018/5/21.
 */

public interface OnAllProjectsListener {
    void onSuccess(List<AllProjectsBean> list);
    void onFailure(String msg, Exception e);
}
