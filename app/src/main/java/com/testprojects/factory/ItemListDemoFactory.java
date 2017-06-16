package com.testprojects.factory;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.Button;

import com.testprojects.MainActivity;
import com.testprojects.R;
import com.testprojects.activity.LoginActivity;
import com.testprojects.activity.ScrollingActivity;
import com.testprojects.activity.SplashActivity;
import com.testprojects.activity.YaoYiYaoActivity;
import com.testprojects.net.bean.ItemListDemoBean;
import com.testprojects.utils.IntentUtil;

import me.xiaopan.assemblyadapter.AssemblyRecyclerItem;
import me.xiaopan.assemblyadapter.AssemblyRecyclerItemFactory;

/**
 * Created by she on 2017/6/16.
 * listDem的 Item
 */

public class ItemListDemoFactory extends AssemblyRecyclerItemFactory<ItemListDemoFactory.ListItem> {
    @Override
    public boolean isTarget(Object o) {
        return o instanceof ItemListDemoBean;
    }

    @Override
    public ListItem createAssemblyItem(ViewGroup viewGroup) {
        return new ListItem(R.layout.item_list_demo, viewGroup);
    }

    public class ListItem extends AssemblyRecyclerItem<ItemListDemoBean> {

        Context mContext;

        public ListItem(int itemLayoutId, ViewGroup parent) {
            super(itemLayoutId, parent);
        }

        private Button button;

        @Override
        protected void onFindViews() {
            button = (Button) findViewById(R.id.button);

        }

        @Override
        protected void onConfigViews(Context context) {
            mContext = context;
        }

        @Override
        protected void onSetData(int i, ItemListDemoBean itemListDemoBean) {
            if (null != itemListDemoBean) {
                button.setText(itemListDemoBean.getItemName());

                button.setOnClickListener(view -> {
                    if (i == 0) {
                        IntentUtil.launch(mContext, YaoYiYaoActivity.class);
                    } else if (i == 1) {
                        IntentUtil.launch(mContext, MainActivity.class);
                    } else if (i == 2) {
                        IntentUtil.launch(mContext, SplashActivity.class);
                    } else if (i == 3) {
                        IntentUtil.launch(mContext, ScrollingActivity.class);
                    } else if (i == 4) {
                        IntentUtil.launch(mContext, LoginActivity.class);
                    }
                });
            }
        }
    }
}
