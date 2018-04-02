package com.example.utils;

import android.animation.ValueAnimator;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Administrator on 2018/4/2.
 */

public class AnimationUtils {

    public static int originHeight;

    public static void switcher(final View view) {
        final int height = view.getHeight();
        if (height == 0) {
            originHeight = height;
        }
        ValueAnimator valueAnimator = ValueAnimator.ofFloat(originHeight, 0f);
        valueAnimator.setDuration(100);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float fraction = animation.getAnimatedFraction();
                float v = height == 0 ? fraction * originHeight : (1 - fraction) * originHeight;
                Log.e("xxx", "onAnimationUpdate: ====" + v);
                ViewGroup.LayoutParams params = view.getLayoutParams();
                params.height = (int) v;
                view.setLayoutParams(params);
            }
        });

        valueAnimator.start();

    }


}
