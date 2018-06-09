package com.mango.leo.zsproject.personalcenter.show.baoming;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;

import com.mango.leo.zsproject.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class BaoMingActivity extends AppCompatActivity {

    @Bind(R.id.imageView_baomingback)
    ImageView imageViewBaomingback;
    @Bind(R.id.recycle_baoming)
    RecyclerView recycleBaoming;
    @Bind(R.id.refresh_baoming)
    SwipeRefreshLayout refreshBaoming;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bao_ming);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.imageView_baomingback)
    public void onViewClicked() {
        finish();
    }
}
