package com.example.customwidget.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
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
        private static final float MIN_SCALE = 0.85f;

        @Override
        public void transformPage(View page, float position) {
            page.setScaleX(0.9f);
            Log.e("CustomActivity xxx", "transformPage: ---------------------------------------");
            Log.e("CustomActivity xxx", "transformPage: " + page.toString() + "    " + position);
            float scaleFactor = Math.max(MIN_SCALE, 1 - Math.abs(position));
            float rotate = 10 * Math.abs(position);
            //position小于等于1的时候，代表page已经位于中心item的最左边，
            //此时设置为最小的缩放率以及最大的旋转度数
            if (position <= -1) {
                page.setScaleX(MIN_SCALE);
                page.setScaleY(MIN_SCALE);
                page.setRotationY(rotate);
            } else if (position < 0) {//position从0变化到-1，page逐渐向左滑动 // [-1,0]
                page.setScaleX(scaleFactor);
                page.setScaleY(scaleFactor);
                page.setRotationY(rotate);
            } else if (position >= 0 && position < 1) {//position从0变化到1，page逐渐向右滑动  // (0,1]
                page.setScaleX(scaleFactor);
                page.setScaleY(scaleFactor);
                page.setRotationY(-rotate);
            } else if (position >= 1) {//position大于等于1的时候，代表page已经位于中心item的最右边
                page.setScaleX(scaleFactor);
                page.setScaleY(scaleFactor);
                page.setRotationY(-rotate);
            }
        }
    }
}