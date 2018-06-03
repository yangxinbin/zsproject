package com.mango.leo.zsproject.personalcenter.show;

import android.content.Intent;
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

public class ChangePhoneActivity extends AppCompatActivity {

    @Bind(R.id.imageView_changephone_back)
    ImageView imageViewChangephoneBack;
    @Bind(R.id.editText_phone)
    EditText editTextPhone;
    @Bind(R.id.editText_verification_code)
    EditText editTextVerificationCode;
    @Bind(R.id.verification_code)
    TextView verificationCode;
    @Bind(R.id.button_changephoneok)
    Button buttonChangephoneok;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_phone);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.imageView_changephone_back, R.id.verification_code, R.id.button_changephoneok})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.imageView_changephone_back:
                finish();
                break;
            case R.id.verification_code:
                //获取验证码
                break;
            case R.id.button_changephoneok:
                //请求重新绑定
                break;
        }
    }
}
