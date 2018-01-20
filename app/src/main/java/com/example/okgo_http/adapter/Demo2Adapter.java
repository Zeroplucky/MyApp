package com.example.okgo_http.adapter;

import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.base.R;
import com.example.bean.ReportEventsBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/12/12.
 */

public class Demo2Adapter extends BaseQuickAdapter<ReportEventsBean.DetailBean.SuccessBean, Demo2Adapter.ViewHolder> {


    public Demo2Adapter(@Nullable List<ReportEventsBean.DetailBean.SuccessBean> data) {
        super(R.layout.demo2_adapter_list_item, data);
    }

    @Override
    protected void convert(ViewHolder helper, ReportEventsBean.DetailBean.SuccessBean item) {
        helper.textView.setText(item.getEnterprise_name());
    }

    class ViewHolder extends BaseViewHolder {

        @BindView(R.id.text1)
        TextView textView;

        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
