package com.testprojects.activity;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.testprojects.R;
import com.testprojects.base.BaseActivity;
import com.testprojects.factory.ItemListDemoFactory;
import com.testprojects.net.bean.ItemListDemoBean;
import com.testprojects.utils.IntentUtil;

import java.util.ArrayList;
import java.util.List;

import me.xiaopan.assemblyadapter.AssemblyRecyclerAdapter;

/**
 * Created by she on 2017/6/16.
 * demo 列表入口
 */

public class ListDemoActivity extends BaseActivity {
    @Override
    protected int getLayoutId() {
        return R.layout.activity_list_demo;
    }

    @Override
    protected void initView() {
        List<ItemListDemoBean> dataString = new ArrayList<>();
        dataString.add(new ItemListDemoBean().setItemData("摇一摇"));
        dataString.add(new ItemListDemoBean().setItemData("底部导航栏"));
        dataString.add(new ItemListDemoBean().setItemData("滑动详情页"));
        dataString.add(new ItemListDemoBean().setItemData("登录"));
        dataString.add(new ItemListDemoBean().setItemData("进度条"));
        dataString.add(new ItemListDemoBean().setItemData("通知"));

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        AssemblyRecyclerAdapter adapter = new AssemblyRecyclerAdapter(dataString);
        adapter.addItemFactory(new ItemListDemoFactory());
        recyclerView.setAdapter(adapter);
        findViewById(R.id.floatingActionButton).setOnClickListener(view -> {
                    //google 模版设置页面
                    IntentUtil.launch(ListDemoActivity.this, SettingsActivity.class);
                }
        );
    }
}
