package com.example.dagger.component;

import com.example.coordinators.CoordinatorActivity;
import com.example.customwidget.CustomWidgetActivity;
import com.example.dagger.module.AppModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {AppModule.class})
public interface AppComponent {

    void inject(CustomWidgetActivity activity);

    void inject(CoordinatorActivity activity);

}
