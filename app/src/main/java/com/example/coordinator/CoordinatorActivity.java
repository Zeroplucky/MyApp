package com.example.coordinator;

import android.Manifest;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import com.example.coordinator.adpter.FragmentAdapter;
import com.example.coordinator.constant.Constants;
import com.example.coordinator.fragment.CustomFragment;
import com.example.coordinator.fragment.ZhihuFragment;
import com.example.coordinator.presenter.IFragmentItemPresenter;
import com.example.coordinator.presenter.impl.FragmentItemPresenter;
import com.example.coordinator.view.IFragmentItemView;
import com.example.base.BaseActivity;
import com.example.base.R;
import com.jaeger.library.StatusBarUtil;
import com.tbruyelle.rxpermissions2.RxPermissions;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import io.reactivex.functions.Consumer;

public class CoordinatorActivity extends BaseActivity implements IFragmentItemView {


    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.tl_tabs)
    TabLayout tlTabs;
    @BindView(R.id.app_bar)
    AppBarLayout appBar;
    @BindView(R.id.fab_classify)
    FloatingActionButton fabClassify;
    @BindView(R.id.vp_fragment)
    ViewPager vpFragment;
    private IFragmentItemPresenter presenter;
    private List<Fragment> fragments;

    @Override
    protected int getContentViewId() {
        return R.layout.activity_coordinator;
    }

    @Override
    protected void initView() {
        StatusBarUtil.setColor(this, getResources().getColor(R.color.colorPrimary));
        presenter = new FragmentItemPresenter(this);
        fragments = new ArrayList<>();
        presenter.getTabs();
        //rxpermissions2 的用法
        RxPermissions rxPermissions = new RxPermissions(this);
        rxPermissions.request(Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.READ_EXTERNAL_STORAGE)
                .subscribe(new Consumer<Boolean>() {
                    @Override
                    public void accept(Boolean aBoolean) throws Exception {
                        if (!aBoolean) {
                            Toast.makeText(mContext, "相关权限被拒，部分功能不可使用", Toast.LENGTH_SHORT).show();
                            finish();
                        }
                    }
                });

    }

    @Override
    protected void initData() {
        super.initData();
        appBar.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (verticalOffset == 0) {
                    fabClassify.show();
                } else {
                    fabClassify.hide();
                }
            }
        });

        fabClassify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }

    @Override
    public void showTabList(String[] tabs) {
        for (int i = 0; i < tabs.length; i++) {
            tlTabs.addTab(tlTabs.newTab().setText(tabs[i]));
            switch (i) {
                case Constants.TAB_GANK_DAY_INDEX:
                    fragments.add(ZhihuFragment.newInstance());
                    break;
                case Constants.TAB_GANK_CUSTOM_INDEX:
                    fragments.add(CustomFragment.newInstance());
                    break;
            }
        }
        vpFragment.setAdapter(new FragmentAdapter(getSupportFragmentManager(), fragments));
        vpFragment.setCurrentItem(Constants.TAB_GANK_DAY_INDEX);//要设置到viewpager
        tlTabs.setupWithViewPager(vpFragment);
        tlTabs.setVerticalScrollbarPosition(Constants.TAB_GANK_DAY_INDEX);
        //这里重新设置一遍tabs的text，否则tabs的text不显示
        for (int i = 0; i < tabs.length; i++) {
            tlTabs.getTabAt(i).setText(tabs[i]);
        }
    }
}
