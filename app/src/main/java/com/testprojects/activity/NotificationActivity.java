package com.testprojects.activity;

import com.testprojects.R;
import com.testprojects.base.BaseActivity;

/**
 * Created by she on 2017/6/16.
 * 通知  Google 示例模版
 */

public class NotificationActivity extends BaseActivity {
    @Override
    protected int getLayoutId() {
        return R.layout.activity_notification;
    }

    @Override
    protected void initView() {
        findViewById(R.id.sendNewMessage).setOnClickListener(view ->
                showNewMessage(true)
        );
        findViewById(R.id.sendSimpleMessage).setOnClickListener(view ->
                showNewMessage(false)
        );
        findViewById(R.id.closeMessage).setOnClickListener(view ->
                NewMessageNotification.cancel(this)
        );
    }

    private void showNewMessage(boolean isShow) {
        if (isShow) {
            NewMessageNotification.notify(this, "我是一个通知", 10);
        } else {
            NewMessageNotification.notify(this, "我是一个简单的通知");
        }
    }
}
