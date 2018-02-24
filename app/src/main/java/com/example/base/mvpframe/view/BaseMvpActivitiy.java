package com.example.base.mvpframe.view;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.example.base.mvpframe.factory.PresenterMvpFactory;
import com.example.base.mvpframe.factory.PresenterMvpFactoryImpl;
import com.example.base.mvpframe.presenter.BaseMvpPresenter;
import com.example.base.mvpframe.proxy.BaseMvpProxy;
import com.example.base.mvpframe.proxy.PresenterProxyInterface;


/**
 * @description 继承自Activity的基类MvpActivity
 * 使用代理模式来代理Presenter的创建、销毁、绑定、解绑以及Presenter的状态保存,其实就是管理Presenter的生命周期
 */
public abstract class BaseMvpActivitiy<V extends BaseMvpView, P extends BaseMvpPresenter<V>> extends Activity implements PresenterProxyInterface<V, P> {

    private static final String PRESENTER_SAVE_KEY = "PRESENTER_SAVE_KEY";
    /**
     * 创建被代理对象,传入默认Presenter的工厂
     */
    private BaseMvpProxy<V, P> mProxy = new BaseMvpProxy<>(PresenterMvpFactoryImpl.<V, P>createFactory(getClass()));

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        mProxy.onCreate((V) this);
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null) {
            mProxy.onRestoreInstanceState(savedInstanceState.getBundle(PRESENTER_SAVE_KEY));
        }

    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mProxy.onDestroy();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBundle(PRESENTER_SAVE_KEY, mProxy.onSaveInstanceState());
    }

    @Override
    public void setPresenterFactory(PresenterMvpFactory<V, P> presenterFactory) {
        mProxy.setPresenterFactory(presenterFactory);
    }

    @Override
    public PresenterMvpFactory<V, P> getPresenterFactory() {
        return mProxy.getPresenterFactory();
    }

    @Override
    public P getMvpPresenter() {
        return mProxy.getMvpPresenter();
    }
}
