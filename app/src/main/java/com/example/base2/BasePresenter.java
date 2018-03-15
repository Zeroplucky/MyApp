package com.example.base2;

import java.lang.ref.Reference;
import java.lang.ref.WeakReference;

/**
 * Created by zp on 2018/2/28.
 */

public class BasePresenter<V> {


    protected Reference<V> mViewRef;

    public void attachView(V view) {
        mViewRef = new WeakReference<V>(view);
    }

    public void detachView() {
        if (mViewRef != null) {
            mViewRef.clear();
            mViewRef = null;
        }
    }

    public V getView() {
        return mViewRef != null ? mViewRef.get() : null;
    }

}
