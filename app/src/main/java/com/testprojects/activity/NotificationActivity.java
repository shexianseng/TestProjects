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
        findViewById(R.id.cancelMessage).setOnClickListener(view ->
                showNewMessage(true)
        );
    }

    private void showNewMessage(boolean isShow) {
        if (isShow) {
            NewMessageNotification.notify(this, "我是一个通知", 10);
        } else {
            // TODO: 2017/6/16  暂时不知道什么原因 不起作用
            NewMessageNotification.cancel(this);
        }
    }
}
