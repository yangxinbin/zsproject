package com.mango.leo.zsproject.industrialservice.createrequirements;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.ImageView;

import com.mango.leo.zsproject.R;
import com.mango.leo.zsproject.base.BaseActivity;
import com.mango.leo.zsproject.industrialservice.createrequirements.carditems.CardFirstItemActivity;

import org.greenrobot.eventbus.EventBus;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CreatedStyleActivity extends BaseActivity {

    @Bind(R.id.imageView_back)
    ImageView imageView_back;
    @Bind(R.id.carone)
    CardView carone;
    @Bind(R.id.cartwo)
    CardView cartwo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_created_style);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.carone, R.id.cartwo, R.id.imageView_back})
    public void onViewClicked(View view) {
        Intent intent;
        switch (view.getId()) {
            case R.id.carone:
                intent = new Intent(this, BusinessPlanActivity.class);
                startActivity(intent);
                EventBus.getDefault().removeAllStickyEvents();
                break;
            case R.id.cartwo:
                intent = new Intent(this, CustomRequirementsActivity.class);
                startActivity(intent);
                break;
            case R.id.imageView_back:
                finish();
                break;
        }
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }
}
