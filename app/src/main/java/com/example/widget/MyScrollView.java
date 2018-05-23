package com.example.widget;

import android.content.Context;
import android.os.StrictMode;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.FrameLayout;
import android.widget.OverScroller;

/**
 * Created by Administrator on 2018/5/2.
 * <p>
 * 学习scrollview
 */

public class MyScrollView extends FrameLayout {

    private boolean mIsBeingDragged = false;
    private ViewGroup mMainView;
    private VelocityTracker mVelocityTracker;
    private int mLastMotionY;
    private int mNestedYOffset;

    private int mTouchSlop;
    private int mMinimumVelocity;
    private int mMaximumVelocity;
    private int mOverscrollDistance;
    private int mOverflingDistance;

    private OverScroller mScroller;

    public MyScrollView(@NonNull Context context) {
        this(context, null);
    }

    public MyScrollView(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyScrollView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        mScroller = new OverScroller(getContext());
        setFocusable(true);
        setDescendantFocusability(FOCUS_AFTER_DESCENDANTS);
        setWillNotDraw(false);
        final ViewConfiguration configuration = ViewConfiguration.get(context);
        mTouchSlop = configuration.getScaledTouchSlop();
        mMinimumVelocity = configuration.getScaledMinimumFlingVelocity();
        mMaximumVelocity = configuration.getScaledMaximumFlingVelocity();
        mOverscrollDistance = configuration.getScaledOverscrollDistance();
        mOverflingDistance = configuration.getScaledOverflingDistance();
    }


    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {

        final int action = ev.getAction();
        if ((action == MotionEvent.ACTION_MOVE) && (mIsBeingDragged)) {
            return true;
        }

        switch (action) {
            case MotionEvent.ACTION_DOWN: {
                final int y = (int) ev.getY();
                final int x = (int) ev.getX();
                View pointView = findTopChildUnder(this, x, y);
                if (pointView != null && pointView == mMainView) {
                    mIsBeingDragged = false;
                    recycleVelocityTracker();
                    break;
                }

                mLastMotionY = y;

                initOrResetVelocityTracker();
                mVelocityTracker.addMovement(ev);

                mScroller.computeScrollOffset();
                mIsBeingDragged = !mScroller.isFinished();  //如果在滑动过程中则mIsBeingDragged = true
            }

            case MotionEvent.ACTION_MOVE: {
                final int y = (int) ev.getY();
                final int yDiff = Math.abs(y - mLastMotionY);
                if (yDiff > mTouchSlop) {
                    mIsBeingDragged = true;
                    mLastMotionY = y;
                    initVelocityTrackerIfNotExists();
                    mVelocityTracker.addMovement(ev);
                    mNestedYOffset = 0;
                    final ViewParent parent = getParent();
                    if (parent != null) {
                        parent.requestDisallowInterceptTouchEvent(true);
                    }
                }
                break;
            }

            case MotionEvent.ACTION_CANCEL:
            case MotionEvent.ACTION_UP:
                mIsBeingDragged = false;
                recycleVelocityTracker();
                break;
        }
        return mIsBeingDragged;

    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        initVelocityTrackerIfNotExists();

        final int action = ev.getAction();

        switch (action) {

            case MotionEvent.ACTION_DOWN: {
                if (getChildCount() == 0) {
                    return false;
                }

                if ((mIsBeingDragged = !mScroller.isFinished())) {
                    final ViewParent parent = getParent();
                    if (parent != null) {
                        parent.requestDisallowInterceptTouchEvent(true);
                    }
                }

                if (!mScroller.isFinished()) {
                    mScroller.abortAnimation();
                }

                mLastMotionY = (int) ev.getY();
                break;
            }

            case MotionEvent.ACTION_MOVE:

                final int y = (int) ev.getY();
                int deltaY = mLastMotionY - y;

                if (!mIsBeingDragged && Math.abs(deltaY) > mTouchSlop) {
                    final ViewParent parent = getParent();
                    if (parent != null) {
                        parent.requestDisallowInterceptTouchEvent(true);
                    }
                    mIsBeingDragged = true;
                    if (deltaY > 0) {
                        deltaY -= mTouchSlop;
                    } else {
                        deltaY += mTouchSlop;
                    }
                }
                if (mIsBeingDragged) {
                    offsetTopAndBottom(-deltaY);
                }
                break;

            case MotionEvent.ACTION_UP:
                if (mIsBeingDragged) {
                    final VelocityTracker velocityTracker = mVelocityTracker;
                    velocityTracker.computeCurrentVelocity(1000, mMaximumVelocity);
                    int initialVelocity = (int) velocityTracker.getYVelocity();

                    if ((Math.abs(initialVelocity) > mMinimumVelocity)) {
                        Log.e("xxx", "onTouchEvent: xxx=" + initialVelocity);
                    } else if (mScroller.springBack(getScrollX(), getScrollY(), 0, 0, 0, 100)) {
                        postInvalidateOnAnimation();
                    }

                    mIsBeingDragged = false;
                }
                break;
            case MotionEvent.ACTION_CANCEL:
                if (mIsBeingDragged && getChildCount() > 0) {
                    if (mScroller.springBack(getScrollX(), getScrollY(), 0, 0, 0, 100)) {
                        postInvalidateOnAnimation();
                    }

                    mIsBeingDragged = false;
                }
                break;

        }


        return true;


    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        mMainView = (ViewGroup) getChildAt(0);
    }

    private void recycleVelocityTracker() {
        if (mVelocityTracker != null) {
            mVelocityTracker.recycle();
            mVelocityTracker = null;
        }
    }

    private void initOrResetVelocityTracker() {
        if (mVelocityTracker == null) {
            mVelocityTracker = VelocityTracker.obtain();
        } else {
            mVelocityTracker.clear();
        }
    }

    private void initVelocityTrackerIfNotExists() {
        if (mVelocityTracker == null) {
            mVelocityTracker = VelocityTracker.obtain();
        }
    }

    public void fling(int velocityY) {
        if (getChildCount() > 0) {
            int height = getHeight();
            int bottom = getChildAt(0).getHeight();

            mScroller.fling(getScrollX(), getScrollY(), 0, velocityY, 0, 0, 0,
                    Math.max(0, bottom - height), 0, height / 2);
            postInvalidateOnAnimation();
        }
    }


    protected static View findTopChildUnder(ViewGroup parent, int x, int y) {
        final int childCount = parent.getChildCount();
        for (int i = childCount - 1; i >= 0; i--) {
            final View child = parent.getChildAt(i);
            if (x >= child.getLeft() && x < child.getRight()
                    && y >= child.getTop() && y < child.getBottom()) {
                return child;
            }
        }
        return null;
    }

}
