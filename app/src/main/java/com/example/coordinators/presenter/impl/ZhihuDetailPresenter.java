package com.example.coordinators.presenter.impl;

import com.example.coordinators.presenter.IZhihuDetailPresenter;
import com.example.coordinators.view.IZhihuDetailView;
import com.example.bean.ZhihuDailyDetailBean;
import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;

/**
 * Created by Administrator on 2017/12/27.
 */

public class ZhihuDetailPresenter implements IZhihuDetailPresenter {

    private IZhihuDetailView view;

    public ZhihuDetailPresenter(IZhihuDetailView view) {
        this.view = view;
    }

    @Override
    public void loadDailyDetail(String id) {

        OkGo.<String>get("http://news-at.zhihu.com/api/4/news/" + id)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        ZhihuDailyDetailBean bean = new Gson().fromJson(response.body(), ZhihuDailyDetailBean.class);
                        view.showDailyDetail(bean);
                    }

                    @Override
                    public void onFinish() {
                        super.onFinish();

                    }
                });
    }
}
