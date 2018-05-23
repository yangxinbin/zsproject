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

public class CardFifthItemActivity extends BaseCardActivity {
    public static final int TYPE5 = 5;
    @Bind(R.id.imageView5_back)
    ImageView imageView5Back;
    @Bind(R.id.constraintLayout)
    ConstraintLayout constraintLayout;
    @Bind(R.id.button5_save)
    Button button5Save;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_fifth_item);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.imageView5_back, R.id.button5_save})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.imageView5_back:
                break;
            case R.id.button5_save:
                break;
        }
    }
}
