package com.mango.leo.zsproject.eventexhibition.util;

import android.content.Context;

import android.util.AttributeSet;

import android.util.Log;
import android.webkit.WebView;

/**
 * 重新webview
 *
 * @author paoyx
 */

public class FoundWebView extends WebView {
    public ScrollInterface mScrollInterface;
    public FoundWebView (Context context) {
        super(context);
    }
    public FoundWebView (Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }
    public FoundWebView (Context context, AttributeSet attrs) {
        super(context, attrs);
    }
    //实时滑动监控
    //参数l代表滑动后当前位置，old代表原来原值
    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        Log.v("ccccccccccc","-----onScrollChanged");

        super.onScrollChanged(l, t, oldl, oldt);
        mScrollInterface.onSChanged(l, t, oldl, oldt);
    }
    //供外部调用，监控滑动
    public void setOnCustomScroolChangeListener(ScrollInterface scrollInterface) {
        this.mScrollInterface = scrollInterface;
    }
    public interface ScrollInterface {
        public void onSChanged(int l, int t, int oldl, int oldt);
    }
}
