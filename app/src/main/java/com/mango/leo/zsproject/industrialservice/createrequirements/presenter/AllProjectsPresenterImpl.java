package com.mango.leo.zsproject.industrialservice.createrequirements.presenter;

import android.content.Context;
import android.util.Log;

import com.mango.leo.zsproject.industrialservice.createrequirements.AllAndCreatedPlanActivity;
import com.mango.leo.zsproject.industrialservice.createrequirements.bean.AllProjectsBean;
import com.mango.leo.zsproject.industrialservice.createrequirements.listener.OnAllProjectsListener;
import com.mango.leo.zsproject.industrialservice.createrequirements.model.AllProjectsModel;
import com.mango.leo.zsproject.industrialservice.createrequirements.model.AllProjectsModelImpl;
import com.mango.leo.zsproject.industrialservice.createrequirements.view.AllProjectsView;
import com.mango.leo.zsproject.utils.AppUtils;
import com.mango.leo.zsproject.utils.Urls;

import java.util.List;

/**
 * Created by admin on 2018/5/21.
 */

public class AllProjectsPresenterImpl implements AllProjectsPresenter, OnAllProjectsListener {
    private AllProjectsView allProjectsView;
    private AllProjectsModel allProjectsModel;

    public AllProjectsPresenterImpl(AllProjectsView u) {
        this.allProjectsView = u;
        this.allProjectsModel = new AllProjectsModelImpl();
    }

    @Override
    public void visitProjects(Context context, int type,int page) {
        String url = getUrl(type,context)+"?page="+page;
        allProjectsModel.visitProjects(context, type, url, this);
    }

    @Override
    public void onSuccess(List<AllProjectsBean> list) {
        allProjectsView.addProjectsSuccess(list);
    }

    @Override
    public void onFailure(String msg, Exception e) {
        allProjectsView.addProjectsFail(msg);
    }

    private String getUrl(int type,Context context) {
        StringBuffer sburl = new StringBuffer();
        switch (type) {
            case AllAndCreatedPlanActivity.PROJECTS_TYPE_DRAFTBOX:
                sburl.append(Urls.HOST_PROJECT).append("18318836309"/*AppUtils.getSharePreferences(context,"CIFIT","authPhone","")*/);
                break;
            case AllAndCreatedPlanActivity.PROJECTS_TYPE_SUBMISSION:
                sburl.append(Urls.HOST_PROJECT).append("18318836309"/*AppUtils.getSharePreferences(context,"CIFIT","authPhone","")*/);

                break;
            case AllAndCreatedPlanActivity.PROJECTS_TYPE_AUDITED:
                sburl.append(Urls.HOST_PROJECT).append("18318836309"/*AppUtils.getSharePreferences(context,"CIFIT","authPhone","")*/);

                break;
        }
        return sburl.toString();
    }
}
