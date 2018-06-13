package com.mango.leo.zsproject.personalcenter.show.shengbao;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.mango.leo.zsproject.R;
import com.mango.leo.zsproject.base.BaseActivity;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ShenBaoDetailActivity extends BaseActivity {

    @Bind(R.id.imageView_sback)
    ImageView imageViewSback;
    @Bind(R.id.textView_name)
    TextView textViewName;
    @Bind(R.id.textView_c)
    TextView textViewC;
    @Bind(R.id.textView_p)
    TextView textViewP;
    @Bind(R.id.textView_po)
    TextView textViewPo;
    @Bind(R.id.textView_e)
    TextView textViewE;
    @Bind(R.id.textView_co)
    TextView textViewCo;
    @Bind(R.id.textView_PName)
    TextView textViewPName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shen_bao_detail);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.imageView_sback)
    public void onViewClicked() {
        finish();
    }
}
