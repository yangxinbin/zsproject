package com.mango.leo.zsproject.login;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.mango.leo.zsproject.R;
import com.mango.leo.zsproject.ZsActivity;
import com.mango.leo.zsproject.base.BaseActivity;
import com.mango.leo.zsproject.login.bean.UserMessageBean;
import com.mango.leo.zsproject.login.bean.User;
import com.mango.leo.zsproject.login.presenter.UserStatePresenter;
import com.mango.leo.zsproject.login.presenter.UserStatePresenterImpl;
import com.mango.leo.zsproject.login.view.UserStateView;
import com.mango.leo.zsproject.utils.AppUtils;

import java.lang.ref.WeakReference;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PwdSettingActivity extends BaseActivity implements UserStateView {

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
    UserStatePresenter userStatePresenter;
    private User user;
    private SharedPreferences sharedPreferences;
    private static SharedPreferences.Editor editor;
    private String username;
    private static String token;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pwd_setting);
        ButterKnife.bind(this);
        username = getIntent().getStringExtra("username");
        userStatePresenter = new UserStatePresenterImpl(this);
        sharedPreferences = getSharedPreferences("CIFIT",MODE_PRIVATE);
        editor = sharedPreferences.edit();

    }
    private void initEdit() {
        user = new User(editTextPwdok.getText().toString(), username);
        //通过editor对象写入数据
        editor.putString("authPhone",username);//都是电话号码
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
                initEdit();
                if (user != null && editTextPwd.getText().equals(editTextPwdok.getText())){
                    userStatePresenter.visitPwdUserState(this, 5, user);
                }else {
                    AppUtils.showToast(this,"两次密码输入不相同");
                }

                break;
        }
    }

    @Override
    public void showStateView(String string) {
        //里面不能更新UI
        Intent intent;
        if (string.equals("SET_SUCCESS")) {
            editor.putString("isOk", "yes")
                    .commit();
            mHandler.sendEmptyMessage(0);
            // Toast.makeText(this, s, Toast.LENGTH_LONG);
            intent = new Intent(this, UserActivity.class);
            startActivity(intent);
            finish();
        }if (string.equals("SET_FAILURE)")){
            mHandler.sendEmptyMessage(1);
        }
    }
    private final PwdSettingActivity.MyHandler mHandler = new PwdSettingActivity.MyHandler(this);

    private static class MyHandler extends Handler {
        private final WeakReference<PwdSettingActivity> mActivity;

        public MyHandler(PwdSettingActivity activity) {
            mActivity = new WeakReference<PwdSettingActivity>(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            PwdSettingActivity activity = mActivity.get();
            if (activity != null) {
                switch (msg.what) {
                    case 0:
                        editor.commit();
                        AppUtils.showToast(activity, "密码设置成功");
                        break;
                    case 1:
                        AppUtils.showToast(activity, "密码设置失败");
                        break;
                    case 2:
                        AppUtils.showToast(activity, "令牌保存成功");
                        editor.putString("token",token)
                                .commit();
                        Log.v("ttttt","--------------"+token);
                        break;
                    default:
                        break;
                }
            }
        }
    }
    @Override
    public void showVisitFailMsg(String string) {
        mHandler.sendEmptyMessage(1);
    }

    @Override
    public void responeUserMessage(UserMessageBean bean) {
        if (bean.getResponseObject().getToken() != "" && bean.getResponseObject().getToken() != null && bean.getResponseObject() != null && bean != null){
            token = bean.getResponseObject().getToken();
            mHandler.sendEmptyMessage(2);
        }
    }
}
