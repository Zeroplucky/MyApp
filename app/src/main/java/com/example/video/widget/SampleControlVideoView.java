package com.example.video.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.base.R;
import com.shuyu.gsyvideoplayer.utils.GSYVideoType;
import com.shuyu.gsyvideoplayer.video.StandardGSYVideoPlayer;
import com.shuyu.gsyvideoplayer.video.base.GSYBaseVideoPlayer;
import com.shuyu.gsyvideoplayer.video.base.GSYVideoPlayer;

/**
 * Created by Administrator on 2017/12/29.
 */

public class SampleControlVideoView extends StandardGSYVideoPlayer {

    private TextView mMoreScale;
    private TextView mChangeRotate;
    //记住切换数据源类型
    private int mType = 0;
    //数据源
    private int mSourcePosition = 0;


    public SampleControlVideoView(Context context, Boolean fullFlag) {
        super(context, fullFlag);
    }

    public SampleControlVideoView(Context context) {
        super(context);
    }

    public SampleControlVideoView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    /**
     * 全屏时将对应处理参数逻辑赋给全屏播放器
     */
    @Override
    public GSYBaseVideoPlayer startWindowFullscreen(Context context, boolean actionBar, boolean statusBar) {
        SampleControlVideoView sampleVideo = (SampleControlVideoView) super.startWindowFullscreen(context, actionBar, statusBar);
        sampleVideo.mSourcePosition = mSourcePosition;
        sampleVideo.mType = mType;
        sampleVideo.resolveTypeUI();
        //sampleVideo.resolveRotateUI();
        //这个播放器的demo配置切换到全屏播放器
        //这只是单纯的作为全屏播放显示，如果需要做大小屏幕切换，请记得在这里耶设置上视频全屏的需要的自定义配置
        //比如已旋转角度之类的等等
        //可参考super中的实现
        return sampleVideo;
    }

    /**
     * 推出全屏时将对应处理参数逻辑返回给非播放器
     */
    @Override
    protected void resolveNormalVideoShow(View oldF, ViewGroup vp, GSYVideoPlayer gsyVideoPlayer) {
        super.resolveNormalVideoShow(oldF, vp, gsyVideoPlayer);
        if (gsyVideoPlayer != null) {
            SampleControlVideoView sampleVideo = (SampleControlVideoView) gsyVideoPlayer;
            mSourcePosition = sampleVideo.mSourcePosition;
            mType = sampleVideo.mType;
            resolveTypeUI();
        }
    }

    @Override
    public int getLayoutId() {
        return R.layout.sample_control_video_view;
    }

    @Override
    protected void init(Context context) {
        super.init(context);
        initView();
    }

    private void initView() {
        mMoreScale = (TextView) findViewById(R.id.moreScale);
        mChangeRotate = (TextView) findViewById(R.id.change_rotate);

        //切换清晰度
        mMoreScale.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!mHadPlay) {
                    return;
                }
                if (mType == 0) {
                    mType = 1;
                } else if (mType == 1) {
                    mType = 2;
                } else if (mType == 2) {
                    mType = 3;
                } else if (mType == 3) {
                    mType = 4;
                } else if (mType == 4) {
                    mType = 0;
                }
                resolveTypeUI();
            }
        });

        //旋转播放角度
        mChangeRotate.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!mHadPlay) {
                    return;
                }
                if ((mTextureView.getRotation() - mRotate) == 270) {
                    mTextureView.setRotation(mRotate);
                    mTextureView.requestLayout();
                } else {
                    mTextureView.setRotation(mTextureView.getRotation() + 90);
                    mTextureView.requestLayout();
                }
            }
        });
    }


    /**
     * 显示比例
     * 注意，GSYVideoType.setShowType是全局静态生效，除非重启APP。
     */
    private void resolveTypeUI() {
        if (!mHadPlay) {
            return;
        }
        if (mType == 1) {
            mMoreScale.setText("16:9");
            GSYVideoType.setShowType(GSYVideoType.SCREEN_TYPE_16_9);
        } else if (mType == 2) {
            mMoreScale.setText("4:3");
            GSYVideoType.setShowType(GSYVideoType.SCREEN_TYPE_4_3);
        } else if (mType == 3) {
            mMoreScale.setText("全屏");
            GSYVideoType.setShowType(GSYVideoType.SCREEN_TYPE_FULL);
        } else if (mType == 4) {
            mMoreScale.setText("拉伸全屏");
            GSYVideoType.setShowType(GSYVideoType.SCREEN_MATCH_FULL);
        } else if (mType == 0) {
            mMoreScale.setText("标准");
            GSYVideoType.setShowType(GSYVideoType.SCREEN_TYPE_DEFAULT);
        }
        changeTextureViewShowType();
        if (mTextureView != null)
            mTextureView.requestLayout();
    }

}
