package com.example.coordinators.presenter.impl;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.example.coordinators.view.ZhihuWebActivity;
import com.example.coordinators.constant.Constants;
import com.example.coordinators.presenter.IZhuhuPresenter;
import com.example.coordinators.view.IZhihuView;
import com.example.bean.ZhihuDailyItemBean;
import com.example.bean.ZhihuDailyListBean;
import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;

/**
 * Created by ZhangPeng on 2017/12/26.
 */

public class ZhihuPresenter implements IZhuhuPresenter {
    private IZhihuView view;
    private Context mContext;
    private String date;

    public ZhihuPresenter(IZhihuView view, Context mContext) {
        this.view = view;
        this.mContext = mContext;
    }

    @Override
    public void getLastDailyList() {
        view.start();
        OkGo.<String>get("http://news-at.zhihu.com/api/4/news/latest")
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        ZhihuDailyListBean bean = new Gson().fromJson(response.body(), ZhihuDailyListBean.class);
                        if (bean != null) {
                            date = bean.getDate();
                            view.updateContentList(bean.getStories());
                        }
                    }

                    @Override
                    public void onFinish() {
                        super.onFinish();
                        view.onFinish();
                    }

                    @Override
                    public void onError(Response<String> response) {
                        super.onError(response);

                    }
                });
    }

    @Override
    public void onItemClick(int position, ZhihuDailyItemBean item) {
        Intent intent = new Intent(mContext, ZhihuWebActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString(Constants.ARG_KEY_ZHIHU_DETAIL_ID, item.getId());
        bundle.putString(Constants.ARG_KEY_ZHIHU_DETAIL_TITLE, item.getTitle());
        intent.putExtras(bundle);
        mContext.startActivity(intent);
    }

    @Override
    public void loadMoreList() {
        OkGo.<String>get("http://news-at.zhihu.com/api/4/news/before/" + date)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        ZhihuDailyListBean bean = new Gson().fromJson(response.body(), ZhihuDailyListBean.class);
                        if (bean != null) {
                            date = bean.getDate();
                            view.loadMoreList(bean.getStories());
                        }
                    }

                    @Override
                    public void onFinish() {
                        super.onFinish();
                        view.onFinish();
                    }
                });
    }
}
