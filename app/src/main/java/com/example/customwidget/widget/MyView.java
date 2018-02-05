package com.example.customwidget.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Administrator on 2018/2/5.
 */

public class MyView extends View {

    //画笔
    private Paint mPaint;
    //
    private Path path;

    public MyView(Context context) {
        super(context);
    }

    public MyView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public MyView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        mPaint = new Paint();
        mPaint.setColor(Color.BLACK);       //设置画笔颜色
        mPaint.setStyle(Paint.Style.STROKE);  //设置画笔模式为填充
        mPaint.setStrokeWidth(2f);         //设置画笔宽度为10px

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.translate(getScreenW() / 2, getScreenH() / 2);  // 将坐标系原点移动到画布正中心
//        canvas.drawCircle(0, 0, 400, mPaint);          // 绘制两个圆形
//        canvas.drawCircle(0, 0, 380, mPaint);
//
//        for (int i = 0; i <= 360; i += 10) {               // 绘制圆形之间的连接线
//
//            canvas.drawLine(0, 380, 0, 400, mPaint);
//            canvas.rotate(10);
//
//        }
        Path path = new Path();                     // 创建Path

        path.lineTo(200, 200);                      // lineTo

//        path.moveTo(200, 100);                       // moveTo

        path.lineTo(200, 0);                         // lineTo

        path.close();

        canvas.drawPath(path, mPaint);              // 绘制Path


    }


    public int getScreenW() {
        return getContext().getResources().getDisplayMetrics().widthPixels;
    }

    public int getScreenH() {
        return getContext().getResources().getDisplayMetrics().heightPixels;
    }
}
