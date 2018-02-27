package com.example.customwidget;

import android.os.Handler;
import android.os.Message;
import android.view.View;

import com.example.base.BaseActivity;
import com.example.base.R;

import java.lang.ref.WeakReference;

public class CustomActivity extends BaseActivity {
    private MyHandle myHandle;

//    @BindView(R.id.custom01view)
//    Custom01View custom01view;
//    @BindView(R.id.anim_view)
//    MyAnimView animView;


    @Override
    protected int getContentViewId() {
        return R.layout.activity_custom_;
    }

    @Override
    protected void initView() {
        myHandle = new MyHandle();

        //

    }


    public void XXXX(View view) {
        view.setScrollX(20);
        view.setTranslationY(100);
    }


    private class MyHandle extends Handler {

        private WeakReference<CustomActivity> reference;

        public MyHandle() {
            reference = new WeakReference<CustomActivity>(CustomActivity.this);
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (reference.get() != null) {

            }
        }
    }

}
