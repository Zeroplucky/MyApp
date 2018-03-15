package com.example.customwidget.widget;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by Administrator on 2018/1/26.
 */

public class Custom01View extends ViewPager {


    public Custom01View(Context context) {
        super(context);
    }

    public Custom01View(Context context, AttributeSet attrs) {
        super(context, attrs);
    }


    @Override
    protected void onPageScrolled(int position, float offset, int offsetPixels) {
        super.onPageScrolled(position, offset, offsetPixels);
        Log.e("Custom01View", "onPageScrolled: " + position);
    }





}
