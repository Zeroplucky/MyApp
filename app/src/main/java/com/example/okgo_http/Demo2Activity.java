package com.example.okgo_http;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.base.BaseApp;
import com.example.base.R;
import com.example.base.mvpframe.factory.CreatePresenter;
import com.example.base.mvpframe.view.BaseMvpAppCompatActivity;
import com.example.bean.ReportEventsBean;
import com.example.okgo_http.adapter.Demo2Adapter;
import com.example.okgo_http.mvp.Demo2Presenter;
import com.example.okgo_http.mvp.IDemo2View;
import com.example.widget.LoadingView;
import com.example.widget.refreshview.CustomRefreshView;
import com.squareup.leakcanary.RefWatcher;

import java.util.List;

import butterknife.BindView;

@CreatePresenter(Demo2Presenter.class)
public class Demo2Activity extends BaseMvpAppCompatActivity<IDemo2View, Demo2Presenter> implements IDemo2View, BaseQuickAdapter.RequestLoadMoreListener, SwipeRefreshLayout.OnRefreshListener {


    @BindView(R.id.recyclerView)
    CustomRefreshView recyclerView;
    private Demo2Adapter adapter;
    private boolean isRefresh = true;
    private int page = 1;

    @Override
    protected int getContentViewId() {
        return R.layout.activity_demo2;
    }

    @Override
    protected void initView() {
        recyclerView.getRecyclerView().setItemAnimator(new DefaultItemAnimator());
        recyclerView.getRecyclerView().setLayoutManager(new LinearLayoutManager(mContext));
        adapter = new Demo2Adapter(null);
        recyclerView.setAdapter(adapter);
        recyclerView.getSwipeRefreshLayout().setOnRefreshListener(this);
        recyclerView.setLoadMoreEnable(false);
        recyclerView.setRefreshing(true);
        adapter.setOnLoadMoreListener(this, recyclerView.getRecyclerView());
    }

    @Override
    protected void onResume() {
        super.onResume();
        getMvpPresenter().request(1);
        //观察ACIVITY是否内存泄露
//        RefWatcher refWatcher = BaseApp.getRefWatcher(this);
//        refWatcher.watch(this);
    }


    @Override
    public void onFinish() {
        recyclerView.complete();
    }

    @Override
    public void success(ReportEventsBean reportEventsBean) {
        if (reportEventsBean.getType() == 1) {
            List<ReportEventsBean.DetailBean.SuccessBean> success = reportEventsBean.getDetail().getSuccess();
            if (success.size() == 0) {
                adapter.loadMoreEnd();
                return;
            }
            if (isRefresh) {
                isRefresh = false;
                adapter.setNewData(success);
            } else {
                adapter.addData(success);
                adapter.loadMoreComplete();
            }
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onstart() {
        if (isRefresh) {
            adapter.setEmptyView(new LoadingView(mContext));
        }
    }

    @Override
    public void onLoadMoreRequested() {
        page++;
        getMvpPresenter().request(page);
    }

    @Override
    public void onRefresh() {
        isRefresh = true;
        page = 1;
        getMvpPresenter().request(1);
    }
}
