package com.example.video;

import android.content.Intent;
import android.graphics.Color;
import android.view.View;
import android.widget.TextView;

import com.example.base.BaseActivity;
import com.example.base.R;
import com.example.utils.NetworkUtils;
import com.example.video.view.DanmkuVideoActivity;
import com.example.video.view.DetailControlActivity;
import com.example.video.view.WebVideoActivity;
import com.jaeger.library.StatusBarUtil;
import com.scwang.smartrefresh.header.MaterialHeader;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.Random;

import butterknife.BindView;


public class VideoActivity extends BaseActivity {


    @BindView(R.id.RefreshLayout)
    SmartRefreshLayout refreshLayout;
    @BindView(R.id.open_html)
    TextView open_html;
    private boolean isEnter = false;
    private Random random = new Random();

    @Override
    protected int getContentViewId() {
        return R.layout.activity_video_;
    }

    @Override
    protected void initView() {
        StatusBarUtil.setColor(this, getResources().getColor(R.color.black));
        refreshLayout.setRefreshHeader(new MaterialHeader(this));
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                int rInt1 = random.nextInt(255);
                int rInt2 = random.nextInt(255);
                int rInt3 = random.nextInt(255);
                open_html.setTextColor(Color.rgb(rInt3, rInt2, rInt1));
                refreshlayout.finishRefresh(1000);
            }
        });
    }


    public void openHtml(View view) {
        enterActivity(WebVideoActivity.class);
    }

    public void open_control(View view) {
        enterActivity(DetailControlActivity.class);
    }

    public void open_danmu(View view) {
        enterActivity(DanmkuVideoActivity.class);
    }


    private void enterActivity(Class clazz) {
        if (NetworkUtils.isWifiConnected()) {
            isEnter = false;
            startActivity(new Intent(this, clazz));
        } else {
            if (isEnter) {
                isEnter = !isEnter;
                startActivity(new Intent(this, clazz));
            } else {
                isEnter = !isEnter;
                toast("当前不是WIFI连接");
            }
        }
    }


}
