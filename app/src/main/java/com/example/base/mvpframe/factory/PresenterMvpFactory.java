package com.example.base.mvpframe.factory;


import com.example.base.mvpframe.presenter.BaseMvpPresenter;
import com.example.base.mvpframe.view.BaseMvpView;

/**
 * @description Presenter工厂接口
 */
public interface PresenterMvpFactory<V extends BaseMvpView,P extends BaseMvpPresenter<V>> {

    /**
     * 创建Presenter的接口方法
     * @return 需要创建的Presenter
     */
    P createMvpPresenter();
}
