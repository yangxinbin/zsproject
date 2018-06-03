package com.mango.leo.zsproject.personalcenter.show;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.mango.leo.zsproject.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ForgetActivity extends AppCompatActivity {

    @Bind(R.id.imageView_forget_back)
    ImageView imageViewForgetBack;
    @Bind(R.id.editText_phone)
    EditText editTextPhone;
    @Bind(R.id.editText_verification_code)
    EditText editTextVerificationCode;
    @Bind(R.id.verification_code)
    TextView verificationCode;
    @Bind(R.id.button_next)
    Button buttonNext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.imageView_forget_back, R.id.verification_code, R.id.button_next})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.imageView_forget_back:
                finish();
                break;
            case R.id.verification_code:
                //去获取验证码
                break;
            case R.id.button_next:
                //去请求 成功之后再跳转 相当于验证码登录
                break;
        }
    }
}
