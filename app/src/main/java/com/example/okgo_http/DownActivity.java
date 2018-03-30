package com.example.okgo_http;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.base.R;
import com.example.base2.BaseActivity;
import com.example.okgo_http.bean.DownBean;
import com.example.okgo_http.mvp.DownController;

import java.util.List;

import butterknife.BindView;

public class DownActivity extends BaseActivity<DownController.DownView, DownController.DownPresenter> implements DownController.DownView {

    @BindView(R.id.titleName)
    TextView titleName;
    @BindView(R.id.progressBar)
    ProgressBar progressBar;
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
    private DownBean bean;

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

}
