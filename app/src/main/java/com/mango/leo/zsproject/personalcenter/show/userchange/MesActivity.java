package com.mango.leo.zsproject.personalcenter.show.userchange;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.ListView;

import com.mango.leo.zsproject.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MesActivity extends AppCompatActivity {

    @Bind(R.id.imageView_mesback)
    ImageView imageViewMesback;
    @Bind(R.id.mes_list)
    ListView mesList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mes);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.imageView_mesback)
    public void onViewClicked() {
        finish();
    }
}
