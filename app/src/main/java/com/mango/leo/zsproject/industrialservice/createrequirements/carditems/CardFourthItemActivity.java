package com.mango.leo.zsproject.industrialservice.createrequirements.carditems;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.mango.leo.zsproject.R;
import com.mango.leo.zsproject.industrialservice.createrequirements.BusinessPlanActivity;
import com.mango.leo.zsproject.industrialservice.createrequirements.carditems.basecard.BaseCardActivity;
import com.mango.leo.zsproject.industrialservice.createrequirements.carditems.bean.CardFourthItemBean;
import com.mango.leo.zsproject.industrialservice.createrequirements.carditems.presenter.UpdateItemPresenter;
import com.mango.leo.zsproject.industrialservice.createrequirements.carditems.presenter.UpdateItemPresenterImpl;
import com.mango.leo.zsproject.industrialservice.createrequirements.carditems.view.UpdateItemView;
import com.mango.leo.zsproject.utils.AppUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CardFourthItemActivity extends BaseCardActivity implements UpdateItemView {
    public static final int TYPE4 = 4;
    @Bind(R.id.imageView4_back)
    ImageView imageView4Back;
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
    @Bind(R.id.delete4)
    TextView delete4;
    @Bind(R.id.relativeLayout2)
    RelativeLayout relativeLayout2;
    private UpdateItemPresenter updateItemPresenter;
    private CardFourthItemBean cardFourthItemBean;
    private List<CardFourthItemBean> beans4;
    private int position = 0;
    private String projectId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_fourth_item);
        ButterKnife.bind(this);
        updateItemPresenter = new UpdateItemPresenterImpl(this);
        cardFourthItemBean = new CardFourthItemBean();
        beans4 = new ArrayList<>();
        if (beans4 != null) {
            beans4.clear();
        }
        position = getIntent().getIntExtra("position", 0);
        Log.v("44444444", position + "___onCreate____");

        EventBus.getDefault().register(this);

    }

    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void card4EventBus(List<CardFourthItemBean> bean) {
        Log.v("44444444", position + "___card4EventBus___" + beans4.size());
        beans4 = bean;
        if (beans4.size() == position) {
            Log.v("44444444", position + "___add___");
            return;
        }
        editTextName.setText(bean.get(position).getName());
        editTextCompany.setText(bean.get(position).getCompany());
        editTextPhoneNumber.setText(bean.get(position).getPhoneNumber());
        editTextPosition.setText(bean.get(position).getPosition());
        editTextEmail.setText(bean.get(position).getEmail());
       // cardFourthItemBean.setProjectId(bean.get(position).getProjectId());
    }

    private void initDate() {
        cardFourthItemBean.setName(editTextName.getText().toString());
        cardFourthItemBean.setCompany(editTextCompany.getText().toString());
        cardFourthItemBean.setPhoneNumber(editTextPhoneNumber.getText().toString());
        cardFourthItemBean.setPosition(editTextPosition.getText().toString());
        cardFourthItemBean.setEmail(editTextEmail.getText().toString());
        Log.v("44444444", position + "____initDate____" + cardFourthItemBean.getName());
        if (beans4.size() == position) {
            beans4.add(position, cardFourthItemBean);//第几个修改第几个
            Log.v("44444444", "____adddd____" + beans4.get(position).getName());
        } else {
            beans4.set(position, cardFourthItemBean);//第几个修改第几个
        }
    }

    @OnClick({R.id.imageView4_back, R.id.button4_save, R.id.delete4})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.imageView4_back:
                Intent intent = new Intent(this, BusinessPlanActivity.class);
                startActivity(intent);
                finish();
                break;
            case R.id.button4_save:
                initDate();
                if (!TextUtils.isEmpty(editTextName.getText().toString()) && !TextUtils.isEmpty(editTextCompany.getText().toString()) && !TextUtils.isEmpty(editTextPhoneNumber.getText().toString()) && !TextUtils.isEmpty(editTextPosition.getText().toString()) && !TextUtils.isEmpty(editTextEmail.getText().toString()) && cardFourthItemBean != null) {
                    updateItemPresenter.visitUpdateItem(this, TYPE4, beans4);//更新后台数据
                    EventBus.getDefault().postSticky(beans4);
                    intent = new Intent(this, BusinessPlanActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    AppUtils.showSnackar(buttonSave, "必填项不能为空！");
                }
                break;
            case R.id.delete4:
                beans4.remove(position);
                Log.v("44444444", position + "____ddd____" + beans4.size());
                EventBus.getDefault().postSticky(beans4);
                updateItemPresenter.visitUpdateItem(this, TYPE4, beans4);//更新后台数据
                intent = new Intent(this, BusinessPlanActivity.class);
                startActivity(intent);
                finish();
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
