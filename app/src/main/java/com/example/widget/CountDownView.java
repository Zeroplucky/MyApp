package com.example.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.Build;
import android.os.CountDownTimer;
import android.util.AttributeSet;
import android.view.View;

import com.example.base.R;

public class CountDownView extends View {

    private Context mContext;

    public CountDownView(Context context) {
        this(context, null);
    }

    public CountDownView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CountDownView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;

        TypedArray a = mContext.obtainStyledAttributes(attrs, R.styleable.CountDownView);

        mTextSize = a.getDimensionPixelSize(R.styleable.CountDownView_textSize, dp2px(12));

        a.recycle();

        initPaint();
    }

    private Paint mPaint;

    private int mTextSize;

    private void initPaint() {
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setColor(Color.BLACK);
        mPaint.setTextSize(mTextSize);
        mPaint.setStrokeWidth(dp2px(2));
        mPaint.setTextAlign(Paint.Align.CENTER);
    }

    public interface OnCountDownFinishListener {
        void onFinish();
    }

    private long hour;
    private long min;
    private long sec;

    private long mTargetTime;

    private CountDownTimer mTimer = null;
    private long mCountDownTime;
    private OnCountDownFinishListener mOnCountDownFinishListener;

    public void setmOnCountDownFinishListener(OnCountDownFinishListener mOnCountDownFinishListener) {
        this.mOnCountDownFinishListener = mOnCountDownFinishListener;
    }

    public void setCountDownTime(long targetTime) {
        mCountDownTime = targetTime + 1000; //加上一秒
        this.mTargetTime = System.currentTimeMillis() + targetTime;
        calculateTargetTime();
    }

    public void start() {
        if (mCountDownTime < 0) {
            mCountDownTime = 0;
        }
        if (mTimer != null) {
            mTimer.cancel();
        } else {
            mTimer = new CountDownTimer(mCountDownTime, 1000) {
                @Override
                public void onTick(long millisUntilFinished) {
                    calculateTargetTime();
                    invalidate();
                }

                @Override
                public void onFinish() {
                    if (mOnCountDownFinishListener != null) {
                        mOnCountDownFinishListener.onFinish();
                    }
                }
            };
        }
        mTimer.start();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.translate(getMeasuredWidth() / 2, getMeasuredHeight() / 2);

        drawRect(canvas, -31);
        drawRect(canvas, 0);
        drawRect(canvas, 31);

        mPaint.setColor(Color.WHITE);

        String strHour = hour < 10 ? "0" + hour : hour + "";
        canvas.drawText(strHour, dp2px(-31), dp2px(4), mPaint);

        String strMinute = min < 10 ? "0" + min : min + "";
        canvas.drawText(strMinute, 0, dp2px(4), mPaint);

        String strSec = sec < 10 ? "0" + sec : sec + "";
        canvas.drawText(strSec, dp2px(31), dp2px(4), mPaint);

        mPaint.setColor(Color.BLACK);
        canvas.drawText(":", dp2px(-16), dp2px(4), mPaint);
        canvas.drawText(":", dp2px(16), dp2px(4), mPaint);
    }

    private void drawRect(Canvas canvas, float x) {
        mPaint.setColor(Color.BLACK);
        RectF rect = new RectF(dp2px(x - 10), dp2px(-6.5f), dp2px(x + 10), dp2px(6.5f));
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            canvas.drawRoundRect(rect, dp2px(4), dp2px(4), mPaint);
        } else {
            canvas.drawRect(rect, mPaint);
        }
    }

    private void calculateTargetTime() {
        if (mTargetTime != 0) {
            long countDownTime = mTargetTime - System.currentTimeMillis();
            if (countDownTime >= 0) {
                hour = millis2TimeSpan(countDownTime, TimeUnit.HOUR);
                min = millis2TimeSpan(countDownTime, TimeUnit.MIN) % 60;
                sec = millis2TimeSpan(countDownTime, TimeUnit.SEC) % 60;
            }
        }
    }

    public int dp2px(float dpValue) {
        final float scale = mContext.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    /*秒与毫秒的倍数*/
    public static final int SEC = 1000;
    /*分与毫秒的倍数*/
    public static final int MIN = 60000;
    /*时与毫秒的倍数*/
    public static final int HOUR = 3600000;
    /*天与毫秒的倍数*/
    public static final int DAY = 86400000;

    public enum TimeUnit {
        MSEC,
        SEC,
        MIN,
        HOUR,
        DAY
    }

    public static long millis2TimeSpan(long millis, TimeUnit unit) {
        switch (unit) {
            default:
            case MSEC:
                return millis;
            case SEC:
                return millis / SEC;
            case MIN:
                return millis / MIN;
            case HOUR:
                return millis / HOUR;
            case DAY:
                return millis / DAY;
        }
    }

}
