package com.example.dagger.module;

import android.content.Context;

import com.example.coordinators.presenter.IFragmentItemPresenter;
import com.example.coordinators.presenter.impl.FragmentItemPresenter;
import com.example.coordinators.view.IFragmentItemView;
import com.example.customwidget.presenter.CustomPresenter;
import com.example.customwidget.presenter.ICustomPresenter;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;


@Module
public class AppModule {

    private Context context;
    private IFragmentItemView iFragmentItemView;


    public AppModule(Context context) {
        this.context = context;
    }


    public AppModule(Context context, IFragmentItemView iFragmentItemView) {
        this.context = context;
        this.iFragmentItemView = iFragmentItemView;
    }

    @Singleton
    @Provides
    Context getContext() {
        return context;
    }

    @Singleton
    @Provides
    public ICustomPresenter providePresenter() {
        return new CustomPresenter(context);
    }


    @Singleton
    @Provides
    IFragmentItemView getIFragmentItemView() {
        return iFragmentItemView;
    }

    @Singleton
    @Provides
    public IFragmentItemPresenter provideFragmentItemPresenter() {
        return new FragmentItemPresenter(iFragmentItemView);
    }


}
