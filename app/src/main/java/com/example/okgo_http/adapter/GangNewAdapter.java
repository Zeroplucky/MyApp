package com.example.okgo_http.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.base.R;
import com.example.okgo_http.bean.GangRightBean;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2018/3/21.
 */

public class GangNewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private ArrayList<GangRightBean> rightLists;
    private Context mContext;

    public GangNewAdapter(ArrayList<GangRightBean> rightLists, Context mContext) {
        this.rightLists = rightLists;
        this.mContext = mContext;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == 1) {
            View view = LayoutInflater.from(mContext).inflate(R.layout.gang_view_item_header, parent, false);
            return new HeaderView(view);
        } else {
            View view = LayoutInflater.from(mContext).inflate(R.layout.gang_view_item_content, parent, false);
            return new ContentView(view);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        switch (getItemViewType(position)) {
            case 1:
                HeaderView headerView = (HeaderView) holder;
                headerView.headerView.setText(rightLists.get(position).getName());
                break;
            case 2:
                ContentView contentView = (ContentView) holder;
                contentView.textName.setText(rightLists.get(position).getName());
                break;
        }
    }

    @Override
    public int getItemCount() {
        return rightLists == null ? 0 : rightLists.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (rightLists == null) {
            return 0;
        } else if (rightLists.get(position).isTitle()) {
            return 1;
        } else {
            return 2;
        }
    }


    class HeaderView extends RecyclerView.ViewHolder {

        @BindView(R.id.header_view)
        TextView headerView;

        public HeaderView(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    class ContentView extends RecyclerView.ViewHolder {

        @BindView(R.id.textName)
        TextView textName;

        public ContentView(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
