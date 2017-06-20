package com.testprojects.activity;

import android.support.design.widget.BaseTransientBottomBar;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.widget.Toast;

import com.testprojects.R;
import com.testprojects.base.BaseActivity;

/**
 * Created by she on 2017/6/20.
 * SnackBar的使用
 * <p>
 * PS: 滑动删除时 必须顶级view是  <android.support.design.widget.CoordinatorLayout
 * 或者它的子级
 */

public class SnackBarActivity extends BaseActivity {
    @Override
    protected int getLayoutId() {
        return R.layout.activity_snack_bar;
    }

    @Override
    protected void initView() {
        findViewById(R.id.button7).setOnClickListener(view ->
                Snackbar.make(findViewById(R.id.coordinatorLayout), "我不能点击操作", Snackbar.LENGTH_SHORT).show()
        );
        findViewById(R.id.button8).setOnClickListener(view -> {
                    //不自动消失 Snackbar.LENGTH_INDEFINITE
                    Snackbar.make(findViewById(R.id.coordinatorLayout), "点击操作", Snackbar.LENGTH_INDEFINITE)
                            .setAction("点我", v ->
                                    Toast.makeText(SnackBarActivity.this,
                                            "我被点击了", Toast.LENGTH_SHORT).show()
                            )
                            .addCallback(new BaseTransientBottomBar.BaseCallback<Snackbar>() {
                                @Override
                                public void onDismissed(Snackbar transientBottomBar, int event) {
                                    super.onDismissed(transientBottomBar, event);
                                    Log.e(getTAG(), "消失后的会掉");
                                }
                            })
                            .show();
                }
        );
    }
}
