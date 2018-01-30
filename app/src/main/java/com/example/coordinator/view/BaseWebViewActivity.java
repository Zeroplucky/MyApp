package com.example.coordinator.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.base.BaseActivity;
import com.example.base.R;
import com.example.utils.NetworkUtils;
import com.example.widget.NestedScrollWebView;
import com.jaeger.library.StatusBarUtil;

import butterknife.BindView;

//import com.tencent.smtt.sdk.WebChromeClient;
//import com.tencent.smtt.sdk.WebSettings;
//import com.tencent.smtt.sdk.WebView;
//import com.tencent.smtt.sdk.WebViewClient;

public abstract class BaseWebViewActivity extends BaseActivity {

    @BindView(R.id.tv_detail_title)
    TextView tvDetailTitle;
    @BindView(R.id.iv_detail)
    ImageView ivDetail;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.collapsing_toolbar)
    CollapsingToolbarLayout collapsingToolbar;
    @BindView(R.id.app_bar)
    AppBarLayout appBar;
    @BindView(R.id.tv_detail_copyright)
    TextView tvDetailcopyright;
    @BindView(R.id.nswv_detail_content)
    NestedScrollWebView webView;
    @BindView(R.id.pb_web)
    ProgressBar pvWeb;

    private int downX, downY;
    //    private WebViewLongClickedPopWindow popWindow;
    private String mImgurl;


    @Override
    protected int getContentViewId() {
        return R.layout.activity_base_web_view_load_;
    }


    @Override
    protected void initView() {
        StatusBarUtil.setColor(this, getResources().getColor(R.color.colorAccent));
        initTitleBar(toolbar, "跳转中...");
        initWebSetting(webView.getSettings());
        initWebView();

//        popWindow = new WebViewLongClickedPopWindow(BaseWebViewActivity.this,
//                WebViewLongClickedPopWindow.IMAGE_VIEW_POPUPWINDOW, DisplayUtils.dip2px
//                (mContext, 120), ViewGroup.LayoutParams.WRAP_CONTENT);
//
//        popWindow.setOnItemClickListener(new WebViewLongClickedPopWindow.OnItemClickListener() {
//            @Override
//            public void onShowPicClicked() {
////                mPresenter.gotoImageBrowseClicked(mImgurl);
//            }
//
//            @Override
//            public void onSavePicClicked() {
////                mPresenter.saveImageClicked(BaseWebViewActivity.this, mImgurl);
//            }
//        });
    }


    protected void initTitleBar(Toolbar toolbar, String title) {
        toolbar.setTitle(title);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    /**
     * 初始化WebSetting
     *
     * @param settings WebSetting
     */
    protected void initWebSetting(WebSettings settings) {
        // 缩放至屏幕的大小
        settings.setLoadWithOverviewMode(true);
        // 保存表单数据
        settings.setSaveFormData(true);
        // 是否应该支持使用其屏幕缩放控件和手势缩放
        settings.setSupportZoom(true);
        //        //是否支持手势缩放控制
        //settings.setBuiltInZoomControls(true);
        //        是否隐藏原生的缩放控件
        //settings.setDisplayZoomControls(false);
        // 启动应用缓存
        settings.setAppCacheEnabled(true);
        // 排版适应屏幕，只显示一列
        settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        //  页面加载好以后，再放开图片
        settings.setBlockNetworkImage(false);
        // 使用localStorage则必须打开
        settings.setDomStorageEnabled(true);
        settings.setDatabaseEnabled(true);
        // WebView启用JavaScript执行。默认的是false。
        settings.setJavaScriptEnabled(true); // 设置支持javascript脚本
        settings.setJavaScriptCanOpenWindowsAutomatically(true);
        if (NetworkUtils.isConnected()) {
            settings.setCacheMode(WebSettings.LOAD_DEFAULT);
        } else {
            settings.setCacheMode(WebSettings.LOAD_CACHE_ONLY);
        }
    }

    protected void initWebView() {
        // 添加js交互接口类，并起别名 imagelistner
        webView.addJavascriptInterface(new SupportJavascriptInterface(this),
                "imagelistner");
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                view.getSettings().setJavaScriptEnabled(true);
                super.onPageFinished(view, url);
                // html加载完成之后，添加监听图片的点击js函数
                addWebImageClickListner(view);
                toolbar.setTitle(getToolbarTitle());
            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                view.getSettings().setJavaScriptEnabled(true);
                super.onPageStarted(view, url, favicon);
            }

            // 注入js函数监听
            protected void addWebImageClickListner(WebView webView) {
                // 这段js函数的功能就是，遍历所有的img节点，并添加onclick函数，
                // 函数的功能是在图片点击的时候调用本地java接口并传递url过去
                webView.loadUrl("javascript:(function(){" +
                        "var objs = document.getElementsByTagName(\"img\"); " +
                        "for(var i=0;i<objs.length;i++)  " +
                        "{"
                        + "    objs[i].onclick=function()  " +
                        "    {  "
                        + "        window.imagelistner.openImage(this.src);  " +
                        "    }  " +
                        "}" +
                        "})()");
            }
        });

        webView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                if (newProgress == 100) {
                    pvWeb.setVisibility(View.GONE);//加载完网页进度条消失
                } else {
                    pvWeb.setVisibility(View.VISIBLE);//开始加载网页时显示进度条
                    pvWeb.setProgress(newProgress);//设置进度值
                }
            }
        });

        webView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                WebView.HitTestResult result = ((WebView) v).getHitTestResult();
                if (null == result)
                    return false;
//                mPresenter.imageLongClicked(result);
                mImgurl = result.getExtra();

                return true;
            }
        });

        webView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                downX = (int) event.getX();
                downY = (int) event.getY();
                return false;
            }
        });
    }

    /**
     * js接口
     */
    public class SupportJavascriptInterface {

        private Context context;

        public SupportJavascriptInterface(Context context) {
            this.context = context;
        }

        @JavascriptInterface
        public void openImage(final String img) {
            Log.e("xxxx", "openImage: ---" + img);
        }
    }

    /**
     * 返回title，子类实现
     *
     * @return
     */
    protected abstract String getToolbarTitle();


    @Override
    protected void onDestroy() {
        webView.destroy();
        super.onDestroy();
    }
}
