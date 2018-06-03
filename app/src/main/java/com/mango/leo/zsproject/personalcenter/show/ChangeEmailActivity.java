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

public class ChangeEmailActivity extends AppCompatActivity {

    @Bind(R.id.imageView_changeemail_back)
    ImageView imageViewChangeemailBack;
    @Bind(R.id.editText_email)
    EditText editTextEmail;
    @Bind(R.id.editText_verification_code)
    EditText editTextVerificationCode;
    @Bind(R.id.verification_code)
    TextView verificationCode;
    @Bind(R.id.button_changeemailok)
    Button buttonChangeemailok;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_email);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.imageView_changeemail_back, R.id.verification_code, R.id.button_changeemailok})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.imageView_changeemail_back:
                finish();
                break;
            case R.id.verification_code:
                //获取验证码
                break;
            case R.id.button_changeemailok:
                //请求重新绑定
                break;
        }
    }
}
