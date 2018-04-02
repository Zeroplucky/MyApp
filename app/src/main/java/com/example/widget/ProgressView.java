package com.example.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.widget.ProgressBar;

/**
 * Created by Administrator on 2018/4/2
 * https://github.com/guzhigang001/Bailan
 */

public class ProgressView extends ProgressBar {


    private int width;
    private int height;
    private Paint mStrockPaint;
    private Path mStrockPath;
    private int mProgress = 0;

    public ProgressView(Context context) {
        this(context, null);
    }

    public ProgressView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ProgressView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setMax(100);
        init(attrs);
    }


    private void init(AttributeSet attrs) {
        mStrockPaint = new Paint();
        mStrockPaint.setStrokeWidth(2f);
        mStrockPaint.setAntiAlias(true); //抗锯齿
        mStrockPaint.setDither(true);
        //
        mStrockPath = new Path();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        width = getWidth();
        height = getHeight();
    }

    @Override
    protected synchronized void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawStroke(canvas); //划边框
        drawProgress(canvas); //划进度条
        drawText(canvas); //划文字
    }

    private void drawText(Canvas canvas) {
        canvas.save();
        canvas.translate(width / 2, height / 2);
        String text = mProgress + "%";
        mStrockPaint.setColor(Color.BLACK);
        mStrockPaint.setTextSize(20);
        float measureText = mStrockPaint.measureText(text);
        Paint.FontMetrics fontMetrics = mStrockPaint.getFontMetrics();
        float textHeight = (-fontMetrics.ascent - fontMetrics.descent) / 2;
        canvas.drawText(text, -measureText / 2, textHeight, mStrockPaint);
        canvas.restore();
    }

    private void drawProgress(Canvas canvas) {
        Paint paint = mStrockPaint;
        paint.setStyle(Paint.Style.FILL);
        // 0 ~ width
        int progress = mProgress * (width / 100);
        RectF rectF = new RectF(0, 0, progress, height);
        canvas.drawRoundRect(rectF, 5, 5, paint);
    }


    private void drawStroke(Canvas canvas) {
        mStrockPath.reset();
        mStrockPath.moveTo(0, 0);
        mStrockPath.addRoundRect(new RectF(0, 0, width, height),
                new float[]{5, 5, 5, 5, 5, 5, 5, 5}, Path.Direction.CW);
        mStrockPaint.setColor(Color.RED);
        mStrockPaint.setStyle(Paint.Style.STROKE);
        canvas.drawPath(mStrockPath, mStrockPaint);
    }


    @Override
    public synchronized void setProgress(int progress) {
        super.setProgress(progress);
        this.mProgress = progress;
        postInvalidate();
    }
}
