package com.example.base.mvpframe.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.example.base.BaseFragment;
import com.example.base.mvpframe.factory.PresenterMvpFactory;
import com.example.base.mvpframe.factory.PresenterMvpFactoryImpl;
import com.example.base.mvpframe.presenter.BaseMvpPresenter;
import com.example.base.mvpframe.proxy.BaseMvpProxy;
import com.example.base.mvpframe.proxy.PresenterProxyInterface;


/**
 * @description 继承Fragment的MvpFragment基类
 */
public class BaseMvpFragment<V extends BaseMvpView, P extends BaseMvpPresenter<V>> extends BaseFragment implements PresenterProxyInterface<V, P> {

    /**
     * 调用onSaveInstanceState时存入Bundle的key
     */
    private static final String PRESENTER_SAVE_KEY = "PRESENTER_SAVE_KEY";
    /**
     * 创建被代理对象,传入默认Presenter的工厂
     */
    private BaseMvpProxy<V, P> mProxy = new BaseMvpProxy<>(PresenterMvpFactoryImpl.<V, P>createFactory(getClass()));

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(savedInstanceState != null){
            mProxy.onRestoreInstanceState(savedInstanceState);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        mProxy.onResume((V) this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mProxy.onDestroy();
    }

    @Override
    protected int getContentViewId() {
        return 0;
    }

    @Override
    protected void initView(View view) {

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void requestData() {

    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBundle(PRESENTER_SAVE_KEY,mProxy.onSaveInstanceState());
    }





    /**
     * 可以实现自己PresenterMvpFactory工厂
     *
     * @param presenterFactory PresenterFactory类型
     */
    @Override
    public void setPresenterFactory(PresenterMvpFactory<V, P> presenterFactory) {
        mProxy.setPresenterFactory(presenterFactory);
    }


    /**
     * 获取创建Presenter的工厂
     *
     * @return PresenterMvpFactory类型
     */
    @Override
    public PresenterMvpFactory<V, P> getPresenterFactory() {
        return mProxy.getPresenterFactory();
    }

    /**
     * 获取Presenter
     * @return P
     */
    @Override
    public P getMvpPresenter() {
        return mProxy.getMvpPresenter();
    }
}
