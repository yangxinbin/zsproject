package com.mango.leo.zsproject.personalcenter.show.shenbao.presenter;

import android.content.Context;

/**
 * Created by admin on 2018/5/21.
 */

public interface ShenBaoPresenter {
    void visitProjects(Context context, int type,String projectId, int page);//刷新动作加载新闻数据
}
