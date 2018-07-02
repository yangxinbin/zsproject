package com.mango.leo.zsproject.personalcenter.show;

import android.os.Bundle;
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
import com.mango.leo.zsproject.base.BaseActivity;
import com.mango.leo.zsproject.utils.Urls;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.sharesdk.onekeyshare.OnekeyShare;

public class GovActivity extends BaseActivity {

    @Bind(R.id.t_govName)
    TextView tGovName;
    @Bind(R.id.imageView_govBack)
    ImageView imageViewGovBack;
    @Bind(R.id.imageView_share)
    ImageView imageViewShare;
    @Bind(R.id.web_investor)
    WebView webInvestor;
    @Bind(R.id.loading)
    ProgressBar loading;
    @Bind(R.id.tv_load)
    TextView tvLoad;
    private String stringName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gov);
        ButterKnife.bind(this);
        stringName = getIntent().getStringExtra("name");
        if (!stringName.equals("")){
            tGovName.setText(stringName);
        }else {
            stringName = getResources().getString(R.string.investor);
            tGovName.setText(R.string.investor);
        }
        initWeb();
    }

    private void initWeb() {
        webInvestor.setVisibility(View.VISIBLE);
        WebSettings webSettings = webInvestor.getSettings();
        webSettings.setJavaScriptEnabled(true);
        //webview.loadUrl("http://www.baidu.com");
        webInvestor.loadUrl("http://47.106.184.121/jetc/#/iosinvestDetail");
        webInvestor.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                // TODO Auto-generated method stub
                //返回值是true的时候控制去WebView打开，为false调用系统浏览器或第三方浏览器
                view.loadUrl(url);
                return true;
            }
        });
        webInvestor.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                if (newProgress == 100) {
                    loading.setVisibility(View.GONE);
                    tvLoad.setVisibility(View.GONE);
                } else {
                    loading.setVisibility(View.VISIBLE);
                    tvLoad.setVisibility(View.VISIBLE);
                    loading.setProgress(newProgress);
                }
                super.onProgressChanged(view, newProgress);
            }
        });
    }

    @OnClick({R.id.imageView_govBack, R.id.imageView_share})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.imageView_govBack:
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
        oks.setText(stringName);
        // imagePath是图片的本地路径，Linked-In以外的平台都支持此参数
        oks.setImageUrl(Urls.HOST + "/user-service/user/get/file?fileId=5b1f70641233c531ec362024");//确保SDcard下面存在此张图片
        // url在微信、微博，Facebook等平台中使用
        oks.setUrl("http://47.106.184.121/jetc/#/iosinvestDetail");
        // comment是我对这条分享的评论，仅在人人网使用
        oks.setComment("评论");
        // 启动分享GUI
        oks.show(getApplicationContext());
    }
}
