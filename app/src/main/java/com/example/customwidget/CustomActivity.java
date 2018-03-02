package com.example.customwidget;

import com.example.base.BaseActivity;
import com.example.base.R;
import com.example.customwidget.widget.ClockView;

import butterknife.BindView;

public class CustomActivity extends BaseActivity {


    @BindView(R.id.clockView)
    ClockView clockView;

    @Override
    protected int getContentViewId() {
        return R.layout.activity_custom_;
    }

    @Override
    protected void initView() {


    }

}
