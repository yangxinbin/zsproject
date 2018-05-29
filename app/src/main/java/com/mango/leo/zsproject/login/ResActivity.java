package com.mango.leo.zsproject.login;

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

public class ResActivity extends AppCompatActivity {

    @Bind(R.id.imageView_phoneres_back)
    ImageView imageViewPhoneresBack;
    @Bind(R.id.editText_phone_res)
    EditText editTextPhoneRes;
    @Bind(R.id.editText_verification_code_res)
    EditText editTextVerificationCodeRes;
    @Bind(R.id.verification_code_res)
    TextView verificationCodeRes;
    @Bind(R.id.button_res)
    Button buttonRes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_res);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.imageView_phoneres_back, R.id.verification_code_res, R.id.button_res})
    public void onViewClicked(View view) {
        Intent intent;
        switch (view.getId()) {
            case R.id.imageView_phoneres_back:
                break;
            case R.id.verification_code_res:
                break;
            case R.id.button_res:
                intent = new Intent(this, PwdSettingActivity.class);
                startActivity(intent);
                finish();
                break;
        }
    }
}
