package com.example.customwidget;

import android.graphics.Color;
import android.widget.TextView;

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

    @Override
    protected void initView() {

        xxx.setClick(new CalendarView.OnClick() {
                         @Override
                         public void click(TextView view) {
                             view.setBackgroundColor(Color.RED);
                             toast(view.getText().toString());
                         }
                     }
        );

    }


}
