package com.testprojects.activity;

import android.media.MediaPlayer;
import android.util.Log;
import android.widget.VideoView;

import com.testprojects.R;
import com.testprojects.base.BaseActivity;

/**
 * Created by she on 2017/6/26.
 * 播放视频   播放音乐  画中画功能
 */

public class PlayVideoActivity extends BaseActivity {

    private VideoView mVideoView;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_play_video;
    }

    @Override
    protected void initView() {
        mVideoView = (VideoView) findViewById(R.id.videoView);

        mVideoView.setOnErrorListener((mediaPlayer, i, i1) ->
                false);
        mVideoView.setOnCompletionListener(mediaPlayer -> {
            // TODO: 2017/6/26  
            Log.e(TAG, "mediaPlayer--" + mediaPlayer.isPlaying());
        });
    }
}
