package com.testprojects.net.bean;

import me.xiaopan.assemblyadapter.AssemblyItem;

/**
 * Created by she on 2017/6/16.
 * listDemo item 数据
 */

public class ItemListDemoBean {

    private String itemName = "";

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public ItemListDemoBean setItemData(String data) {
        setItemName(data);
        return this;
    }
}
