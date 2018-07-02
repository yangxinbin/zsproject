package com.mango.leo.zsproject.personalcenter.show.shenbao.view;

import com.mango.leo.zsproject.personalcenter.show.shenbao.bean.ShenBaoBean;

import java.util.List;

/**
 * Created by admin on 2018/5/21.
 */

public interface ShenbaoProjectsView {
    void addShengbaoSuccess(List<ShenBaoBean> shengBaoBeans);
    void addShengbaoFail(String e);
}
