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

public class CardEigththItemActivity extends BaseCardActivity {
    public static final int TYPE8 = 8;
    @Bind(R.id.imageView8_back)
    ImageView imageView8Back;
    @Bind(R.id.constraintLayout)
    ConstraintLayout constraintLayout;
    @Bind(R.id.button8_save)
    Button button8Save;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_eigthth_item);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.imageView8_back, R.id.button8_save})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.imageView8_back:
                break;
            case R.id.button8_save:
                break;
        }
    }
}
