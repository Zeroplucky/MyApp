package com.example.http;

/**
 * Created by Administrator on 2018/4/26.
 */

public interface OnHttpCall {


    void onSuccess(String body);

    void onFinish();
}
