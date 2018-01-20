package com.example.video.view;

import android.graphics.PixelFormat;
import android.os.Build;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import com.example.base.BaseActivity;
import com.example.base.R;
import com.example.tbslibs.X5WebView;
import com.jaeger.library.StatusBarUtil;
import com.tencent.smtt.sdk.CookieSyncManager;
import com.tencent.smtt.sdk.WebChromeClient;
import com.tencent.smtt.sdk.WebSettings;
import com.tencent.smtt.sdk.WebView;
import com.tencent.smtt.sdk.WebViewClient;

import butterknife.BindView;
import butterknife.OnClick;

public class WebVideoActivity extends BaseActivity {


    @BindView(R.id.webview_grup)
    ViewGroup mViewParent;
    @BindView(R.id.btnBack)
    ImageButton mBack;
    @BindView(R.id.btnHome)
    ImageButton mHome;
    private final int disable = 120;
    private final int enable = 255;
    private X5WebView mWebView;
    private String url = "http://www.70mao.com/"; //http://www.70mao.com/
    private String homeUrl = "http://c.aaccy.com/";
    private ProgressBar mProgressBar;

    @Override
    protected void onBeforeSetContentLayout() {
        super.onBeforeSetContentLayout();
        getWindow().setFormat(PixelFormat.TRANSLUCENT);
        try {
            if (Integer.parseInt(Build.VERSION.SDK) >= 11) {
                getWindow().setFlags(
                        WindowManager.LayoutParams.FLAG_HARDWARE_ACCELERATED,
                        WindowManager.LayoutParams.FLAG_HARDWARE_ACCELERATED);
            }
        } catch (Exception e) {
        }
    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_web_video_;
    }

    @Override
    protected void initView() {
        StatusBarUtil.setColor(this, getResources().getColor(R.color.white));
        mWebView = new X5WebView(this, null);
        mViewParent.addView(mWebView, new FrameLayout.LayoutParams(
                FrameLayout.LayoutParams.MATCH_PARENT,
                FrameLayout.LayoutParams.MATCH_PARENT));
        if (!url.startsWith("http:") && !url.startsWith("https:")) {
            url = new StringBuilder("http:").append(url).toString();
        }
        initProgressBar();
        initWebView();
    }

    private void initProgressBar() {
        mProgressBar = new ProgressBar(mContext, null,
                android.R.attr.progressBarStyleHorizontal);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, 8);
        mProgressBar.setLayoutParams(layoutParams);
        mProgressBar.setMax(99);
        mViewParent.addView(mProgressBar);
    }


    private void initWebView() {
        WebSettings webSetting = mWebView.getSettings();
        webSetting.setAllowFileAccess(true); // 是否可访问本地文件，默认值 true
        webSetting.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NARROW_COLUMNS);
        webSetting.setSupportZoom(true);
        webSetting.setBuiltInZoomControls(true);
        webSetting.setSupportMultipleWindows(false);// 是否支持多窗口，默认值false
        webSetting.setJavaScriptEnabled(true);
        // 缓存(cache)
        webSetting.setAppCacheEnabled(true);
        webSetting.setAppCacheMaxSize(Long.MAX_VALUE);
        webSetting.setAppCachePath(this.getDir("appcache", 0).getPath());
        // 存储(storage)
        webSetting.setDomStorageEnabled(true);// 启用HTML5 DOM storage API，默认值 false
        webSetting.setDatabaseEnabled(true);      // 默认值 false
        webSetting.setDatabasePath(this.getDir("databases", 0).getPath());
        // 定位(location)
        webSetting.setGeolocationEnabled(true);
        webSetting.setGeolocationDatabasePath(this.getDir("geolocation", 0).getPath());
        webSetting.setPluginState(WebSettings.PluginState.ON_DEMAND);
        webSetting.setUseWideViewPort(true);// 页面自适应手机屏幕
        webSetting.setLoadWithOverviewMode(true);// 当页面宽度大于WebView宽度时，缩小使页面宽度等于WebView宽度
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            // 用户是否需要通过手势播放媒体(不会自动播放)，默认值 true
            webSetting.setMediaPlaybackRequiresUserGesture(false);
        }
        mWebView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                return true;
            }
        });
        mWebView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView webView, int newProgress) {

                if (newProgress >= 99) {
                    mProgressBar.setVisibility(View.GONE);
                } else {
                    mProgressBar.setVisibility(View.VISIBLE);
                    mProgressBar.setProgress(newProgress);
                }
                super.onProgressChanged(webView, newProgress);
            }
        });
        mWebView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView webView, String s) {
                mProgressBar.setVisibility(View.GONE);
                if (Integer.parseInt(Build.VERSION.SDK) >= 16) {
                    changGoForwardButton(webView);
                }
                super.onPageFinished(webView, s);
            }
        });
        mWebView.loadUrl(url);
        CookieSyncManager.createInstance(this);
        CookieSyncManager.getInstance().sync();
    }


    private void changGoForwardButton(WebView view) {
        if (view.canGoBack())
            mBack.setAlpha(enable);
        else
            mBack.setAlpha(disable);
        if (view.getUrl() != null && view.getUrl().equalsIgnoreCase(url)) {
            mHome.setAlpha(disable);
            mHome.setEnabled(false);
        } else {
            mHome.setAlpha(enable);
            mHome.setEnabled(true);
        }
    }


    @Override
    protected void onDestroy() {
        if (mWebView != null) {
            mWebView.clearCache(true);// 传入true表示同时内存与磁盘
            mWebView.destroy();
        }
        super.onDestroy();
    }


    @OnClick({R.id.btnBack, R.id.btnHome})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btnBack:
                if (mWebView != null && mWebView.canGoBack()) {
                    mWebView.goBack();
                }
                break;
            case R.id.btnHome:
                if (mWebView != null) {
                    mWebView.loadUrl(homeUrl);
                }
                break;
        }
    }
}
