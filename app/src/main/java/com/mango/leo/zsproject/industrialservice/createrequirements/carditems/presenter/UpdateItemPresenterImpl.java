package com.mango.leo.zsproject.industrialservice.createrequirements.carditems.presenter;

import android.content.Context;

import com.mango.leo.zsproject.industrialservice.createrequirements.carditems.CardEigththItemActivity;
import com.mango.leo.zsproject.industrialservice.createrequirements.carditems.CardFifthItemActivity;
import com.mango.leo.zsproject.industrialservice.createrequirements.carditems.CardFirstItemActivity;
import com.mango.leo.zsproject.industrialservice.createrequirements.carditems.CardFourthItemActivity;
import com.mango.leo.zsproject.industrialservice.createrequirements.carditems.CardNinthItemActivity;
import com.mango.leo.zsproject.industrialservice.createrequirements.carditems.CardSecondItemActivity;
import com.mango.leo.zsproject.industrialservice.createrequirements.carditems.CardSeventhItemActivity;
import com.mango.leo.zsproject.industrialservice.createrequirements.carditems.CardSixthItemActivity;
import com.mango.leo.zsproject.industrialservice.createrequirements.carditems.CardThirdItemActivity;
import com.mango.leo.zsproject.industrialservice.createrequirements.carditems.listener.OnUpdateItemListener;
import com.mango.leo.zsproject.industrialservice.createrequirements.carditems.model.UpdateItemModel;
import com.mango.leo.zsproject.industrialservice.createrequirements.carditems.model.UpdateItemModelImpl;
import com.mango.leo.zsproject.industrialservice.createrequirements.carditems.view.UpdateItemView;
import com.mango.leo.zsproject.utils.Urls;

/**
 * Created by admin on 2018/5/22.
 */

public class UpdateItemPresenterImpl implements UpdateItemPresenter, OnUpdateItemListener {

    private UpdateItemView updateItemView;
    private UpdateItemModel updateItemModel;

    public UpdateItemPresenterImpl(UpdateItemView u) {
        this.updateItemView = u;
        this.updateItemModel = new UpdateItemModelImpl();
    }

    @Override
    public void visitUpdateItem(Context context, int type, Object o) {
        String url = getUrl(type);
        updateItemModel.visitUpdateItem(context,type,o,url,this);
    }

    @Override
    public void onSuccess(String string) {
        updateItemView.showUpdateStateView(string);
    }

    @Override
    public void onFailure(String msg, Exception e) {
        updateItemView.showUpdateFailMsg(msg);
    }

    private String getUrl(int type) {
        StringBuffer sburl = new StringBuffer();
        switch (type) {
            case CardFirstItemActivity.TYPE1:
                sburl.append(Urls.HOST_PROJECT).append(Urls.UPDATE1);
                break;
            case CardSecondItemActivity.TYPE2:
                sburl.append(Urls.HOST_PROJECT).append(Urls.UPDATE2);
                break;
            case CardThirdItemActivity.TYPE3:
                sburl.append(Urls.HOST_PROJECT).append(Urls.UPDATE3);
                break;
            case CardFourthItemActivity.TYPE4:
                sburl.append(Urls.HOST_PROJECT).append(Urls.UPDATE4);
                break;
            case CardFifthItemActivity.TYPE5:
                sburl.append(Urls.HOST_PROJECT).append(Urls.UPDATE5);
                break;
            case CardSixthItemActivity.TYPE6:
                sburl.append(Urls.HOST_PROJECT).append(Urls.UPDATE6);
                break;
            case CardSeventhItemActivity.TYPE7:
                sburl.append(Urls.HOST_PROJECT).append(Urls.UPDATE7);
                break;
            case CardEigththItemActivity.TYPE8:
                sburl.append(Urls.HOST_PROJECT).append(Urls.UPDATE8);
                break;
            case CardNinthItemActivity.TYPE9:
                sburl.append(Urls.HOST_PROJECT).append(Urls.UPDATE9);
                break;
        }
        return sburl.toString();
    }
}
