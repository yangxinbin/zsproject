package com.mango.leo.zsproject.industrialservice.createrequirements.carditems;

import android.content.Intent;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.mango.leo.zsproject.R;
import com.mango.leo.zsproject.industrialservice.createrequirements.BusinessPlanActivity;
import com.mango.leo.zsproject.industrialservice.createrequirements.carditems.basecard.BaseCardActivity;

import org.greenrobot.eventbus.EventBus;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CardFourthItemActivity extends BaseCardActivity {
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_fourth_item);
        ButterKnife.bind(this);
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
                break;
        }
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
        EventBus.getDefault().unregister(this);
    }
}
