package com.example.aac;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.example.aac.adapter.ExpandableItemAdapter;
import com.example.aac.bean.AdInfo;
import com.example.aac.bean.RiverSegment;
import com.example.aac.viewmodel.AdInfoViewModel;
import com.example.aac.viewmodel.RiverSegmentVM;
import com.example.base.BaseActivity;
import com.example.base.R;

import java.util.ArrayList;
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
    private RiverSegmentVM riverSegmentVM;
    private ExpandableItemAdapter adapter;
    private List<MultiItemEntity> data = new ArrayList<>();

    @Override
    protected int getContentViewId() {
        return R.layout.activity_aac_;
    }

    @Override
    protected void initView() {
        adInfoViewModel = ViewModelProviders.of(this).get(AdInfoViewModel.class);
        riverSegmentVM = ViewModelProviders.of(this).get(RiverSegmentVM.class);
    }

    @Override
    protected void initData() {
        super.initData();
        getRiverSegment("");
        adapter = new ExpandableItemAdapter(data);
        contentRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        contentRecyclerView.setAdapter(adapter);
    }


    private void getAdInfo() {
        adInfoViewModel.getAdInfo("").observe(this, new Observer<AdInfo>() {
            @Override
            public void onChanged(@Nullable AdInfo adInfo) {
                if (adInfo != null) {
                    List<AdInfo.DetailBean.TownBean> adList = adInfo.getDetail().getAdList();
                    if (adList != null) {
                        adList.add(0, new AdInfo.DetailBean.TownBean("区级", true));

                        if (adList.size() >= 15) {
                            List<AdInfo.DetailBean.TownBean> townBeans = adList.subList(0, 15);
                            List<Object> multiTypeData = new ArrayList<>();
                            multiTypeData.addAll(townBeans);


                        }
                    }
                }
            }
        });
    }

    private void getRiverSegment(String code) {
        riverSegmentVM.getRiverSegment(1, code).observe(this, new Observer<RiverSegment>() {
            @Override
            public void onChanged(@Nullable RiverSegment riverSegment) {
                if (riverSegment != null) {
                    List<RiverSegment.DetailBean.RiverSegmentsBean> riverSegments = riverSegment.getDetail().getRiverSegments();
                    if (riverSegments != null) {
                        data.clear();
                        for (int i = 0; i < riverSegments.size(); i++) {
                            RiverSegment.DetailBean.RiverSegmentsBean riverSegmentsBean = riverSegments.get(i);
                            for (int j = 0; j < riverSegmentsBean.getChild().size(); j++) {
                                RiverSegment.DetailBean.RiverSegmentsBean.ChildBean childBean = riverSegmentsBean.getChild().get(j);
                                riverSegmentsBean.addSubItem(childBean);
                            }
                            data.add(riverSegmentsBean);
                        }
                        adapter.notifyDataSetChanged();
                    }
                }
            }
        });
    }
}
