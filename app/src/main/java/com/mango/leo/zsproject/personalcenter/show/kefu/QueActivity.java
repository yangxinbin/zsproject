package com.mango.leo.zsproject.personalcenter.show.kefu;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.mango.leo.zsproject.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class QueActivity extends AppCompatActivity {

    @Bind(R.id.imageView_kefuback)
    ImageView imageViewKefuback;
    @Bind(R.id.textView_qName)
    TextView textViewQName;
    @Bind(R.id.textView_qContent)
    TextView textViewQContent;
    @Bind(R.id.bang1)
    RelativeLayout bang1;
    @Bind(R.id.bang2)
    RelativeLayout bang2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_que);
        ButterKnife.bind(this);
        textViewQName.setText(getIntent().getStringExtra("k1"));
        textViewQContent.setText(getIntent().getStringExtra("k2"));
    }


    @OnClick({R.id.imageView_kefuback, R.id.bang1, R.id.bang2})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.imageView_kefuback:
                finish();
                break;
            case R.id.bang1:
                break;
            case R.id.bang2:
                break;
        }
    }
}
