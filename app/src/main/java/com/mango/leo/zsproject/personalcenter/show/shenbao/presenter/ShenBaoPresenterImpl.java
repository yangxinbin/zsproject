package com.mango.leo.zsproject.personalcenter.show.shenbao.presenter;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.mango.leo.zsproject.personalcenter.show.shenbao.bean.ShenBaoBean;
import com.mango.leo.zsproject.personalcenter.show.shenbao.listener.OnShenBaoListener;
import com.mango.leo.zsproject.personalcenter.show.shenbao.model.ShenBaoModel;
import com.mango.leo.zsproject.personalcenter.show.shenbao.model.ShenBaoModelImpl;
import com.mango.leo.zsproject.personalcenter.show.shenbao.view.ShenbaoProjectsView;
import com.mango.leo.zsproject.utils.Urls;

import java.util.List;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by admin on 2018/5/21.
 */

public class ShenBaoPresenterImpl implements ShenBaoPresenter, OnShenBaoListener {
    private ShenbaoProjectsView shengbaoProjectsView;
    private ShenBaoModel allProjectsModel;
    private SharedPreferences sharedPreferences;
    public ShenBaoPresenterImpl(ShenbaoProjectsView u) {
        this.shengbaoProjectsView = u;
        this.allProjectsModel = new ShenBaoModelImpl();
    }

    @Override
    public void visitProjects(Context context, int type,String projectId,int page) {
        sharedPreferences = context.getSharedPreferences("CIFIT",MODE_PRIVATE);
        String url;
        if (type == 0){
            url = getUrl(type,context)+"?token="+sharedPreferences.getString("token", "")+"&type=investor"+"&projectId="+projectId+"&page="+page;
        }else {
            url = getUrl(type,context)+"?token="+sharedPreferences.getString("token", "")+"&type=venture"+"&projectId="+projectId+"&page="+page;
        }
        Log.v("pppppppppppp",""+url);
        allProjectsModel.visitProjects(context, type, url, this);
    }

    @Override
    public void onSuccess(List<ShenBaoBean> list) {
        shengbaoProjectsView.addShengbaoSuccess(list);
    }

    @Override
    public void onFailure(String msg, Exception e) {
        shengbaoProjectsView.addShengbaoFail(msg);
    }

    private String getUrl(int type,Context context) {
        StringBuffer sburl = new StringBuffer();
        switch (type) {
            case 0:
                sburl.append(Urls.HOST_REQUEST);
                break;
            case 1:
                sburl.append(Urls.HOST_REQUEST);
                break;
        }
        return sburl.toString();
    }
}
