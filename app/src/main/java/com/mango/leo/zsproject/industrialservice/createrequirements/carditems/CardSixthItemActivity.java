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

public class CardSixthItemActivity extends BaseCardActivity {
    public static final int TYPE6 = 6;
    @Bind(R.id.imageView6_back)
    ImageView imageView6Back;
    @Bind(R.id.constraintLayout)
    ConstraintLayout constraintLayout;
    @Bind(R.id.button6_save)
    Button button6Save;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_sixth_item);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.imageView6_back, R.id.button6_save})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.imageView6_back:
                break;
            case R.id.button6_save:
                break;
        }
    }
}
