package com.example.customwidget.widget;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;

import com.example.utils.DisplayUtils;

/**
 * Created by Administrator on 2018/1/26.
 */

public class Custom01View extends android.support.v7.widget.AppCompatTextView {


    public Custom01View(Context context) {
        super(context);
    }

    public Custom01View(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public Custom01View(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float x;
        float rawX;
        int leftView;
        float xView;
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                x = event.getX(); //当前触摸事件距离当前View左边的距离
                rawX = event.getRawX(); //当前触摸事件距离整个屏幕左边的距离
                //
                leftView = getLeft(); //View自身左边到父布局左边的距离
                xView = getX();
                //
                Rect rect = new Rect();
                getLocalVisibleRect(rect);
                Log.e("xxx", "onTouchEvent: down---" + "rect.left=" + rect.left + "---rect.top=" + rect.top +
                        "---rect.right=" + DisplayUtils.dip2px(getContext(), rect.right) + "---rect.bottom=" + DisplayUtils.dip2sp(getContext(), rect.bottom));
//                Log.e("xxx", "onTouchEvent: down---" + "x=" + x + "---rawX=" + rawX + "---leftView=" + leftView + "---xView=" + xView);
                break;
            case MotionEvent.ACTION_MOVE:

                break;
            case MotionEvent.ACTION_UP:

                break;
        }


        return super.onTouchEvent(event);
    }

    @Override
    public void setBackgroundColor(int color) {
        super.setBackgroundColor(color);
    }

    @Override
    public void setAlpha(float alpha) {
        super.setAlpha(alpha);
    }

    @Override
    public void setTranslationX(float translationX) {
        super.setTranslationX(translationX);
    }

}
