package com.mango.leo.zsproject.personalcenter.show;

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
import com.mango.leo.zsproject.base.BaseActivity;
import com.mango.leo.zsproject.industrialservice.createrequirements.util.ProjectsJsonUtils;
import com.mango.leo.zsproject.login.bean.UserMessageBean;
import com.mango.leo.zsproject.personalcenter.show.userchange.CompanyActivity;
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

public class ChangePhoneActivity extends BaseActivity {

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
    private SharedPreferences sharedPreferences;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_phone);
        ButterKnife.bind(this);
        sharedPreferences = getSharedPreferences("CIFIT", MODE_PRIVATE);
    }

    @OnClick({R.id.imageView_changephone_back, R.id.verification_code, R.id.button_changephoneok})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.imageView_changephone_back:
                finish();
                break;
            case R.id.verification_code:
                //获取验证码
                getPhoneCode();
                break;
            case R.id.button_changephoneok:
                //请求重新绑定
                changePhone();
                break;
        }
    }

    private void getPhoneCode() {
        HttpUtils.doGet(Urls.HOST_CODE + "?phoneOrEmail=" + editTextPhone.getText().toString(), new Callback() {
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

    private void changePhone() {
        Map<String, String> mapParams = new HashMap<String, String>();
        Log.v("changePhone",sharedPreferences.getString("token","")+"****"+editTextPhone.getText().toString()+"  "+editTextVerificationCode.getText().toString());
        mapParams.put("newPhone",editTextPhone.getText().toString());
        mapParams.put("code",editTextVerificationCode.getText().toString());
        mapParams.put("token",sharedPreferences.getString("token",""));
        HttpUtils.doPut(Urls.HOST_PHONE, mapParams, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.v("changePhone",e.getMessage()+"_e__");
                mHandler.sendEmptyMessage(2);
            }
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (String.valueOf(response.code()).startsWith("2")){
                    mHandler.sendEmptyMessage(3);
                    Log.v("changePhone","___");

                }else {
                    Log.v("changePhone",response.body().string()+"___");
                    mHandler.sendEmptyMessage(2);
                }
            }
        });
    }

    private final ChangePhoneActivity.MyHandler mHandler = new ChangePhoneActivity.MyHandler(this);

    private class MyHandler extends Handler {
        private final WeakReference<ChangePhoneActivity> mActivity;

        public MyHandler(ChangePhoneActivity activity) {
            mActivity = new WeakReference<ChangePhoneActivity>(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            ChangePhoneActivity activity = mActivity.get();
            if (activity != null) {
                switch (msg.what) {
                    case 0:
                        AppUtils.showToast(activity, "验证码发送失败");
                        break;
                    case 1:
                        AppUtils.showToast(activity, "验证码发送成功");
                        break;
                    case 2:
                        AppUtils.showToast(activity, "绑定新号码失败");
                        break;
                    case 3:
                        Intent intent = new Intent(activity, AccountSecurityActivity.class);
                        startActivity(intent);
                        AppUtils.showToast(activity, "成功绑定新号码");
                        break;
                    default:
                        break;
                }
            }
        }
    }
}
