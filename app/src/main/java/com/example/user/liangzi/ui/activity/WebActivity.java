package com.example.user.liangzi.ui.activity;

import android.content.Intent;
import android.net.Uri;
import android.net.http.SslError;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.webkit.SslErrorHandler;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.user.liangzi.R;
import com.example.user.liangzi.ui.BaseActivity;
import com.example.user.liangzi.view.CustomerDialog;

import static com.example.user.liangzi.constans.ToolsUtils.checkAliPayInstalled;

/**
 * Created by User on 2017/4/25.
 */

public class WebActivity extends BaseActivity implements View.OnClickListener {
    private WebView webView;
    private boolean isUrl = true;
    private String title;
    private ImageButton ib_share;
    private ImageView icon_back;
    private TextView tv_title;
    private LinearLayout back_layout;
    private CustomerDialog dialog;
    private int shareType = 2;

    @Override
    public void loadXml() {
        setContentView(R.layout.activity_webview);
    }

    @Override
    public void init() {
        Bundle bundle = getIntent().getExtras();
        if (null != bundle) {
            icon_back= (ImageView) findViewById(R.id.icon_back);
            ib_share= (ImageButton) findViewById(R.id.ib_share);
            tv_title= (TextView) findViewById(R.id.title);
            back_layout = (LinearLayout) findViewById(R.id.back_layout);
            back_layout.setOnClickListener(this);
            icon_back.setOnClickListener(this);
            ib_share.setOnClickListener(this);
            dialog = new CustomerDialog(this);
            isUrl = bundle.getBoolean("isUrl",true);
            title = bundle.getString("title");
            init(bundle.getString("openUrl"));
        } else {
            finish();
        }
    }

    private void init(String openUrl){
        Log.e("openUrl", openUrl);
        webView = (WebView) findViewById(R.id.webView);
        WebSettings settings =webView.getSettings();
        webView.getSettings().setDomStorageEnabled(true);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            webView.getSettings().setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);

        }
        webView.setDrawingCacheEnabled(true);
        settings.setJavaScriptEnabled(true);
        // 取消滚动条
        webView.setScrollBarStyle(WebView.SCROLLBARS_OUTSIDE_OVERLAY);
        // 触摸焦点起作用
        webView.requestFocus();
        settings.setSavePassword(false);// 不弹窗浏览器是否保存密码
        settings.setDefaultTextEncodingName("utf-8");
        settings.setCacheMode(WebSettings.LOAD_NO_CACHE);
        settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        // 自动适应屏幕尺寸
        settings.setLoadWithOverviewMode(true);
        settings.setUseWideViewPort(true);
        settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NARROW_COLUMNS);
//        webView.addJavascriptInterface(new ShareJavaScriptInterface(), "shareJavaScriptInterface");
        //覆盖WebView默认使用第三方或系统默认浏览器打开网页的行为，使网页用WebView打开
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                // TODO Auto-generated method stub
                //返回值是true的时候控制去WebView打开，为false调用系统浏览器或第三方浏览器
                Log.e("url", url);
                if(url.contains("alipays://platformapi")){
                    boolean visit = checkAliPayInstalled(mActivity);
                    if(visit){
                        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                        startActivity(intent);
                    }
                }
                return false;
            }
            @Override
            public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error){
                handler.proceed();
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                if (!url.contains("index.html") && !url.contains("activity/inviteFriends.html") && !url.contains("mine/mine.html")) {
                    shareType = 2;
                } else {
                    shareType = 1;
                }
            }

        });
        webView.setWebChromeClient(new WebChromeClient(){
            @Override
            public void onReceivedTitle(WebView view, String title) {
                super.onReceivedTitle(view, title);
                if(!TextUtils.isEmpty(title)) {
                    tv_title.setText(title);
                }

            }

        });
        //WebView加载web资源
        if(isUrl) {
            webView.loadUrl(openUrl);
        }else {
            webView.loadData(openUrl, "text/html; charset=UTF-8", null);
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

    @Override
    public void getIntentData(Bundle savedInstanceState) {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back_layout:
            case R.id.icon_back:
                if ( webView.canGoBack()) { // 表示按返回键
                    webView.goBack(); // 后退
                }else {
                    finish();
                }
                break;
            case R.id.ib_share:
                dialog.showShareDialog(shareType);
                dialog.show();
                break;
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if ( webView.canGoBack()) { // 表示按返回键
            webView.goBack(); // 后退
        }else {
            finish();
        }
    }
}
