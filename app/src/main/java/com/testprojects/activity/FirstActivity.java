package com.testprojects.activity;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import com.testprojects.utils.IntentUtil;
import com.testprojects.utils.SharedUtil;

/**
 * 程序入口activity 处理跳转分发页面 不处理具体逻辑
 * <p>
 * 没有白屏 黑屏的闪屏页
 */
public class FirstActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 当前页面是外部调用 还是普通启动
        if (SharedUtil.getBoolean(this, "isFirst", true)) {
            SharedUtil.putBoolean(this, "isFirst", false);
            //可以添加引导页  个人不太喜欢 感觉影响不是很大

        } else {
            //有登录需求的 判断登录问题
            //            if (UserInfoUtil.isLogin(this)) {
            //                IntentUtil.launch(this, ListDemoActivity.class);
            //            } else {
            //                IntentUtil.launch(this, LoginActivity.class);
            //            }
        }
        //时间太快 人为延迟一下
        new Handler().postDelayed(() ->
                {
                    IntentUtil.launch(this, ListDemoActivity.class);
                    finish();
                }
                , 1500);
    }
}
