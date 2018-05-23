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

public class CardSeventhItemActivity extends BaseCardActivity {
    public static final int TYPE7 = 7;
    @Bind(R.id.imageView7_back)
    ImageView imageView7Back;
    @Bind(R.id.constraintLayout)
    ConstraintLayout constraintLayout;
    @Bind(R.id.button7_save)
    Button button7Save;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_seventh_item);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.imageView7_back, R.id.button7_save})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.imageView7_back:
                break;
            case R.id.button7_save:
                break;
        }
    }
}
