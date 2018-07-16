package com.example.customwidget.widget.custom1;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;

/**
 * Created by Administrator on 2018/2/7.
 */

public class MyButton extends android.support.v7.widget.AppCompatButton {

    public MyButton(Context context) {
        super(context);
    }

    public MyButton(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        boolean f = super.dispatchTouchEvent(event);   //super方法会调用自己类中的onTouchEvent,
//        boolean f = false;
        Log.e("xxx", "dispatchTouchEvent:  MyButton  " + f);
        return f; //若不调用super,则该方法会调用父类的onTouchEvent方法
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) { //此方法会调用自己类中的dispatchTouchEvent,且onTouchEvent的返回值=dispatchTouchEvent的返回值
        boolean f = super.onTouchEvent(event);//默认为false
//        boolean f = true;
        Log.e("xxx", "dispatchTouchEvent: MyButton   " + f);
        return f;
    }
}
