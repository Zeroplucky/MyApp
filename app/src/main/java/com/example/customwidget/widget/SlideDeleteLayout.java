package com.example.customwidget.widget;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.ViewDragHelper;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;

/**
 * Created by Administrator on 2018/3/13.
 */

public class SlideDeleteLayout extends FrameLayout {


    public static final String TAG = "xxx";
    private ViewDragHelper dragHelper;
    private View leftView;
    private View rightView;
    private int mWidth;
    private int mHeight;
    private int mRightWidth;

    public SlideDeleteLayout(@NonNull Context context) {
        this(context, null);
    }

    public SlideDeleteLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SlideDeleteLayout(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        dragHelper = ViewDragHelper.create(this, new ViewHelper());
    }


    public class ViewHelper extends ViewDragHelper.Callback {

        @Override
        public boolean tryCaptureView(View child, int pointerId) {
            return child == leftView;
        }

        @Override
        public int getViewHorizontalDragRange(View child) {
            return 10;
        }

        @Override
        public int clampViewPositionHorizontal(View child, int left, int dx) {
            if (left <= -mRightWidth) {
                left = -mRightWidth;
            } else if (left > 0) {
                left = 0;
            }
            Log.e(TAG, "clampViewPositionHorizontal: " + left);
            return left;
        }

        @Override
        public void onViewPositionChanged(View changedView, int left, int top, int dx, int dy) {
            super.onViewPositionChanged(changedView, left, top, dx, dy);

            leftView.layout(left, 0, left + mWidth, mHeight);
            rightView.offsetLeftAndRight(dx);
            Log.e(TAG, "onViewPositionChanged: " + left + " " + dx);
        }

        @Override
        public void onViewReleased(View releasedChild, float xvel, float yvel) {
            super.onViewReleased(releasedChild, xvel, yvel);

            if (xvel == 0 && leftView.getLeft() > -mRightWidth / 2) {
                if (dragHelper.smoothSlideViewTo(leftView, -mRightWidth, 0)) {
                    ViewCompat.postInvalidateOnAnimation(SlideDeleteLayout.this);
                }
            } else if (xvel < 0) {
                if (dragHelper.smoothSlideViewTo(leftView, -mRightWidth, 0)) {
                    ViewCompat.postInvalidateOnAnimation(SlideDeleteLayout.this);
                }
            } else {
                if (dragHelper.smoothSlideViewTo(leftView, 0, 0)) {
                    ViewCompat.postInvalidateOnAnimation(SlideDeleteLayout.this);
                }
            }

        }
    }

    @Override
    public void computeScroll() {
        super.computeScroll();
        if (dragHelper.continueSettling(true)) {
            ViewCompat.postInvalidateOnAnimation(SlideDeleteLayout.this);
        }
    }


    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
//        int action = ev.getAction();
//        switch (action) {
//            case MotionEvent.ACTION_DOWN:
//            case MotionEvent.ACTION_MOVE:
//            case MotionEvent.ACTION_UP:
//                return dragHelper.shouldInterceptTouchEvent(ev);
//        }
        return dragHelper.shouldInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
//        switch (ev.getAction()) {
//            case MotionEvent.ACTION_DOWN:
//            case MotionEvent.ACTION_MOVE:
//            case MotionEvent.ACTION_UP:
        dragHelper.processTouchEvent(ev);
        return true;
//        }
//        return super.onTouchEvent(ev);
    }


    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        leftView = getChildAt(0);
        rightView = getChildAt(1);
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        mWidth = leftView.getMeasuredWidth();
        mHeight = leftView.getMeasuredHeight();

        mRightWidth = rightView.getMeasuredWidth();

        Log.e(TAG, "onSizeChanged: " + mWidth + "  " + mHeight + "  " + mRightWidth);

    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        rightView.layout(mWidth, 0, mWidth + mRightWidth, mHeight);

    }

}
