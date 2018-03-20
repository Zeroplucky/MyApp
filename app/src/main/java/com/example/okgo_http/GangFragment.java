package com.example.okgo_http;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.base.R;
import com.example.base2.BaseFragment;
import com.example.base2.BasePresenter;
import com.example.okgo_http.adapter.GangRightAdapter;
import com.example.okgo_http.bean.GangRightBean;

import java.util.ArrayList;

import butterknife.BindView;


public class GangFragment extends BaseFragment {


    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    private ArrayList<GangRightBean> rightLists;
    private GangRightAdapter adapter;

    public static GangFragment newInstance(ArrayList<GangRightBean> rightBeanList) {
        Bundle args = new Bundle();
        GangFragment fragment = new GangFragment();
        args.putParcelableArrayList("right", rightBeanList);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            rightLists = getArguments().getParcelableArrayList("right");
        }
    }

    @Override
    protected int setContentViewId() {
        return R.layout.fragment_gang;
    }


    @Override
    protected BasePresenter creatPresenter() {
        return null;
    }

    @Override
    protected void initView(View view) {
        super.initView(view);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 3));
        adapter = new GangRightAdapter(null);
        recyclerView.setAdapter(adapter);
    }

    @Override
    protected void initData() {
        super.initData();
        if (rightLists != null && rightLists.size() != 0) {
            adapter.setNewData(rightLists);
        }

    }

}
