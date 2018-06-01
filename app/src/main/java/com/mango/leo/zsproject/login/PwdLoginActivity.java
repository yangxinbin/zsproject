package com.mango.leo.zsproject.login;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.mango.leo.zsproject.R;
import com.mango.leo.zsproject.ZsActivity;
import com.mango.leo.zsproject.base.BaseActivity;
import com.mango.leo.zsproject.login.bean.User;
import com.mango.leo.zsproject.login.presenter.UserStatePresenter;
import com.mango.leo.zsproject.login.presenter.UserStatePresenterImpl;
import com.mango.leo.zsproject.login.view.UserStateView;
import com.mango.leo.zsproject.utils.AppUtils;

import java.lang.ref.WeakReference;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PwdLoginActivity extends BaseActivity implements UserStateView {

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
    UserStatePresenter userStatePresenter;
    private User user;
    private SharedPreferences sharedPreferences;
    private static SharedPreferences.Editor editor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pwd_login);
        ButterKnife.bind(this);
        ifShowPwd();
        userStatePresenter = new UserStatePresenterImpl(this);
        sharedPreferences = getSharedPreferences("CIFIT",MODE_PRIVATE);
        editor = sharedPreferences.edit();
        if (sharedPreferences.getString("isOk","no").equals("yes")){
            Intent intent = new Intent(this, ZsActivity.class);
            startActivity(intent);
            finish();
        }
    }

    private void initEdit() {
        user = new User(editTextPhoneNum.getText().toString(), editTextPwd.getText().toString());
        //通过editor对象写入数据
        editor.putString("authPhone",editTextPhoneNum.getText().toString());
    }

    private void ifShowPwd() {
        togglePwd.setChecked(false);
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
                initEdit();
                userStatePresenter.visitPwdUserState(this,1, user);
                Log.v("yyyy", user.getUserPwd() + "====initEdit=====" + user.getUserName());
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

    @Override
    public void showStateView(String s) {
        //里面不能更新UI
        Intent intent;
        if (s.equals("SUCCESS")) {
            sharedPreferences = getSharedPreferences("isOk", MODE_PRIVATE);
            editor.putString("isOk", "yes")
                    .commit();
            mHandler.sendEmptyMessage(0);
            // Toast.makeText(this, s, Toast.LENGTH_LONG);
            intent = new Intent(this, ZsActivity.class);
            startActivity(intent);
            finish();
        }else {
            mHandler.sendEmptyMessage(1);
        }
    }

    @Override
    public void showVisitFailMsg(String string) {
        mHandler.sendEmptyMessage(0);
    }

    private final MyHandler mHandler = new MyHandler(this);

    private static class MyHandler extends Handler {
        private final WeakReference<PwdLoginActivity> mActivity;

        public MyHandler(PwdLoginActivity activity) {
            mActivity = new WeakReference<PwdLoginActivity>(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            PwdLoginActivity activity = mActivity.get();
            if (activity != null) {
                switch (msg.what) {
                    case 0:
                        editor.commit();
                        AppUtils.showToast(activity, "SUCCESS");
                        break;
                    case 1:
                        AppUtils.showToast(activity, "ERROR");
                        break;
                    default:
                        break;
                }
            }
        }
    }
}
