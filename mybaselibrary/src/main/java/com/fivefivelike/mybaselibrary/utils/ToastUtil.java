package com.fivefivelike.mybaselibrary.utils;

import android.content.Context;
import android.text.TextUtils;
import android.widget.Toast;


/**
 * Toast
 */
public class ToastUtil {

    private static Context context;

    static Toast toast;

    public static void show(Context contexts, String message) {
        context = contexts;
        toastShow(message);
    }

    public static void show(String message) {
        context = GlobleContext.getInstance().getApplicationContext();
        toastShow(message);
    }


    private static void toastShow(String toastString) {
        if (context == null) {
            return;
        }
        if (TextUtils.isEmpty(toastString)) {
            toastString = "";
        }
        if (toast == null) {
            if (toastString.length() < 15) {
                toast = Toast.makeText(context, toastString, Toast.LENGTH_SHORT);
            } else {
                toast = Toast.makeText(context, toastString, Toast.LENGTH_LONG);
            }
        } else {
            if (toastString.length() < 15) {
                toast.setDuration(Toast.LENGTH_SHORT);
            } else {
                toast.setDuration(Toast.LENGTH_LONG);
            }
            toast.setText(toastString);
        }
        toast.show();


    }
}
