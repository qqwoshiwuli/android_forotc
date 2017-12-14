package com.gqfbtc.mvp.databinder;

import com.fivefivelike.mybaselibrary.base.BaseDataBind;
import com.fivefivelike.mybaselibrary.http.HttpRequest;
import com.fivefivelike.mybaselibrary.http.RequestCallback;
import com.gqfbtc.http.HttpUrl;
import com.gqfbtc.mvp.delegate.AddAddressDelegate;

import io.reactivex.disposables.Disposable;

/**
 * Created by 郭青枫 on 2017/10/14.
 */

public class AddAddressBinder extends BaseDataBind<AddAddressDelegate> {
    public AddAddressBinder(AddAddressDelegate viewDelegate) {
        super(viewDelegate);
    }

    public Disposable addCoinAddress(
            String ownerIsUser,
            String collectionType,
            String collectionAddr,
            String alias,
            String code,
            RequestCallback requestCallback) {
        getBaseMapWithUid();
        viewDelegate.commitEnableView(false);
        baseMap.put("ownerIsUser", ownerIsUser);
        baseMap.put("collectionType", collectionType);
        baseMap.put("collectionAddr", collectionAddr);
        baseMap.put("alias", alias);
        return new HttpRequest.Builder()
                .setRequestCode(0x123)
                .setRequestUrl(HttpUrl.getIntance().addCoinAddress + "/" + code)
                .setShowDialog(true)
                .setDialog(viewDelegate.getNetConnectDialog())
                .setRequestName("添加地址")
                .setRequestMode(HttpRequest.RequestMode.POST)
                .setParameterMode(HttpRequest.ParameterMode.Json)
                .setRequestObj(baseMap)
                .setRequestCallback(requestCallback)
                .build()
                .RxSendRequest();

    }


    public Disposable checkCoinAddress(
            String ownerIsUser,
            String collectionType,
            String collectionAddr,
            String alias,
            RequestCallback requestCallback) {
        getBaseMapWithUid();
        viewDelegate.commitEnableView(false);
        baseMap.put("ownerIsUser", ownerIsUser);
        baseMap.put("collectionType", collectionType);
        baseMap.put("collectionAddr", collectionAddr);
        baseMap.put("alias", alias);
        return new HttpRequest.Builder()
                .setRequestCode(0x124)
                .setRequestUrl(HttpUrl.getIntance().checkCoinAddress)
                .setShowDialog(true)
                .setDialog(viewDelegate.getNetConnectDialog())
                .setRequestName("地址校验")
                .setRequestMode(HttpRequest.RequestMode.POST)
                .setParameterMode(HttpRequest.ParameterMode.Json)
                .setRequestObj(baseMap)
                .setRequestCallback(requestCallback)
                .build()
                .RxSendRequest();

    }

    // 1.yinhang 2.zhifubao 3btc
    public Disposable ic_addIntermediaryBeneBankInfo(
            String collectionAddr,
            String alias,
            String code,
            RequestCallback requestCallback) {
        getBaseMapWithUid();
        viewDelegate.commitEnableView(false);
        baseMap.put("collectionType", "3");
        baseMap.put("collectionAddr", collectionAddr);
        baseMap.put("alias", alias);
        return new HttpRequest.Builder()
                .setRequestCode(0x123)
                .setRequestUrl(HttpUrl.getIntance().ic_addIntermediaryBeneBankInfo + "/" + code)
                .setShowDialog(true)
                .setDialog(viewDelegate.getNetConnectDialog())
                .setRequestName("中介添加收币地址")
                .setRequestMode(HttpRequest.RequestMode.POST)
                .setParameterMode(HttpRequest.ParameterMode.Json)
                .setRequestObj(baseMap)
                .setRequestCallback(requestCallback)
                .build()
                .RxSendRequest();

    }

    public Disposable ic_checkInteBankInfo(
            String collectionAddr,
            String alias,
            RequestCallback requestCallback) {
        getBaseMapWithUid();
        viewDelegate.commitEnableView(false);
        baseMap.put("collectionType", "3");
        baseMap.put("collectionAddr", collectionAddr);
        baseMap.put("alias", alias);
        return new HttpRequest.Builder()
                .setRequestCode(0x124)
                .setRequestUrl(HttpUrl.getIntance().ic_checkInteBankInfo)
                .setShowDialog(true)
                .setDialog(viewDelegate.getNetConnectDialog())
                .setRequestName("中介校验收币地址")
                .setRequestMode(HttpRequest.RequestMode.POST)
                .setParameterMode(HttpRequest.ParameterMode.Json)
                .setRequestObj(baseMap)
                .setRequestCallback(requestCallback)
                .build()
                .RxSendRequest();

    }
}