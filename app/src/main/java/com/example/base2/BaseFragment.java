package com.example.base2;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import me.yokeyword.fragmentation.SupportFragment;


public abstract class BaseFragment<V, P extends BasePresenter<V>> extends SupportFragment {

    public P mPresenter;
    private Unbinder unbinder;

    @Override
    public View onCreateView(LayoutInflater mInflater, ViewGroup container, Bundle savedInstanceState) {
        View mView = null;
        if (mView == null && setContentViewId() > 0) {
            mView = mInflater.inflate(setContentViewId(), container, false);
            mPresenter = creatPresenter();
            if (mPresenter != null) {
                mPresenter.attachView((V) this);
            }
            unbinder = ButterKnife.bind(this, mView);
            initView(mView);
            initData();
        }
        return mView;
    }

    //得到当前界面的布局文件id(由子类实现)
    protected abstract int setContentViewId();

    protected abstract P creatPresenter();

    protected void initView(View view) {
    }

    protected void initData() {

    }

    public P getPresenter() {
        return mPresenter;
    }

    @Override
    public void onDestroy() {
        if (unbinder != null) {
            unbinder.unbind();
        }
        super.onDestroy();
        if (mPresenter != null) {
            mPresenter.detachView();
        }
    }

}
