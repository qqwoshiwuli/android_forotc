package com.fivefivelike.mybaselibrary.utils;

import android.annotation.SuppressLint;
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
        if (context == null) {
            context = GlobleContext.getInstance().getApplicationContext();
        }
        toastShow(message);
    }

    @SuppressLint("ShowToast")
    private static void toastShow(String toastString) {
        if (context == null) {
            return;
        }
        if (TextUtils.isEmpty(toastString)) {
            toastString = "";
        }
        if (toast == null) {
            toast = Toast.makeText(context, toastString, Toast.LENGTH_SHORT);
            // toast.setGravity(Gravity.BOTTOM, 0, 0);
        } else {
            toast.setText(toastString);
        }
        toast.show();

    }

}
