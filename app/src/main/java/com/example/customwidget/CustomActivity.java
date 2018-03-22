package com.example.customwidget;

import com.example.base.BaseActivity;
import com.example.base.R;
import com.example.customwidget.widget.Custom01View;

import butterknife.BindView;

public class CustomActivity extends BaseActivity {


    public static final String TAG = "CustomActivity";
    @BindView(R.id.myDragLayout)
    Custom01View myDragLayout;


    @Override
    protected int getContentViewId() {
        return R.layout.activity_custom_;
    }


    @Override
    protected void initView() {

    }


}
