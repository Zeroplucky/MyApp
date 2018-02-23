package com.example.huanxinim.presenter.impl;

import android.util.Log;

import com.example.huanxinim.presenter.ILoginPresenter;
import com.example.huanxinim.view.ILoginView;
import com.example.utils.StringUtils;
import com.example.utils.ThreadUtils;
import com.hyphenate.EMCallBack;
import com.hyphenate.chat.EMClient;

/**
 * Created by Administrator on 2017/12/14.
 */

public class LoginPresenterImpl implements ILoginPresenter {

    private ILoginView mLoginView;

    public LoginPresenterImpl(ILoginView loginView) {
        this.mLoginView = loginView;
    }

    @Override
    public void login(String userName, String pwd) {
        if (StringUtils.checkUserName(userName)) {
            if (StringUtils.checkPassword(pwd)) {
                mLoginView.onStartLogin();
                startLogin(userName, pwd);
            } else {
                mLoginView.onPasswordError();
            }
        } else {
            mLoginView.onUserNameError();
        }
    }

    private void startLogin(String userName, String pwd) {
        EMClient.getInstance().login(userName, pwd, mEMCallBack);
    }

    private EMCallBack mEMCallBack = new EMCallBack() {

        @Override
        public void onSuccess() {
            ThreadUtils.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    mLoginView.onLoginSuccess();
                }
            });
        }

        @Override
        public void onError(int i, String s) {
            Log.e("xxx", "onError: " + s + " i = " + i);
            if (i == 200) {
                //用户已登录
                ThreadUtils.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        EMClient.getInstance().logout(true);
                        mLoginView.onLodinAgin();
                    }
                });
            } else {
                ThreadUtils.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mLoginView.onLoginFailed();
                    }
                });
            }

        }

        @Override
        public void onProgress(int i, String s) {

        }
    };
}
