package com.example.okgo_http.adapter;

import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.base.R;
import com.example.okgo_http.bean.GangRightBean;

import java.util.List;

/**
 * Created by Administrator on 2018/3/20.
 */

public class GangRightAdapter extends BaseMultiItemQuickAdapter<GangRightBean, BaseViewHolder> {


    public GangRightAdapter(@Nullable List<GangRightBean> data) {
        super(data);
        addItemType(1, R.layout.gang_view_item_header);
        addItemType(2, R.layout.gang_view_item_content);
    }


    @Override
    protected void convert(BaseViewHolder helper, GangRightBean item) {
        switch (helper.getItemViewType()) {
            case 1:
                helper.setText(R.id.header_view, item.getName());
                break;
            case 2:
                helper.setText(R.id.textName, item.getName());
                break;
        }
    }

}
