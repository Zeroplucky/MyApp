package com.example.aac.http;

import android.content.Context;

/**
 * Created by cai.jia on 2017/10/26 0026.
 */

public class RepositoryFactory {

    private static volatile RepositoryFactory instance;

    private IApiRepository repository;

    private RepositoryFactory(Context context) {
        repository = new ApiRepositoryImpl(context);
    }

    public static RepositoryFactory getInstance(Context context) {
        if (instance == null) {
            synchronized (RepositoryFactory.class) {
                if (instance == null) {
                    instance = new RepositoryFactory(context.getApplicationContext());
                }
            }
        }
        return instance;
    }

    public IApiRepository getApiRepository() {
        return repository;
    }
}
