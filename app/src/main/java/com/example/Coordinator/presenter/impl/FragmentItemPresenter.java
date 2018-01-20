package com.example.Coordinator.presenter.impl;

import com.example.Coordinator.presenter.IFragmentItemPresenter;
import com.example.Coordinator.view.IFragmentItemView;

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
