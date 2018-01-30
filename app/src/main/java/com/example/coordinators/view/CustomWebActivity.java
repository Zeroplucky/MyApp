package com.example.coordinators.view;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;

import com.example.coordinators.constant.Constants;
import com.example.coordinators.presenter.ICustomDetailPresenter;
import com.example.coordinators.presenter.impl.CustomDetailPresenter;
import com.example.utils.DisplayUtils;
import com.example.utils.ScreenUtils;

public class CustomWebActivity extends BaseWebViewActivity implements ICustomDetailView {

    private String mTitle, mUrl;
    private ICustomDetailPresenter mPresenter;

    @Override
    protected void initView() {
        super.initView();
        AppBarLayout.LayoutParams params = (AppBarLayout.LayoutParams) appBar.getChildAt(0)
                .getLayoutParams();
        // 控件的高强制设成56dp+状态栏高度
        params.height = DisplayUtils.dip2px(mContext, 56) + ScreenUtils.getStatusBarHeight(this);
        //
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            mUrl = bundle.getString(Constants.ARG_KEY_GANKIO_DETAIL_URL);
            mTitle = bundle.getString(Constants.ARG_KEY_GANKIO_DETAIL_TITLE);
        }
    }

    @Override
    protected void initData() {
        super.initData();
        mPresenter = new CustomDetailPresenter(this);
        mPresenter.loadDetail(mUrl);
    }


    @Override
    protected String getToolbarTitle() {
        return mTitle;
    }

    @Override
    public void loadUrl(String url) {
        webView.loadUrl(url);
    }
}
