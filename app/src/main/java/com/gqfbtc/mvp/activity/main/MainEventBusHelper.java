package com.gqfbtc.mvp.activity.main;

import android.view.View;

import com.circledialog.callback.ConfigText;
import com.circledialog.params.TextParams;
import com.fivefivelike.mybaselibrary.entity.ResultDialogEntity;
import com.fivefivelike.mybaselibrary.utils.ActUtil;
import com.gqfbtc.R;
import com.gqfbtc.Utils.UiHeplUtils;
import com.gqfbtc.base.Application;
import com.gqfbtc.entity.bean.AppVersion;
import com.gqfbtc.entity.bean.OrderDetails;
import com.gqfbtc.entity.event.CustomerServiceEvent;
import com.gqfbtc.entity.event.FilePathsEvent;
import com.gqfbtc.entity.event.TransactEvent;
import com.gqfbtc.greenDaoUtils.SingSettingDBUtil;
import com.gqfbtc.mvp.activity.transact.BigDealsActivity;
import com.gqfbtc.mvp.activity.transact.WaitTransactActivity;
import com.gqfbtc.mvp.activity.user.AddAddressActivity;
import com.gqfbtc.mvp.activity.user.AddCollectionAddressActivity;
import com.gqfbtc.mvp.activity.user.ConversationActivity;
import com.gqfbtc.mvp.activity.user.RealNameAuthenticationActivity;
import com.gqfbtc.mvp.databinder.MainBinder;
import com.gqfbtc.mvp.delegate.MainDelegate;
import com.gqfbtc.server.NotificationHelper;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.File;

/**
 * Created by 郭青枫 on 2017/11/24.
 */

public class MainEventBusHelper {

    MainActivity activity;
    MainDelegate viewDelegate;
    MainBinder binder;
    OrderDetails orderDetails;


    public MainEventBusHelper(MainActivity activity, MainDelegate viewDelegate, MainBinder binder) {
        EventBus.getDefault().register(this);
        this.activity = activity;
        this.viewDelegate = viewDelegate;
        this.binder = binder;
    }

    public void onDestroy() {
        EventBus.getDefault().unregister(this);
    }

    //订单消息
    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void onTransactEvent(TransactEvent event) {
        //获取当前订单聊天页面的 订单id 进行判断 如果不相等 则提示有新消息
        if (!ActUtil.getInstance().getTopActivity().getClass().equals(WaitTransactActivity.class)||!ActUtil.getInstance().getTopActivity().getClass().equals(BigDealsActivity.class)) {
            if(orderDetails!=null){
                if(orderDetails.getCode().equals(event.getCode())){
                    //在订单页面 三方发出消息时不提醒
                }else{
                    sendTransactMsg(event);
                }
            }else{
                sendTransactMsg(event);
            }
        }else{
            sendTransactMsg(event);
        }
    }
    private void sendTransactMsg(TransactEvent event){
        if (SingSettingDBUtil.isUser()) {
            viewDelegate.viewHolder.tl_2.showDot(1);
        } else {
            viewDelegate.viewHolder.tl_2.showDot(0);
        }
        if (activity.orderFragment != null) {
            activity.orderFragment.addTransactEvent(event);
        }
        NotificationHelper.getInstence().sendGroupMsgNotification(activity, event, "订单" + event.getCode() + "有新消息", event.getMsg(), R.drawable.artboard);
    }

    //订单详情页面信息
    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void onOrderDetailsEvent(OrderDetails event) {
        if(orderDetails!=null){
            if(event.isDistory()==false){
                orderDetails=event;
            }else if(event.isDistory()==true){
                if(orderDetails.getId().equals(event.getId())){
                    orderDetails=null;
                }
            }
        }
    }


    //客服消息
    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void onCustomerServiceEvent(CustomerServiceEvent event) {
        if (!ActUtil.getInstance().getTopActivity().getClass().equals(ConversationActivity.class)) {
            if (SingSettingDBUtil.isUser()) {
                viewDelegate.viewHolder.tl_2.showDot(4);
            } else {
                viewDelegate.viewHolder.tl_2.showDot(2);
            }
            activity.userFragment.setHaveDot(true);
            NotificationHelper.getInstence().sentCoustomerServiceNotification(activity, event, R.drawable.artboard);
        }
    }

    //app更新是否失败
    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void onAppVersionEvent(final AppVersion event) {
        UiHeplUtils.initDefaultDialog(activity, "下载失败，是否去官网下载", new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UiHeplUtils.startWeb(activity, event.getDownloadAddrForotc());
            }
        })
                .configText(new ConfigText() {
                    @Override
                    public void onConfig(TextParams params) {
                        params.textColor = activity.getResources().getColor(R.color.color_font2);
                    }
                }).setNegative("取消", new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (activity.appVersion.isIsNeedFresh()) {
                    ActUtil.getInstance().AppExit(activity);
                }
            }
        }).show();
    }

    //文件上传
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onFileUploadEvent(FilePathsEvent event) {
        File path1 = new File(event.getPaths().get(0));
        File path2 = new File(event.getPaths().get(1));
        File path3 = new File(event.getPaths().get(2));
        activity.addRequest(binder.batch(path1, path2, path3, activity));

    }

    //返回弹窗点击事件
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onResuleDialgoEvent(ResultDialogEntity event) {
        if ("0".equals(event.getCode())) {
            //去首页
            ActUtil.getInstance().killAllActivity(activity);
            activity.goToPage(0);
        } else if ("E200".equals(event.getCode()) || "E201".equals(event.getCode()) || "E202".equals(event.getCode()) || "E203".equals(event.getCode()) || "E204".equals(event.getCode()) || "E205".equals(event.getCode())) {
            //去充值中心
            ActUtil.getInstance().killAllActivity(activity);
            activity.goToPage(2);
        } else if ("E154".equals(event.getCode())) {
            //去实名认证
            activity.gotoActivity(RealNameAuthenticationActivity.class).startAct();
        } else if ("E155".equals(event.getCode())) {
            //添加收款地址
            activity.gotoActivity(AddCollectionAddressActivity.class)
                    .startActResult(0x123);
        } else if ("E156".equals(event.getCode())) {
            //添加收币地址
            AddAddressActivity.startAct(activity,
                    0,
                    0x123
            );
        } else if ("E601".equals(event.getCode())) {
            //去客服
            Application.getInstance().startCustomerService(activity);
        } else if ("E3001".equals(event.getCode())) {
            //广告下架,到首页
            ActUtil.getInstance().killAllActivity(activity);
            activity.goToPage(0);
        }
    }
}
