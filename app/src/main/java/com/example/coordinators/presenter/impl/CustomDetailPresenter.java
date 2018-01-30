package com.example.coordinators.presenter.impl;

import com.example.coordinators.presenter.ICustomDetailPresenter;
import com.example.coordinators.view.ICustomDetailView;

/**
 * Created by Administrator on 2017/12/27.
 */

public class CustomDetailPresenter implements ICustomDetailPresenter {

    private ICustomDetailView view;

    public CustomDetailPresenter(ICustomDetailView view) {
        this.view = view;
    }

    @Override
    public void loadDetail(String url) {
        if (url == "" || url == null) {
            return;
        }
        view.loadUrl(url);
    }
}
