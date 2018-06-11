package com.mango.leo.zsproject.industrialpanorama.show;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;

import com.mango.leo.zsproject.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.sharesdk.onekeyshare.OnekeyShare;

public class ZhaoShanDetailActivity extends AppCompatActivity {

    @Bind(R.id.imageView_zhaoshan_back)
    ImageView imageViewZhaoshanBack;
    @Bind(R.id.imageView_zhaoshan_share)
    ImageView imageViewZhaoshanShare;
    @Bind(R.id.web_zhaoshan)
    WebView webZhaoshan;
    private String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zhao_shan_detail);
        ButterKnife.bind(this);
        id = getIntent().getStringExtra("id");
        webZhaoshan.setVisibility(View.VISIBLE);
        WebSettings webSettings = webZhaoshan.getSettings();
        webSettings.setJavaScriptEnabled(true);
        //webview.loadUrl("http://www.baidu.com");
        webZhaoshan.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                // TODO Auto-generated method stub
                //返回值是true的时候控制去WebView打开，为false调用系统浏览器或第三方浏览器
                view.loadUrl(url);
                return true;
            }
        });
       //?? webZhaoshan.loadUrl("http://192.168.1.166:8080/jetc/#/iosactivityDetail/:"+id);
    }

    @OnClick({R.id.imageView_zhaoshan_back, R.id.imageView_zhaoshan_share})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.imageView_zhaoshan_back:
                finish();
                break;
            case R.id.imageView_zhaoshan_share:
                newShare();
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
        oks.setImageUrl("http:\\/\\/04.imgmini.eastday.com\\/mobile\\/20180610\\/20180610002454_6ab3e88e0c01455e287a65eda6bf7b1a_8_mwpm_03200403.jpg");//确保SDcard下面存在此张图片
        // url在微信、微博，Facebook等平台中使用
        oks.setUrl("http://192.168.1.166:8080/jetc/#/iosactivityDetail/:"+id);
        // comment是我对这条分享的评论，仅在人人网使用
        oks.setComment("评论");
        // 启动分享GUI
        oks.show(this);
    }
}