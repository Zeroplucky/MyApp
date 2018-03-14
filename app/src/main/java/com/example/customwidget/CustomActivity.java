package com.example.customwidget;

import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.base.BaseActivity;
import com.example.base.R;
import com.example.customwidget.widget.MyDragLayout;
import com.example.customwidget.widget.MyDragLinearLayout;
import com.example.tencentqq.domain.Cheeses;

import java.util.Arrays;
import java.util.List;

import butterknife.BindView;

public class CustomActivity extends BaseActivity {


    public static final String TAG = "CustomActivity";
    @BindView(R.id.listView)
    RecyclerView listView;
    @BindView(R.id.myDragLinearLayout)
    MyDragLinearLayout myDragLinearLayout;
    @BindView(R.id.myDragLayout)
    MyDragLayout myDragLayout;


    @Override
    protected int getContentViewId() {
        return R.layout.activity_custom_;
    }


    @Override
    protected void initView() {
        myDragLinearLayout.setDragLayout(myDragLayout);
        listView.setLayoutManager(new LinearLayoutManager(this));

        listView.setAdapter(new MyAdapter(null));

    }


    class MyAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

        public MyAdapter(@Nullable List data) {
            super(R.layout.activity_custom_item, Arrays.asList(Cheeses.NAMES));
        }

        @Override
        protected void convert(BaseViewHolder helper, String item) {
            helper.setText(R.id.item_tv, item + "");
        }

    }


}
