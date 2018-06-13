package com.mango.leo.zsproject.personalcenter.show;

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

public class ChangePwdForActivity extends BaseActivity {

    @Bind(R.id.imageView_change_back)
    ImageView imageViewChangeBack;
    @Bind(R.id.editText_newpwd)
    EditText editTextNewpwd;
    @Bind(R.id.editText_newpwdok)
    EditText editTextNewpwdok;
    @Bind(R.id.button2_finish)
    Button button2Finish;
    private SharedPreferences sharedPreferences;
    private String username,code;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_pwd2);
        ButterKnife.bind(this);
        sharedPreferences = getSharedPreferences("CIFIT", MODE_PRIVATE);
        username = getIntent().getStringExtra("username");
        code = getIntent().getStringExtra("code");

    }

    @OnClick({R.id.imageView_change_back, R.id.button2_finish})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.imageView_change_back:
                Intent intent = new Intent(this, AccountSecurityActivity.class);
                startActivity(intent);
                finish();
                break;
            case R.id.button2_finish:
                //修改密码
                if (editTextNewpwd.getText().toString().equals(editTextNewpwdok.getText().toString())) {
                    changePwd();
                } else {
                    AppUtils.showToast(this, "两次密码输入不相同");
                }
                break;
        }
    }

    private void changePwd() {
        Map<String, String> mapParams = new HashMap<String, String>();
        mapParams.put("username", username);
        mapParams.put("password", "");
        mapParams.put("code", code);
        mapParams.put("phone", username);
        mapParams.put("newPassword", editTextNewpwdok.getText().toString());
        HttpUtils.doPutRePhone(Urls.HOST_RETRIEVE, mapParams, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                mHandler.sendEmptyMessage(2);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (String.valueOf(response.code()).startsWith("2")) {
                    mHandler.sendEmptyMessage(3);
                } else {
                    Log.v("ccccc","*****"+response.body().string());
                    mHandler.sendEmptyMessage(2);
                }
            }
        });
    }

    private final ChangePwdForActivity.MyHandler mHandler = new ChangePwdForActivity.MyHandler(this);

    private class MyHandler extends Handler {
        private final WeakReference<ChangePwdForActivity> mActivity;

        public MyHandler(ChangePwdForActivity activity) {
            mActivity = new WeakReference<ChangePwdForActivity>(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            ChangePwdForActivity activity = mActivity.get();
            if (activity != null) {
                switch (msg.what) {
                    case 2:
                        AppUtils.showToast(activity, "密码重置失败");
                        break;
                    case 3:
                        AppUtils.showToast(activity, "密码重置成功");
                        Intent intent = new Intent(activity, AccountSecurityActivity.class);
                        startActivity(intent);
                        break;
                    default:
                        break;
                }
            }
        }
    }
}
