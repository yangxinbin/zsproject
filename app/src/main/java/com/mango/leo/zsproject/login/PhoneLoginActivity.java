package com.mango.leo.zsproject.login;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
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
import com.mango.leo.zsproject.ZsActivity;
import com.mango.leo.zsproject.base.BaseActivity;
import com.mango.leo.zsproject.bean.ErrorBean;
import com.mango.leo.zsproject.industrialservice.createrequirements.util.ProjectsJsonUtils;
import com.mango.leo.zsproject.login.bean.UserMessageBean;
import com.mango.leo.zsproject.login.bean.User;
import com.mango.leo.zsproject.login.bean.UserPhone;
import com.mango.leo.zsproject.login.presenter.UserStatePresenter;
import com.mango.leo.zsproject.login.presenter.UserStatePresenterImpl;
import com.mango.leo.zsproject.login.util.CountDownTextView;
import com.mango.leo.zsproject.login.view.UserStateView;
import com.mango.leo.zsproject.utils.ACache;
import com.mango.leo.zsproject.utils.AppUtils;
import com.mango.leo.zsproject.utils.NetUtil;

import org.greenrobot.eventbus.EventBus;

import java.lang.ref.WeakReference;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PhoneLoginActivity extends BaseActivity implements UserStateView {

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
    UserStatePresenter userStatePresenter;
    private SharedPreferences sharedPreferences;
    private static SharedPreferences.Editor editor;
    private UserPhone userPhone;
    private static String token;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        userStatePresenter = new UserStatePresenterImpl(this);
        sharedPreferences = getSharedPreferences("CIFIT", MODE_PRIVATE);
        editor = sharedPreferences.edit();
        if (sharedPreferences.getString("isOk", "no").equals("yes")) {
            ACache mCache = ACache.get(this);
            EventBus.getDefault().postSticky(ProjectsJsonUtils.readJsonUserMessageBeans(mCache.getAsString("message")));
            Intent intent = new Intent(this, ZsActivity.class);
            startActivity(intent);
            finish();
        }
    }

    private void initEdit() {
        userPhone = new UserPhone();
        userPhone.setPhoneN(editTextPhone.getText().toString());
        userPhone.setPhoneC(editTextVerificationCode.getText().toString());
        //通过editor对象写入数据
        editor.putString("userName", editTextPhone.getText().toString());
    }

    @OnClick({R.id.verification_code, R.id.button_login, R.id.textView_pwdlogin, R.id.imageView_phonelogin_back})
    public void onViewClicked(View view) {
        if (!NetUtil.isNetConnect(this)) {
            AppUtils.showToast(this, "请连接网络");
            return;
        }
        Intent intent;
        switch (view.getId()) {
            case R.id.verification_code:
                Log.v("uuuuuuu", "!!!!!!" + editTextPhone.getText().length());
                if (editTextPhone.getText().length() == 11) {
                    timer.start();
                    initEdit();
                    Log.v("uuuuuuu", "!!!dsfs!!!" + userPhone.getPhoneN());
                    userStatePresenter.visitPwdUserState(this, 2, userPhone);
                } else {
                    AppUtils.showToast(getBaseContext(), "请输入正确的手机号码");
                }
                break;
            case R.id.button_login:
                initEdit();
                userStatePresenter.visitPwdUserState(this, 3, userPhone);
                break;
            case R.id.textView_pwdlogin:
                intent = new Intent(this, PwdLoginActivity.class);
                startActivity(intent);
                finish();
                break;
            case R.id.imageView_phonelogin_back:
                intent = new Intent(this, StartActivity.class);
                startActivity(intent);
                finish();
                break;
        }
    }

    @Override
    public void showStateView(String string) {
        //里面不能更新UI
        Intent intent;
        if (string.equals("CODE_SUCCESS")) {
            mHandler.sendEmptyMessage(2);
        }
        if (string.equals("CODE_FAILURE")) {
            mHandler.sendEmptyMessage(3);
        }
        if (string.equals("SUCCESS")) {
            editor.putString("isOk", "yes")
                    .commit();
            editor.putString("skip", "no")
                    .commit();
            mHandler.sendEmptyMessage(0);
            // Toast.makeText(this, s, Toast.LENGTH_LONG);
            intent = new Intent(this, ZsActivity.class);
            startActivity(intent);
            finish();
        }
        if (string.equals("FAILURE")) {
            mHandler.sendEmptyMessage(1);
        }
    }

    @Override
    public void showVisitFailMsg(String string) {
        if (string.equals("FAILURE")) {
            mHandler.sendEmptyMessage(1);
        }
        if (string.equals("CODE_FAILURE")) {
            mHandler.sendEmptyMessage(3);
        }

    }

    @Override
    public void responeUserMessage(UserMessageBean bean) {
        if (bean != null) {
            EventBus.getDefault().postSticky(bean);
            editor.putString("where", String.valueOf(bean.getResponseObject().getLocation().getProvince()) + String.valueOf(bean.getResponseObject().getLocation().getCity()) + String.valueOf(bean.getResponseObject().getLocation().getDistrict())).commit();
        }
        if (bean.getResponseObject().getTenant() == null) {
            editor.putString("type", "no").commit();
        } else {
            editor.putString("type", "yes").commit();
        }
        if (bean.getResponseObject().getToken() != "" && bean.getResponseObject().getToken() != null && bean.getResponseObject() != null && bean != null) {
            editor.putString("token", bean.getResponseObject().getToken())
                    .commit();
            //token = bean.getResponseObject().getToken();
            mHandler.sendEmptyMessage(4);
        }
    }

    @Override
    public void responeErrorUserMessage(final ErrorBean bean) {
        if (bean != null) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    AppUtils.showToast(getBaseContext(), bean.getMessage());
                }
            });
        }
    }

    private final PhoneLoginActivity.MyHandler mHandler = new PhoneLoginActivity.MyHandler(this);

    private class MyHandler extends Handler {
        private final WeakReference<PhoneLoginActivity> mActivity;

        public MyHandler(PhoneLoginActivity activity) {
            mActivity = new WeakReference<PhoneLoginActivity>(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            PhoneLoginActivity activity = mActivity.get();
            if (activity != null) {
                switch (msg.what) {
                    case 0:
                        editor.commit();
                        AppUtils.showToast(activity, "登录成功");
                        break;
                    case 1:
                        AppUtils.showToast(activity, "登录失败");
                        break;
                    case 2:
                        AppUtils.showToast(activity, "验证码获取成功");

                        break;
                    case 3:
                        AppUtils.showToast(activity, "验证码获取失败");
                        break;
                    case 4:
                        //AppUtils.showToast(activity, "令牌保存成功");
                        Log.v("zzzzzz", "--------------" + token);
                        break;
                    default:
                        break;
                }
            }
        }
    }

    CountDownTimer timer = new CountDownTimer(60000, 1000) {

        @Override
        public void onTick(long millisUntilFinished) {
            buttonVerificationCode.setText("   " + millisUntilFinished / 1000 + "秒");
        }

        @Override
        public void onFinish() {
            buttonVerificationCode.setEnabled(true);
            buttonVerificationCode.setText("发送验证码");
        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
        if (timer != null){
            timer.cancel();
        }
    }
}
