package com.mango.leo.zsproject.industrialservice.createrequirements.presenter;

import android.content.Context;
import android.content.SharedPreferences;
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

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by admin on 2018/5/21.
 */

public class AllProjectsPresenterImpl implements AllProjectsPresenter, OnAllProjectsListener {
    private AllProjectsView allProjectsView;
    private AllProjectsModel allProjectsModel;
    private SharedPreferences sharedPreferences;

    public AllProjectsPresenterImpl(AllProjectsView u) {
        this.allProjectsView = u;
        this.allProjectsModel = new AllProjectsModelImpl();
    }

    @Override
    public void visitProjects(Context context, int type, int page) {
        sharedPreferences = context.getSharedPreferences("CIFIT", MODE_PRIVATE);
        String url = null;
        if (type == 0) {
            url = getUrl(type, context) + "?token=" + sharedPreferences.getString("token", "") + "&stage=" + 1 + "&page=" + page;
        } else if (type == 1) {
            url = getUrl(type, context) + "?token=" + sharedPreferences.getString("token", "") + "&stage=" + 0 + "&page=" + page;
        } else if (type == 2) {
            url = getUrl(type, context) + "?token=" + sharedPreferences.getString("token", "") + "&stage=" + 2 + "&page=" + page;
        }
        Log.v("pppppppppppp", "" + url);
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

    private String getUrl(int type, Context context) {
        StringBuffer sburl = new StringBuffer();
        switch (type) {
            case AllAndCreatedPlanActivity.PROJECTS_TYPE_DRAFTBOX:
                sburl.append(Urls.HOST_PROJECT_BUSSINESSLIST);
                break;
            case AllAndCreatedPlanActivity.PROJECTS_TYPE_BUSSINESS:
                sburl.append(Urls.HOST_PROJECT_BUSSINESSLIST);
                break;
            case 2:
                sburl.append(Urls.HOST_PROJECT_BUSSINESSLIST);//已审核
                break;
        }
        return sburl.toString();
    }
}
