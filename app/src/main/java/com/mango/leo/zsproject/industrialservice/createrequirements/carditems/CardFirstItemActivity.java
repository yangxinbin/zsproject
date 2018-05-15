package com.mango.leo.zsproject.industrialservice.createrequirements.carditems;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.mango.leo.zsproject.R;
import com.mango.leo.zsproject.base.BaseActivity;
import com.mango.leo.zsproject.industrialservice.createrequirements.AllAndCreatedPlanActivity;
import com.mango.leo.zsproject.viewutil.LinedEditText;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CardFirstItemActivity extends BaseActivity {

    @Bind(R.id.imageView1_back)
    ImageView imageView1Back;
    @Bind(R.id.textView1_save)
    TextView textView1Save;
    @Bind(R.id.item_title)
    EditText itemTitle;
    @Bind(R.id.item_content)
    LinedEditText itemContent;
    @Bind(R.id.add_iv_photo)
    ImageView addIvPhoto;
    @Bind(R.id.add_iv_show)
    ImageView addIvShow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_first_item);
        ButterKnife.bind(this);

    }

    @OnClick({R.id.imageView1_back, R.id.textView1_save})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.imageView1_back:
                Intent intent = new Intent(this, AllAndCreatedPlanActivity.class);
                startActivity(intent);
                finish();
                break;
            case R.id.textView1_save:
                break;
        }
    }
}
