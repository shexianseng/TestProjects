package com.testprojects.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.testprojects.MainActivity;
import com.testprojects.utils.IntentUtil;
import com.testprojects.utils.SharedUtil;
import com.testprojects.utils.UserInfoUtil;

/**
 * 程序入口activity 处理跳转分发页面 不处理具体逻辑
 */
public class FirstActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //        setContentView(R.layout.activity_first);
        // 当前页面是外部调用 还是普通启动
//        SharedUtil.putBoolean(this, "isLogin", true);
        if (SharedUtil.getBoolean(this, "isFirst", true)) {
            SharedUtil.putBoolean(this, "isFirst", false);
            IntentUtil.launch(this, LeftMenuActivity.class);
        } else {
            // TODO: 2017/6/15 暂时 登录入口放在这里
            if (UserInfoUtil.isLogin(this)) {
                IntentUtil.launch(this, MainActivity.class);
            } else {
                IntentUtil.launch(this, LoginActivity.class);
            }
        }
        finish();
    }
}
