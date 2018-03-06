package com.example.customwidget;

import android.support.annotation.IntRange;
import android.util.Log;

import com.example.base.BaseActivity;
import com.example.base.R;
import com.example.customwidget.widget.ClockView;

import butterknife.BindView;
import butterknife.OnClick;

public class CustomActivity extends BaseActivity {


    @BindView(R.id.clockView)
    ClockView clockView;

    @Override
    protected int getContentViewId() {
        return R.layout.activity_custom_;
    }

    @Override
    protected void initView() {

        setString(0);

    }


    private void setString(@IntRange(from = 0, to = 255) int s) {

        Log.e(TAG, "setString: ---" + s);
    }

    @OnClick(R.id.clockView)
    public void onViewClicked() {

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();

    }
}
