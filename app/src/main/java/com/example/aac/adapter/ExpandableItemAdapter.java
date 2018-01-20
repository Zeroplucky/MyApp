package com.example.aac.adapter;

import android.view.View;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.example.aac.bean.RiverSegment;
import com.example.base.R;

import java.util.List;

/**
 * Created by Administrator on 2018/1/18.
 */

public class ExpandableItemAdapter extends BaseMultiItemQuickAdapter<MultiItemEntity, BaseViewHolder> {


    public ExpandableItemAdapter(List<MultiItemEntity> data) {
        super(data);
        addItemType(0, R.layout.item_river_segment_p);
        addItemType(1, R.layout.item_river_segment_child);
    }


    @Override
    protected void convert(final BaseViewHolder holder, MultiItemEntity item) {

        switch (holder.getItemViewType()) {
            case 0:
                final RiverSegment.DetailBean.RiverSegmentsBean bean = (RiverSegment.DetailBean.RiverSegmentsBean) item;
                holder.setText(R.id.tv_name, bean.getRv_name());
//                expandAll();
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        int pos = holder.getAdapterPosition();
                        if (bean.isExpanded()) {
                            collapse(pos,false);
                        } else {
                            expand(pos,false);
                        }
                    }
                });
                break;
            case 1:
                RiverSegment.DetailBean.RiverSegmentsBean.ChildBean childBean = (RiverSegment.DetailBean.RiverSegmentsBean.ChildBean) item;
                holder.setText(R.id.tv_name, childBean.getRv_name());
                break;
        }

    }
}
