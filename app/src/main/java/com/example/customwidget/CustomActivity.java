package com.example.customwidget;

import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.base.BaseActivity;
import com.example.base.R;
import com.example.okgo_http.db.UserInfoDao;
import com.example.okgo_http.db.entity.UserInfo;
import com.example.okgo_http.db.utils.DBUtils;

import java.util.List;

import butterknife.BindView;

public class CustomActivity extends BaseActivity {



    @Override
    protected int getContentViewId() {
        return R.layout.activity_custom_;
    }


    @Override
    protected void initView() {

    }


    public void onClick(View view) {
        toast("sssss");
    }
}
