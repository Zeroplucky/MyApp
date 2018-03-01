package com.example.customwidget.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.SweepGradient;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import java.util.Calendar;

/**
 * Created by Administrator on 2018/3/1.
 */

public class ClockView extends View {

    private int width;
    //圆心点x和y坐标,x=y
    private int mCenterPoint;
    //圆的半径,设置为屏幕的三分之一
    private int radius;
    //屏幕边缘到圆环的距离
    private int distance;

    /* 时针角度 */
    private float mHourDegree;
    /* 分针角度 */
    private float mMinuteDegree;
    /* 秒针角度 */
    private float mSecondDegree;
    //刻度笔,为浅白色
    private Paint paint;

    /* 渐变矩阵，作用在SweepGradient */
    private Matrix mGradientMatrix;
    /* 梯度扫描渐变 */
    private SweepGradient mSweepGradient;

    private int weithColor;
    private int grayColor;

    //渐变比
    private Paint change_paint;
    //秒针比
    private Paint sPaint;
    //分针比
    private Paint mPaint;
    //是真比
    private Paint hPaint;
    private Canvas mCanvas;
    /* 时针路径 */
    private Path mHourHandPath;
    /* 分针路径 */
    private Path mMinuteHandPath;
    /* 秒针路径 */
    private Path mSecondHandPath;
    private RectF mCircleRectF;

    public ClockView(Context context) {
        this(context, null);
    }

    public ClockView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ClockView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        paint = new Paint();
        paint.setColor(Color.parseColor("#88ffffff"));
        paint.setStyle(Paint.Style.STROKE);
        paint.setAntiAlias(true);

        mGradientMatrix = new Matrix();

        weithColor = Color.parseColor("#ffffff");
        grayColor = Color.parseColor("#88ffffff");

        mSecondHandPath = new Path();
        mMinuteHandPath = new Path();
        mHourHandPath = new Path();

        sPaint = new Paint();
        sPaint.setColor(Color.parseColor("#ffffff"));
        sPaint.setStyle(Paint.Style.STROKE);
        sPaint.setAntiAlias(true);

        change_paint = new Paint();
        change_paint.setStyle(Paint.Style.STROKE);
        change_paint.setAntiAlias(true);
        DashPathEffect dashPathEffect = new DashPathEffect(new float[]{5, 5}, 0);
        change_paint.setPathEffect(dashPathEffect);

        mCircleRectF = new RectF();
        mPaint = new Paint();
        mPaint.setColor(Color.parseColor("#ffffff"));
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setAntiAlias(true);


