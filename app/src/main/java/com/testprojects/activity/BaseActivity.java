package com.testprojects.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
/**
 * Created by she on 2017/6/15.
 * BaseActivity
 */

public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        initView();
        initData();
    }

    protected abstract int getLayoutId();

    protected String getTAG() {
        return getClass().getSimpleName();
    }

    protected abstract void initView();

    protected void initData() {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
