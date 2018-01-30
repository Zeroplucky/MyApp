package com.example.coordinator.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.coordinator.adpter.CustomAdapter;
import com.example.coordinator.presenter.ICustomPresenter;
import com.example.coordinator.presenter.impl.CustomPresenter;
import com.example.coordinator.view.ICustomView;
import com.example.base.BaseFragment;
import com.example.base.R;
import com.example.bean.CustomItemBean;
import com.scwang.smartrefresh.header.PhoenixHeader;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;


public class CustomFragment extends BaseFragment implements ICustomView, BaseQuickAdapter.RequestLoadMoreListener, OnRefreshListener {


    @BindView(R.id.rv_gankio_custom)
    RecyclerView refreshView;
    @BindView(R.id.SwipeRefreshLayout)
    SmartRefreshLayout refreshLayout;
    private CustomAdapter mCustomAdapter;
    private String mCustomType = "all";
    private ICustomPresenter presenter;
    private static int page = 1;
    private List<CustomItemBean> lists;

    public static CustomFragment newInstance() {
        CustomFragment fragment = new CustomFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    protected int getContentViewId() {
        return R.layout.fragment_custom_;
    }

    @Override
    public int getLoadFrameId() {
        return R.id.content;
    }

    @Override
    protected void initView(View view) {
        lists = new ArrayList<>();
        presenter = new CustomPresenter(this);
        mCustomAdapter = new CustomAdapter(lists, getContext());
        refreshView.setAdapter(mCustomAdapter);
        refreshView.setLayoutManager(new LinearLayoutManager(getContext()));
        mCustomAdapter.setOnLoadMoreListener(this, refreshView);
        mCustomAdapter.openLoadAnimation(BaseQuickAdapter.ALPHAIN);
        //下拉刷新的头部
        refreshLayout.setRefreshHeader(new PhoenixHeader(getContext()));
        refreshLayout.setOnRefreshListener(this);
    }

    @Override
    protected void onLazyLoad() {
        super.onLazyLoad();
        presenter.getList(mCustomType, 10, page);
        updateLoadStatus(LOADING);
    }

    @Override
    public void start() {

    }

    @Override
    public void updateContentList(List<CustomItemBean> list) {
        updateLoadStatus(LOAD_SUCCESS);
        lists.clear();
        lists.addAll(list);
        mCustomAdapter.notifyDataSetChanged();
    }

    @Override
    public void loadMoreList(List<CustomItemBean> list) {
        mCustomAdapter.addData(list);
    }

    @Override
    public void onFinish() {
        refreshLayout.finishRefresh();
        mCustomAdapter.loadMoreComplete();
    }


    @Override
    public void onLoadMoreRequested() {
        page++;
        presenter.loadMoreList(page);
    }


    @Override
    public void onRefresh(RefreshLayout refreshlayout) {
        presenter.getList(mCustomType, 10, page);
    }
}
