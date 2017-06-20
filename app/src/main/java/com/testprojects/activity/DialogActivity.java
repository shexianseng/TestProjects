package com.testprojects.activity;

import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.view.View;

import com.testprojects.R;
import com.testprojects.base.BaseActivity;

/**
 * Created by she on 2017/6/19.
 * 对话框
 */

public class DialogActivity extends BaseActivity implements View.OnClickListener {
    @Override
    protected int getLayoutId() {
        return R.layout.activity_dialog;
    }

    @Override
    protected void initView() {
        findViewById(R.id.button2).setOnClickListener(this);
        findViewById(R.id.button3).setOnClickListener(this);
        findViewById(R.id.button4).setOnClickListener(this);
        findViewById(R.id.button5).setOnClickListener(this);
        findViewById(R.id.button6).setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button2:
                // 确定|取消 对话框
                showDialog(v, 2);
                break;
            case R.id.button3:
                // 确定 对话框
                showDialog(v, 3);
                break;
            case R.id.button4:
                // 列表类 对话框
                Snackbar.make(v, "SnackBar使用", Snackbar.LENGTH_SHORT).show();
                break;
            case R.id.button5:
                //单选 对话框
                showDialog(v, 5);
                break;
            case R.id.button6:
                //多选 对话框
                showDialog(v, 6);
                break;
        }
    }

    private void showDialog(View view, int i) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("我是一个标题");
        builder.setMessage("我是一个测试内容");
        //设置对话框是否可以点击屏幕任意区域 消失
        // 默认为true 为false 时 只能点击给定的确定或者取消操作按钮才能消失
        //        alertDialog.setCancelable(false);
        builder.setPositiveButton("确定", (dialog, which) -> {
            //显示一个 SnackBar 类型的 提示  属于弱类型提示 不会显示在屏幕中间 影响当前流程
            Snackbar.make(view, "我是一个测试", Snackbar.LENGTH_SHORT).show();
        });

        builder.setNeutralButton("我是菜单按钮", (dialog, which) ->
                Snackbar.make(view, "菜单", Snackbar.LENGTH_SHORT).show()
        );


        if (i == 3) {
            builder.setNegativeButton("取消", (dialog, which) -> {
                Snackbar.make(view, "我是一个取消", Snackbar.LENGTH_SHORT).show();
            });
        }
        String[] items = {"第一个", "第二个", "第二个", "第二个", "第二个", "第二个"};

        // TODO: 2017/6/20 单选和多选暂时有问题 不显示 目前不知道问题在哪 待修复
        if (i == 5) {
            boolean[] ids = {false, false, false, false, false, false};
            builder.setMultiChoiceItems(items, ids, (dialog, which, isChecked) ->
                    Snackbar.make(view, "我是一个列表", Snackbar.LENGTH_SHORT).show());
        }
        if (i == 6) {
            builder.setSingleChoiceItems(items, 1, (dialog, which) ->
                    Snackbar.make(view, "我是一个列表", Snackbar.LENGTH_SHORT).show());
        }

        AlertDialog dialog = builder.create();
        dialog.show();
    }
}