        hPaint = new Paint();
        hPaint.setColor(Color.parseColor("#88ffffff"));
        hPaint.setStyle(Paint.Style.FILL);
        hPaint.setAntiAlias(true);
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        width = Math.min(widthSize, heightSize);
        if (widthMode == MeasureSpec.UNSPECIFIED) { //设置多少就是多少。
            width = heightSize;
        } else if (heightMode == MeasureSpec.UNSPECIFIED) {
            width = widthSize;
        }
        //圆心坐标
        mCenterPoint = width / 2;
        //圆半径
        radius = width / 3;
        //圆环距屏幕边缘距离,都会算吧  哈哈哈
        distance = width / 6;
        mSweepGradient = new SweepGradient(mCenterPoint, mCenterPoint,
                new int[]{grayColor, weithColor}, new float[]{0.75f, 1});
        //设置尺寸
        setMeasuredDimension(width, width);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mCanvas = canvas;
        //获取系统时间
        getTime();
//        //
//        drawNum(canvas);
//        //
//        drawArc(canvas);
//        //外弧到刻度的距离,
//        drawSweep(canvas);
//        //画秒针
//        drawSPointer();
//        //画时针，被分针覆盖
//        drawHPointer();
//        //画分针
//        drawMPointer();
        drawSweep();
        //刷新
        invalidate();
    }


    //扫描
    private void drawSweep() {
        Paint paint = new Paint();
        paint.setColor(Color.parseColor("#55ffffff"));
        paint.setStyle(Paint.Style.FILL);
        mCanvas.drawCircle(mCenterPoint, mCenterPoint, radius, paint);
        //
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.parseColor("#ffffff"));

        RectF rectF = new RectF(distance, distance,
                width - distance, width - distance);
        mCanvas.drawArc(rectF, -90, mSecondDegree, true, paint);

    }


    private void drawSweep(Canvas canvas) {
        canvas.save();
        //处理颜色渐变
        mGradientMatrix.setRotate(mSecondDegree - 90, mCenterPoint, mCenterPoint);
        mSweepGradient.setLocalMatrix(mGradientMatrix);
        change_paint.setShader(mSweepGradient);
        int innerdistance = radius / 8;
        change_paint.setStrokeWidth(30);
        canvas.drawCircle(mCenterPoint, mCenterPoint, radius - innerdistance, change_paint);
        canvas.restore();
    }

    private void drawArc(Canvas canvas) {
        RectF rectF = new RectF(distance - 10, distance - 10, distance + radius * 2 + 10, distance + radius * 2 + 10);
        canvas.drawArc(rectF, -80, 75, false, paint);
        canvas.drawArc(rectF, 5, 75, false, paint);
        canvas.drawArc(rectF, 95, 75, false, paint);
        canvas.drawArc(rectF, 185, 75, false, paint);
    }

    private void drawNum(Canvas canvas) {
        //
        paint.setTextSize(30);
        canvas.drawText("12", width / 2, distance, paint);
        canvas.drawText("3", width - distance, width / 2, paint);
        canvas.drawText("6", width / 2, width - distance + 10, paint);
        canvas.drawText("9", distance - 10, width / 2, paint);
    }

    private void drawSPointer() {
        mCanvas.save();
        mCanvas.rotate(mSecondDegree, mCenterPoint, mCenterPoint);
        mSecondHandPath.reset();
        mSecondHandPath.moveTo(mCenterPoint, distance - 20 + 0.26f * radius);
        mSecondHandPath.lineTo(mCenterPoint - 0.05f * radius, distance - 20 + 0.34f * radius);
        mSecondHandPath.lineTo(mCenterPoint + 0.05f * radius, distance - 20 + 0.34f * radius);
        mSecondHandPath.close();
        mCanvas.drawPath(mSecondHandPath, sPaint);
        mCanvas.restore();

    }

    private void drawHPointer() {
        mCanvas.save();
        mCanvas.rotate(mHourDegree, getWidth() / 2, getHeight() / 2);
        mHourHandPath.reset();
        float offset = distance;
        mHourHandPath.moveTo(getWidth() / 2 - 0.018f * radius, getHeight() / 2 - 0.03f * radius);
        mHourHandPath.lineTo(getWidth() / 2 - 0.009f * radius, offset + 0.48f * radius);
        mHourHandPath.quadTo(getWidth() / 2, offset + 0.46f * radius,
                getWidth() / 2 + 0.009f * radius, offset + 0.48f * radius);
        mHourHandPath.lineTo(getWidth() / 2 + 0.018f * radius, getHeight() / 2 - 0.03f * radius);
        mHourHandPath.close();
        hPaint.setStyle(Paint.Style.FILL);
        mCanvas.drawPath(mHourHandPath, hPaint);

        mCircleRectF.set(getWidth() / 2 - 0.03f * radius, getHeight() / 2 - 0.03f * radius,
                getWidth() / 2 + 0.03f * radius, getHeight() / 2 + 0.03f * radius);
        hPaint.setStyle(Paint.Style.STROKE);
        hPaint.setStrokeWidth(0.01f * radius);
        mCanvas.drawArc(mCircleRectF, 0, 360, false, hPaint);
        mCanvas.restore();
    }

    private void drawMPointer() {
        mCanvas.save();
        mCanvas.rotate(mMinuteDegree, getWidth() / 2, getHeight() / 2);
        mMinuteHandPath.reset();
        float offset = distance - 30;
        mMinuteHandPath.moveTo(getWidth() / 2 - 0.01f * radius, getHeight() / 2 - 0.03f * radius);
        mMinuteHandPath.lineTo(getWidth() / 2 - 0.008f * radius, offset + 0.365f * radius);
        mMinuteHandPath.quadTo(getWidth() / 2, offset + 0.345f * radius,
                getWidth() / 2 + 0.008f * radius, offset + 0.365f * radius);
        mMinuteHandPath.lineTo(getWidth() / 2 + 0.01f * radius, getHeight() / 2 - 0.03f * radius);
        mMinuteHandPath.close();
        mPaint.setStyle(Paint.Style.FILL);
        mCanvas.drawPath(mMinuteHandPath, mPaint);

        mCircleRectF.set(getWidth() / 2 - 0.03f * radius, getHeight() / 2 - 0.03f * radius,
                getWidth() / 2 + 0.03f * radius, getHeight() / 2 + 0.03f * radius);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(0.02f * radius);
        mCanvas.drawArc(mCircleRectF, 0, 360, false, mPaint);
        mCanvas.restore();
    }

    private void getTime() {
        Calendar calendar = Calendar.getInstance();
        //毫秒
        float milliSecond = calendar.get(Calendar.MILLISECOND);
        //秒
        float second = calendar.get(Calendar.SECOND) + milliSecond / 1000;
        //分
        float minute = calendar.get(Calendar.MINUTE) + second / 60;
        //时
        float hour = calendar.get(Calendar.HOUR) + minute / 60;
        //求出三个指针的角度
        mSecondDegree = second * 6;
        mMinuteDegree = 6 * minute;
        mHourDegree = 30 * hour;
    }
}
