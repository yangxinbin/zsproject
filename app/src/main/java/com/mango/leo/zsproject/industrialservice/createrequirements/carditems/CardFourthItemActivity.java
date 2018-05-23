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

public class CardFourthItemActivity extends BaseCardActivity {
    public static final int TYPE4 = 4;
    @Bind(R.id.imageView4_back)
    ImageView imageView4Back;
    @Bind(R.id.constraintLayout)
    ConstraintLayout constraintLayout;
    @Bind(R.id.button4_save)
    Button buttonSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_fourth_item);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.imageView4_back, R.id.button4_save})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.imageView4_back:
                break;
            case R.id.button4_save:
                break;
        }
    }
}
