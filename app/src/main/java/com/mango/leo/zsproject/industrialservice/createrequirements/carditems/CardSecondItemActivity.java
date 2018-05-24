package com.mango.leo.zsproject.industrialservice.createrequirements.carditems;

import android.content.Intent;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.mango.leo.zsproject.R;
import com.mango.leo.zsproject.industrialservice.createrequirements.BusinessPlanActivity;
import com.mango.leo.zsproject.industrialservice.createrequirements.carditems.basecard.BaseCardActivity;
import com.mango.leo.zsproject.industrialservice.createrequirements.carditems.bean.CardSecondItemBean;
import com.mango.leo.zsproject.industrialservice.createrequirements.carditems.presenter.UpdateItemPresenter;
import com.mango.leo.zsproject.industrialservice.createrequirements.carditems.presenter.UpdateItemPresenterImpl;
import com.mango.leo.zsproject.industrialservice.createrequirements.carditems.view.UpdateItemView;
import com.mango.leo.zsproject.utils.AppUtils;

import org.greenrobot.eventbus.EventBus;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CardSecondItemActivity extends BaseCardActivity implements UpdateItemView {
    public static final int TYPE2 = 2;
    @Bind(R.id.imageView2_back)
    ImageView imageView2Back;
    @Bind(R.id.constraintLayout)
    ConstraintLayout constraintLayout;
    @Bind(R.id.button2_save)
    Button buttonSave;
    private UpdateItemPresenter updateItemPresenter;
    private CardSecondItemBean cardSecondItemBean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_second_item);
        ButterKnife.bind(this);
        updateItemPresenter = new UpdateItemPresenterImpl(this);
        cardSecondItemBean = new CardSecondItemBean();
    }

    @OnClick({R.id.imageView2_back, R.id.button2_save})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.imageView2_back:
                Intent intent = new Intent(this, BusinessPlanActivity.class);
                startActivity(intent);
                finish();
                break;
            case R.id.button2_save:
                updateItemPresenter.visitUpdateItem(this, TYPE2, cardSecondItemBean);//更新后台数据
                break;
        }
    }

    @Override
    public void showUpdateStateView(final String string) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                AppUtils.showToast(getApplicationContext(), string);
            }
        });
    }

    @Override
    public void showUpdateFailMsg(final String string) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                AppUtils.showToast(getApplicationContext(), string);
            }
        });
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
        EventBus.getDefault().unregister(this);
    }
}
