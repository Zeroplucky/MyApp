package com.example.aac;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.example.aac.adapter.ContentAdapter;
import com.example.aac.adapter.HeaderAdapter;
import com.example.aac.bean.AdInfo;
import com.example.aac.viewmodel.AdInfoViewModel;
import com.example.base.BaseActivity;
import com.example.base.R;

import java.util.List;

import butterknife.BindView;

public class AACActivity extends BaseActivity {

    @BindView(R.id.header_recycler_view)
    RecyclerView headerRecyclerView;
    @BindView(R.id.tv_select_river)
    TextView tvSelectRiver;
    @BindView(R.id.tv_look_detail)
    TextView tvLookDetail;
    @BindView(R.id.content_recycler_view)
    RecyclerView contentRecyclerView;
    private AdInfoViewModel adInfoViewModel;
    private HeaderAdapter headerAdapter;
    private ContentAdapter contentAdapter;


    @Override
    protected int getContentViewId() {
        return R.layout.activity_aac_;
    }

    @Override
    protected void initView() {
        adInfoViewModel = ViewModelProviders.of(this).get(AdInfoViewModel.class);
        initAdapter();
    }

    private void initAdapter() {
        headerAdapter = new HeaderAdapter(null);
        headerRecyclerView.setLayoutManager(new GridLayoutManager(mContext, 4));
        headerRecyclerView.setAdapter(headerAdapter);

        contentAdapter = new ContentAdapter(null);
        contentRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        contentRecyclerView.setAdapter(contentAdapter);
    }

    @Override
    protected void initData() {
        super.initData();
        getAdInfo();

    }


    private void getAdInfo() {
        adInfoViewModel.getAdInfo("").observe(this, new Observer<AdInfo>() {
            @Override
            public void onChanged(@Nullable AdInfo adInfo) {
                if (adInfo != null) {
                    List<AdInfo.DetailBean.TownBean> adList = adInfo.getDetail().getAdList();
                    if (adList != null) {
                        headerAdapter.setNewData(adList);
                        contentAdapter.setNewData(adList);
                    }
                }
            }
        });
    }


}
