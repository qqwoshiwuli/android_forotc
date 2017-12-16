package com.gqfbtc.mvp.databinder;

import com.fivefivelike.mybaselibrary.base.BaseDataBind;
import com.fivefivelike.mybaselibrary.http.HttpRequest;
import com.fivefivelike.mybaselibrary.http.RequestCallback;
import com.gqfbtc.http.HttpUrl;
import com.gqfbtc.mvp.delegate.PostedBigDealDelegate;

import io.reactivex.disposables.Disposable;

public class PostedBigDealBinder extends BaseDataBind<PostedBigDealDelegate> {

    public PostedBigDealBinder(PostedBigDealDelegate viewDelegate) {
        super(viewDelegate);
    }

    //大宗交易 买
    public Disposable saveAd1(
            String price,//单价
            String quantity,
            String remark,//备注
            String startTime,
            String endTime,
            String threshold,
            RequestCallback requestCallback) {
        getBaseMapWithUid();
        viewDelegate.commitEnableView(false);
        baseMap.put("currency", 3);
        baseMap.put("isSale", false);
        baseMap.put("price", price);
        baseMap.put("remark", remark);
        baseMap.put("quantity", quantity);
        baseMap.put("start", startTime);
        baseMap.put("end", endTime);
        baseMap.put("threshold", threshold);
        return new HttpRequest.Builder()
                .setRequestCode(0x123)
                .setRequestUrl(HttpUrl.getIntance().adwkc_saveAd)
                .setShowDialog(true)
                .setDialog(viewDelegate.getNetConnectDialog())
                .setRequestName("大宗交易 买")
                .setRequestMode(HttpRequest.RequestMode.POST)
                .setParameterMode(HttpRequest.ParameterMode.Json)
                .setRequestObj(baseMap)
                .setRequestCallback(requestCallback)
                .build()
                .RxSendRequest();

    }

    //大宗交易 卖
    public Disposable saveAd2(
            String price,//单价
            String quantity,
            String remark,//备注
            String startTime,
            String endTime,
            String threshold,
            String collectionCashAddr,
            RequestCallback requestCallback) {
        getBaseMapWithUid();
        viewDelegate.commitEnableView(false);
        baseMap.put("currency", 3);
        baseMap.put("isSale", true);
        baseMap.put("price", price);
        baseMap.put("remark", remark);
        baseMap.put("quantity", quantity);
        baseMap.put("start", startTime);
        baseMap.put("end", endTime);
        baseMap.put("threshold", threshold);
        baseMap.put("collectionCashAddr", collectionCashAddr);
        return new HttpRequest.Builder()
                .setRequestCode(0x123)
                .setRequestUrl(HttpUrl.getIntance().adwkc_saveAd)
                .setShowDialog(true)
                .setDialog(viewDelegate.getNetConnectDialog())
                .setRequestName("大宗交易 卖")
                .setRequestMode(HttpRequest.RequestMode.POST)
                .setParameterMode(HttpRequest.ParameterMode.Json)
                .setRequestObj(baseMap)
                .setRequestCallback(requestCallback)
                .build()
                .RxSendRequest();

    }

    //收款地址
    public Disposable getPaymentAddressList(
            RequestCallback requestCallback) {
        getBaseMapWithUid();
        return new HttpRequest.Builder()
                .setRequestCode(0x124)
                .setRequestUrl(HttpUrl.getIntance().getPaymentAddressList)
                .setRequestName("获得收款地址列表")
                .setRequestObj(baseMap)
                .setShowDialog(true)
                .setDialog(viewDelegate.getNetConnectDialog())
                .setRequestCallback(requestCallback)
                .setRequestMode(HttpRequest.RequestMode.GET)
                .setParameterMode(HttpRequest.ParameterMode.KeyValue)
                .build()
                .RxSendRequest();
    }



}