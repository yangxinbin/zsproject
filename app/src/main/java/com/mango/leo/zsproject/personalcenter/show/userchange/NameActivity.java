package com.mango.leo.zsproject.personalcenter.show.userchange;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.mango.leo.zsproject.R;
import com.mango.leo.zsproject.base.BaseActivity;
import com.mango.leo.zsproject.industrialservice.createrequirements.util.ProjectsJsonUtils;
import com.mango.leo.zsproject.login.bean.UserMessageBean;
import com.mango.leo.zsproject.personalcenter.show.AccountSecurityActivity;
import com.mango.leo.zsproject.personalcenter.show.UserChangeActivity;
import com.mango.leo.zsproject.utils.AppUtils;
import com.mango.leo.zsproject.utils.HttpUtils;
import com.mango.leo.zsproject.utils.Urls;

import org.greenrobot.eventbus.EventBus;

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

public class NameActivity extends BaseActivity {

    @Bind(R.id.textView_cancel)
    TextView textViewCancel;
    @Bind(R.id.textView_save)
    TextView textViewSave;
    @Bind(R.id.editText_change)
    EditText editTextChange;
    private SharedPreferences sharedPreferences;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_name);
        ButterKnife.bind(this);
        editTextChange.setText(getIntent().getStringExtra("name"));
        sharedPreferences = getSharedPreferences("CIFIT", MODE_PRIVATE);

    }

    @OnClick({R.id.textView_cancel, R.id.textView_save})
    public void onViewClicked(View view) {
        Intent intent;
        switch (view.getId()) {
            case R.id.textView_cancel:
                intent = new Intent(this, UserChangeActivity.class);
                startActivity(intent);
                finish();
                break;
            case R.id.textView_save:
                changeName();
                finish();
                break;
        }
    }

    private void changeName() {
        Map<String, String> mapParams = new HashMap<String, String>();
        mapParams.put("name", editTextChange.getText().toString());
        mapParams.put("token", sharedPreferences.getString("token", ""));
        HttpUtils.doPut(Urls.HOST_PROFILE, mapParams, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                mHandler.sendEmptyMessage(0);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (String.valueOf(response.code()).startsWith("2")) {
                    mHandler.sendEmptyMessage(1);
                    //Log.v("uuuuuu",editTextChange.getText().toString()+"______"+response.body().string());
                    UserMessageBean bean = ProjectsJsonUtils.readJsonUserMessageBeans(response.body().string());
                    EventBus.getDefault().postSticky(bean);
                } else {
                    Log.v("yxbbbb", "*****" + response.body().string());
                    mHandler.sendEmptyMessage(0);
                }
            }
        });
    }

    private final NameActivity.MyHandler mHandler = new NameActivity.MyHandler(this);

    private class MyHandler extends Handler {
        private final WeakReference<NameActivity> mActivity;

        public MyHandler(NameActivity activity) {
            mActivity = new WeakReference<NameActivity>(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            NameActivity activity = mActivity.get();
            if (activity != null) {
                switch (msg.what) {
                    case 0:
                        AppUtils.showToast(activity, "姓名修改失败");
                        break;
                    case 1:
                        AppUtils.showToast(activity, "姓名修改成功");
                        Intent intent = new Intent(activity, UserChangeActivity.class);
                        startActivity(intent);
                        break;
                    default:
                        break;
                }
            }
        }
    }
}
