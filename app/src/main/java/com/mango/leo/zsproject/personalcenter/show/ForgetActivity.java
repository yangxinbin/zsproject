package com.mango.leo.zsproject.personalcenter.show;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.mango.leo.zsproject.R;
import com.mango.leo.zsproject.base.BaseActivity;
import com.mango.leo.zsproject.utils.AppUtils;
import com.mango.leo.zsproject.utils.HttpUtils;
import com.mango.leo.zsproject.utils.Urls;

import java.io.IOException;
import java.lang.ref.WeakReference;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class ForgetActivity extends BaseActivity {

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
                getCode();
                break;
            case R.id.button_next:
                //去请求 成功之后再跳转 相当于验证码登录
                Intent intent = new Intent(this, ChangePwdForActivity.class);
                intent.putExtra("username",editTextPhone.getText().toString());
                intent.putExtra("code",editTextVerificationCode.getText().toString());
                startActivity(intent);
                break;
        }
    }

    private void getCode() {
        HttpUtils.doGet(Urls.HOST_CODE+"?phoneOrEmail="+editTextPhone.getText().toString(), new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                mHandler.sendEmptyMessage(0);
            }
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (String.valueOf(response.code()).startsWith("2")){
                    mHandler.sendEmptyMessage(1);
                }else {
                    mHandler.sendEmptyMessage(0);
                }
            }
        });
    }
    private final ForgetActivity.MyHandler mHandler = new ForgetActivity.MyHandler(this);

    private class MyHandler extends Handler {
        private final WeakReference<ForgetActivity> mActivity;

        public MyHandler(ForgetActivity activity) {
            mActivity = new WeakReference<ForgetActivity>(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            ForgetActivity activity = mActivity.get();
            if (activity != null) {
                switch (msg.what) {
                    case 0:
                        AppUtils.showToast(activity, "验证码发送失败");
                        break;
                    case 1:
                        AppUtils.showToast(activity, "验证码发送成功");

                        break;
                    default:
                        break;
                }
            }
        }
    }
}
