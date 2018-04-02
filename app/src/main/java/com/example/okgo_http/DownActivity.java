package com.example.okgo_http;

import android.os.Handler;
import android.os.Message;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.base.R;
import com.example.base2.BaseActivity;
import com.example.okgo_http.bean.DownBean;
import com.example.okgo_http.mvp.DownController;
import com.example.utils.AnimationUtils;
import com.example.widget.ProgressView;
import com.zhy.autolayout.AutoLinearLayout;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class DownActivity extends BaseActivity<DownController.DownView, DownController.DownPresenter> implements DownController.DownView {

    @BindView(R.id.titleName)
    TextView titleName;
    @BindView(R.id.imageview01)
    ImageView imageview01;
    @BindView(R.id.textview02)
    TextView textview02;
    @BindView(R.id.textview03)
    TextView textview03;
    @BindView(R.id.ratingbar04)
    RatingBar ratingbar04;
    @BindView(R.id.linearlayout05)
    LinearLayout linearlayout05;
    @BindView(R.id.textview06)
    TextView textview06;
    @BindView(R.id.layoutDown07)
    LinearLayout layoutDown07;
    @BindView(R.id.upOrDown)
    AutoLinearLayout upOrDown;
    @BindView(R.id.progressBar)
    ProgressView progressBar;
    private DownBean bean;
    private String downUrl;

    @Override
    protected int setContentViewId() {
        return R.layout.activity_down;
    }

    @Override
    public DownController.DownPresenter creatPresenter() {
        return new DownController.DownPresenter();
    }

    @Override
    public void initView() {
        super.initView();
        titleName.setText("下载详情");
        DownFragment fragment = findFragment(DownFragment.class);
        if (fragment == null) {
            loadRootFragment(R.id.content, DownFragment.newInstance());
        }
        getPresenter().getData();

    }


    @Override
    public void getData(DownBean bean, String url) {
        this.bean = bean;
        this.downUrl = url;
        if (bean != null) {
            Glide.with(mContext).load(bean.getIcoUri()).into(imageview01);
            textview02.setText(bean.getName() + "");
            textview03.setText(bean.getIntro() + "");
            ratingbar04.setNumStars(bean.getStars());
            setlabelNames();
            setSafeLabel();
        }

    }

    private void setSafeLabel() {
        List<DownBean.SafeLabelsBean> safeLabels = bean.getSafeLabels();
        for (int i = 0; i < safeLabels.size(); i++) {
            View view = LayoutInflater.from(mContext).inflate(R.layout.item_down_safe, null);
            TextView mTextName04 = (TextView) view.findViewById(R.id.textName04);
            TextView mLabel03 = (TextView) view.findViewById(R.id.label03);
            ImageView mImageview01 = (ImageView) view.findViewById(R.id.imageview01);
            TextView mDescTextview02 = (TextView) view.findViewById(R.id.descTextview02);
            Glide.with(mContext).load(safeLabels.get(i).getUrl()).into(mImageview01);
            mDescTextview02.setText(safeLabels.get(i).getDetectorDesc());
            mLabel03.setText(safeLabels.get(i).getDetectorName());
            mDescTextview02.setText(safeLabels.get(i).getName());
            layoutDown07.addView(view);
        }
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) layoutDown07.getLayoutParams();
        layoutParams.leftMargin = 10;
        layoutParams.rightMargin = 10;
    }

    @Override
    public void onError(Exception e) {

    }

    public void setlabelNames() {
        List<DownBean.LabelNamesBean> labelNames = bean.getLabelNames();
        for (int i = 0; i < labelNames.size(); i++) {
            String name = labelNames.get(i).getName();
            TextView textView = new TextView(mContext);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(-2, -2);
            params.rightMargin = 5;
            textView.setLayoutParams(params);
            textView.setGravity(Gravity.CENTER);
            textView.setText(name + "");
            textView.setTextSize(8);
            textView.setBackgroundResource(R.drawable.rect_rounded);
            linearlayout05.addView(textView);
        }
    }


    @OnClick({R.id.layout, R.id.upOrDown})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.upOrDown:
                AnimationUtils.switcher(layoutDown07);
                handler.sendEmptyMessage(0);
                break;
        }
    }


    private int p = 0;

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (p <= 100) {
                sendEmptyMessageDelayed(0, 500);
            }
            p++;
            progressBar.setProgress(p);
        }
    };


}
