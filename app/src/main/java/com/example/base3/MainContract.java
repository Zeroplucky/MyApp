package com.example.base3;


public interface MainContract {

    interface MainView extends MvpView {

        void showData(String data);
    }

    interface MainPresenter extends MvpPresenter<MainView> {

        void getData();
    }
}
