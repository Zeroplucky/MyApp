package com.example.customwidget;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.view.PagerAdapter;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.base.BaseActivity;
import com.example.base.R;
import com.example.customwidget.widget.Custom01View;

import butterknife.BindView;

public class CustomActivity extends BaseActivity {


    public static final String TAG = "CustomActivity";
    @BindView(R.id.myDragLayout)
    Custom01View myDragLayout;


    @Override
    protected int getContentViewId() {
        return R.layout.activity_custom_;
    }


    @Override
    protected void initView() {

//        myDragLayout.setAdapter(new PageAdaper(this));

        TextView textView = new TextView(this);
        textView.setLayoutParams(new ViewGroup.LayoutParams(-1, -1));
        textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 15);
        textView.setText("xxxxxxxxxxx");
        textView.setTextColor(Color.RED);

        addContentView(textView, new ViewGroup.LayoutParams(-1, -1));
    }

    class PageAdaper extends PagerAdapter {

        private Context context;

        public PageAdaper(Context context) {
            this.context = context;
        }

        @Override
        public int getCount() {
            return 10;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            TextView textView = new TextView(context);
            textView.setLayoutParams(new ViewGroup.LayoutParams(-1, -1));
            textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 15);
            textView.setText("xxxxxxxxxxx");
            textView.setTextColor(Color.RED);
            return textView;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {

        }
    }


}
