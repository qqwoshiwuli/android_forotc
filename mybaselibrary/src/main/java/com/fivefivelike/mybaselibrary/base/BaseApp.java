package com.fivefivelike.mybaselibrary.base;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.support.multidex.MultiDexApplication;

/**
 * @创建者 CSDN_LQR
 * @描述 Application基类
 */
public abstract class BaseApp extends MultiDexApplication {
    protected static BaseApp instance;

    //以下属性应用于整个应用程序，合理利用资源，减少资源浪费
    public static synchronized BaseApp getInstance() {
        return instance;
    }

    public abstract void startCustomerService(Activity activity);

    //获取登录页面class
    public abstract Class getLoginActivityClass();

    public static String getCurProcessName(Context context) {
        int pid = android.os.Process.myPid();
        ActivityManager activityManager = (ActivityManager) context
                .getSystemService(Context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningAppProcessInfo appProcess : activityManager
                .getRunningAppProcesses()) {
            if (appProcess.pid == pid) {
                return appProcess.processName;
            }
        }
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        //对全局属性赋值
        if (getApplicationInfo().packageName.equals(getCurProcessName(getApplicationContext()))) {
            instance = this;
        }
    }

    /**
     * OnLowMemory是Android提供的API，在系统内存不足，
     * 所有后台程序（优先级为background的进程，不是指后台运行的进程）都被杀死时，系统会调用OnLowMemory
     */
    @Override
    public void onLowMemory() {
        super.onLowMemory();
        //垃圾回收
        System.gc();
    }
}
