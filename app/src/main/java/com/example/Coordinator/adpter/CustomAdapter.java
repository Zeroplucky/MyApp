package com.example.Coordinator.adpter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.Coordinator.view.CustomWebActivity;
import com.example.Coordinator.constant.Constants;
import com.example.base.R;
import com.example.bean.CustomItemBean;
import com.example.utils.StringUtils;

import java.util.List;

/**
 * Created by Administrator on 2017/12/26.
 */

public class CustomAdapter extends BaseMultiItemQuickAdapter<CustomItemBean, BaseViewHolder> {
    private String mImageSize = "?imageView2/0/w/200";
    private Context mConext;

    public CustomAdapter(List<CustomItemBean> data, Context context) {
        super(data);
        mConext = context;
        addItemType(CustomItemBean.GANK_IO_DAY_ITEM_CUSTOM_NORMAL, R.layout.item__custom_normal);
        addItemType(CustomItemBean.GANK_IO_DAY_ITEM_CUSTOM_IMAGE, R.layout.item__custom_image);
        addItemType(CustomItemBean.GANK_IO_DAY_ITEM_CUSTOM_NO_IMAGE, R.layout.item__custom_no_image);
    }

    @Override
    protected void convert(BaseViewHolder helper, final CustomItemBean item) {

        helper.setText(R.id.tv_item_who, StringUtils.isEmpty(item.getWho()) ? "佚名" : item.getWho());
        helper.setText(R.id.tv_item_type, item.getType());
        helper.setText(R.id.tv_item_time, item.getCreatedAt().substring(0, 10));

        switch (helper.getItemViewType()) {
            case CustomItemBean.GANK_IO_DAY_ITEM_CUSTOM_NORMAL:
                helper.setText(R.id.tv_item_title, item.getDesc());
                if (item.getImages() != null) {
                    if (item.getImages().size() > 0 && !TextUtils.isEmpty(item.getImages().get(0)))
                        Glide.with(mContext)
                                .load(item.getImages().get(0) + mImageSize)
                                .asBitmap()
                                .into((ImageView) helper.getView(R.id.iv_item_image));
                }
                break;
            case CustomItemBean.GANK_IO_DAY_ITEM_CUSTOM_IMAGE:
                Glide.with(mContext)
                        .load(item.getUrl())
                        .asBitmap()
                        .centerCrop()
                        .placeholder(R.drawable.ic_vector_image_load_before)
                        .into((ImageView) helper.getView(R.id.iv_item_image));
                break;
            case CustomItemBean.GANK_IO_DAY_ITEM_CUSTOM_NO_IMAGE:
                helper.setText(R.id.tv_item_title, item.getDesc());
                break;
        }

        if (helper.getItemViewType() != CustomItemBean.GANK_IO_DAY_ITEM_CUSTOM_IMAGE) {
            helper.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(mContext, CustomWebActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putString(Constants.ARG_KEY_GANKIO_DETAIL_URL, item.getUrl());
                    bundle.putString(Constants.ARG_KEY_GANKIO_DETAIL_TITLE, item.getDesc());
                    intent.putExtras(bundle);
                    mConext.startActivity(intent);
                }
            });
        }
    }
}
