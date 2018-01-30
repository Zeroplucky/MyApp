package com.example.coordinators.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.coordinators.adpter.ZhihuAdapter;
import com.example.coordinators.presenter.IZhuhuPresenter;
import com.example.coordinators.presenter.impl.ZhihuPresenter;
import com.example.coordinators.view.IZhihuView;
import com.example.base.BaseFragment;
import com.example.base.R;
import com.example.bean.ZhihuDailyItemBean;
import com.example.widget.refreshview.CustomRefreshView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;


public class ZhihuFragment extends BaseFragment implements IZhihuView, CustomRefreshView.OnLoadListener {

    @BindView(R.id.rv_zhihu)
    CustomRefreshView refreshView;
    private ZhihuAdapter mZhihuAdapter;
    private List<ZhihuDailyItemBean> list;
    private IZhuhuPresenter presenter;

    public static ZhihuFragment newInstance() {
        ZhihuFragment fragment = new ZhihuFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    protected int getContentViewId() {
        return R.layout.fragment_zhihu_;
    }

    @Override
    public int getLoadFrameId() {
        return R.id.content;
    }

    @Override
    protected void initView(View view) {
        presenter = new ZhihuPresenter(this, getContext());
        list = new ArrayList<>();
        mZhihuAdapter = new ZhihuAdapter(list);
        refreshView.setAdapter(mZhihuAdapter);
        refreshView.getRecyclerView().setLayoutManager(new LinearLayoutManager(getContext()));
        refreshView.setOnLoadListener(this);
        //点击事件
        mZhihuAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                presenter.onItemClick(position, (ZhihuDailyItemBean) adapter.getItem(position));
            }
        });

    }

    @Override
    protected void onLazyLoad() {
        super.onLazyLoad();
        presenter.getLastDailyList();
        updateLoadStatus(LOADING);
    }


    @Override
    public void start() {

    }

    @Override
    public void updateContentList(List<ZhihuDailyItemBean> lists) {
        updateLoadStatus(LOAD_SUCCESS);
        list.clear();
        list.addAll(lists);
        mZhihuAdapter.notifyDataSetChanged();
    }

    @Override
    public void loadMoreList(List<ZhihuDailyItemBean> lists) {
        updateLoadStatus(LOAD_SUCCESS);
        list.addAll(lists);
        mZhihuAdapter.notifyDataSetChanged();
    }

    @Override
    public void onFinish() {
        refreshView.complete();
//        mZhihuAdapter.loadMoreComplete();
    }

    @Override
    public void onRefresh() {
        presenter.getLastDailyList();
    }

    @Override
    public void onLoadMore() {
        presenter.loadMoreList();
    }
}
