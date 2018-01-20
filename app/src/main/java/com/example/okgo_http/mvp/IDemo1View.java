package com.example.okgo_http.mvp;

import com.example.base.mvpframe.view.BaseMvpView;
import com.lzy.okgo.model.Progress;

import java.io.File;

/**
 * Created by Administrator on 2017/12/11.
 */

public interface IDemo1View extends BaseMvpView {

    void onProgress(Progress progress);

    void success(File file);

    void onstart();

    void onFinish();

    void weather(String s);


}
