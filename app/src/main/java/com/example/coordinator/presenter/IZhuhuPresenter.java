package com.example.coordinator.presenter;

import com.example.bean.ZhihuDailyItemBean;

/**
 * Created by ZhangPeng on 2017/12/26.
 */

public interface IZhuhuPresenter {

    void getLastDailyList();

    void onItemClick(int position, ZhihuDailyItemBean item);

    void loadMoreList();
}
