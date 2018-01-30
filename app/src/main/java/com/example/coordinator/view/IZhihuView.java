package com.example.coordinator.view;

import com.example.bean.ZhihuDailyItemBean;

import java.util.List;

/**
 * Created by ZhangPeng on 2017/12/26.
 */

public interface IZhihuView {

    void start();

    void updateContentList(List<ZhihuDailyItemBean> list);

    void loadMoreList(List<ZhihuDailyItemBean> lists);

    void onFinish();
}
