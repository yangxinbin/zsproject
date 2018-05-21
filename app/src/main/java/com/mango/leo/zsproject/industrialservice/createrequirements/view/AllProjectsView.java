package com.mango.leo.zsproject.industrialservice.createrequirements.view;

import com.mango.leo.zsproject.industrialservice.createrequirements.bean.AllProjectsBean;

import java.util.List;

/**
 * Created by admin on 2018/5/21.
 */

public interface AllProjectsView {
    void addProjectsSuccess(List<AllProjectsBean> projectsList);
    void addProjectsFail(String e);
}
