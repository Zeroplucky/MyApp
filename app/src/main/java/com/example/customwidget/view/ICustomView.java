package com.example.customwidget.view;

import com.example.customwidget.bean.CountByAdcodeBean;

/**
 * Created by Administrator on 2018/1/23.
 */

public interface ICustomView {


    void onBegin();

    void getDetailBean(CountByAdcodeBean.DetailBean bean);

    void onEnd();
}
