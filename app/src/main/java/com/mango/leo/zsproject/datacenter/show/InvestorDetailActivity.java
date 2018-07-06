package com.mango.leo.zsproject.datacenter.show;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.mango.leo.zsproject.R;
import com.mango.leo.zsproject.utils.Urls;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.sharesdk.onekeyshare.OnekeyShare;

public class InvestorDetailActivity extends AppCompatActivity {

    @Bind(R.id.t_name)
    TextView tName;
    @Bind(R.id.imageView_back)
    ImageView imageViewBack;
    @Bind(R.id.imageView_share)
    ImageView imageViewShare;
    @Bind(R.id.webview)
    WebView webview;
    @Bind(R.id.loading)
    ProgressBar loading;
    @Bind(R.id.tv_load)
    TextView tvLoad;
    private SharedPreferences sharedPreferences;
    private String id;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_investor_detail);
        ButterKnife.bind(this);
        sharedPreferences = getSharedPreferences("CIFIT", MODE_PRIVATE);
        id = getIntent().getStringExtra("Investor_Id");
        Log.v("ssss", "" + "http://cms.jetc.vc/jetc/#/iosinvestDetail/:" + id);
        webview.setVisibility(View.VISIBLE);
        WebSettings webSettings = webview.getSettings();
        webSettings.setJavaScriptEnabled(true);
        //webview.loadUrl("http://www.baidu.com");
        webview.loadUrl("http://cms.jetc.vc/jetc/#/iosinvestDetail/:" + id);
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
                    tvLoad.setVisibility(View.GONE);
                } else {
                    loading.setVisibility(View.VISIBLE);
                    tvLoad.setVisibility(View.VISIBLE);
                    //loading.setProgress(newProgress);
                }
                super.onProgressChanged(view, newProgress);
            }
        });
    }

    @OnClick({R.id.imageView_back, R.id.imageView_share})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.imageView_back:
                finish();
                break;
            case R.id.imageView_share:
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
        oks.setText("投资机构");
        // imagePath是图片的本地路径，Linked-In以外的平台都支持此参数
        oks.setImageUrl(Urls.HOST + "/user-service/user/get/file?fileId=5b1f70641233c531ec362024");//确保SDcard下面存在此张图片
        // url在微信、微博，Facebook等平台中使用
        oks.setUrl("http://cms.jetc.vc/jetc/#/iosinvestDetail/:" + id);
        // comment是我对这条分享的评论，仅在人人网使用
        oks.setComment("评论");
        // 启动分享GUI
        oks.show(getApplicationContext());
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }
}
