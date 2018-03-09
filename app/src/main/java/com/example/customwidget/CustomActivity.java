package com.example.customwidget;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.util.Log;
import android.view.View;

import com.example.base.BaseActivity;
import com.example.base.R;
import com.example.customwidget.widget.CalendarView;

import butterknife.BindView;

public class CustomActivity extends BaseActivity {


    @BindView(R.id.xxx)
    CalendarView xxx;

    @Override
    protected int getContentViewId() {
        return R.layout.activity_custom_;
    }


    public void clickToChang(View view) {
        Log.e(TAG, "clickToChang: " + xxx.getLeft() + "    " + xxx.getTranslationX() + " " + xxx.getScaleX());
        xxx.animate()
                .setDuration(1000)
                .xBy(50)
                .setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        super.onAnimationEnd(animation);
                        Log.e(TAG, "clickToChang: " + xxx.getLeft() + "    " + xxx.getTranslationX() + " " + xxx.getScaleX());
                    }
                }).start();
    }

    @Override
    protected void initView() {

    }


}
