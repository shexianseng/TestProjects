package com.testprojects.activity;

import android.widget.RatingBar;
import android.widget.TextView;

import com.testprojects.R;
import com.testprojects.base.BaseActivity;

/**
 * Created by she on 2017/6/16.
 * 进度条 原生
 */

public class ProgressActivity extends BaseActivity {
    private RatingBar ratingBar;
    private TextView textShowNum;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_progress;
    }

    @Override
    protected void initView() {
        ratingBar = (RatingBar) findViewById(R.id.ratingBar);
        ratingBar.setProgress(1);
        ratingBar.setNumStars(5);
        textShowNum = (TextView) findViewById(R.id.textShowNum);
        findViewById(R.id.button).setOnClickListener(v ->
                textShowNum.setText(String.valueOf(ratingBar.getProgress())));
    }
}
