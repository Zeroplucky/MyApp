package com.example.widget;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Created by Administrator on 2017/12/14.
 */

public class NoScrollViewPager extends ViewPager {
    private boolean isScrollable = false;

    public NoScrollViewPager(Context context) {
        super(context);
    }

    public NoScrollViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onTouchEvent(MotionEvent arg0) {
        if (isScrollable) {
            return super.onTouchEvent(arg0);
        }
        return false;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent arg0) {
        if (isScrollable) {
            return super.onInterceptTouchEvent(arg0);
        }
        return false;
    }
}
