package com.mango.leo.zsproject.login;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.mango.leo.zsproject.R;
import com.mango.leo.zsproject.ZsActivity;
import com.mango.leo.zsproject.base.BaseActivity;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PwdLoginActivity extends BaseActivity {

    @Bind(R.id.imageView_pwd_back)
    ImageView imageViewPwdBack;
    @Bind(R.id.editText_phoneNum)
    EditText editTextPhoneNum;
    @Bind(R.id.editText_pwd)
    EditText editTextPwd;
    @Bind(R.id.togglePwd)
    ToggleButton togglePwd;
    @Bind(R.id.button_login)
    Button buttonLogin;
    @Bind(R.id.textView_phnoelogin)
    TextView textViewPhnoelogin;
    @Bind(R.id.textView_for)
    TextView textViewFor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pwd_login);
        ButterKnife.bind(this);
        ifShowPwd();
    }

    private void ifShowPwd() {
        togglePwd.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    //如果选中，显示密码
                    editTextPwd.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                } else {
                    //否则隐藏密码
                    editTextPwd.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
            }
        });
    }

    @OnClick({R.id.imageView_pwd_back, R.id.button_login, R.id.textView_phnoelogin, R.id.textView_for})
    public void onViewClicked(View view) {
        Intent intent;
        switch (view.getId()) {
            case R.id.imageView_pwd_back:
                finish();
                break;
            case R.id.button_login:
                intent = new Intent(this, ZsActivity.class);
                startActivity(intent);
                finish();
                break;
            case R.id.textView_phnoelogin:
                intent = new Intent(this, PhoneLoginActivity.class);
                startActivity(intent);
                finish();
                break;
            case R.id.textView_for:
                break;
        }
    }
}
