package com.mango.leo.zsproject.eventexhibition.show;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.mango.leo.zsproject.R;
import com.mango.leo.zsproject.base.BaseActivity;
import com.mango.leo.zsproject.eventexhibition.bean.LovedEventBean;
import com.mango.leo.zsproject.eventexhibition.bean.SingedEventBean;
import com.mango.leo.zsproject.industrialservice.createrequirements.util.ProjectsJsonUtils;
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
import cn.sharesdk.onekeyshare.OnekeyShare;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class EventDetailActivity extends BaseActivity {

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
    @Bind(R.id.loading)
    ProgressBar loading;
    @Bind(R.id.tv_load)
    TextView textView23;
    private SharedPreferences sharedPreferences;
    private String id;
    private int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_detail);
        ButterKnife.bind(this);
        sharedPreferences = getSharedPreferences("CIFIT", MODE_PRIVATE);
        id = getIntent().getStringExtra("id");
        position = getIntent().getIntExtra("position", -1);
        Log.v("ssss", "" + "http://47.106.184.121/jetc/#/iosactivityDetailnobtn/:" + id);
        webview.setVisibility(View.VISIBLE);
        WebSettings webSettings = webview.getSettings();
        webSettings.setJavaScriptEnabled(true);
        //webview.loadUrl("http://www.baidu.com");
        webview.loadUrl("http://47.106.184.121/jetc/#/iosactivityDetail/:" + id);
        webview.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                // TODO Auto-generated method stub
                //返回值是true的时候控制去WebView打开，为false调用系统浏览器或第三方浏览器
                view.loadUrl(url);
                return true;
            }
        });
        webview.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                if (newProgress == 100) {
                    loading.setVisibility(View.GONE);
                    textView23.setVisibility(View.GONE);
                    signUp.setVisibility(View.VISIBLE);
                } else {
                    loading.setVisibility(View.VISIBLE);
                    textView23.setVisibility(View.VISIBLE);
                    signUp.setVisibility(View.GONE);
                    loading.setProgress(newProgress);
                }
                super.onProgressChanged(view, newProgress);
            }
        });
    }

    @OnClick({R.id.imageView_back, R.id.imageView_share, R.id.imageView_love, R.id.sign_up})
    public void onViewClicked(View view) {
        Intent intent;
        switch (view.getId()) {
            case R.id.imageView_back:
                finish();
                break;
            case R.id.imageView_share:
                Log.v("yxbbbbb", "---------");
                newShare();
                break;
            case R.id.imageView_love:
                loadLove();
                break;
            case R.id.sign_up:
                intent = new Intent(this, EventRegistrationActivity.class);
                startActivity(intent);
                finish();
                break;
        }
    }

    private void newShare() {
        OnekeyShare oks = new OnekeyShare();
        //关闭sso授权
        oks.disableSSOWhenAuthorize();

        // title标题，微信、QQ和QQ空间等平台使用
        //oks.setTitle(title);
        // titleUrl QQ和QQ空间跳转链接
        //oks.setTitleUrl(newsurl);
        // text是分享文本，所有平台都需要这个字段
        oks.setText("活动详情");
        // imagePath是图片的本地路径，Linked-In以外的平台都支持此参数
        oks.setImageUrl(Urls.HOST + "/user-service/user/get/file?fileId=5b1f70641233c531ec362024");//确保SDcard下面存在此张图片
        // url在微信、微博，Facebook等平台中使用
        oks.setUrl("http://47.106.184.121/jetc/#/iosactivityDetail/:" + id);
        // comment是我对这条分享的评论，仅在人人网使用
        oks.setComment("评论");
        // 启动分享GUI
        oks.show(getApplicationContext());
    }

    private void loadLove() {
        Log.v("FFFFFF", sharedPreferences.getString("token", "") + "******");
        Map<String, String> mapParams = new HashMap<String, String>();
        mapParams.clear();
        mapParams.put("eventId", id);
        //mapParams.put("createdBy",sharedPreferences.getString("userName",""));
        // mapParams.put("type","EVENT");
        mapParams.put("token", sharedPreferences.getString("token", ""));
        HttpUtils.doPost(Urls.HOST_FAVOURITE, mapParams, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                mHandler.sendEmptyMessage(0);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (String.valueOf(response.code()).startsWith("2")) {
                    mHandler.sendEmptyMessage(1);
                } else {
                    Log.v("FFFFFF", "******" + response.code());
                    LovedEventBean singedEventBean = ProjectsJsonUtils.readJsonLovedEventBean(response.body().string());//data是json字段获得data的值即对象数组
                    Message message = mHandler.obtainMessage();
                    message.obj = singedEventBean;
                    message.what = 0;
                    message.sendToTarget();
                }
            }
        });
    }

    private final MyHandler mHandler = new MyHandler(this);

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
                        LovedEventBean lovedEventBean = (LovedEventBean) msg.obj;
                        AppUtils.showToast(activity, lovedEventBean.getMessage());
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
