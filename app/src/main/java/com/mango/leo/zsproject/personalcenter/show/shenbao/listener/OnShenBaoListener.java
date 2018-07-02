package com.mango.leo.zsproject.personalcenter.show.shenbao.listener;

import com.mango.leo.zsproject.personalcenter.show.shenbao.bean.ShenBaoBean;

import java.util.List;

/**
 * Created by admin on 2018/5/21.
 */

public interface OnShenBaoListener {
    void onSuccess(List<ShenBaoBean> list);
    void onFailure(String msg, Exception e);
}
