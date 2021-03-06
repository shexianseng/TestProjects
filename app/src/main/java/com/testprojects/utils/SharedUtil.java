package com.testprojects.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by she on 2017/6/15.
 * SharedPreferences    存储一些简单参数状态
 */

public class SharedUtil {

    public static final String PREF_NAME = "com.testProjects.sp";

    public static SharedPreferences getSharedPreferences(Context context) {
        return getSharedPreferences(context, PREF_NAME);
    }

    public static SharedPreferences getSharedPreferences(Context context, String prefName) {
        return context.getSharedPreferences(prefName, Context.MODE_PRIVATE);
    }

    //--------------------------Int
    public static void putInt(Context context, String key, int value) {
        getSharedPreferences(context).edit().putInt(key, value).apply();
    }

    public static int getInt(Context context, String key, int defValue) {
        return getSharedPreferences(context).getInt(key, defValue);
    }

    public static int getInt(Context context, String key) {
        return getInt(context, key, 0);
    }

    //--------------------------Float
    public static void putFloat(Context context, String key, float value) {
        getSharedPreferences(context).edit().putFloat(key, value).apply();
    }

    public static float getFloat(Context context, String key, float defValue) {
        return getSharedPreferences(context).getFloat(key, defValue);
    }

    public static float getFloat(Context context, String key) {
        return getFloat(context, key, 0);
    }

    //--------------------------Long
    public static void putLong(Context context, String key, long value) {
        getSharedPreferences(context).edit().putLong(key, value).apply();
    }

    public static long getLong(Context context, String key, long defValue) {
        return getSharedPreferences(context).getLong(key, defValue);
    }

    public static long getLong(Context context, String key) {
        return getLong(context, key, 0);
    }

    //--------------------------Boolean
    public static void putBoolean(Context context, String key, boolean value) {
        getSharedPreferences(context).edit().putBoolean(key, value).apply();
    }

    public static boolean getBoolean(Context context, String key, boolean defValue) {
        return getSharedPreferences(context).getBoolean(key, defValue);
    }

    public static boolean getBoolean(Context context, String key) {
        return getBoolean(context, key, false);
    }

    //--------------------------String
    public static void putString(Context context, String key, String value) {
        getSharedPreferences(context).edit().putString(key, value).apply();
    }

    public static String getString(Context context, String key, String defValue) {
        return getSharedPreferences(context).getString(key, defValue);
    }

    public static String getString(Context context, String key) {
        return getString(context, key, null);
    }

    /**
     * 自增(默认自增1)
     *
     * @param context
     * @param key
     */
    public static void increase(Context context, String key) {
        increase(context, key, 1);
    }

    /**
     * 自增
     *
     * @param context
     * @param key
     * @param deltaValue 增量值(基数)
     */
    public static void increase(Context context, String key, int deltaValue) {
        getSharedPreferences(context).edit().putInt(key, getInt(context, key) + deltaValue).apply();
    }
}
