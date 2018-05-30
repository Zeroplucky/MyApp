package com.example.base3;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;


public abstract class MvpFragment<P extends MvpPresenter> extends Fragment implements MvpView {

    private P mPresenter;




    @Override
    public void loading() {

    }

    @Override
    public void succeed() {

    }

    @Override
    public void error() {

    }

    @Override
    public void empty() {

    }

    @Override
    public void complete() {

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (mPresenter != null) {
            mPresenter.destroy();
            mPresenter = null;
        }
    }

    @NonNull
    public abstract P createPresenter();

    protected P getPresenter() {
        if (mPresenter == null) {
            synchronized (MvpActivity.class) {
                if (mPresenter == null) {
                    mPresenter = createPresenter();
                    mPresenter.attachView(this);
                }
            }
        }
        return mPresenter;
    }
}
