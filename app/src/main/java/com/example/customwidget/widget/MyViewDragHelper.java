package com.example.customwidget.widget;

import android.content.Context;
import android.support.v4.widget.ViewDragHelper;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RelativeLayout;

/**
 * Created by Administrator on 2018/1/26.
 */

public class MyViewDragHelper extends RelativeLayout {

    private ViewDragHelper dragHelper;

    public MyViewDragHelper(Context context) {
        super(context);
        init();
    }

    public MyViewDragHelper(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public MyViewDragHelper(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        dragHelper = ViewDragHelper.create(this, 1f, new ViewCallback());
    }

    private class ViewCallback extends ViewDragHelper.Callback {

        @Override
        public boolean tryCaptureView(View child, int pointerId) {
            return false;
        }
    }


    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return super.onInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return super.onTouchEvent(event);
    }
}
