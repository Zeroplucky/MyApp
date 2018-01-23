package com.example.customwidget;

import com.example.base.BaseActivity;
import com.example.base.R;
import com.example.customwidget.widget.VerticalTextview;

import butterknife.BindView;


public class CustomWidgetActivity extends BaseActivity  {

    @BindView(R.id.mTextView)
    VerticalTextview mTextView;

    @Override
    protected int getContentViewId() {
        return R.layout.activity_custom_widget_;
    }

    @Override
    protected void initView() {

    }


    @Override
    protected void onResume() {
        super.onResume();

    }

    @Override
    protected void onPause() {
        super.onPause();

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

}
