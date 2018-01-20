package com.example.video.view;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.base.R;
import com.example.bean.VideoUrl;
import com.example.video.utils.SampleListener;
import com.example.video.widget.SampleControlVideoView;
import com.shuyu.gsyvideoplayer.GSYBaseActivityDetail;
import com.shuyu.gsyvideoplayer.builder.GSYVideoOptionBuilder;
import com.shuyu.gsyvideoplayer.utils.OrientationUtils;
import com.shuyu.gsyvideoplayer.video.StandardGSYVideoPlayer;
import com.shuyu.gsyvideoplayer.video.base.GSYBaseVideoPlayer;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailControlActivity extends GSYBaseActivityDetail {

    @BindView(R.id.detail_player)
    SampleControlVideoView detailPlayer;
    @BindView(R.id.change_speed)
    Button changeSpeed;
    private float speed = 1;
    @BindView(R.id.shot)
    Button shot;

    private String url = VideoUrl.URL01;
    private OrientationUtils orientationUtils;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_control_);
        ButterKnife.bind(this);


        //使用自定义的全屏切换图片，!!!注意xml布局中也需要设置为一样的
        //必须在setUp之前设置
        detailPlayer.setShrinkImageRes(R.drawable.custom_shrink);
        detailPlayer.setEnlargeImageRes(R.drawable.custom_enlarge);
        //外部辅助的旋转，帮助全屏
        orientationUtils = new OrientationUtils(this, detailPlayer);
        //初始化不打开外部的旋转
        orientationUtils.setEnable(false);

        resolveNormalVideoUI();

        initVideoBuilderMode();

        changeSpeed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resolveTypeUI();
            }
        });

        detailPlayer.setStandardVideoAllCallBack(new SampleListener() {
            @Override
            public void onPrepared(String url, Object... objects) {
                super.onPrepared(url, objects);
                //开始播放了才能旋转和全屏
                orientationUtils.setEnable(true);
                isPlay = true;
            }

            @Override
            public void onAutoComplete(String url, Object... objects) {
                super.onAutoComplete(url, objects);
            }

            @Override
            public void onClickStartError(String url, Object... objects) {
                super.onClickStartError(url, objects);
            }

            @Override
            public void onQuitFullscreen(String url, Object... objects) {
                super.onQuitFullscreen(url, objects);
                if (orientationUtils != null) {
                    orientationUtils.backToProtVideo();
                }
            }
        });
    }

    /**
     * 显示比例
     * 注意，GSYVideoType.setShowType是全局静态生效，除非重启APP。
     */
    private void resolveTypeUI() {
        if (speed == 1) {
            speed = 1.5f;
        } else if (speed == 1.5f) {
            speed = 2f;
        } else if (speed == 2) {
            speed = 0.5f;
        } else if (speed == 0.5f) {
            speed = 0.25f;
        } else if (speed == 0.25f) {
            speed = 1;
        }
        changeSpeed.setText("播放速度：" + speed);
        detailPlayer.setSpeedPlaying(speed, true);
    }

    @Override
    public GSYBaseVideoPlayer getGSYVideoPlayer() {
        return detailPlayer;
    }

    @Override
    public GSYVideoOptionBuilder getGSYVideoOptionBuilder() {
        //内置封面可参考SampleCoverVideo
        ImageView imageView = new ImageView(this);
        loadCover(imageView, url);
        return new GSYVideoOptionBuilder()
                .setThumbImageView(imageView)
                .setUrl(url)
                .setCacheWithPlay(true)
                .setVideoTitle(" ")
                .setIsTouchWiget(true)
                .setRotateViewAuto(false)
                .setLockLand(false)
                .setShowFullAnimation(false)//打开动画
                .setNeedLockFull(true)
                .setSeekRatio(1);
    }

    private void loadCover(ImageView imageView, String url) {
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        imageView.setImageResource(R.mipmap.xxx2);
        Glide.with(this.getApplicationContext())
                .load(url)
                .error(R.mipmap.xxx2)
                .placeholder(R.mipmap.xxx2)
                .into(imageView);
    }

    @Override
    public void clickForFullScreen() {
        orientationUtils.resolveByClick();
    }


    @Override
    public void onBackPressed() {
        if (orientationUtils != null) {
            orientationUtils.backToProtVideo();
        }
        if (StandardGSYVideoPlayer.backFromWindowFull(this)) {
            return;
        }
        super.onBackPressed();
    }

    @Override
    public boolean getDetailOrientationRotateAuto() {
        return false;
    }

    private void resolveNormalVideoUI() {
        //增加title
        detailPlayer.getTitleTextView().setVisibility(View.GONE);
        detailPlayer.getBackButton().setVisibility(View.GONE);
    }
}
