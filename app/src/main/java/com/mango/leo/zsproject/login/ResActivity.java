package com.mango.leo.zsproject.login;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.mango.leo.zsproject.R;
import com.mango.leo.zsproject.login.bean.UserMessageBean;
import com.mango.leo.zsproject.login.bean.UserPhone;
import com.mango.leo.zsproject.login.presenter.UserStatePresenter;
import com.mango.leo.zsproject.login.presenter.UserStatePresenterImpl;
import com.mango.leo.zsproject.login.view.UserStateView;
import com.mango.leo.zsproject.utils.AppUtils;

import java.lang.ref.WeakReference;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ResActivity extends AppCompatActivity implements UserStateView {

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
    UserStatePresenter userStatePresenter;
    /*@Bind(R.id.editText_pwd)
    EditText editTextPwd;
    @Bind(R.id.editText_pwdok)
    EditText editTextPwdok;*/
    private UserPhone userPhone;
    private static String token;
    private SharedPreferences sharedPreferences;
    private static SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_res);
        userStatePresenter = new UserStatePresenterImpl(this);
        sharedPreferences = getSharedPreferences("CIFIT", MODE_PRIVATE);
        editor = sharedPreferences.edit();
        ButterKnife.bind(this);
    }

    private void initEdit() {
        userPhone = new UserPhone(editTextPhoneRes.getText().toString(), editTextVerificationCodeRes.getText().toString());
        //通过editor对象写入数据
    }

    @OnClick({R.id.imageView_phoneres_back, R.id.verification_code_res, R.id.button_res})
    public void onViewClicked(View view) {
        Intent intent;
        switch (view.getId()) {
            case R.id.imageView_phoneres_back:
                intent = new Intent(this, StartActivity.class);
                startActivity(intent);
                finish();
                break;
            case R.id.verification_code_res:
                initEdit();
                userStatePresenter.visitPwdUserState(this, 2, userPhone);
                break;
            case R.id.button_res:
                initEdit();
                userStatePresenter.visitPwdUserState(this, 4, userPhone);
                break;
        }
    }

    @Override
    public void showStateView(String string) {
        Intent intent;
        if (string.equals("RES_SUCCESS")) {
            mHandler.sendEmptyMessage(0);
            intent = new Intent(this, PwdSettingActivity.class);
            intent.putExtra("code",editTextVerificationCodeRes.getText().toString());
            intent.putExtra("username", editTextPhoneRes.getText().toString());
            startActivity(intent);
            finish();
        }
        if (string.equals("RES_FAILURE")) {
            mHandler.sendEmptyMessage(1);
        }
        if (string.equals("CODE_SUCCESS")) {
            mHandler.sendEmptyMessage(2);
        }
        if (string.equals("CODE_FAILURE")) {
            mHandler.sendEmptyMessage(3);
        }
    }

    @Override
    public void showVisitFailMsg(String string) {

    }

    @Override
    public void responeUserMessage(UserMessageBean bean) {
        if (bean.getResponseObject().getToken() != "" && bean.getResponseObject().getToken() != null && bean.getResponseObject() != null && bean != null) {
            token = bean.getResponseObject().getToken();
            mHandler.sendEmptyMessage(4);
            Log.v("tttttr1","--------------"+token);

        }
        Log.v("tttttr2","--------------"+token);
    }

    private final MyHandler mHandler = new MyHandler(this);

    private static class MyHandler extends Handler {
        private final WeakReference<ResActivity> mActivity;

        public MyHandler(ResActivity activity) {
            mActivity = new WeakReference<ResActivity>(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            ResActivity activity = mActivity.get();
            if (activity != null) {
                switch (msg.what) {
                    case 0:
                        AppUtils.showToast(activity, "请设置密码");
                        break;
                    case 1:
                        AppUtils.showToast(activity, "注册失败");
                        break;
                    case 2:
                        AppUtils.showToast(activity, "验证码获取成功");
                        break;
                    case 3:
                        AppUtils.showToast(activity, "验证码获取失败");
                        break;
                    case 4:
                        AppUtils.showToast(activity, "令牌保存成功");
                        editor.putString("token", token)
                                .commit();
                        Log.v("zzzzzz", "--------------" + token);
                        break;
                }
            }
        }
    }

}
