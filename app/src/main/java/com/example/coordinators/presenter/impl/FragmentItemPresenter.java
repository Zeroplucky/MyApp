package com.example.coordinators.presenter.impl;

import com.example.coordinators.presenter.IFragmentItemPresenter;
import com.example.coordinators.view.IFragmentItemView;

/**
 * Created by Administrator on 2017/12/26.
 */

public class FragmentItemPresenter implements IFragmentItemPresenter {

    private IFragmentItemView itemView;

    public FragmentItemPresenter(IFragmentItemView itemView) {
        this.itemView = itemView;
    }

    @Override
    public void getTabs() {
        itemView.showTabList(new String[]{"知乎日报", "IT定制"});
    }
}
