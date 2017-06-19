package com.testprojects.activity;

import android.app.ProgressDialog;
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
        findViewById(R.id.button).setOnClickListener(v -> {
                    showProgressDialog();
                }
        );
    }

    /**
     * 显示一个ProgressDialog
     */
    private void showProgressDialog() {
        ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("加载中～");
        //设置触摸消失
        progressDialog.setCanceledOnTouchOutside(true);
        progressDialog.setOnCancelListener(dialog ->
                //消失时显示 当然选中的星星个数
                textShowNum.setText(String.valueOf(ratingBar.getProgress()))
        );

        // 自动消失
        new android.os.Handler().postDelayed(() -> {
            if (progressDialog.isShowing()) {
                progressDialog.cancel();
            }
        }, 2000);
        progressDialog.show();
    }
}
