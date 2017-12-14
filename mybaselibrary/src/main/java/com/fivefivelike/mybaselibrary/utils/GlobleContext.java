package com.fivefivelike.mybaselibrary.utils;

import android.content.Context;

/**
 * 全局可用的context对象  单例模式
 * 在Application中初始化
 */
public class GlobleContext {
    private static GlobleContext instance;

    private Context applicationContext;

    public static GlobleContext getInstance() {
        if (instance == null) {
            throw new RuntimeException(GlobleContext.class.getSimpleName() + "has not been initialized!");
        }

        return instance;
    }

    public Context getApplicationContext() {
        return applicationContext;
    }

    public GlobleContext(Context applicationContext) {
        this.applicationContext = applicationContext;
    }

    /**
     * 全局信息 只能调用一次
     */
    public static void init(Context applicationContext) {
        if (instance != null) {
            throw new RuntimeException(GlobleContext.class.getSimpleName() + " can not be initialized multiple times!");
        }
        instance = new GlobleContext(applicationContext);
    }

    public static boolean isInitialized() {
        return (instance != null);
    }
}
