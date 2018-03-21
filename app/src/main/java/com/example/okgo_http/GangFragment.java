package com.example.okgo_http;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.base.R;
import com.example.base2.BaseFragment;
import com.example.base2.BasePresenter;
import com.example.okgo_http.adapter.GangNewAdapter;
import com.example.okgo_http.adapter.ItemHeaderDecoration;
import com.example.okgo_http.bean.GangRightBean;

import java.util.ArrayList;

import butterknife.BindView;


public class GangFragment extends BaseFragment {

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    private ArrayList<GangRightBean> rightLists;
    private GridLayoutManager mManager;
    private GangNewAdapter adapter;

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
        mManager = new GridLayoutManager(getContext(), 3);
        //通过isTitle的标志来判断是否是title
        mManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                return rightLists.get(position).isTitle() ? 3 : 1;
            }
        });
        recyclerView.setLayoutManager(mManager);
        adapter = new GangNewAdapter(rightLists, getContext());
        recyclerView.setAdapter(adapter);
        ItemHeaderDecoration mDecoration = new ItemHeaderDecoration(getContext(), rightLists);
        recyclerView.addItemDecoration(mDecoration);
    }

    @Override
    protected void initData() {
        super.initData();
        if (rightLists != null && rightLists.size() != 0) {
            adapter.notifyDataSetChanged();
        }

    }

}
