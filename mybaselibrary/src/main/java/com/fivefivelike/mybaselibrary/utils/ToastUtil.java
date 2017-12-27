package com.fivefivelike.mybaselibrary.utils;

import com.blankj.utilcode.util.ToastUtils;
import com.fivefivelike.mybaselibrary.R;


/**
 * Toast
 * ToastUtils 常用方式整合
 */
public class ToastUtil {

    public static void show(String message) {
        ToastUtils.setBgResource(R.drawable.toast_bg);
        if (message.length() < 15) {
            ToastUtils.showShort(message);
        } else {
            ToastUtils.showLong(message);
        }
    }

}
