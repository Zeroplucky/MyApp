package com.example.customwidget.widget.custom1;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.animation.LinearInterpolator;

import static android.animation.ValueAnimator.INFINITE;

/**
 * Created by Administrator on 2019/10/25.
 * 博客：https://blog.csdn.net/itermeng/article/details/80291361
 */
public class PathView1 extends View {

    private Paint paint;
    private Path path;
    private int widthPixels;
    private int heightPixels;
    private PathMeasure pathMeasure;
    private ValueAnimator mAnimator;
    private float mCurrentValue;

    public PathView1(Context context) {
        this(context, null);
    }

    public PathView1(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PathView1(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        widthPixels = displayMetrics.widthPixels;
        heightPixels = displayMetrics.heightPixels;

        paint = new Paint();

        paint.setStrokeWidth(2);
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(Color.RED);

        path = new Path();
        path.addCircle(widthPixels / 2, heightPixels / 2, 100, Path.Direction.CW);

        pathMeasure = new PathMeasure();

        mAnimator = ValueAnimator.ofFloat(0, 1);
        mAnimator.setDuration(3000);
        mAnimator.setInterpolator(new LinearInterpolator());
        mAnimator.setRepeatCount(INFINITE);
        mAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                mCurrentValue = (float) valueAnimator.getAnimatedValue();
                invalidate();
            }
        });

        mAnimator.start();
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //mCurrentValue = 0.f;
        canvas.drawPath(path, paint);

        pathMeasure.setPath(path, false);
        Path dst = new Path();
        pathMeasure.getSegment(0, pathMeasure.getLength() / 2, dst, true);

        paint.setColor(Color.BLACK);
        canvas.drawPath(dst, paint);

        float[] pos = new float[2];
        float[] tan = new float[2];
        pathMeasure.getPosTan(pathMeasure.getLength() * mCurrentValue, pos, tan);
        double degree = Math.atan2(tan[1], tan[0]) * 180.0 / Math.PI;
        canvas.drawCircle(pos[0], pos[1], 10, paint);

        canvas.save();
        canvas.translate(widthPixels / 2, heightPixels / 2);
        canvas.rotate((float) degree - 90);
        canvas.drawLine(100, 0, 100, -200, paint);

        canvas.restore();

        //Log.e(this.getClass().getSimpleName(), "onDraw: " + degree);

    }
}
