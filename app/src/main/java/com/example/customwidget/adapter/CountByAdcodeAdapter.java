package com.example.customwidget.adapter;

import android.view.View;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.example.base.R;
import com.example.customwidget.bean.CountByAdcodeBean;

import java.util.List;

/**
 * Created by Administrator on 2018/1/23.
 */

public class CountByAdcodeAdapter extends BaseMultiItemQuickAdapter<MultiItemEntity, BaseViewHolder> {

    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     *
     * @param data A new list is created out of this one to avoid mutable list
     */
    public CountByAdcodeAdapter(List<MultiItemEntity> data) {
        super(data);
        addItemType(0, R.layout.item_river_segment_p);
        addItemType(1, R.layout.item_river_segment_child);
    }

    @Override
    protected void convert(final BaseViewHolder holder, MultiItemEntity item) {
        switch (holder.getItemViewType()) {
            case 0:
                final CountByAdcodeBean.DetailBean.RvEvCountListBean bean = (CountByAdcodeBean.DetailBean.RvEvCountListBean) item;
                holder.setText(R.id.tv_name, bean.getName());
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        int pos = holder.getAdapterPosition();
                        if (bean.isExpanded()) {
                            collapse(pos, false);
                        } else {
                            expand(pos, false);
                        }
                        holder.getView(R.id.tv_expand).setSelected(bean.isExpanded());
                    }
                });
                break;
            case 1:
                CountByAdcodeBean.DetailBean.RvEvCountListBean.ChildBean childBean = (CountByAdcodeBean.DetailBean.RvEvCountListBean.ChildBean) item;
                holder.setText(R.id.tv_name, childBean.getName());
                break;
        }
    }
}
