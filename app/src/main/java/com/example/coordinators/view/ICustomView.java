package com.example.coordinators.view;

import com.example.bean.CustomItemBean;

import java.util.List;

/**
 * Created by Administrator on 2017/12/26.
 */

public interface ICustomView {

    void start();

    void updateContentList(List<CustomItemBean> list);

    void loadMoreList(List<CustomItemBean> list);

    void onFinish();

}
