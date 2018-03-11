package com.example.customwidget.widget;

import android.content.Context;
import android.support.v4.widget.ViewDragHelper;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RelativeLayout;

/**
 * Created by Administrator on 2018/1/26.
 */

public class MyViewDragHelper extends RelativeLayout {

    private ViewDragHelper dragHelper;
    private View leftView;
    private View rightView;
    private String TAG = "xxx";

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


        //1
        @Override
        public boolean tryCaptureView(View child, int pointerId) {
            return child == rightView;
        }

        //2
        @Override
        public void onViewCaptured(View capturedChild, int activePointerId) {
            super.onViewCaptured(capturedChild, activePointerId);

        }


        @Override
        public int clampViewPositionHorizontal(View child, int left, int dx) {
            Log.e(TAG, "clampViewPositionHorizontal: " + left + "  " + dx);
            return left;
        }

        @Override
        public void onViewPositionChanged(View changedView, int left, int top, int dx, int dy) {
            super.onViewPositionChanged(changedView, left, top, dx, dy);
        }

        @Override
        public void onViewReleased(View releasedChild, float xvel, float yvel) {
            super.onViewReleased(releasedChild, xvel, yvel);
        }


    }


    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return dragHelper.shouldInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        try {
            dragHelper.processTouchEvent(event);
        } catch (Exception e) {

        }
        return true; //消费事件
    }


    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        leftView = getChildAt(0);
        rightView = getChildAt(1);
    }
}
