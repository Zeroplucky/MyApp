package com.example.widget;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ScrollView;
import android.widget.Scroller;


/**
 * Created by --https://github.com/jczmdeveloper/XCUpHideScrollView
 * alter by zp
 */
public class FindScrollView extends ScrollView {

    public FindScrollView(Context context) {
        this(context, null);
    }

    public FindScrollView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public FindScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mScroller = new Scroller(context);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public FindScrollView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    private View mHeaderView;

    public void setHeaderView(View view) {
        mHeaderView = view;
    }

    private View mContentView;

    public void setContentView(View view) {
        this.mContentView = view;
    }

    private View scrollableView; //设置recyclerview

    public void setContentInnerScrollableView(View scrollableView) {
        this.scrollableView = scrollableView;
    }

    private int maxMoveY;
    private float mFirstY, mFirstX;
    private boolean isIntercept;


    public boolean onInterceptTouchEvent(MotionEvent ev) {
        if (isIntercept) {
            return true;
        }
        return super.onInterceptTouchEvent(ev);
    }


    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        if (mHeaderView == null || mContentView == null) {
            return super.dispatchTouchEvent(event);
        }

        if (maxMoveY == 0) {
            maxMoveY = mHeaderView.getMeasuredHeight();
        }

        isIntercept = false;

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mFirstY = event.getY();
                mFirstX = event.getX();
                break;
            case MotionEvent.ACTION_MOVE:
                if (scrollableView != null) {
                    float mCurrentY = event.getY();
                    float mCurrentX = event.getX();

                    float detalX = mCurrentX - mFirstX;
                    float detalY = mCurrentY - mFirstY;

                    if (Math.abs(detalY) < Math.abs(detalX)) {
                        break;
                    }

                    boolean isDown = mCurrentY > mFirstY;
                    boolean isUp = mCurrentY < mFirstY;

                    int scrollY = getScrollY();
                    if (isUp) { //上滑
                        if (scrollY >= maxMoveY) {
                            isIntercept = false;
                            scrollableView.dispatchTouchEvent(event);
                            return false;
                        } else {
                            isIntercept = true;
                        }
                    } else if (isDown) { //下滑
                        isIntercept = false;
                        scrollableView.dispatchTouchEvent(event);
                        return false;
                    }
                }
                break;
            case MotionEvent.ACTION_UP:
                if (getScrollY() < maxMoveY / 2) {
                    smoothScrollToSlow(0, 0, 500);
                } else {
                    smoothScrollToSlow(0, maxMoveY, 500);
                }
                break;
        }
        return super.dispatchTouchEvent(event);
    }

    public interface OnScrollChangedListener {
        void onScrollChanged(int x, int y, int oldX, int oldY);
    }

    private OnScrollChangedListener onScrollChangedListener;

    public void setOnScrollListener(OnScrollChangedListener onScrollChangedListener) {
        this.onScrollChangedListener = onScrollChangedListener;
    }

    @Override
    protected void onScrollChanged(int x, int y, int oldX, int oldY) {
        super.onScrollChanged(x, y, oldX, oldY);
        if (onScrollChangedListener != null) {
            onScrollChangedListener.onScrollChanged(x, y, oldX, oldY);
        }
    }

    private Scroller mScroller;

    //调用此方法滚动到目标位置  duration滚动时间
    public void smoothScrollToSlow(int fx, int fy, int duration) {
        int dx = fx - getScrollX();
        int dy = fy - getScrollY();
        smoothScrollBySlow(dx, dy, duration);
    }

    //调用此方法设置滚动的相对偏移
    public void smoothScrollBySlow(int dx, int dy, int duration) {
        mScroller.startScroll(getScrollX(), getScrollY(), dx, dy, duration);
        invalidate();//这里必须调用invalidate()才能保证computeScroll()会被调用，否则不一定会刷新界面，看不到滚动效果
    }

    @Override
    public void computeScroll() {
        super.computeScroll();
        //先判断mScroller滚动是否完成
        if (mScroller.computeScrollOffset()) {
            //这里调用View的scrollTo()完成实际的滚动
            scrollTo(mScroller.getCurrX(), mScroller.getCurrY());
            //必须调用该方法，否则不一定能看到滚动效果
            postInvalidate();
        }
    }

}
