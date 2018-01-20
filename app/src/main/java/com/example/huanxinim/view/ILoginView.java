package com.example.huanxinim.view;

/**
 * Created by Administrator on 2017/12/14.
 */

public interface ILoginView {

    void onUserNameError();

    void onPasswordError();

    void onStartLogin();

    void onLoginSuccess();

    void onLoginFailed();
}
