package com.example.aac.adapter;

import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.aac.bean.AdInfo;
import com.example.base.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2018/1/25.
 */

public class ContentAdapter extends BaseQuickAdapter<AdInfo.DetailBean.TownBean, ContentAdapter.MyViewHolder> {


    public ContentAdapter(@Nullable List<AdInfo.DetailBean.TownBean> data) {
        super(R.layout.item_header_country, data);
    }

    @Override
    protected void convert(ContentAdapter.MyViewHolder helper, AdInfo.DetailBean.TownBean item) {
        helper.tvName.setText(item.getName());
    }

    class MyViewHolder extends BaseViewHolder {
        @BindView(R.id.tv_name)
        TextView tvName;

        public MyViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
