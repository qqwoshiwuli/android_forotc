package com.gqfbtc.server;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;

import com.gqfbtc.R;
import com.gqfbtc.entity.event.CustomerServiceEvent;
import com.gqfbtc.entity.event.TransactEvent;
import com.gqfbtc.mvp.activity.transact.WaitTransactActivity;
import com.gqfbtc.mvp.activity.user.ConversationActivity;

/**
 * Created by 郭青枫 on 2017/11/18.
 */

public class NotificationHelper {

    private static class helper {
        private static NotificationHelper notificationHelper = new NotificationHelper();
    }

    public static NotificationHelper getInstence() {
        return helper.notificationHelper;
    }

    //客服消息通知
    public void sentCoustomerServiceNotification(Context context, CustomerServiceEvent event, int iconId) {
        Intent intent = new Intent(context, ConversationActivity.class);
        createNotification(context, intent, "收到一条订单消息", event.getTitle(), event.getMsg(), iconId, true);
    }

    //订单群组消息通知
    public void sendGroupMsgNotification(Context context, TransactEvent transactMessage, String title, String content, int iconId) {
        // 获取通知服务对象NotificationManager
        Intent intent = new Intent(context, WaitTransactActivity.class);
        intent.putExtra("id", transactMessage.getId()); // 携带内容
        createNotification(context, intent, "收到一条客服消息", title, content, iconId, true);
    }

    //系统通知
    public void systemNotification(Context context, String title, String content) {
        createNotification(context, null, "收到一条FOROTC消息", title, content, R.drawable.artboard, true);
    }

    private void createNotification(Context context, Intent intent, String ticker, String title, String msg, int iconId, boolean isAutoCancel) {
        // 获取通知服务对象NotificationManager
        NotificationManager notiManager = (NotificationManager)
                context.getSystemService(context.NOTIFICATION_SERVICE);
        PendingIntent pendingIntent = PendingIntent.getActivity(context,
                0x123,   //请求码
                intent, //意图对象
                PendingIntent.FLAG_CANCEL_CURRENT);
        // 创建Notification对象
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context);
        builder.setTicker(ticker);            // 通知弹出时状态栏的提示文本
        builder.setContentTitle(title)    // 通知标题
                .setContentText(msg)  // 通知内容
                .setSmallIcon(iconId);    // 通知小图标
        builder.setDefaults(Notification.DEFAULT_SOUND);    // 设置声音/震动等
        Notification notification = builder.build();
        // 设置通知的点击行为：自动取消/跳转等
        builder.setAutoCancel(isAutoCancel);
        if (intent != null) {
            builder.setContentIntent(pendingIntent);
        }
        // 通过NotificationManager发送通知
        notiManager.notify(1003, notification);
    }


}
