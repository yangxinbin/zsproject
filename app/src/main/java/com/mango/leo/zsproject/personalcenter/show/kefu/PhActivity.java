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

public class PhActivity extends AppCompatActivity {

    @Bind(R.id.imageView_ph)
    ImageView imageViewPh;
    @Bind(R.id.p1)
    RelativeLayout p1;
    @Bind(R.id.p2)
    RelativeLayout p2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ph);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.imageView_ph, R.id.p1, R.id.p2})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.imageView_ph:
                finish();
                break;
            case R.id.p1:
                break;
            case R.id.p2:
                break;
        }
    }
}
