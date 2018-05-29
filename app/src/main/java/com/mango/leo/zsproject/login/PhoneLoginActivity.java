package com.mango.leo.zsproject.login;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.mango.leo.zsproject.R;
import com.mango.leo.zsproject.base.BaseActivity;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PhoneLoginActivity extends BaseActivity {

    @Bind(R.id.imageView_phonelogin_back)
    ImageView imageViewPhoneloginBack;
    @Bind(R.id.editText_phone)
    EditText editTextPhone;
    @Bind(R.id.editText_verification_code)
    EditText editTextVerificationCode;
    @Bind(R.id.verification_code)
    TextView buttonVerificationCode;
    @Bind(R.id.button_login)
    Button buttonLogin;
    @Bind(R.id.textView_pwdlogin)
    TextView textViewPwdlogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.verification_code, R.id.button_login, R.id.textView_pwdlogin})
    public void onViewClicked(View view) {
        Intent intent;
        switch (view.getId()) {
            case R.id.verification_code:
                break;
            case R.id.button_login:
                finish();
                break;
            case R.id.textView_pwdlogin:
                intent = new Intent(this, PwdLoginActivity.class);
                startActivity(intent);
                break;
        }
    }
}
