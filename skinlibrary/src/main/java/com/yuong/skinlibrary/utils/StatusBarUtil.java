package com.yuong.skinlibrary.utils;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.res.TypedArray;
import android.os.Build;

public class StatusBarUtil {
    public static void forStatusBar(Activity activity) {
        TypedArray a = activity.getTheme().obtainStyledAttributes(0, new int[] {
                android.R.attr.statusBarColor
        });
        int color = a.getColor(0, 0);
        activity.getWindow().setStatusBarColor(color);
        a.recycle();
    }

    public static void forStatusBar(Activity activity, int skinColor) {
        activity.getWindow().setStatusBarColor(skinColor);
    }
}
