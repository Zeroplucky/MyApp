package com.example.customwidget;

import android.graphics.Color;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.example.base.BaseActivity;
import com.example.base.R;
import com.example.customwidget.adapter.CountByAdcodeAdapter;
import com.example.customwidget.bean.CountByAdcodeBean;
import com.example.customwidget.presenter.CustomPresenter;
import com.example.customwidget.presenter.ICustomPresenter;
import com.example.customwidget.view.ICustomView;
import com.example.customwidget.widget.loadview.AVLoadingIndicatorView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;


public class CustomWidgetActivity extends BaseActivity implements ICustomView {

    @BindView(R.id.background_img)
    ImageView backgroundImg;
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.frame_layout2)
    FrameLayout frameLayout2;
    private ICustomPresenter presenter;
    private List<MultiItemEntity> data = new ArrayList<>();
    private CountByAdcodeAdapter adapter;
    private AVLoadingIndicatorView loadView;

    @Override
    protected int getContentViewId() {
        return R.layout.activity_custom_widget_;
    }


    @Override
    protected void initView() {
        loadView = new AVLoadingIndicatorView(mContext);
        loadView.setIndicatorView();
        loadView.setIndicatorColor(Color.RED);
        frameLayout2.addView(loadView);
        adapter = new CountByAdcodeAdapter(data);
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        recyclerView.setAdapter(adapter);
        presenter = new CustomPresenter(mContext);
        presenter.getCountByAdcode(this);

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
    }


    @Override
    public void onBegin() {
//        loadView.show();
    }

    @Override
    public void getDetailBean(CountByAdcodeBean.DetailBean bean) {
        data.clear();
        List<CountByAdcodeBean.DetailBean.RvEvCountListBean> rvEvCountList = bean.getRvEvCountList();
        for (int i = 0; i < rvEvCountList.size(); i++) {
            if (rvEvCountList.get(i).hasChild()) {
                for (int j = 0; j < rvEvCountList.get(i).getChild().size(); j++) {
                    rvEvCountList.get(i).addSubItem(rvEvCountList.get(i).getChild().get(j));
                }
            }
            data.add(rvEvCountList.get(i));
        }
        adapter.setNewData(data);
    }

    @Override
    public void onEnd() {
        loadView.hide();
    }

}
