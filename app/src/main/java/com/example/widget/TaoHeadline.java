package com.example.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.ViewSwitcher;

import com.example.base.R;
import com.example.bean.HeadlineBean;

import java.util.List;

/**
 * Created by Administrator on 2017/12/19.
 */

public class TaoHeadline extends RelativeLayout {

    private HeadlineClickListener listener;
    private ViewSwitcher viewSwitcher;
    private List<HeadlineBean> data;
    private RelativeLayout subView1, subView2;
    private int currentPosition = 0;
    private final Runnable runnable = new Runnable() {
        @Override
        public void run() {
            currentPosition++;
            final ViewHolder holder = (ViewHolder) ((currentPosition % 2) == 0 ? subView1.getTag() : subView2.getTag());
            holder.title_tv.setText(data.get(currentPosition % data.size()).getTitle());
            holder.content_tv.setText(data.get(currentPosition % data.size()).getContent());
            viewSwitcher.setDisplayedChild(currentPosition % 2);
            postDelayed(runnable, 4000);
        }
    };
    private View.OnClickListener headlineItemClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (listener != null) {
                listener.onHeadlineClick(data.get(currentPosition % data.size()));
            }
        }
    };

    public TaoHeadline(Context context) {
        this(context, null);
    }

    public TaoHeadline(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    private void initView() {
        View rootView = LayoutInflater.from(getContext()).inflate(R.layout.taobao_headline_layout, this, true);
        viewSwitcher = (ViewSwitcher) rootView.findViewById(R.id.tao_viewswitcher);
        if (subView1 == null) {
            subView1 = (RelativeLayout) viewSwitcher.findViewById(R.id.subView1);
            final ViewHolder holder = new ViewHolder();
            holder.title_tv = (TextView) subView1.findViewById(R.id.headline_title_tv);
            holder.content_tv = (TextView) subView1.findViewById(R.id.headline_content_tv);
            holder.title_tv.setText(data.get(0).getTitle());
            holder.content_tv.setText(data.get(0).getContent());
            subView1.setTag(holder);
            subView1.setOnClickListener(headlineItemClickListener);
        }
        if (subView2 == null) {
            subView2 = (RelativeLayout) viewSwitcher.findViewById(R.id.subView2);
            final ViewHolder holder = new ViewHolder();
            holder.title_tv = (TextView) subView2.findViewById(R.id.headline_title_tv);
            holder.content_tv = (TextView) subView2.findViewById(R.id.headline_content_tv);
            subView2.setTag(holder);
            subView2.setOnClickListener(headlineItemClickListener);
        }
        viewSwitcher.setDisplayedChild(0);
        //进入动画
        viewSwitcher.setInAnimation(getContext(), R.anim.headline_in);
        //退出动画
        viewSwitcher.setOutAnimation(getContext(), R.anim.headline_out);
        if (data.size() != 1) {
            post(runnable);
        }
    }

    //配置滚动的数据
    public void setData(List<HeadlineBean> data) {
        this.data = data;
        initView();
    }

    public void setHeadlineClickListener(HeadlineClickListener listener) {
        this.listener = listener;
    }

    public interface HeadlineClickListener {
        void onHeadlineClick(HeadlineBean bean);
    }

    private class ViewHolder {
        TextView title_tv;
        TextView content_tv;
    }
}