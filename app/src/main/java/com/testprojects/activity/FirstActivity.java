package com.testprojects.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.testprojects.MainActivity;
import com.testprojects.R;
import com.testprojects.utils.IntentUtil;
import com.testprojects.utils.SharedUtil;

/**
 * 程序入口activity 处理跳转分发页面 不处理具体逻辑
 */
public class FirstActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //        setContentView(R.layout.activity_first);
        // 当前页面是外部调用 还是普通启动
        if (SharedUtil.getBoolean(this, "isFirst", true)) {
            SharedUtil.putBoolean(this, "isFirst", false);
            IntentUtil.launch(this, LeftMenuActivity.class);
        } else {
            IntentUtil.launch(this, MainActivity.class);
        }
        finish();
    }
}
