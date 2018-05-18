package com.mango.leo.zsproject.login;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.mango.leo.zsproject.R;
import com.mango.leo.zsproject.ZsActivity;
import com.mango.leo.zsproject.base.BaseActivity;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PwdSettingActivity extends BaseActivity {

    @Bind(R.id.editText_pwd)
    EditText editTextPwd;
    @Bind(R.id.editText_pwdok)
    EditText editTextPwdok;
    @Bind(R.id.button2_login)
    Button button2Login;
    @Bind(R.id.imageView16_back)
    ImageView imageView16Back;
    @Bind(R.id.relativeLayout)
    RelativeLayout relativeLayout;
    @Bind(R.id.textView15)
    TextView textView15;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pwd_setting);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.imageView16_back, R.id.button2_login})
    public void onViewClicked(View view) {
        Intent intent;
        switch (view.getId()) {
            case R.id.imageView16_back:
                intent = new Intent(this, PhoneLoginActivity.class);
                startActivity(intent);
                finish();
                break;
            case R.id.button2_login:
                intent = new Intent(this, ZsActivity.class);
                startActivity(intent);
                finish();
                break;
        }
    }
}
