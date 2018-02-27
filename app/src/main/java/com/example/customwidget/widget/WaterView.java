package com.example.customwidget.widget;

import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Administrator on 2018/2/27.
 */

public class WaterView extends FrameLayout {

    private List<View> mViews = new ArrayList<>();
    private int maxW, maxH;
    private Random mRandom = new Random();
    private Point mDestroyPoint;//view销毁时的点
    private float mMaxSpace;//父控件对角线的距离

    public WaterView(@NonNull Context context) {
        super(context);
    }

    public WaterView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public WaterView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mMaxSpace = (float) Math.sqrt(w * w + h * h);
        maxW = w - (int) dp2Px(30);
        maxH = h - (int) dp2Px(30);
        mDestroyPoint = new Point((int) getX(), h);
        setData("10 g");

//        Log.e("xxx", "onSizeChanged: w = " + w + " h =" + h +
//                " oldw = " + oldw + "  oldh =" + oldh +
//                " getScreenW=" + getScreenW() + " getScreenH=" + getScreenH() +
//                " getX=" + getX()
//        );
    }


    public void setData(String s) {
        CircleTextView textView = new CircleTextView(getContext(), s);

        int nextIntx = mRandom.nextInt(maxW);
        int nextInty = mRandom.nextInt(maxH);

//        Log.e("xxx", "setData: " + nextIntx + "  " + nextInty);
        textView.setX(nextIntx);
        textView.setY(nextInty);

        addView(textView);

        textView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                handlePoint(v);
            }
        });
    }


    private void handlePoint(final View v) {
        final float x = v.getX();
        final float y = v.getY();
        Log.e("xxx", "handlePoint: " + x + "  " + y);

        float distance = getDistance(new Point((int) x, (int) y), mDestroyPoint);

        final ValueAnimator valueAnimator = ValueAnimator.ofFloat(x, 0);
        valueAnimator.setDuration((long) (2000 / mMaxSpace * distance));
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float value = (float) animation.getAnimatedValue();
                float alpha = value / x;
                float translationY = y + (x - value) * (maxH - y) / x;

                v.setTranslationY(translationY);
                v.setTranslationX(value);
                v.setAlpha(alpha);
                v.setScaleY(alpha);
                v.setScaleX(alpha);

                Log.e("xxx", "onAnimationUpdate:translationY= " + translationY
                        + "  setTranslationX" + value + "  alpha=" + alpha
                );
            }

        });
        valueAnimator.start();
    }

    /**
     * 获取两个点之间的距离
     */
    public float getDistance(Point p1, Point p2) {
        float _x = Math.abs(p2.x - p1.x);
        float _y = Math.abs(p2.y - p1.y);
        return (float) Math.sqrt(_x * _x + _y * _y);
    }

    private float getScreenW() {
        return getContext().getResources().getDisplayMetrics().widthPixels;
    }

    private int getScreenH() {
        return getContext().getResources().getDisplayMetrics().heightPixels;
    }

    private float dp2Px(int value) {
        return value * (getContext().getResources().getDisplayMetrics().density);
    }


    @SuppressLint("AppCompatCustomView")
    private class CircleTextView extends TextView {

        private Paint paint, mTextPaint;
        private Object object;

        public CircleTextView(Context context, Object object) {
            super(context);

            this.object = object;

            paint = new Paint();
            paint.setColor(Color.BLUE);

            mTextPaint = new Paint();
            mTextPaint.setColor(Color.RED);
            mTextPaint.setTextSize(dp2Px(20));


        }

        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);
            canvas.drawCircle(dp2Px(30), dp2Px(30), dp2Px(30), paint);
            canvas.drawText(object.toString(), dp2Px(10), dp2Px(30), mTextPaint);
        }
    }
}
