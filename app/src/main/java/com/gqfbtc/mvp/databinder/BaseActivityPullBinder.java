package com.gqfbtc.mvp.databinder;

import com.fivefivelike.mybaselibrary.base.BaseDataBind;
import com.fivefivelike.mybaselibrary.http.HttpRequest;
import com.fivefivelike.mybaselibrary.http.RequestCallback;
import com.gqfbtc.http.HttpUrl;
import com.gqfbtc.mvp.delegate.BaseActivityPullDelegate;

import io.reactivex.disposables.Disposable;


/**
 * Created by 郭青枫 on 2017/9/27.
 */

public class BaseActivityPullBinder<T extends BaseActivityPullDelegate> extends BaseDataBind<T> {
    public BaseActivityPullBinder(T viewDelegate) {
        super(viewDelegate);
    }

    /**
     * 广告页面
     */

    public Disposable getMessage(
            String adId,
            RequestCallback requestCallback) {
        getBaseMapWithUid();
        baseMap.put("adId", adId);
        return new HttpRequest.Builder()
                .setRequestCode(0x123)
                .setRequestUrl(HttpUrl.getIntance().getMessage)
                .setShowDialog(true)
                .setDialog(viewDelegate.getNetConnectDialog())
                .setRequestName("广告留言")
                .setRequestMode(HttpRequest.RequestMode.GET)
                .setParameterMode(HttpRequest.ParameterMode.KeyValue)
                .setRequestObj(baseMap)
                .setRequestCallback(requestCallback)
                .build()
                .RxSendRequest();

    }

    public Disposable getAdDetail(
            String adId,
            RequestCallback requestCallback) {
        getBaseMapWithUid();
        baseMap.put("adId", adId);
        return new HttpRequest.Builder()
                .setRequestCode(0x124)
                .setRequestUrl(HttpUrl.getIntance().getAdDetail)
                .setShowDialog(true)
                .setDialog(viewDelegate.getNetConnectDialog())
                .setRequestName("获取广告详情")
                .setRequestMode(HttpRequest.RequestMode.GET)
                .setParameterMode(HttpRequest.ParameterMode.KeyValue)
                .setRequestObj(baseMap)
                .setRequestCallback(requestCallback)
                .build()
                .RxSendRequest();

    }

    public Disposable saveMessage(
            String adId,
            String msg,
            RequestCallback requestCallback) {
        getBaseMapWithUid();
        baseMap.put("adId", adId);
        baseMap.put("msg", msg);
        return new HttpRequest.Builder()
                .setRequestCode(0x125)
                .setRequestUrl(HttpUrl.getIntance().saveMessage)
                .setShowDialog(true)
                .setDialog(viewDelegate.getNetConnectDialog())
                .setRequestName("保存留言")
                .setRequestMode(HttpRequest.RequestMode.POST)
                .setParameterMode(HttpRequest.ParameterMode.KeyValue)
                .setRequestObj(baseMap)
                .setRequestCallback(requestCallback)
                .build()
                .RxSendRequest();
    }

    public Disposable beforeSaveDeal(
            String adId,
            String dealPrice,
            String dealQuantity,
            String dealMoney,
            String intermediaryId,
            RequestCallback requestCallback) {
        getBaseMapWithUid();
        baseMap.put("adId", adId);
        baseMap.put("dealPrice", dealPrice);
        baseMap.put("dealQuantity", dealQuantity);
        baseMap.put("dealMoney", dealMoney);
        baseMap.put("intermediaryId", intermediaryId);
        return new HttpRequest.Builder()
                .setRequestCode(0x126)
                .setRequestUrl(HttpUrl.getIntance().beforeSaveDeal)
                .setShowDialog(true)
                .setDialog(viewDelegate.getNetConnectDialog())
                .setRequestName("保存订单前的校验并返回手续费")
                .setRequestMode(HttpRequest.RequestMode.POST)
                .setParameterMode(HttpRequest.ParameterMode.Json)
                .setRequestObj(baseMap)
                .setRequestCallback(requestCallback)
                .build()
                .RxSendRequest();
    }


