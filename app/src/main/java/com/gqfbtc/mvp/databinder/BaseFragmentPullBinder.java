package com.gqfbtc.mvp.databinder;


import com.fivefivelike.mybaselibrary.base.BaseDataBind;
import com.fivefivelike.mybaselibrary.http.HttpRequest;
import com.fivefivelike.mybaselibrary.http.RequestCallback;
import com.gqfbtc.http.HttpUrl;
import com.gqfbtc.mvp.delegate.BaseFragentPullDelegate;

import io.reactivex.disposables.Disposable;

/**
 * Created by 郭青枫 on 2017/9/27.
 */

public class BaseFragmentPullBinder extends BaseDataBind<BaseFragentPullDelegate> {
    public BaseFragmentPullBinder(BaseFragentPullDelegate viewDelegate) {
        super(viewDelegate);
    }

    public Disposable getPaymentAddressList(
            RequestCallback requestCallback) {
        getBaseMapWithUid();

        return new HttpRequest.Builder()
                .setRequestCode(0x123)
                .setRequestUrl(HttpUrl.getIntance().getPaymentAddressList)
                .setRequestName("获得收款地址列表")
                .setRequestObj(baseMap)
                .setRequestCallback(requestCallback)
                .setRequestMode(HttpRequest.RequestMode.GET)
                .setParameterMode(HttpRequest.ParameterMode.KeyValue)
                .build()
                .RxSendRequest();

    }


    /**
     * 资产界面
     */

    public Disposable withdraw(
            RequestCallback requestCallback) {
        getBaseMapWithUid();
        return new HttpRequest.Builder()
                .setRequestCode(0x124)
                .setRequestUrl(HttpUrl.getIntance().withdraw + "/" + viewDelegate.page)
                .setRequestName("提现记录")
                .setRequestMode(HttpRequest.RequestMode.GET)
                .setParameterMode(HttpRequest.ParameterMode.KeyValue)
                .setRequestObj(baseMap)
                .setRequestCallback(requestCallback)
                .build()
                .RxSendRequest();

    }

    public Disposable recharge(
            RequestCallback requestCallback) {
        getBaseMapWithUid();
        return new HttpRequest.Builder()
                .setRequestCode(0x123)
                .setRequestUrl(HttpUrl.getIntance().recharge + "/" + viewDelegate.page)
                .setRequestName("充值记录")
                .setRequestMode(HttpRequest.RequestMode.GET)
                .setParameterMode(HttpRequest.ParameterMode.KeyValue)
                .setRequestObj(baseMap)
                .setRequestCallback(requestCallback)
                .build()
                .RxSendRequest();

    }


    public Disposable deposit_add(
            String privateBTCAddr,
            String quantity,
            String withdrawFee,
            RequestCallback requestCallback) {

        getBaseMapWithUid();
        baseMap.put("privateBTCAddr", privateBTCAddr);
        baseMap.put("quantity", quantity);
        baseMap.put("withdrawFee", withdrawFee);
        return new HttpRequest.Builder()
                .setRequestCode(0x125)
                .setRequestUrl(HttpUrl.getIntance().deposit_add)
                .setRequestName("提现")
                .setRequestMode(HttpRequest.RequestMode.POST)
                .setParameterMode(HttpRequest.ParameterMode.Json)
                .setRequestObj(baseMap)
                .setRequestCallback(requestCallback)
                .build()
                .RxSendRequest();

    }

    public Disposable getCoinAddressList(
            String conllectionType,
            RequestCallback requestCallback) {
        getBaseMapWithUid();
        baseMap.put("conllectionType", conllectionType);
        return new HttpRequest.Builder()
                .setRequestCode(0x123)
                .setRequestUrl(HttpUrl.getIntance().getCoinAddressList)
                .setRequestName("获得收币地址列表")
                .setRequestMode(HttpRequest.RequestMode.GET)
                .setParameterMode(HttpRequest.ParameterMode.KeyValue)
                .setRequestObj(baseMap)
                .setRequestCallback(requestCallback)
                .build()
                .RxSendRequest();

    }


    public Disposable ic_getIntermediaryBeneBankInfo(
            String type,
            RequestCallback requestCallback) {
        getBaseMapWithUid();
        baseMap.put("type", type);
        return new HttpRequest.Builder()
                .setRequestCode(0x123)
                .setRequestUrl(HttpUrl.getIntance().ic_getIntermediaryBeneBankInfo)
                .setRequestName("获取中介个人地址")
                .setRequestMode(HttpRequest.RequestMode.GET)
                .setParameterMode(HttpRequest.ParameterMode.KeyValue)
                .setRequestObj(baseMap)
                .setRequestCallback(requestCallback)
                .build()
                .RxSendRequest();

    }

    /**
     * 资产界面
     */

