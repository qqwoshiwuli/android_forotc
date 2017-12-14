package com.gqfbtc.mvp.databinder;

import com.fivefivelike.mybaselibrary.base.BaseDataBind;
import com.fivefivelike.mybaselibrary.http.HttpRequest;
import com.fivefivelike.mybaselibrary.http.RequestCallback;
import com.gqfbtc.http.HttpUrl;
import com.gqfbtc.mvp.delegate.ChooseBuyBtcModeDelegate;

import io.reactivex.disposables.Disposable;

/**
 * Created by 郭青枫 on 2017/11/12.
 */

public class ChooseBuyBtcModeBinder extends BaseDataBind<ChooseBuyBtcModeDelegate> {
    public ChooseBuyBtcModeBinder(ChooseBuyBtcModeDelegate viewDelegate) {
        super(viewDelegate);
    }


    ///coinType=1&isSale=0&isSafe=1
    public Disposable beforeSaveAd(
            String coinType,
            String dealMoney,
            String isSale,
            String isSafe,
            RequestCallback requestCallback) {
        getBaseMapWithUid();
        baseMap.put("coinType", coinType);
        baseMap.put("isSale", isSale);
        baseMap.put("dealMoney", dealMoney);
        baseMap.put("isSafe", isSafe);
        viewDelegate.commitEnableView(false);
        return new HttpRequest.Builder()
                .setRequestCode(0x123)
                .setRequestUrl(HttpUrl.getIntance().beforeSaveAd)
                .setShowDialog(true)
                .setDialog(viewDelegate.getNetConnectDialog())
                .setRequestName("进入广告前验证")
                .setRequestMode(HttpRequest.RequestMode.POST)
                .setParameterMode(HttpRequest.ParameterMode.KeyValue)
                .setRequestObj(baseMap)
                .setRequestCallback(requestCallback)
                .build()
                .RxSendRequest();

    }


    //保存订单信息
    public Disposable saveDeal(
            String adId,
            String dealPrice,
            String dealQuantity,
            String intermediaryId,
            boolean dealIsSafe,
            boolean isBuy,
            String dealCollectionCoinAddr,
            String dealMoney,
            RequestCallback requestCallback) {
        getBaseMapWithUid();
        baseMap.put("adId", adId);
        baseMap.put("dealPrice", dealPrice);
        baseMap.put("dealQuantity", dealQuantity);
        baseMap.put("intermediaryId", intermediaryId);
        baseMap.put("dealIsSafe", dealIsSafe);
        baseMap.put("dealMoney", dealMoney);
        viewDelegate.commitEnableView(false);
        //        userCollectionCashAddr   安全卖时
        //        dealCollectionCoinAddr   安全买时
        baseMap.put(!isBuy ? "userCollectionCashAddr" : "dealCollectionCoinAddr", dealCollectionCoinAddr);
        return new HttpRequest.Builder()
                .setRequestCode(0x124)
                .setRequestUrl(HttpUrl.getIntance().saveDeal)
                .setShowDialog(true)
                .setDialog(viewDelegate.getNetConnectDialog())
                .setRequestName("保存订单信息")
                .setRequestMode(HttpRequest.RequestMode.POST)
                .setParameterMode(HttpRequest.ParameterMode.Json)
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
                .setRequestCode(0x125)
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

}
