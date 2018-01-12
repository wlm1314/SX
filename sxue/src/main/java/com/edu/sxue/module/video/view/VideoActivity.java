package com.edu.sxue.module.video.view;

import android.content.res.Configuration;
import android.net.Uri;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.edu.sxue.R;
import com.edu.sxue.databinding.ActivityVideoBinding;
import com.edu.sxue.module.base.BaseActivity;
import com.edu.sxue.module.video.controller.CustomMediaController;

import io.vov.vitamio.MediaPlayer;
import io.vov.vitamio.Vitamio;
import io.vov.vitamio.widget.VideoView;

/**
 * 王少岩 在 2017/6/7 创建了它
 */

public class VideoActivity extends BaseActivity<ActivityVideoBinding> implements MediaPlayer.OnInfoListener, MediaPlayer.OnBufferingUpdateListener {

    //视频地址
    private String path;
    private Uri uri;
    private ProgressBar pb;
    private TextView downloadRateView, loadRateView;
    private CustomMediaController mCustomMediaController;
    private VideoView mVideoView;

    @Override
    protected int attachLayoutRes() {
        return R.layout.activity_video;
    }

    @Override
    protected void initInjector() {

    }

    @Override
    protected void initViews() {
        //必须写这个，初始化加载库文件
        Vitamio.isInitialized(this);
        initView();
        initData();
    }

    //初始化控件
    private void initView() {
        mVideoView = mBinding.videoView;
        mCustomMediaController = new CustomMediaController(this, mVideoView, this);
        mCustomMediaController.setVideoName("白火锅 x 红火锅");
        pb = mBinding.probar;
        downloadRateView = mBinding.downloadRate;
        loadRateView = mBinding.loadRate;
    }

    //初始化数据
    private void initData() {
        path = getIntent().getStringExtra("path");
        uri = Uri.parse(path);
        mVideoView.setVideoURI(uri);//设置视频播放地址
        mCustomMediaController.show(5000);
        mVideoView.setMediaController(mCustomMediaController);
        mVideoView.setVideoQuality(MediaPlayer.VIDEOQUALITY_HIGH);//高画质
        mVideoView.requestFocus();
        mVideoView.setOnInfoListener(this);
        mVideoView.setOnBufferingUpdateListener(this);
        mVideoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mediaPlayer) {
                mediaPlayer.setPlaybackSpeed(1.0f);
            }
        });
    }

    @Override
    public boolean onInfo(MediaPlayer mp, int what, int extra) {
        switch (what) {
            case MediaPlayer.MEDIA_INFO_BUFFERING_START:
                if (mVideoView.isPlaying()) {
                    mVideoView.pause();
                    pb.setVisibility(View.VISIBLE);
                    downloadRateView.setText("");
                    loadRateView.setText("");
                    downloadRateView.setVisibility(View.VISIBLE);
                    loadRateView.setVisibility(View.VISIBLE);

                }
                break;
            case MediaPlayer.MEDIA_INFO_BUFFERING_END:
                mVideoView.start();
                pb.setVisibility(View.GONE);
                mVideoView.setBackgroundColor(getResources().getColor(R.color.transparent));
                downloadRateView.setVisibility(View.GONE);
                loadRateView.setVisibility(View.GONE);
                break;
            case MediaPlayer.MEDIA_INFO_DOWNLOAD_RATE_CHANGED:
                downloadRateView.setText("" + extra + "kb/s" + "  ");
                break;
        }
        return true;
    }

    @Override
    public void onBufferingUpdate(MediaPlayer mp, int percent) {
        loadRateView.setText(percent + "%");
    }


    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        //屏幕切换时，设置全屏
        if (mVideoView != null) {
            mVideoView.setVideoLayout(VideoView.VIDEO_LAYOUT_SCALE, 0);
        }
        super.onConfigurationChanged(newConfig);
    }

    @Override
    protected void updateViews(boolean isRefresh) {

    }
}
