package com.example.base3;

import android.content.Context;
import android.support.annotation.StringRes;


public interface MvpView {



    void loading();

    void succeed();

    void error();

    void empty();

    void complete();

}
