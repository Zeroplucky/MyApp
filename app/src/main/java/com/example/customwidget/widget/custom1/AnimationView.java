package com.example.customwidget.widget.custom1;

import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.base.R;

/**
 * Created by Administrator on 2019/10/23.
 * 疑问：1.当imageView为可点击状态时，onTouchEvent 的 ACTION_DOWN 事件 返回false,可移动，当不可点击时，返回false,却不可移动，为什么？
 * <p>
 * 某个View一旦开始处理事件，如果它不消耗ACTION_DOWN事件（onTouchEvent返回了false）
 * 那么同一事件序列中的其他事件都不会再交给它来处理，并且事件将重新交由它的父元素去处理，即父元素的onTouchEvent会被调用
 */
public class AnimationView extends FrameLayout {


    private ImageView imageView;
    private ViewGroup.LayoutParams layoutParams;
    private float rawX;
    private float rawY;
    private int widthPixels;
    private int heightPixels;

    public AnimationView(@NonNull Context context) {
        this(context, null);
    }

    public AnimationView(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public AnimationView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        widthPixels = context.getResources().getDisplayMetrics().widthPixels;
        heightPixels = context.getResources().getDisplayMetrics().heightPixels;

        setBackgroundColor(Color.BLUE);
        imageView = new ImageView(context);
        imageView.setImageResource(R.drawable.head);
        this.layoutParams = getLayoutParams();
        if (this.layoutParams == null) {
            this.layoutParams = new FrameLayout.LayoutParams(-2, -2);
        }
        addView(imageView, this.layoutParams);

        imageView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                double random = Math.random();
                widthPixels = (int) (random * widthPixels);
                //heightPixels = (int) (random * heightPixels);
                createAnim(widthPixels, (int) (heightPixels * 0.5), imageView);
            }
        });
    }

    private void createAnim(int endX, int endY, View view) {
        int[] des = new int[2];
        view.getLocationInWindow(des);

        PropertyValuesHolder a1 = PropertyValuesHolder.ofFloat("translationX", des[0], endX);
        PropertyValuesHolder a2 = PropertyValuesHolder.ofFloat("translationY", des[1], endY);
        PropertyValuesHolder a3 = PropertyValuesHolder.ofFloat("alpha", 1f, 0.5f);
        ObjectAnimator.ofPropertyValuesHolder(view, a1, a2, a3).setDuration(500).start();

    }


    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        float lastY = 0;
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN: {
                lastY = ev.getRawY();
                return false;
            }
            case MotionEvent.ACTION_MOVE: {
                rawX = ev.getRawX();
                rawY = ev.getRawY();
                if (Math.abs(ev.getRawY() - lastY) >= 10) {
                    return true;
                }
            }
            default:
                return false;
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                rawX = event.getRawX();
                rawY = event.getRawY();
//                break;
                return true;
            case MotionEvent.ACTION_MOVE:
                float deY = event.getRawY() - rawY;
                float deX = event.getRawX() - rawX;
                //
                scrollBy(-(int) deX, -(int) deY);
                //
//                LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) getLayoutParams();
//                layoutParams.leftMargin = (int) deX + getLeft();
//                layoutParams.topMargin = (int) deY+ getTop();
//                setLayoutParams(layoutParams);
                //
                // scrollTo(-(int) deX + getScrollX(), -(int) deY + getScrollY());
                break;
            case MotionEvent.ACTION_UP:
                break;
        }
        rawX = event.getRawX();
        rawY = event.getRawY();
        return false;
    }


}
