package com.testprojects.utils;

import android.content.Context;
import android.content.Intent;

/**
 * Created by she on 2017/6/15.
 * 跳转类
 */

public class IntentUtil {
    public static void launch(Context context, Class clazz) {
        Intent intent = new Intent(context, clazz);
        context.startActivity(intent);
    }
}
