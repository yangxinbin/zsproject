package com.mango.leo.zsproject.login;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

import com.mango.leo.zsproject.R;
import com.mango.leo.zsproject.ZsActivity;
import com.mango.leo.zsproject.base.BaseActivity;
import com.mango.leo.zsproject.industrialservice.createrequirements.util.ProjectsJsonUtils;
import com.mango.leo.zsproject.utils.ACache;
import com.mango.leo.zsproject.utils.AppUtils;
import com.mango.leo.zsproject.utils.NetUtil;

import org.greenrobot.eventbus.EventBus;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class StartActivity extends BaseActivity {

    @Bind(R.id.start)
    Button start;
    @Bind(R.id.res)
    Button res;
    private Intent intent;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_start);
        ButterKnife.bind(this);
        sharedPreferences = getSharedPreferences("CIFIT",MODE_PRIVATE);
        editor = sharedPreferences.edit();
        if (sharedPreferences.getString("isOk","no").equals("yes")){
            ACache mCache = ACache.get(this);
            EventBus.getDefault().postSticky(ProjectsJsonUtils.readJsonUserMessageBeans(mCache.getAsString("message")));
            Log.v("yxb",""+mCache.getAsString("message"));
            Intent intent = new Intent(this, ZsActivity.class);
            startActivity(intent);
            finish();
        }
    }

    @OnClick({R.id.start, R.id.res})
    public void onViewClicked(View view) {
/*        if (!NetUtil.isNetConnect(this)){
            AppUtils.showToast(this,"请连接网络");
            return;
        }*/
        switch (view.getId()) {
            case R.id.start:
                intent = new Intent(this, PhoneLoginActivity.class);
                startActivity(intent);
                finish();
                break;
            case R.id.res:
                intent = new Intent(this, ResActivity.class);
                startActivity(intent);
                finish();
                break;
        }
    }
}
