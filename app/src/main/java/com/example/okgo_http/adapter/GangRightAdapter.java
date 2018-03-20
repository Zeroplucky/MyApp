package com.example.okgo_http.adapter;

import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.okgo_http.bean.GangRightBean;

import java.util.List;

/**
 * Created by Administrator on 2018/3/20.
 */

public class GangRightAdapter extends BaseQuickAdapter<GangRightBean, GangRightAdapter.ViewHolder> {


    public GangRightAdapter(@Nullable List<GangRightBean> data) {
        super(data);
    }


    @Override
    protected ViewHolder onCreateDefViewHolder(ViewGroup parent, int viewType) {
        return super.onCreateDefViewHolder(parent, viewType);
    }

    @Override
    protected void convert(ViewHolder helper, GangRightBean item) {

    }

    class ViewHolder extends BaseViewHolder {

        public ViewHolder(View view) {
            super(view);
        }
    }
}
