package com.example.base3;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.example.http.HttpManager;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import me.yokeyword.fragmentation.SupportActivity;


public abstract class MvpActivity<P extends MvpPresenter> extends SupportActivity implements MvpView {

    private P mPresenter;
    private Unbinder unbinder;
    public Context mContext;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initBeforSetView();
        if (setContentViewId() != 0) {
            setContentView(setContentViewId());
        }
        mContext = this;
        unbinder = ButterKnife.bind(this);
        initView();
        initData();


    }

    private void initBeforSetView() {

    }

    private void initData() {

    }

    private void initView() {

    }

    protected abstract int setContentViewId();

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
    protected void onDestroy() {
        if (unbinder != null) {
            unbinder.unbind();
        }
        super.onDestroy();
        if (mPresenter != null) {
            mPresenter.destroy();
            mPresenter = null;
        }
    }


    @NonNull
    public P createPresenter() {
        return null;
    }


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
