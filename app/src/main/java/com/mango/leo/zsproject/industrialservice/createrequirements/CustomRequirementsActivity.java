package com.mango.leo.zsproject.industrialservice.createrequirements;

import android.os.Bundle;

import com.mango.leo.zsproject.R;
import com.mango.leo.zsproject.base.BaseActivity;

import butterknife.ButterKnife;

public class CustomRequirementsActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_requirements);
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }
}
