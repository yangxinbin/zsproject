package com.mango.leo.zsproject.industrialservice.createrequirements.carditems;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.mango.leo.zsproject.R;
import com.mango.leo.zsproject.industrialservice.createrequirements.carditems.basecard.BaseCardActivity;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CardThirdItemActivity extends BaseCardActivity {
    public static final int TYPE3 = 3;
    @Bind(R.id.imageView3_back)
    ImageView imageView3Back;
    @Bind(R.id.button3_save)
    Button buttonSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_third_item);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.imageView3_back, R.id.button3_save})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.imageView3_back:
                break;
            case R.id.button3_save:
                break;
        }
    }
}
