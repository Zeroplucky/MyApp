package com.example.base.mvpframe.view;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.example.base.BaseActivity;
import com.example.base.mvpframe.factory.PresenterMvpFactory;
import com.example.base.mvpframe.factory.PresenterMvpFactoryImpl;
import com.example.base.mvpframe.presenter.BaseMvpPresenter;
import com.example.base.mvpframe.proxy.BaseMvpProxy;
import com.example.base.mvpframe.proxy.PresenterProxyInterface;


/**
 * @description
 */
public class BaseMvpAppCompatActivity<V extends BaseMvpView, P extends BaseMvpPresenter<V>> extends BaseActivity implements PresenterProxyInterface<V, P> {
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
    protected int getContentViewId() {
        return 0;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void requestData() {

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
