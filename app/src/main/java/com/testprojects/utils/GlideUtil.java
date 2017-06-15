package com.testprojects.utils;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

/**
 * Created by she on 2017/6/15.
 * 图片加载 Glide
 */

public class GlideUtil {
    public static void HttpImage(Context context, ImageView view, String httpUrl) {
        Glide.with(context).load(httpUrl).into(view);
    }
}