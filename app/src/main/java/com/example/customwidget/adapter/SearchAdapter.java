package com.example.customwidget.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.base.R;
import com.example.customwidget.bean.RiverList;

import java.util.List;

/**
 * Created by Administrator on 2018/1/25.
 */

public class SearchAdapter extends BaseQuickAdapter<RiverList.DetailBean.RiverListBean, BaseViewHolder> {


    public SearchAdapter(@Nullable List<RiverList.DetailBean.RiverListBean> data) {
        super(R.layout.item_river_segment_child, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, RiverList.DetailBean.RiverListBean item) {
        helper.setText(R.id.tv_name, item.getRv_name());
    }
}
