package com.example.okgo_http;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.base.R;
import com.example.base2.BaseActivity;
import com.example.okgo_http.adapter.GangAdapter;
import com.example.okgo_http.bean.GangRightBean;
import com.example.okgo_http.mvp.GangPresenter;
import com.example.okgo_http.mvp.GangView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class GangedActivity extends BaseActivity<GangView, GangPresenter> implements GangView, BaseQuickAdapter.RequestLoadMoreListener {


    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.content_layout)
    FrameLayout contentLayout;
    private GangAdapter adapter;
    private LinearLayoutManager layoutManager;

    @Override
    protected int setContentViewId() {
        return R.layout.activity_ganged;
    }

    @Override
    public GangPresenter creatPresenter() {
        return new GangPresenter(this);
    }

    @Override
    public void initView() {
        super.initView();

    }

    @Override
    public void initData() {
        super.initData();
        adapter = new GangAdapter(null);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        adapter.setOnLoadMoreListener(this, recyclerView);
        getPresenter().getData("sort.json");
        setListener();
    }

    private void setListener() {
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapterBase, View view, int position) {
                adapter.setCheckedPosition(position);
                moveToCenter(position);
            }
        });
    }

    private void moveToCenter(int position) {
        View viewByPosition = layoutManager.findViewByPosition(position);
//        Log.e("xxx", "moveToCenter: " + position + "  ");
        if (viewByPosition != null) {
            int top = viewByPosition.getTop();
            int height = recyclerView.getHeight();
            recyclerView.smoothScrollBy(0, top - height / 2);
//            Log.e("xxx", "moveToCenter: " + top + "  " + height / 2);
        }
    }

    @Override
    public void getData(List<String> listName) {
        adapter.setNewData(listName);
        Log.e("xxx", "getData: " + adapter.getItemCount());
    }

    @Override
    public void getRight(ArrayList<GangRightBean> rightBeanList) {
        GangFragment gangFragment = findFragment(GangFragment.class);
        if (gangFragment == null) {
            loadRootFragment(R.id.content_layout, GangFragment.newInstance(rightBeanList));
        }
    }


    @Override
    public void getEmpty() {

    }

    @Override
    public void onError(IOException e) {

    }

    @Override
    public void onLoadMoreRequested() {
        Log.e("xxx", "getData: " + adapter.getItemCount());
    }
}
