package com.example.base3;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import me.yokeyword.fragmentation.SupportFragment;


public abstract class MvpFragment<P extends MvpPresenter> extends SupportFragment implements MvpView {

    private P mPresenter;
    private Unbinder unbinder;
    public Context mContext;

    @Override
    public View onCreateView(LayoutInflater mInflater, ViewGroup container, Bundle savedInstanceState) {
        View mView = null;
        mContext = getContext();
        if (mView == null && setContentViewId() > 0) {
            mView = mInflater.inflate(setContentViewId(), container, false);
            unbinder = ButterKnife.bind(this, mView);
            initView(mView);
            initData();
        }
        return mView;
    }

    private void initData() {

    }

    private void initView(View mView) {

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
