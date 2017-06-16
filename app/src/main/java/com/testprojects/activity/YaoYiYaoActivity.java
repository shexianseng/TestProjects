package com.testprojects.activity;

import android.widget.Button;
import android.widget.TextView;

import com.testprojects.R;
import com.testprojects.base.BaseActivity;
import com.testprojects.utils.ShakeUtil;

/**
 * Created by she on 2017/6/16.
 * 摇一摇
 */

public class YaoYiYaoActivity extends BaseActivity {
    private ShakeUtil shakeUtil;
    private TextView textYao;
    private Button button;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_yao_yi_yao;
    }

    @Override
    protected void initView() {
        button = (Button) findViewById(R.id.button_yao);
        textYao = (TextView) findViewById(R.id.text_yao);

        button.setOnClickListener(view -> {
            //开启传感器
            shakeUtil = new ShakeUtil(this);
            shakeUtil.setOnShakeListener(() -> {
                textYao.setText("摇一摇成功了");
                shakeUtil.stop();
            });
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (null != shakeUtil)
            shakeUtil.stop();
    }
}
