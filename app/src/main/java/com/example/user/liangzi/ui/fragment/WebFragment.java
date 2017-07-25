package com.example.user.liangzi.ui.fragment;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.view.View;
import android.webkit.SslErrorHandler;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.user.liangzi.R;
import com.example.user.liangzi.UserManager;
import com.example.user.liangzi.ui.BaseFragment;
import com.example.user.liangzi.ui.activity.MainActivity;

import static com.example.user.liangzi.constans.ToolsUtils.checkAliPayInstalled;

/**
 * Created by User on 2017/4/18.
 */

public class WebFragment extends BaseFragment {
    private WebView web;
    private ProgressBar bar;

    private String url = null;

    public class WebInterface{
        private Context context;
        public WebInterface(Context context) {
            this.context = context;
        }
//        @JavascriptInterface
//        public void show(){
//            exit();
//        }
//        @JavascriptInterface
//        public void testapp(){
//            exit();
//        }
    }
    @Override
    public void loadXml() {
        setContentView(R.layout.fragment_web);
    }

    @Override
    public void init() {
        bar= (ProgressBar) rootView.findViewById(R.id.progress_bar);
        web= (WebView) rootView.findViewById(R.id.web);
//        web.setOnKeyListener(this);

        //覆盖WebView默认使用第三方或系统默认浏览器打开网页的行为，使网页用WebView打开
        web.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                if(url.contains("alipays://platformapi")){
                    boolean visit = checkAliPayInstalled(mContext);
                    if(visit){
                        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                        startActivity(intent);
                    }
                }
                return false;
            }

            @Override
            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                super.onReceivedError(view, errorCode, description, failingUrl);
                Toast.makeText(mContext,"同步失败,请稍候再试",Toast.LENGTH_LONG).show();
            }

            public void onReceivedSslError(WebView view, SslErrorHandler handler, android.net.http.SslError error) {
                handler.proceed();
            }

            @Override
            public void onPageFinished(WebView view, String curl) {
                url = curl;
                if (!url.contains("index.html")&&!url.contains("activity/inviteFriends.html")&&!url.contains("mine/mine.html")){
                    if(mContext instanceof MainActivity) {
                        ((MainActivity)mContext).setShareType(1);
                    }
                }else{
                    if(mContext instanceof MainActivity) {
                        ((MainActivity)mContext).setShareType(2);
                    }
                }
            }

        });
        web.setWebChromeClient(new WebChromeClient(){

            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                if (newProgress == 100) {
                    bar.setVisibility(View.GONE);
                } else {
                    bar.setVisibility(View.VISIBLE);
                    bar.setProgress(newProgress);
                }
                super.onProgressChanged(view, newProgress);

            }
        });
        WebSettings settings =web.getSettings();
        web.getSettings().setDomStorageEnabled(true);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            web.getSettings().setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);

        }
        web.setDrawingCacheEnabled(true);
        settings.setJavaScriptEnabled(true);
        // 取消滚动条
        web.setScrollBarStyle(WebView.SCROLLBARS_OUTSIDE_OVERLAY);
        // 触摸焦点起作用
        web.requestFocus();
        settings.setSavePassword(false);// 不弹窗浏览器是否保存密码
        settings.setDefaultTextEncodingName("utf-8");
        settings.setCacheMode(WebSettings.LOAD_NO_CACHE);
        settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        // 自动适应屏幕尺寸
        settings.setLoadWithOverviewMode(true);
        settings.setUseWideViewPort(true);
        settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NARROW_COLUMNS);
//        webView.addJavascriptInterface(new ShareJavaScriptInterface(), "shareJavaScriptInterface");


        if(mBundle != null) {
            url = mBundle.getString("address");
        }
        if(url != null) {
            web.loadUrl(url+"?"+"juid="+ UserManager.getInstance().getJuid()+"&src=1");
        }

    }

    @Override
    public void setListener() {

    }

    @Override
    public void setData() {

    }

    @Override
    public void setOther() {

    }

    public boolean onBack(){
        if ( web.canGoBack()) { // 表示按返回键
            web.goBack(); // 后退
            return true;
        }
        return false;
    }

    @Override
    public void onResume() {
        super.onResume();
    }


    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if(!hidden) {
            if(url != null) {
                web.loadUrl(url);
            }
        }
    }
}
