package com.example.okgo_http;

import android.os.Bundle;

import com.example.base.R;
import com.example.base2.BaseFragment;
import com.example.base2.BasePresenter;

public class DownFragment extends BaseFragment {

    public static DownFragment newInstance() {
        DownFragment fragment = new DownFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    protected int setContentViewId() {
        return R.layout.fragment_down;
    }

    @Override
    protected BasePresenter creatPresenter() {
        return null;
    }


}
