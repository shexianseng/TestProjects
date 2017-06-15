package com.testprojects.utils;

import android.content.Context;

/**
 * Created by she on 2017/6/15.
 * 用户信息工具类
 */

public class UserInfoUtil {
    public static boolean isLogin(Context context) {
        return SharedUtil.getBoolean(context, "isLogin", false);
    }

}
