package com.example.customwidget.widget.custom1;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.LinearLayout;

/**
 * Created by Administrator on 2018/3/14.
 */

public class MyDragLinearLayout extends LinearLayout {

    public static final String TAG = "MyDragLinearLayoutxx";
    private MyDragLayout dl;

    public MyDragLinearLayout(@NonNull Context context) {
        super(context);
    }

    public MyDragLinearLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public MyDragLinearLayout(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    public void setDragLayout(MyDragLayout dl) {
        this.dl = dl;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent event) {
        if (dl.updateStatus() != MyDragLayout.Status.Close) {
            return true;
        } else if (dl.updateStatus() == MyDragLayout.Status.Close) {
            return super.onInterceptTouchEvent(event);
        } else {
            return super.onInterceptTouchEvent(event);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (dl.updateStatus() != MyDragLayout.Status.Close) {
            if (event.getAction() == MotionEvent.ACTION_UP) {
                dl.close();
            }
            return true;
        } else if (dl.updateStatus() == MyDragLayout.Status.Close) {
            return super.onTouchEvent(event);
        } else {
            return super.onTouchEvent(event);
        }
    }
}