    /**
     * 广告页面
     */

    /**
     * 个人中心
     */

    public Disposable doLoginOut(
    ) {
        getBaseMapWithUid();
        return new HttpRequest.Builder()
                .setRequestCode(0x126)
                .setRequestUrl(HttpUrl.getIntance().doLoginOut)
                .setRequestName("用户登出")
                .setRequestMode(HttpRequest.RequestMode.GET)
                .setParameterMode(HttpRequest.ParameterMode.KeyValue)
                .setRequestObj(baseMap)
                .build()
                .RxSendRequest();
    }

    public Disposable ic_doLoginOut(
    ) {
        getBaseMapWithUid();
        return new HttpRequest.Builder()
                .setRequestCode(0x126)
                .setRequestUrl(HttpUrl.getIntance().ic_doLoginOut)
                .setRequestName("中介登出")
                .setRequestMode(HttpRequest.RequestMode.GET)
                .setParameterMode(HttpRequest.ParameterMode.KeyValue)
                .setRequestObj(baseMap)
                .build()
                .RxSendRequest();
    }

    public Disposable ic_editIsOnline(
            boolean isOnline,
            String msgCode,
            RequestCallback requestCallback
    ) {
        getBaseMapWithUid();
        baseMap.put("isOnline", isOnline);
        baseMap.put("msgCode", msgCode);
        return new HttpRequest.Builder()
                .setRequestCode(0x127)
                .setRequestUrl(HttpUrl.getIntance().ic_editIsOnline)
                .setRequestName("修改中介在线状态")
                .setRequestMode(HttpRequest.RequestMode.POST)
                .setParameterMode(HttpRequest.ParameterMode.KeyValue)
                .setRequestObj(baseMap)
                .setRequestCallback(requestCallback)
                .build()
                .RxSendRequest();
    }

    public Disposable userCenter(
            boolean isUser,
            RequestCallback requestCallback
    ) {
        getBaseMapWithUid();
        return new HttpRequest.Builder()
                .setRequestCode(0x128)
                .setRequestUrl(isUser ? HttpUrl.getIntance().userCenter : HttpUrl.getIntance().ic_userCenter)
                .setRequestName("个人中心")
                .setRequestMode(HttpRequest.RequestMode.GET)
                .setParameterMode(HttpRequest.ParameterMode.KeyValue)
                .setRequestObj(baseMap)
                .setRequestCallback(requestCallback)
                .build()
                .RxSendRequest();
    }

    public Disposable checkIsUpload(
            RequestCallback requestCallback
    ) {
        getBaseMapWithUid();
        return new HttpRequest.Builder()
                .setRequestCode(0x129)
                .setRequestUrl(HttpUrl.getIntance().checkIsUpload)
                .setRequestName("检测实名认证")
                .setShowDialog(true)
                .setDialog(viewDelegate.getNetConnectDialog())
                .setRequestMode(HttpRequest.RequestMode.POST)
                .setParameterMode(HttpRequest.ParameterMode.KeyValue)
                .setRequestObj(baseMap)
                .setRequestCallback(requestCallback)
                .build()
                .RxSendRequest();
    }

    /**
     * 个人中心
     */


    /**
     * 订单列表
     */

    public Disposable orderList(
            RequestCallback requestCallback
    ) {
        getBaseMapWithUid();
        return new HttpRequest.Builder()
                .setRequestCode(0x123)
                .setRequestUrl(HttpUrl.getIntance().orderList + "/" + viewDelegate.page)
                .setRequestName("个人中心")
                .setRequestMode(HttpRequest.RequestMode.GET)
                .setParameterMode(HttpRequest.ParameterMode.KeyValue)
                .setRequestObj(baseMap)
                .setRequestCallback(requestCallback)
                .build()
                .RxSendRequest();
    }

    /**
     * 订单列表
     */
}