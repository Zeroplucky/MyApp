package com.example.customwidget.widget.custom1;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.LinearLayout;
import android.widget.Scroller;

/**
 * Created by Administrator on 2018/1/26.
 */

public class VerticalLayout extends LinearLayout {

    private Scroller scroller;
    private View firstView;
    private View secView;
    private float evX;
    private float evY;
    private float mOffsetX, mOffsetY;

    public VerticalLayout(Context context) {
        super(context);
        init();
    }

    public VerticalLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public VerticalLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        this.setOrientation(VERTICAL);
        scroller = new Scroller(getContext());
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();

        if (getChildCount() != 2) {
            throw new RuntimeException("Only need two child view! Please check you xml file!");
        }

        firstView = getChildAt(0);
        secView = getChildAt(1);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {

        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                evX = ev.getX();
                evY = ev.getY();
                return true;
            case MotionEvent.ACTION_MOVE:
                mOffsetX = ev.getX() - evX;
                mOffsetY = ev.getY() - evY;
                Log.e("xxx", "dispatchTouchEvent: " + mOffsetX + " --- " + mOffsetY);
                if (Math.abs(mOffsetY) - Math.abs(mOffsetX) > ViewConfiguration.getTouchSlop()) {

//                    if (getScrollY() + mOffsetY > firstView.getHeight()) {
//                        return true;
//                    }
                    this.scrollBy(0, -(int) mOffsetY);
                    evX = ev.getX();
                    evY = ev.getY();
                }
                return true;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
                //松手时刻滑动
                scroller.startScroll(this.getScrollX(), this.getScrollY(), 0, 0);
                invalidate();
                evX = 0;
                evY = 0;
                mOffsetX = 0;
                mOffsetY = 0;
                break;
        }

        return super.dispatchTouchEvent(ev);
    }


    @Override
    public void computeScroll() {
        super.computeScroll();
        if (scroller.computeScrollOffset()) {
            this.scrollTo(scroller.getCurrX(), scroller.getCurrY());
            postInvalidate();
        }
    }
}
