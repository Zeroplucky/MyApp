package com.example.okgo_http.mvp;

import android.content.Context;

import com.example.base.mvpframe.presenter.BaseMvpPresenter;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.FileCallback;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Progress;
import com.lzy.okgo.model.Response;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;

/**
 * Created by Administrator on 2017/12/11.
 */

public class Demo1Presenter extends BaseMvpPresenter<IDemo1View> {

    private boolean isInitCache = false;

    public void downLoadFile(final Context mContext) {
        final IDemo1View mvpView = getMvpView();
        if (mvpView != null) {
            mvpView.onstart();
            OkGo.<File>get("http://gdown.baidu.com/data/wisegame/367722490deff692/baiduditu_830.apk")
                    .execute(new FileCallback() {
                        @Override
                        public void onSuccess(Response<File> response) {
                            mvpView.success(response.body());
                        }

                        @Override
                        public void downloadProgress(Progress progress) {
                            super.downloadProgress(progress);
                            mvpView.onProgress(progress);
                        }

                        @Override
                        public void onFinish() {
                            super.onFinish();
                            mvpView.onFinish();
                        }
                    });
        }
    }


    public void usedOkGoPost() {
        final IDemo1View mvpView = getMvpView();
        JSONObject jsonObject = new JSONObject();
        JSONObject obj2 = new JSONObject();
        try {
            obj2.put("areaParentId", "" + 0);
            jsonObject.put("methodName", "selectAreaInfo");
            jsonObject.put("requestParam", obj2);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        OkGo.<String>post("https://ceshi.zytdwl.cn/zyadmin/rest/user/admin")
                .headers("Authorization", "2e08705e80ad4e469fdc74fbbb88010d")
                .upJson(jsonObject)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        mvpView.weather(response.body());
                    }
                });

    }

}
