package com.eric.basic.base.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.StringRes;
import android.text.TextUtils;
import android.widget.Toast;

/**
 * Author: Eric
 * Date: Created in 2019/2/14 1:24 PM
 * Description： 显示toast的工具类
 */
public class ToastUtils {
    private static Toast toast;

    /**
     * 显示Toast
     * @param context 上下文
     * @param content 要显示的内容
     */
    @SuppressLint("ShowToast")
    public static void showToast(Context context, String content) {
        if (TextUtils.isEmpty(content)) return;
        if (toast == null) {
            toast = Toast.makeText(context, content, Toast.LENGTH_SHORT);
        } else {
            toast.setText(content);
        }
        toast.show();
    }

    /**
     * 显示Toast
     * @param context 上下文
     * @param content 要显示的内容
     * @param duration 显示时间长度
     */
    @SuppressLint("ShowToast")
    public static void showToast(Context context, String content, int duration) {
        if (TextUtils.isEmpty(content)) return;
        if (toast == null) {
            toast = Toast.makeText(context, content, duration);
        } else {
            toast.setText(content);
        }
        toast.show();
    }

    /**
     * 显示Toast
     * @param context 上下文
     * @param resId 要显示的资源id
     */
    public static void showToast(Context context, @StringRes int resId) {
        showToast(context, context.getResources().getString(resId));
    }
}
