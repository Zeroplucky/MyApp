package com.example.coordinators.presenter;

/**
 * Created by Administrator on 2017/12/26.
 */

public interface ICustomPresenter {
    void getList(String type, int prePage, int page);

    void loadMoreList(int page);
}