    public Disposable ic_dealIntermediaryBeneBankInfo(
            String id,
            String code,
            RequestCallback requestCallback) {
        getBaseMapWithUid();
        baseMap.put("id", id);
        return new HttpRequest.Builder()
                .setRequestCode(0x124)
                .setRequestUrl(HttpUrl.getIntance().ic_dealIntermediaryBeneBankInfo + "/" + code)
                .setRequestName("中介个人地址删除")
                .setRequestMode(HttpRequest.RequestMode.POST)
                .setParameterMode(HttpRequest.ParameterMode.KeyValue)
                .setRequestObj(baseMap)
                .setRequestCallback(requestCallback)
                .build()
                .RxSendRequest();

    }

    public Disposable delUserAddress(
            String id,
            String code,
            RequestCallback requestCallback) {
        getBaseMapWithUid();
        baseMap.put("id", id);
        return new HttpRequest.Builder()
                .setRequestCode(0x124)
                .setRequestUrl(HttpUrl.getIntance().delUserAddress + "/" + code)
                .setRequestName("用户地址删除")
                .setRequestMode(HttpRequest.RequestMode.POST)
                .setParameterMode(HttpRequest.ParameterMode.KeyValue)
                .setRequestObj(baseMap)
                .setRequestCallback(requestCallback)
                .build()
                .RxSendRequest();

    }

    /**
     * 我的广告
     */
    public Disposable ads(
            String type,
            RequestCallback requestCallback) {
        getBaseMapWithUid();
        return new HttpRequest.Builder()
                .setRequestCode(0x123)
                .setRequestUrl(HttpUrl.getIntance().ads + "/" + type)
                .setRequestName("我的广告")
                .setRequestMode(HttpRequest.RequestMode.GET)
                .setParameterMode(HttpRequest.ParameterMode.KeyValue)
                .setRequestObj(baseMap)
                .setRequestCallback(requestCallback)
                .build()
                .RxSendRequest();

    }
    public Disposable adsucx(
            String type,
            RequestCallback requestCallback) {
        getBaseMapWithUid();
        return new HttpRequest.Builder()
                .setRequestCode(0x123)
                .setRequestUrl(HttpUrl.getIntance().adsucx + "/" + type)
                .setRequestName("ucx我的广告")
                .setRequestMode(HttpRequest.RequestMode.GET)
                .setParameterMode(HttpRequest.ParameterMode.KeyValue)
                .setRequestObj(baseMap)
                .setRequestCallback(requestCallback)
                .build()
                .RxSendRequest();

    }

    public Disposable down(
            String id,
            RequestCallback requestCallback) {
        getBaseMapWithUid();
        return new HttpRequest.Builder()
                .setRequestCode(0x124)
                .setRequestUrl(HttpUrl.getIntance().down + "/" + id)
                .setRequestName("广告下架")
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
                .setRequestCode(0x124)
                .setRequestUrl(HttpUrl.getIntance().dealwkc_dealCancle )
                .setRequestName("ucx广告下架")
                .setRequestMode(HttpRequest.RequestMode.POST)
                .setParameterMode(HttpRequest.ParameterMode.Json)
                .setRequestObj(baseMap)
                .setRequestCallback(requestCallback)
                .build()
                .RxSendRequest();

    }
    public Disposable up(
            String id,
            RequestCallback requestCallback) {
        getBaseMapWithUid();
        return new HttpRequest.Builder()
                .setRequestCode(0x124)
                .setRequestUrl(HttpUrl.getIntance().up + "/" + id)
                .setRequestName("广告上架")
                .setRequestMode(HttpRequest.RequestMode.POST)
                .setParameterMode(HttpRequest.ParameterMode.KeyValue)
                .setRequestObj(baseMap)
                .setRequestCallback(requestCallback)
                .build()
                .RxSendRequest();

    }

    public Disposable changetime(
            String id,
            String start,
            String end,
            RequestCallback requestCallback) {
        getBaseMapWithUid();
        baseMap.put("id", id);
        baseMap.put("start", start);
        baseMap.put("end", end);

        return new HttpRequest.Builder()
                .setRequestCode(0x125)
                .setRequestUrl(HttpUrl.getIntance().changetime)
                .setRequestName("广告修改时间")
                .setShowDialog(true)
                .setDialog(viewDelegate.getNetConnectDialog())
                .setRequestMode(HttpRequest.RequestMode.POST)
                .setParameterMode(HttpRequest.ParameterMode.Json)
                .setRequestObj(baseMap)
                .setRequestCallback(requestCallback)
                .build()
                .RxSendRequest();
    }

    /**
     * 我的广告
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
                .setRequestName("btc订单列表")
                .setRequestMode(HttpRequest.RequestMode.GET)
                .setParameterMode(HttpRequest.ParameterMode.KeyValue)
                .setRequestObj(baseMap)
                .setRequestCallback(requestCallback)
                .build()
                .RxSendRequest();
    }

    public Disposable dealwkcList(
            RequestCallback requestCallback
    ) {
        getBaseMapWithUid();
        return new HttpRequest.Builder()
                .setRequestCode(0x123)
                .setRequestUrl(HttpUrl.getIntance().dealwkcList + "/" + viewDelegate.page)
                .setRequestName("ucx订单列表")
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