package com.fivefivelike.mybaselibrary.utils;

import com.blankj.utilcode.util.ToastUtils;


/**
 * Toast
 * ToastUtils 常用方式整合
 */
public class ToastUtil {

    public static void show(String message) {
        if (message.length() < 15) {
            ToastUtils.showShortSafe(message);
        } else {
            ToastUtils.showLongSafe(message);
        }
    }


}
