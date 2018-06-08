package com.mango.leo.zsproject.personalcenter.show.kefu;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.mango.leo.zsproject.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class WeActivity extends AppCompatActivity {

    @Bind(R.id.imageView_we)
    ImageView imageViewWe;
    @Bind(R.id.w1)
    RelativeLayout w1;
    @Bind(R.id.w2)
    RelativeLayout w2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_we);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.imageView_we,R.id.w1, R.id.w2})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.imageView_we:
                finish();
                break;
            case R.id.w1:
                break;
            case R.id.w2:
                break;
        }
    }
}
