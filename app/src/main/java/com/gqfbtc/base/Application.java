package com.gqfbtc.base;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;

import com.biv.BigImageViewer;
import com.biv.loader.glide.GlideImageLoader;
import com.blankj.utilcode.util.Utils;
import com.fivefivelike.mybaselibrary.base.BaseAppLinsener;
import com.fivefivelike.mybaselibrary.utils.GlobleContext;
import com.fivefivelike.mybaselibrary.utils.ToastUtil;
import com.fivefivelike.mybaselibrary.utils.logger.KLog;
import com.gqfbtc.Utils.https.SSLContextUtil;
import com.gqfbtc.entity.event.CustomerServiceEvent;
import com.gqfbtc.entity.event.TransactEvent;
import com.gqfbtc.greenDaoUtils.DaoManager;
import com.gqfbtc.greenDaoUtils.SingSettingDBUtil;
import com.mob.MobSDK;
import com.yanzhenjie.nohttp.InitializationConfig;
import com.yanzhenjie.nohttp.Logger;
import com.yanzhenjie.nohttp.NoHttp;
import com.yanzhenjie.nohttp.OkHttpNetworkExecutor;
import com.yanzhenjie.nohttp.cache.DBCacheStore;
import com.yanzhenjie.nohttp.cookie.DBCookieStore;
import com.yanzhenjie.nohttp.ssl.SSLUtils;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;

import io.rong.imkit.RongIM;
import io.rong.imlib.RongIMClient;
import io.rong.imlib.model.CSCustomServiceInfo;
import io.rong.imlib.model.Conversation;
import io.rong.imlib.model.Message;
import io.rong.imlib.model.MessageContent;
import io.rong.message.TextMessage;

import static com.gqfbtc.base.AppConst.isLog;
import static com.gqfbtc.base.AppConst.isSSL;
import static com.gqfbtc.base.AppConst.rongId;


/**
 * Created by 郭青枫 on 2017/9/25.
 */

public class Application extends BaseApp implements RongIMClient.OnReceiveMessageListener, BaseAppLinsener {
    private static Application instance;

    boolean isInit = false;

    public static synchronized Application getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        //初始化工具类集合
        Utils.init(this);
        GlobleContext.init(this);
        initNohttp();
        //初始化数据库
        DaoManager.getInstance().init(this);
        BigImageViewer.initialize(GlideImageLoader.with(this));
        //融云初始化
        initRongCloud();
        //开启log日志
        KLog.init(isLog);
        //分享
        MobSDK.init(this);
    }

    @Override
    public void onTerminate() {
        // 程序终止的时候执行
        super.onTerminate();
    }


    private void initNohttp() {
        SSLContext sslContext = SSLContextUtil.getSSLContext();
        SSLSocketFactory socketFactory = sslContext.getSocketFactory();
        //修复在Android4.x系统中不支持TLSv1.1、TLSv1.2协议的问题
        socketFactory = SSLUtils.fixSSLLowerThanLollipop(socketFactory);
        InitializationConfig config = InitializationConfig.newBuilder(this)
                // 全局连接服务器超时时间，单位毫秒，默认10s。
                .connectionTimeout(30 * 1000)
                // 全局等待服务器响应超时时间，单位毫秒，默认10s。
                .readTimeout(30 * 1000)
                // 配置缓存，默认保存数据库DBCacheStore，保存到SD卡使用DiskCacheStore。
                .cacheStore(
                        // 如果不使用缓存，setEnable(false)禁用。
                        new DBCacheStore(this).setEnable(false)
                )
                // 配置Cookie，默认保存数据库DBCookieStore，开发者可以自己实现CookieStore接口。
                .cookieStore(
                        // 如果不维护cookie，setEnable(false)禁用。
                        new DBCookieStore(this).setEnable(false)
                )
                // 配置网络层，默认URLConnectionNetworkExecutor，如果想用OkHttp：OkHttpNetworkExecutor。
                .networkExecutor(new OkHttpNetworkExecutor())
                .sslSocketFactory(isSSL ? socketFactory : null)
                .retry(1) // 全局重试次数，配置后每个请求失败都会重试x次。
                .build();
        Logger.setDebug(false);
        Logger.setTag("NoHttpSample");// 打印Log的tag。
        NoHttp.initialize(config);
    }

    private void initRongCloud() {
        /**
         * OnCreate 会被多个进程重入，这段保护代码，确保只有您需要使用 RongIMClient 的进程和 Push 进程执行了 init。
         * io.rong.push 为融云 push 进程名称，不可修改。
         */
        if (getApplicationInfo().packageName.equals(getCurProcessName(getApplicationContext()))
                || "io.rong.push".equals(getCurProcessName(getApplicationContext()))
                ) {
            RongIM.init(this, rongId);
            RongIMClient.init(this, rongId);
            RongIM.getInstance().setMessageAttachedUserInfo(true);
        }

        //监听接收到的消息
        //RongIMClient.setOnReceiveMessageListener(this);
        RongIM.setOnReceiveMessageListener(this);
    }

    //客户服务
    public void startCustomerService(Activity activity) {
        if (SingSettingDBUtil.getUserLogin() != null) {
            CSCustomServiceInfo.Builder csBuilder = new CSCustomServiceInfo.Builder();
            CSCustomServiceInfo csInfo = csBuilder.nickName(SingSettingDBUtil.getUserLogin().getNickName()).build();
            RongIM.getInstance().startCustomerServiceChat(activity, "KEFU151082869574315", "客服中心", csInfo);
        } else {
            ToastUtil.show("登录后才能使用客服帮助");
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
    public boolean onReceived(Message message, int i) {
        MessageContent messageContent = message.getContent();
        if (messageContent instanceof TextMessage) {
            if (message.getConversationType() == Conversation.ConversationType.GROUP) {
                RongIM.getInstance().setCurrentUserInfo(messageContent.getUserInfo());
                org.greenrobot.eventbus.EventBus.getDefault().post(new TransactEvent(message.getTargetId(), ((TextMessage) messageContent).getContent()));
            } else if (message.getConversationType() == Conversation.ConversationType.CUSTOMER_SERVICE) {
                org.greenrobot.eventbus.EventBus.getDefault().post(new CustomerServiceEvent("FOROTC客服发来一条消息", ((TextMessage) messageContent).getContent()));
            }
        }
        return false;
    }
}
