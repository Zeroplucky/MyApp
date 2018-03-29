package com.example.customwidget.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.base.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/3/23.
 */

public class MyPagerAdapter extends PagerAdapter {
    private List<Integer> imgs;
    private Context mContext;

    public MyPagerAdapter(Context context) {
        this.mContext = context;
        imgs = new ArrayList<>();
        imgs.add(R.drawable.scrollview_header);
        imgs.add(R.drawable.scrollview_header);
        imgs.add(R.drawable.scrollview_header);
        imgs.add(R.drawable.scrollview_header);
        imgs.add(R.drawable.scrollview_header);
    }

    @Override
    public int getCount() {
        return imgs.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        ImageView imageView = new ImageView(mContext);
        imageView.setLayoutParams(new ViewGroup.LayoutParams(-1, -1));
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        Glide.with(mContext).load(imgs.get(position)).into(imageView);
        container.addView(imageView);
        return imageView;
    }


    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }


    public static class RotationPageTransformer implements ViewPager.PageTransformer {
        private static final float DEFAULT_MIN_SCALE = 0.85f;
        private float mMinScale = DEFAULT_MIN_SCALE;
        public static final float DEFAULT_CENTER = 0.5f;

        @Override
        public void transformPage(View view, float position) {

            int pageWidth = view.getWidth();
            view.setPivotX(pageWidth / 2);
            if (position < -1) { // [-Infinity,-1)
                view.setScaleX(mMinScale);
                view.setScaleY(mMinScale);
                view.setPivotX(pageWidth);
            } else if (position <= 1) { // [-1,1]
                if (position < 0) {//1-2:1[0,-1] ;2-1:1[-1,0]
                    float scaleFactor = (1 + position) * (1 - mMinScale) + mMinScale;
                    view.setScaleX(scaleFactor);
                    view.setScaleY(scaleFactor);
                    view.setPivotX(pageWidth * (DEFAULT_CENTER + (DEFAULT_CENTER * -position)));
                } else {//1-2:2[1,0] ;2-1:2[0,1]
                    float scaleFactor = (1 - position) * (1 - mMinScale) + mMinScale;
                    view.setScaleX(scaleFactor);
                    view.setScaleY(scaleFactor);
                    view.setPivotX(pageWidth * ((1 - position) * DEFAULT_CENTER));
                }
            } else { // (1,+Infinity]
                view.setPivotX(0);
                view.setScaleX(mMinScale);
                view.setScaleY(mMinScale);
            }
        }
    }
}