package com.example.aac.adapter;

import android.view.View;

import com.chad.library.adapter.base.BaseSectionQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.aac.bean.AdInfo;

import java.util.List;

/**
 * Created by Administrator on 2018/1/18.
 */

public class TownCountryAdapter extends BaseSectionQuickAdapter<AdInfo, TownCountryAdapter.MyViewHolder> {


    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     *
     * @param layoutResId      The layout resource id of each item.
     * @param sectionHeadResId The section head layout id for each item
     * @param data             A new list is created out of this one to avoid mutable list
     */
    public TownCountryAdapter(int layoutResId, int sectionHeadResId, List<AdInfo> data) {
        super(layoutResId, sectionHeadResId, data);
    }

    @Override
    protected void convertHead(MyViewHolder helper, AdInfo item) {

    }

    @Override
    protected void convert(MyViewHolder helper, AdInfo item) {

    }

    class MyViewHolder extends BaseViewHolder {

        public MyViewHolder(View view) {
            super(view);
        }
    }
}
