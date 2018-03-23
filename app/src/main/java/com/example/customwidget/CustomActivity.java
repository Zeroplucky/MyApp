package com.example.customwidget;

import android.support.v4.view.ViewPager;

import com.example.base.BaseActivity;
import com.example.base.R;
import com.example.customwidget.adapter.MyPagerAdapter;

import butterknife.BindView;

public class CustomActivity extends BaseActivity {


    public static final String TAG = "CustomActivity";
    @BindView(R.id.id_viewpager)
    ViewPager mViewPager;


    @Override
    protected int getContentViewId() {
        return R.layout.activity_custom_;
    }


    @Override
    protected void initView() {
        MyPagerAdapter mPagerAdapter = new MyPagerAdapter(this);
        mViewPager.setAdapter(mPagerAdapter);
        mViewPager.setPageTransformer(true, new MyPagerAdapter.RotationPageTransformer());
        mViewPager.setOffscreenPageLimit(2);//设置预加载的数量，这里设置了2,会预加载中心item左边两个Item和右边两个Item
        mViewPager.setPageMargin(10);//设置两个Page之间的距离

    }


}
