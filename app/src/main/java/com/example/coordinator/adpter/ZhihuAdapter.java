package com.example.coordinator.adpter;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.base.R;
import com.example.bean.ZhihuDailyItemBean;

import java.util.List;

/**
 * Created by ZhangPeng on 2017/12/26.
 */

public class ZhihuAdapter extends BaseQuickAdapter<ZhihuDailyItemBean, BaseViewHolder> {
    public ZhihuAdapter(@LayoutRes int layoutResId, @Nullable List<ZhihuDailyItemBean> data) {
        super(R.layout.item_recycle_home, data);
    }

    public ZhihuAdapter(@Nullable List<ZhihuDailyItemBean> data) {
        this(0, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, ZhihuDailyItemBean item) {
        helper.setText(R.id.tv_item_title, item.getTitle());
        Glide.with(mContext)
                .load(item.getImages()[0])
                .crossFade()
                .into((ImageView) helper.getView(R.id.iv_item_image));
    }
}
