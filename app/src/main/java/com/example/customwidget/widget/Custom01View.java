package com.example.customwidget.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Administrator on 2018/1/26.
 */

public class Custom01View extends View {


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
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

//        canvas.drawRoundRect();
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
