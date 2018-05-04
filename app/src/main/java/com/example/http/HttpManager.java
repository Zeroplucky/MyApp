package com.example.http;

import android.app.Application;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.interceptor.HttpLoggingInterceptor;
import com.lzy.okgo.model.Response;
import com.lzy.okgo.request.GetRequest;
import com.lzy.okgo.request.PostRequest;

import java.util.Map;
import java.util.logging.Level;

import okhttp3.OkHttpClient;

/**
 * Created by Administrator on 2018/4/26.
 */

public class HttpManager {

    //DCL单例模式
    private static volatile HttpManager instance;

    private HttpManager(Application context) {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor("xxxx");
        loggingInterceptor.setPrintLevel(HttpLoggingInterceptor.Level.BODY);
        loggingInterceptor.setColorLevel(Level.SEVERE);
        builder.addInterceptor(loggingInterceptor);
        OkGo.getInstance().init(context)
                .setOkHttpClient(builder.build());
    }

    public static HttpManager init(Application context) {
        if (instance == null) {
            synchronized (HttpManager.class) {
                if (instance == null) {
                    instance = new HttpManager(context);
                }
            }
        }
        return instance;
    }


    public static void get(String url, Map<String, String> params, OnHttpCall httpBack) {
        GetRequest<String> request = OkGo.<String>get(url)
                .tag(url);
        if (params != null) {
            request.params(params);
        }
        request.execute(new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {
                String body = response.body();
                httpBack.onSuccess(body);

            }

            @Override
            public void onError(Response<String> response) {
                super.onError(response);

            }

            @Override
            public void onFinish() {
                super.onFinish();
//                httpBack.onFinish();
            }

        });

    }


    public static void post(String url, Map<String, String> params, OnHttpCall httpBack) {
        PostRequest<String> request = OkGo.<String>post(url)
                .tag(url);
        if (params != null) {
            request.params(params);
        }
        request.execute(new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {
                String body = response.body();
                httpBack.onSuccess(body);
            }

            @Override
            public void onError(Response<String> response) {
                super.onError(response);

            }

            @Override
            public void onFinish() {
                super.onFinish();
//                httpBack.onFinish();
            }
        });
    }


}
