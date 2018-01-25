package com.example.customwidget.view;

import com.example.customwidget.bean.RiverList;

import java.util.List;

/**
 * Created by Administrator on 2018/1/25.
 */

public interface ISearchView {

    void onBegin();

    void getDetailBean(List<RiverList.DetailBean.RiverListBean> listBeans);

    void onEnd();
}
