package com.gqfbtc.mvp.databinder;

import com.fivefivelike.mybaselibrary.base.BaseDataBind;
import com.fivefivelike.mybaselibrary.http.HttpRequest;
import com.fivefivelike.mybaselibrary.http.RequestCallback;
import com.gqfbtc.http.HttpUrl;
import com.gqfbtc.mvp.delegate.WaitTransactDelegate;

import io.reactivex.disposables.Disposable;

/**
 * Created by 郭青枫 on 2017/11/14.
 */

public class WaitTransactBinder extends BaseDataBind<WaitTransactDelegate> {
    public WaitTransactBinder(WaitTransactDelegate viewDelegate) {
        super(viewDelegate);
    }


    public Disposable dealdt(
            String dealId,
            boolean isShowDialog,
            RequestCallback requestCallback) {
        getBaseMapWithUid();
        baseMap.put("dealId", dealId);
        return new HttpRequest.Builder()
                .setRequestCode(0x123)
                .setRequestUrl(HttpUrl.getIntance().dealdt)
                .setShowDialog(isShowDialog)
                .setDialog(viewDelegate.getNetConnectDialog())
                .setRequestName("订单详情")
                .setRequestMode(HttpRequest.RequestMode.POST)
                .setParameterMode(HttpRequest.ParameterMode.KeyValue)
                .setRequestObj(baseMap)
                .setRequestCallback(requestCallback)
                .build()
                .RxSendRequest();

    }

    public Disposable dealwkc_dealdt(
            String dealId,
            boolean isShowDialog,
            RequestCallback requestCallback) {
        getBaseMapWithUid();
        baseMap.put("dealId", dealId);
        return new HttpRequest.Builder()
                .setRequestCode(0x123)
                .setRequestUrl(HttpUrl.getIntance().dealwkc_dealdt)
                .setShowDialog(isShowDialog)
                .setDialog(viewDelegate.getNetConnectDialog())
                .setRequestName("ucx订单详情")
                .setRequestMode(HttpRequest.RequestMode.POST)
                .setParameterMode(HttpRequest.ParameterMode.KeyValue)
                .setRequestObj(baseMap)
                .setRequestCallback(requestCallback)
                .build()
                .RxSendRequest();

    }

    public Disposable dealwkc_dealCancle(
            String id,
            RequestCallback requestCallback) {
        getBaseMapWithUid();
        baseMap.put("id", id);
        return new HttpRequest.Builder()
                .setRequestCode(0x125)
                .setRequestUrl(HttpUrl.getIntance().dealwkc_dealCancle)
                .setRequestName("ucx广告下架")
                .setRequestMode(HttpRequest.RequestMode.POST)
                .setParameterMode(HttpRequest.ParameterMode.Json)
                .setRequestObj(baseMap)
                .setDialog(viewDelegate.getNetConnectDialog())
                .setShowDialog(true)
                .setRequestCallback(requestCallback)
                .build()
                .RxSendRequest();

    }

    public Disposable change(
            String id,
            String dealStatus,
            String userCollectionCashAddr,
            String intermediaryCollectionCashAddr,
            RequestCallback requestCallback) {
        getBaseMapWithUid();
        viewDelegate.commitEnableView(false);
        baseMap.put("id", id + "");
        baseMap.put("dealStatus", dealStatus + "");
        //用户地址
        baseMap.put("userCollectionCashAddr", "" + userCollectionCashAddr + "");
        //中介地址
        baseMap.put("intermediaryCollectionCashAddr", "" + intermediaryCollectionCashAddr + "");
        return new HttpRequest.Builder()
                .setRequestCode(0x124)
                .setRequestUrl(HttpUrl.getIntance().change)
                .setShowDialog(true)
                .setDialog(viewDelegate.getNetConnectDialog())
                .setRequestName("修改订单状态")
                .setRequestMode(HttpRequest.RequestMode.POST)
                .setParameterMode(HttpRequest.ParameterMode.Json)
                .setRequestObj(baseMap)
                .setRequestCallback(requestCallback)
                .build()
                .RxSendRequest();

    }

    public Disposable dealwkc_change(
            String id,
            String dealStatus,
            RequestCallback requestCallback) {
        getBaseMapWithUid();
        viewDelegate.commitEnableView(false);
        baseMap.put("id", id + "");
        baseMap.put("dealStatus", dealStatus + "");
        return new HttpRequest.Builder()
                .setRequestCode(0x124)
                .setRequestUrl(HttpUrl.getIntance().dealwkc_change)
                .setShowDialog(true)
                .setDialog(viewDelegate.getNetConnectDialog())
                .setRequestName("修改订单状态")
                .setRequestMode(HttpRequest.RequestMode.POST)
                .setParameterMode(HttpRequest.ParameterMode.Json)
                .setRequestObj(baseMap)
                .setRequestCallback(requestCallback)
                .build()
                .RxSendRequest();

    }
}