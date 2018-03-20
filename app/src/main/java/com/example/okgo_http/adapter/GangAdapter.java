package com.example.okgo_http.adapter;

import android.graphics.Color;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.base.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2018/3/20.
 */

public class GangAdapter extends BaseQuickAdapter<String, GangAdapter.ViewHolder> {

    private int checkedPosition;

    public GangAdapter(@Nullable List<String> data) {
        super(R.layout.gang_recyclerview_item);
    }

    public void setCheckedPosition(int checkedPosition) {
        this.checkedPosition = checkedPosition;
        notifyDataSetChanged();
    }

    @Override
    protected void convert(ViewHolder helper, String item) {
        helper.tvName.setText(item);
//        Log.e("GangAdapter xxx", "convert: " + helper.getLayoutPosition());
        int position = helper.getLayoutPosition();
        if (position == checkedPosition) {
            helper.itemView.setBackgroundColor(Color.parseColor("#f3f3f3"));
            helper.tvName.setTextColor(Color.parseColor("#0068cf"));
        } else {
            helper.itemView.setBackgroundColor(Color.parseColor("#FFFFFF"));
            helper.tvName.setTextColor(Color.parseColor("#1e1d1d"));
        }
    }

    class ViewHolder extends BaseViewHolder {

        @BindView(R.id.textName)
        TextView tvName;

        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
