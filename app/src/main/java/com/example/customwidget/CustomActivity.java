package com.example.customwidget;

import android.animation.ObjectAnimator;

import com.example.base.BaseActivity;
import com.example.base.R;
import com.example.customwidget.widget.Custom01View;
import com.example.customwidget.widget.obj_point.MyAnimView;

import butterknife.BindView;

public class CustomActivity extends BaseActivity {

    @BindView(R.id.custom01view)
    Custom01View custom01view;
    @BindView(R.id.anim_view)
    MyAnimView animView;


    @Override
    protected int getContentViewId() {
        return R.layout.activity_custom_;
    }

    @Override
    protected void initView() {
        ObjectAnimator animator1 = ObjectAnimator.ofFloat(custom01view, "Alpha", 1, 0, 1);
        animator1.setDuration(3000);
        animator1.start();
        ObjectAnimator animator2 = ObjectAnimator.ofFloat(custom01view, "TranslationX", 100, 0);
        animator2.setDuration(3000);
        animator2.start();
        //
    }

}
