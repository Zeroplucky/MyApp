package com.example.Coordinator.view;

import android.os.Bundle;

import com.bumptech.glide.Glide;
import com.example.Coordinator.constant.Constants;
import com.example.Coordinator.presenter.IZhihuDetailPresenter;
import com.example.Coordinator.presenter.impl.ZhihuDetailPresenter;
import com.example.bean.ZhihuDailyDetailBean;
import com.example.utils.HtmlUtils;

public class ZhihuWebActivity extends BaseWebViewActivity implements IZhihuDetailView {

    private String mId, mTitle;
    private IZhihuDetailPresenter mPresenter;

    @Override
    protected void initView() {
        super.initView();
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            mId = bundle.getString(Constants.ARG_KEY_ZHIHU_DETAIL_ID);
            mTitle = bundle.getString(Constants.ARG_KEY_ZHIHU_DETAIL_TITLE);
        }
    }

    @Override
    protected void initData() {
        super.initData();
        tvDetailTitle.setText(mTitle);
        mPresenter = new ZhihuDetailPresenter(this);
        mPresenter.loadDailyDetail(mId);
    }


    @Override
    protected String getToolbarTitle() {
        return mTitle;
    }

    @Override
    public void showDailyDetail(ZhihuDailyDetailBean bean) {
        Glide.with(mContext).load(bean.getImage()).crossFade().into(ivDetail);
        tvDetailTitle.setText(bean.getTitle());
        tvDetailcopyright.setText(bean.getImage_source());
        String htmlData = HtmlUtils.createHtmlData(bean.getBody(), bean.getCss(), bean.getJs());
        webView.loadData(htmlData, HtmlUtils.MIME_TYPE, HtmlUtils.ENCODING);
    }
}
