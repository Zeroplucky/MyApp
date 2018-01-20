package com.example.okgo_http.mvp;

import com.example.base.mvpframe.view.BaseMvpView;
import com.example.bean.ReportEventsBean;

/**
 * Created by Administrator on 2017/12/11.
 */

public interface IDemo2View extends BaseMvpView {

    void onFinish();

    void success(ReportEventsBean reportEventsBean);

    void onstart();

}
