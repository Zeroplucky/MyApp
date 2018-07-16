package com.example.customwidget.widget.custom1;

import android.content.Context;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.ViewDragHelper;
import android.util.AttributeSet;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;

/**
 * Created by Administrator on 2018/1/26.
 */

public class MyDragLayout extends FrameLayout {

    private ViewDragHelper dragHelper;
    private View leftView;
    private View contentView;
    private String TAG = "xxx";
    private int contentWidth;
    private int mRand;
    private int contentHeight;

    private Status mStatus = Status.Close;
    private GestureDetectorCompat gestureDetector;

    public static enum Status {
        Open, Close, Draging
    }

    private OnDragListener mDragListener;

    public interface OnDragListener {
        void onClose();

        void onStartOpen();

        void onOpen();

        void onDrag();
    }

    public OnDragListener getmDragListener() {
        return mDragListener;
    }

    public void setmDragListener(OnDragListener mDragListener) {
        this.mDragListener = mDragListener;
    }

    public MyDragLayout(Context context) {
        super(context);
        init(context);

    }

    public MyDragLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public MyDragLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        dragHelper = ViewDragHelper.create(this, 1.0f, new ViewCallback());
        gestureDetector = new GestureDetectorCompat(context, new GestureDetector.SimpleOnGestureListener() {
            @Override
            public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
                return Math.abs(distanceX) > Math.abs(distanceY);
            }
        });
    }

    private class ViewCallback extends ViewDragHelper.Callback {

        /**
         * 尝试捕获的View,如果返回true 则被捕获的view可以拖动
         *
         * @param child     被捕获的View
         * @param pointerId 区分多点触摸的id
         * @return
         */
        @Override
        public boolean tryCaptureView(View child, int pointerId) {
            return child == contentView;
        }

        /**
         * 类似于down事件
         *
         * @param capturedChild   被捕获的View
         * @param activePointerId
         */
        @Override
        public void onViewCaptured(View capturedChild, int activePointerId) {
            Log.e(TAG, "onViewCaptured:   -----------" + capturedChild.toString());
            super.onViewCaptured(capturedChild, activePointerId);

        }


        /**
         * 实现此方法
         *
         * @param child
         * @return
         */
        @Override
        public int getViewHorizontalDragRange(View child) {
            Log.e(TAG, "getViewHorizontalDragRange:  -- " + child.toString());
            return mRand;
        }

        /**
         * @param child 当前拖拽的View
         * @param left  水平方向可拖动后的值 left+ 向右
         * @param dx    位置变化量 dx+ 向右
         * @return 此值决定view最终的位置
         */
        @Override
        public int clampViewPositionHorizontal(View child, int left, int dx) {
//            Log.e(TAG, "clampViewPositionHorizontal: " + left + "  " + dx);
            if (left > mRand) {
                left = mRand;
            } else if (left <= 0) {
                left = 0;
            }
            return left;
        }

        //  拖动View位置改变的时候调用, 处理要做的事情 (更新状态, 伴随动画, 重绘界面)
        //  类似于move事件
        @Override
        public void onViewPositionChanged(View changedView, int left, int top, int dx, int dy) {
            super.onViewPositionChanged(changedView, left, top, dx, dy);
            //当前状态
            Status updateStatus = updateStatus();
            if (updateStatus != mStatus) {
                if (mStatus == Status.Close && updateStatus == Status.Draging) {
                    if (mDragListener != null) {
                        mDragListener.onStartOpen();
                    }
                } else if (updateStatus == Status.Close) {
                    if (mDragListener != null)
                        mDragListener.onClose();
                } else if (updateStatus == Status.Open) {
                    if (mDragListener != null)
                        mDragListener.onOpen();
                } else {

                }
            } else {
                if (mDragListener != null)
                    mDragListener.onDrag();
            }

            mStatus = updateStatus;
        }

        /**
         * 拖动View被释放的时候调用
         * 类似于up事件
         *
         * @param releasedChild
         * @param xvel          手指松开的瞬时水平方向的速度, 向右为+
         * @param yvel          手指松开的瞬时竖直方向的速度, 向下为+
         */
        @Override
        public void onViewReleased(View releasedChild, float xvel, float yvel) {
            super.onViewReleased(releasedChild, xvel, yvel);
//            Log.e(TAG, "up:   " + releasedChild.toString());
//            Log.e(TAG, "onViewReleased: " + xvel + "  " + yvel);
            if (Math.abs(xvel) > Math.abs(yvel)) {
                if (xvel > 0) { //open
                    if (dragHelper.smoothSlideViewTo(contentView, mRand, 0)) {
                        ViewCompat.postInvalidateOnAnimation(MyDragLayout.this);
                    }
                } else if (xvel == 0 && contentView.getLeft() > mRand / 2) { //open
                    if (dragHelper.smoothSlideViewTo(contentView, mRand, 0)) {
                        ViewCompat.postInvalidateOnAnimation(MyDragLayout.this);
                    }
                } else { //close
                    close();
                }
            } else {
                close();
            }
        }

    }

    public void close() {
        if (dragHelper.smoothSlideViewTo(contentView, 0, 0)) {
            ViewCompat.postInvalidateOnAnimation(MyDragLayout.this);
        }
    }

    public Status updateStatus() {
        if (contentView.getLeft() == 0) {
            return Status.Close;
        } else if (contentView.getLeft() == mRand) {
            return Status.Open;
        } else {
            return Status.Draging;
        }
    }


    @Override
    public void computeScroll() {
        if (dragHelper.continueSettling(true)) {
            ViewCompat.postInvalidateOnAnimation(MyDragLayout.this);
        }
    }


    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return dragHelper.shouldInterceptTouchEvent(ev) & gestureDetector.onTouchEvent(ev);
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
        contentView = getChildAt(1);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        contentWidth = contentView.getMeasuredWidth();
        contentHeight = contentView.getMeasuredHeight();
        mRand = (int) (contentWidth * 0.6);
//        Log.e(TAG, "onFinishInflate: " + contentWidth);

    }
}
