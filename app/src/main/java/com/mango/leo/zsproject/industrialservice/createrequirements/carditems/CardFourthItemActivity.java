package com.mango.leo.zsproject.industrialservice.createrequirements.carditems;

import android.content.Intent;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.mango.leo.zsproject.R;
import com.mango.leo.zsproject.industrialservice.createrequirements.BusinessPlanActivity;
import com.mango.leo.zsproject.industrialservice.createrequirements.carditems.basecard.BaseCardActivity;
import com.mango.leo.zsproject.industrialservice.createrequirements.carditems.bean.CardFirstItemBean;
import com.mango.leo.zsproject.industrialservice.createrequirements.carditems.bean.CardFourthItemBean;
import com.mango.leo.zsproject.industrialservice.createrequirements.carditems.presenter.UpdateItemPresenter;
import com.mango.leo.zsproject.industrialservice.createrequirements.carditems.presenter.UpdateItemPresenterImpl;
import com.mango.leo.zsproject.industrialservice.createrequirements.carditems.view.UpdateItemView;
import com.mango.leo.zsproject.utils.AppUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CardFourthItemActivity extends BaseCardActivity implements UpdateItemView {
    public static final int TYPE4 = 4;
    @Bind(R.id.imageView4_back)
    ImageView imageView4Back;
    @Bind(R.id.constraintLayout)
    ConstraintLayout constraintLayout;
    @Bind(R.id.button4_save)
    Button buttonSave;
    @Bind(R.id.editText_name)
    EditText editTextName;
    @Bind(R.id.editText_company)
    EditText editTextCompany;
    @Bind(R.id.editText_phoneNumber)
    EditText editTextPhoneNumber;
    @Bind(R.id.editText_position)
    EditText editTextPosition;
    @Bind(R.id.editText_email)
    EditText editTextEmail;
    private UpdateItemPresenter updateItemPresenter;
    private CardFourthItemBean cardFourthItemBean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_fourth_item);
        ButterKnife.bind(this);
        updateItemPresenter = new UpdateItemPresenterImpl(this);
        cardFourthItemBean = new CardFourthItemBean();
        EventBus.getDefault().register(this);

    }

    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void card1EventBus(CardFourthItemBean bean) {
        editTextName.setText(bean.getName());
        editTextCompany.setText(bean.getCompany());
        editTextPhoneNumber.setText(bean.getPhoneNumber());
        editTextPosition.setText(bean.getPosition());
        editTextEmail.setText(bean.getEmail());
        cardFourthItemBean.setProjectId(bean.getProjectId());

    }

    private void initDate() {
        cardFourthItemBean.setName(editTextName.getText().toString());
        cardFourthItemBean.setCompany(editTextCompany.getText().toString());
        cardFourthItemBean.setPhoneNumber(editTextPhoneNumber.getText().toString());
        cardFourthItemBean.setPosition(editTextPosition.getText().toString());
        cardFourthItemBean.setEmail(editTextEmail.getText().toString());
    }

    @OnClick({R.id.imageView4_back, R.id.button4_save})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.imageView4_back:
                Intent intent = new Intent(this, BusinessPlanActivity.class);
                startActivity(intent);
                finish();
                break;
            case R.id.button4_save:
                initDate();
//                Log.v("yyyyy","*****cardFirstItemBean*****"+cardFirstItemBean.getItemImagePath().get(0).getPath());
                if (!TextUtils.isEmpty(editTextName.getText().toString()) && !TextUtils.isEmpty(editTextCompany.getText().toString()) && !TextUtils.isEmpty(editTextPhoneNumber.getText().toString()) && !TextUtils.isEmpty(editTextPosition.getText().toString()) && cardFourthItemBean != null) {
                    updateItemPresenter.visitUpdateItem(this, TYPE4, cardFourthItemBean);//更新后台数据
                    EventBus.getDefault().postSticky(cardFourthItemBean);
                    intent = new Intent(this, BusinessPlanActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    AppUtils.showSnackar(buttonSave, "必填项不能为空！");
                }
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
        EventBus.getDefault().unregister(this);
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
}
