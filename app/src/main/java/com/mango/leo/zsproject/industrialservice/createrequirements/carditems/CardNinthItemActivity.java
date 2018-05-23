package com.mango.leo.zsproject.industrialservice.createrequirements.carditems;

import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.mango.leo.zsproject.R;
import com.mango.leo.zsproject.industrialservice.createrequirements.carditems.basecard.BaseCardActivity;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CardNinthItemActivity extends BaseCardActivity {
    public static final int TYPE9 = 9;
    @Bind(R.id.imageView9_back)
    ImageView imageView9Back;
    @Bind(R.id.constraintLayout)
    ConstraintLayout constraintLayout;
    @Bind(R.id.button9_save)
    Button button9Save;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_ninth_item);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.imageView9_back, R.id.button9_save})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.imageView9_back:
                break;
            case R.id.button9_save:
                break;
        }
    }
}
