package com.mango.leo.zsproject.eventexhibition.show;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ImageView;

import com.mango.leo.zsproject.R;
import com.mango.leo.zsproject.personalcenter.show.UserChangeActivity;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class EventDetailActivity extends AppCompatActivity {

    @Bind(R.id.imageView_back)
    ImageView imageViewBack;
    @Bind(R.id.imageView_share)
    ImageView imageViewShare;
    @Bind(R.id.imageView_love)
    ImageView imageViewLove;
    @Bind(R.id.webview)
    WebView webview;
    @Bind(R.id.sign_up)
    Button signUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_detail);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.imageView_back, R.id.imageView_share, R.id.imageView_love, R.id.sign_up})
    public void onViewClicked(View view) {
        Intent intent;
        switch (view.getId()) {
            case R.id.imageView_back:
                break;
            case R.id.imageView_share:
                break;
            case R.id.imageView_love:
                break;
            case R.id.sign_up:
                intent = new Intent(this, EventRegistrationActivity.class);
                startActivity(intent);
                finish();
                break;
        }
    }
}
