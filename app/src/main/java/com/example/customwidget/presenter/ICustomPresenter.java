package com.example.customwidget.presenter;

import com.example.customwidget.view.ICustomView;
import com.example.customwidget.view.ISearchView;

/**
 * Created by Administrator on 2018/1/23.
 */

public interface ICustomPresenter {


    void getCountByAdcode(ICustomView view);

    void getSearchData(ISearchView view);
}
