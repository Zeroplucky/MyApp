package com.example.widget;

import android.content.Context;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.widget.Scroller;

/**
 * Created by Administrator on 2018/4/27.
 */

public class SwipeItemLayout extends ViewGroup {

    private ViewGroup mMainView;
    private ViewGroup mSideView;

    private int mScrollOffset;
    private int mMaxScrollOffset;

    private int mTouchSlop;
    private int mMaximumVelocity;
    private VelocityTracker mVelocityTracker;


    private float mLastMotionX;
    private float mLastMotionY;
    private ScrollRunnable mScrollRunnable;

    public SwipeItemLayout(Context context) {
        this(context, null);
    }

    public SwipeItemLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SwipeItemLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        ViewConfiguration configuration = ViewConfiguration.get(context);
        mTouchSlop = configuration.getScaledTouchSlop();
        mMaximumVelocity = configuration.getScaledMaximumFlingVelocity();

        mScrollRunnable = new ScrollRunnable(context);
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        if (!ensureChildren()) throw new RuntimeException("SwipeItemLayout的子视图不符合规定");

        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        MarginLayoutParams lp = null;
        int horizontalMargin, verticalMargin;
        int horizontalPadding = getPaddingLeft() + getPaddingRight();
        int verticalPadding = getPaddingTop() + getPaddingBottom();

        lp = (MarginLayoutParams) mMainView.getLayoutParams();
        horizontalMargin = lp.leftMargin + lp.rightMargin;
        verticalMargin = lp.topMargin + lp.bottomMargin;
        measureChildWithMargins(mMainView,
                widthMeasureSpec, horizontalMargin + horizontalPadding,
                heightMeasureSpec, verticalMargin + verticalPadding);

        if (widthMode == MeasureSpec.AT_MOST)
            widthSize = Math.min(widthSize, mMainView.getMeasuredWidth() + horizontalMargin + horizontalPadding);
        else if (widthMode == MeasureSpec.UNSPECIFIED)
            widthSize = mMainView.getMeasuredWidth() + horizontalMargin + horizontalPadding;

        if (heightMode == MeasureSpec.AT_MOST)
            heightSize = Math.min(heightSize, mMainView.getMeasuredHeight() + verticalMargin + verticalPadding);
        else if (heightMode == MeasureSpec.UNSPECIFIED)
            heightSize = mMainView.getMeasuredHeight() + verticalMargin + verticalPadding;

        setMeasuredDimension(widthSize, heightSize);

        //side layout大小为自身实际大小
        lp = (MarginLayoutParams) mSideView.getLayoutParams();
        verticalMargin = lp.topMargin + lp.bottomMargin;
        mSideView.measure(MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED),
                MeasureSpec.makeMeasureSpec(getMeasuredHeight() - verticalMargin - verticalPadding, MeasureSpec.EXACTLY));
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        if (!ensureChildren()) throw new RuntimeException("SwipeItemLayout的子视图不符合规定");

        int pl = getPaddingLeft();
        int pt = getPaddingTop();
        int pr = getPaddingRight();
        int pb = getPaddingBottom();

        MarginLayoutParams mainLp = (MarginLayoutParams) mMainView.getLayoutParams();
        int childLeft = pl + mainLp.leftMargin;
        int childTop = pt + mainLp.topMargin;
        int childRight = getWidth() - (pr + mainLp.rightMargin);
        int childBottom = getHeight() - (mainLp.bottomMargin + pb);
        mMainView.layout(childLeft, childTop, childRight, childBottom);

        MarginLayoutParams sideParams = (MarginLayoutParams) mSideView.getLayoutParams();
        childLeft = childRight + sideParams.leftMargin;
        childTop = pt + sideParams.topMargin;
        childRight = childLeft + sideParams.leftMargin + sideParams.rightMargin + mSideView.getMeasuredWidth();
        childBottom = getHeight() - (sideParams.bottomMargin + pb);
        mSideView.layout(childLeft, childTop, childRight, childBottom);

        mMaxScrollOffset = mSideView.getWidth() + sideParams.leftMargin + sideParams.rightMargin;
        mScrollOffset = mScrollOffset < -mMaxScrollOffset / 2 ? -mMaxScrollOffset : 0;

        offsetChildrenLeftAndRight(mScrollOffset);

    }

    void offsetChildrenLeftAndRight(int delta) {
        ViewCompat.offsetLeftAndRight(mMainView, delta);
        ViewCompat.offsetLeftAndRight(mSideView, delta);
    }


    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {

        boolean intercept = false;

        int action = ev.getAction();

        switch (action) {
            case MotionEvent.ACTION_DOWN:
                final int x = (int) ev.getX();
                final int y = (int) ev.getY();
                mLastMotionX = x;
                mLastMotionY = y;
                View pointView = findTopChildUnder(this, x, y);
                if (pointView != null && pointView == mMainView && mScrollOffset != 0) {

                    intercept = true;

                    return intercept;
                }
                break;
            case MotionEvent.ACTION_MOVE:
                final int x2 = (int) ev.getX();

                int deltaX = (int) (x2 - mLastMotionX);
                if (deltaX == 0) return false;

                int newLeft = mScrollOffset + deltaX;
                if ((deltaX > 0 && newLeft > 0) || (deltaX < 0 && newLeft < -mMaxScrollOffset)) {

                    newLeft = Math.min(newLeft, 0);
                    newLeft = Math.max(newLeft, -mMaxScrollOffset);
                }

                offsetChildrenLeftAndRight(newLeft - mScrollOffset);
                mScrollOffset = newLeft;
                break;
            case MotionEvent.ACTION_UP:
                intercept = false;
                break;
        }

        return intercept;
    }


    @Override
    public boolean onTouchEvent(MotionEvent ev) {

        return super.onTouchEvent(ev);
    }

    @Override
    protected LayoutParams generateDefaultLayoutParams() {
        return new MarginLayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
    }

    @Override
    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new MarginLayoutParams(getContext(), attrs);
    }

    private boolean ensureChildren() {
        int childCount = getChildCount();

        if (childCount != 2) return false;

        View childView = getChildAt(0);
        if (!(childView instanceof ViewGroup)) return false;
        mMainView = (ViewGroup) childView;

        childView = getChildAt(1);
        if (!(childView instanceof ViewGroup)) return false;
        mSideView = (ViewGroup) childView;
        return true;
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

    class ScrollRunnable implements Runnable {

        private Scroller mScroller;
        private boolean mAbort;
        private int mMinVelocity;


        ScrollRunnable(Context context) {
            mScroller = new Scroller(context);
            mAbort = false;


            ViewConfiguration configuration = ViewConfiguration.get(context);
            mMinVelocity = configuration.getScaledMinimumFlingVelocity();
        }

        void startScroll(int startX, int endX) {
            if (startX != endX) {
                mAbort = false;
                mScroller.startScroll(startX, 0, endX - startX, 0, 400);
                ViewCompat.postOnAnimation(SwipeItemLayout.this, this);
            }
        }

        void startFling(int startX, int xVel) {

            if (xVel > mMinVelocity && startX != 0) {
                startScroll(startX, 0);
                return;
            }
            if (xVel < -mMinVelocity && startX != -mMaxScrollOffset) {
                startScroll(startX, -mMaxScrollOffset);
                return;
            }
            startScroll(startX, startX > -mMaxScrollOffset / 2 ? 0 : -mMaxScrollOffset);
        }

        void abort() {
            if (!mAbort) {
                mAbort = true;
                if (!mScroller.isFinished()) {
                    mScroller.abortAnimation();
                }
            }
        }

        @Override
        public void run() {

        }
    }
}
