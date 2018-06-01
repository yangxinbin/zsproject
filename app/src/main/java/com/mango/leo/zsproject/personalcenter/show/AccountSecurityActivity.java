package com.mango.leo.zsproject.personalcenter.show;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.mango.leo.zsproject.R;
import com.mango.leo.zsproject.base.BaseActivity;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AccountSecurityActivity extends BaseActivity {

    @Bind(R.id.imageView_security_back)
    ImageView imageViewSecurityBack;
    @Bind(R.id.s1)
    RelativeLayout s1;
    @Bind(R.id.s2)
    RelativeLayout s2;
    @Bind(R.id.s3)
    RelativeLayout s3;
    @Bind(R.id.s4)
    RelativeLayout s4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_security);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.imageView_security_back, R.id.s1, R.id.s2, R.id.s3, R.id.s4})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.imageView_security_back:
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
        }
    }
}
