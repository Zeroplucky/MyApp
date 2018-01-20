package com.example.okgo_http.mvp;

import android.content.Context;

import com.example.base.mvpframe.presenter.BaseMvpPresenter;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.FileCallback;
import com.lzy.okgo.model.Progress;
import com.lzy.okgo.model.Response;

import java.io.File;

/**
 * Created by Administrator on 2017/12/11.
 */

public class Demo2Presenter extends BaseMvpPresenter<IDemo2View> {

    private boolean isInitCache = false;

    public void downLoadFile(final Context mContext) {
        if (getMvpView() != null) {
            OkGo.<File>get("http://gdown.baidu.com/data/wisegame/367722490deff692/baiduditu_830.apk")
                    .execute(new FileCallback() {
                        @Override
                        public void onSuccess(Response<File> response) {

                        }

                        @Override
                        public void downloadProgress(Progress progress) {
                            super.downloadProgress(progress);

                        }
                    });
        }
    }

    public void request(int page) {
        final IDemo2View mvpView = getMvpView();
        if (mvpView != null) {
            mvpView.onstart();
//            OkGo.<String>get(" http://202.96.98.106:8091/yjpt/app/queryRiskEnterpriseInfo.action?uid=&page=" + page)
//                    .cacheMode(CacheMode.FIRST_CACHE_THEN_REQUEST)
//                    .execute(new StringCallback() {
//                        @Override
//                        public void onSuccess(Response<String> response) {
//                            ReportEventsBean reportEventsBean = new Gson().fromJson(response.body(), ReportEventsBean.class);
//                            mvpView.success(reportEventsBean);
//
//                            String title = JSONUtils.getString(response.body(), "success", "");
//                            Log.e("xxxx", "onSuccess: -" + title);
//
//                        }
//
//                        @Override
//                        public void onCacheSuccess(Response<String> response) {
//                            super.onCacheSuccess(response);
//                            if (!NetworkUtils.isConnected()) {
//                                onSuccess(response);
//                                Log.e("xxx", "onFinish: 没有网络");
//                            } else {
//
//                            }
//                        }
//
//                        @Override
//                        public void onFinish() {
//                            super.onFinish();
//                            mvpView.onFinish();
//                        }
//                    });



        }
    }
}
