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
import com.mango.leo.zsproject.personalcenter.show.UserChangeActivity;
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

public class DepartmentActivity extends BaseActivity {

    @Bind(R.id.textView_cancel)
    TextView textViewCancel;
    @Bind(R.id.textView_save)
    TextView textViewSave;
    @Bind(R.id.editText_change_pos)
    EditText editTextChange;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_department);
        ButterKnife.bind(this);
        editTextChange.setText(getIntent().getStringExtra("position"));
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
            case R.id.textView_save:
                changePos();//职位修改
                finish();
                break;
        }
    }

    private void changePos() {
        Map<String, String> mapParams = new HashMap<String, String>();
        mapParams.put("position", editTextChange.getText().toString());
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
                    Log.v("yxb","##"+response.body().string());
                    //UserMessageBean bean = ProjectsJsonUtils.readJsonUserMessageBeans(response.body().string());
                    //EventBus.getDefault().postSticky(bean);
                } else {
                    Log.v("yxbbbb", "*****" + response.body().string());
                    mHandler.sendEmptyMessage(0);
                }
            }
        });
    }
    private final DepartmentActivity.MyHandler mHandler = new DepartmentActivity.MyHandler(this);

    private class MyHandler extends Handler {
        private final WeakReference<DepartmentActivity> mActivity;

        public MyHandler(DepartmentActivity activity) {
            mActivity = new WeakReference<DepartmentActivity>(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            DepartmentActivity activity = mActivity.get();
            if (activity != null) {
                switch (msg.what) {
                    case 0:
                        AppUtils.showToast(activity, "职位修改失败");
                        break;
                    case 1:
                        AppUtils.showToast(activity, "职位修改成功");
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
