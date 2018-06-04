package com.mango.leo.zsproject.eventexhibition.show;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.mango.leo.zsproject.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class EventRegistrationActivity extends AppCompatActivity {

    @Bind(R.id.imageView_back)
    ImageView imageViewBack;
    @Bind(R.id.textView_where)
    TextView textViewWhere;
    @Bind(R.id.textView_time)
    TextView textViewTime;
    @Bind(R.id.textView_zhubannf)
    TextView textViewZhubannf;
    @Bind(R.id.textView_xiuban)
    TextView textViewXiuban;
    @Bind(R.id.editText1)
    EditText editText1;
    @Bind(R.id.editText2)
    EditText editText2;
    @Bind(R.id.editText3)
    EditText editText3;
    @Bind(R.id.editText4)
    EditText editText4;
    @Bind(R.id.editText5)
    EditText editText5;
    @Bind(R.id.tv_signle)
    TextView tvSignle;
    @Bind(R.id.tv_all)
    TextView tvAll;
    @Bind(R.id.event_nofree)
    RelativeLayout eventNofree;
    @Bind(R.id.img_piao1)
    ImageView imgPiao1;
    @Bind(R.id.event_free)
    RelativeLayout eventFree;
    @Bind(R.id.howtoplay)
    RelativeLayout howtoplay;
    @Bind(R.id.sign_up)
    Button signUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_registration);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.imageView_back, R.id.howtoplay, R.id.sign_up})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.imageView_back:
                finish();
                break;
            case R.id.howtoplay:
                break;
            case R.id.sign_up:
                break;
        }
    }
}
