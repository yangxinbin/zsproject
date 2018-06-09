package com.mango.leo.zsproject.eventexhibition.show;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ImageView;

import com.mango.leo.zsproject.R;
import com.mango.leo.zsproject.utils.AppUtils;
import com.mango.leo.zsproject.utils.HttpUtils;
import com.mango.leo.zsproject.utils.Urls;

import java.io.IOException;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

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
    private SharedPreferences sharedPreferences;
    private String id;
    private int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_detail);
        ButterKnife.bind(this);
        sharedPreferences = getSharedPreferences("CIFIT",MODE_PRIVATE);
        id = getIntent().getStringExtra("id");
        position = getIntent().getIntExtra("position",-1);
        Log.v("ssss",""+"http://192.168.1.170:8080/#/iosactivityDetail/:"+id);
        webview.loadUrl("http://192.168.1.170:8080/#/iosactivityDetail/:"+id);
    }

    @OnClick({R.id.imageView_back, R.id.imageView_share, R.id.imageView_love, R.id.sign_up})
    public void onViewClicked(View view) {
        Intent intent;
        switch (view.getId()) {
            case R.id.imageView_back:
                finish();
                break;
            case R.id.imageView_share:
                break;
            case R.id.imageView_love:
                loadLove();
                break;
            case R.id.sign_up:
                intent = new Intent(this, EventRegistrationActivity.class);
                intent.putExtra("position",position);
                startActivity(intent);
                finish();
                break;
        }
    }

    private void loadLove() {
        Log.v("FFFFFF",sharedPreferences.getString("token","")+"******");
        Map<String, String> mapParams = new HashMap<String, String>();
        mapParams.clear();
        mapParams.put("eventId",id);
        //mapParams.put("createdBy",sharedPreferences.getString("userName",""));
       // mapParams.put("type","EVENT");
        mapParams.put("token",sharedPreferences.getString("token",""));
        HttpUtils.doPost(Urls.HOST_FAVOURITE, mapParams, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                mHandler.sendEmptyMessage(0);
            }
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (String.valueOf(response.code()).startsWith("2")){
                    mHandler.sendEmptyMessage(1);
                }else {
                    Log.v("FFFFFF",response.body().string()+"******"+response.code());
                    mHandler.sendEmptyMessage(0);

                }
            }
        });
    }

    private final EventDetailActivity.MyHandler mHandler = new EventDetailActivity.MyHandler(this);

    private static class MyHandler extends Handler {
        private final WeakReference<EventDetailActivity> mActivity;

        public MyHandler(EventDetailActivity activity) {
            mActivity = new WeakReference<EventDetailActivity>(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            EventDetailActivity activity = mActivity.get();
            if (activity != null) {
                switch (msg.what) {
                    case 0:
                        AppUtils.showToast(activity, "收藏失败");
                        break;
                    case 1:
                        AppUtils.showToast(activity, "收藏成功");
                        break;
                    default:
                        break;
                }
            }
        }
    }
}
