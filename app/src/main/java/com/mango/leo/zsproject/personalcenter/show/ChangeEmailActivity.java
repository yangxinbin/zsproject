package com.mango.leo.zsproject.personalcenter.show;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
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
import java.util.HashMap;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class ChangeEmailActivity extends BaseActivity {

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
    private SharedPreferences sharedPreferences;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_email);
        ButterKnife.bind(this);
        sharedPreferences = getSharedPreferences("CIFIT", MODE_PRIVATE);
    }
    CountDownTimer timer = new CountDownTimer(60000, 1000) {
        @Override
        public void onTick(long millisUntilFinished) {
            verificationCode.setText("   "+millisUntilFinished/1000 + "秒");
        }
        @Override
        public void onFinish() {
            verificationCode.setEnabled(true);
            verificationCode.setText("发送验证码");
        }
    };
    @OnClick({R.id.imageView_changeemail_back, R.id.verification_code, R.id.button_changeemailok})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.imageView_changeemail_back:
                finish();
                break;
            case R.id.verification_code:
                //获取验证码
                timer.start();
                getEmailCode();
                break;
            case R.id.button_changeemailok:
                //请求重新绑定
                changeEmail();
                break;
        }
    }

    private void getEmailCode() {
        HttpUtils.doGet(Urls.HOST_CODE + "?phoneOrEmail=" + editTextEmail.getText().toString(), new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                mHandler.sendEmptyMessage(0);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (String.valueOf(response.code()).startsWith("2")) {
                    mHandler.sendEmptyMessage(1);
                } else {
                    mHandler.sendEmptyMessage(0);
                }
            }
        });
    }

    private void changeEmail() {
        Map<String, String> mapParams = new HashMap<String, String>();
        mapParams.put("newEmail",editTextEmail.getText().toString());
        mapParams.put("code",editTextVerificationCode.getText().toString());
        mapParams.put("token",sharedPreferences.getString("token",""));
        HttpUtils.doPut(Urls.HOST_EMAIL, mapParams, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                mHandler.sendEmptyMessage(2);
            }
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (String.valueOf(response.code()).startsWith("2")){
                    mHandler.sendEmptyMessage(3);
                }else {
                    mHandler.sendEmptyMessage(2);
                }
            }
        });
    }
    private final ChangeEmailActivity.MyHandler mHandler = new ChangeEmailActivity.MyHandler(this);

    private class MyHandler extends Handler {
        private final WeakReference<ChangeEmailActivity> mActivity;

        public MyHandler(ChangeEmailActivity activity) {
            mActivity = new WeakReference<ChangeEmailActivity>(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            ChangeEmailActivity activity = mActivity.get();
            if (activity != null) {
                switch (msg.what) {
                    case 0:
                        AppUtils.showToast(activity, "验证码发送失败");
                        break;
                    case 1:
                        AppUtils.showToast(activity, "验证码发送成功");
                        break;
                    case 2:
                        AppUtils.showToast(activity, "绑定新邮箱失败");
                        break;
                    case 3:
                        AppUtils.showToast(activity, "成功绑定新邮箱");
                        Intent intent = new Intent(activity, AccountSecurityActivity.class);
                        startActivity(intent);
                        break;
                    default:
                        break;
                }
            }
        }
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
        if (timer != null){
            timer.cancel();
        }
    }
}
