package com.mango.leo.zsproject.login;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.mango.leo.zsproject.R;
import com.mango.leo.zsproject.base.BaseActivity;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class StartActivity extends BaseActivity {

    @Bind(R.id.start)
    Button start;
    @Bind(R.id.res)
    Button res;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.start, R.id.res})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.start:
                intent = new Intent(this, PhoneLoginActivity.class);
                startActivity(intent);
                finish();
                break;
            case R.id.res:

                break;
        }
    }
}
