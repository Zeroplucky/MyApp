package com.example.customwidget;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListView;

import com.example.base.BaseActivity;
import com.example.base.R;
import com.example.tencentqq.domain.Cheeses;

import butterknife.BindView;

public class CustomActivity extends BaseActivity {


    public static final String TAG = "CustomActivity";
    @BindView(R.id.listView)
    ListView listView;


    @Override
    protected int getContentViewId() {
        return R.layout.activity_custom_;
    }


    @Override
    protected void initView() {

        listView.setAdapter(new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, Cheeses.sCheeseStrings));


    }




}
