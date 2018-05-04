package com.example.customwidget;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.base.BaseActivity;
import com.example.base.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CustomActivity extends BaseActivity {


    @BindView(R.id.textview)
    TextView textview;

    @Override
    protected int getContentViewId() {
        return R.layout.activity_custom_;
    }


    @Override
    protected void initView() {

        textview.setOnClickListener((v) -> {
                    Toast.makeText(mContext, "......", Toast.LENGTH_SHORT).show();

                }
        );
    }


}
