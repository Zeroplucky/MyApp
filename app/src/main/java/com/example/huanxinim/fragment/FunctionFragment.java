package com.example.huanxinim.fragment;


import android.view.View;

import com.example.base.BaseFragment;
import com.example.base.R;


public class FunctionFragment extends BaseFragment {

    public static FunctionFragment newInstance() {
        FunctionFragment fragment = new FunctionFragment();
        return fragment;
    }


    @Override
    protected int getContentViewId() {
        return R.layout.fragment_function;
    }

    @Override
    protected void initView(View view) {

    }

}
