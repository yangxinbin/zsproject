package com.mango.leo.zsproject.personalcenter.show;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.mango.leo.zsproject.R;
import com.mango.leo.zsproject.utils.PublicWay;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SettingActivity extends AppCompatActivity {

    @Bind(R.id.imageView_setting_back)
    ImageView imageViewSettingBack;
    @Bind(R.id.s1)
    RelativeLayout s1;
    @Bind(R.id.s2)
    RelativeLayout s2;
    @Bind(R.id.s3)
    RelativeLayout s3;
    @Bind(R.id.s4)
    RelativeLayout s4;
    @Bind(R.id.button_exit)
    Button buttonExit;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        ButterKnife.bind(this);
        sharedPreferences = getSharedPreferences("CIFIT", MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }

    @OnClick({R.id.imageView_setting_back, R.id.s1, R.id.s2, R.id.s3, R.id.s4, R.id.button_exit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.imageView_setting_back:
                finish();
                break;
            case R.id.s1:
                break;
            case R.id.s2:
                break;
            case R.id.s3:
                break;
            case R.id.s4:
                break;
            case R.id.button_exit:
                editor.putString("isOk", "no")
                        .commit();
                for (int i = 0; i < PublicWay.activityList.size(); i++) {
                    if (null != PublicWay.activityList.get(i)) {
                        // 关闭存放在activityList集合里面的所有activity
                        PublicWay.activityList.get(i).finish();
                    }
                }
                System.exit(0);
                finish();
                break;
        }
    }
}
