package com.example.customwidget.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.RelativeLayout;

/**
 * Created by Administrator on 2018/2/7.
 */

public class MyRelative extends RelativeLayout {

    public MyRelative(Context context) {
        super(context);
    }

    public MyRelative(Context context, AttributeSet attrs) {
        super(context, attrs);
    }


    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        boolean f = super.dispatchTouchEvent(ev); //super方法调用自己类中的onInterceptTouchEvent方法
        Log.e("xxx", "dispatchTouchEvent:  MyRelative  " + f);
        return f;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {//该方法会调用子类的dispatchTouchEvent方法
//        boolean f = super.onInterceptTouchEvent(ev); //默认为false
//        boolean f = false; //不打断,调用子类的dispatchTouchEvent方法
        boolean f = true; //打断,调用自己类中的onTouchEvent方法
        Log.e("xxx", "dispatchTouchEvent:  MyRelative  " + f);
        return f;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        boolean f = super.onTouchEvent(event);//默认为false
        Log.e("xxx", "dispatchTouchEvent:  MyRelative  " + f);
        return f;
    }
}
