package com.gqfbtc.mvp.databinder;

import com.fivefivelike.mybaselibrary.base.BaseDataBind;
import com.fivefivelike.mybaselibrary.http.HttpRequest;
import com.fivefivelike.mybaselibrary.http.RequestCallback;
import com.gqfbtc.http.HttpUrl;
import com.gqfbtc.mvp.delegate.AddCollectionAddressDelegate;

import io.reactivex.disposables.Disposable;

/**
 * Created by 郭青枫 on 2017/10/14.
 */

public class AddCollectionAddressBinder extends BaseDataBind<AddCollectionAddressDelegate> {
    public AddCollectionAddressBinder(AddCollectionAddressDelegate viewDelegate) {
        super(viewDelegate);
    }

    public Disposable addPaymentAddress(
            String ownerIsUser,
            String collectionType,
            String collectionAddr,
            String alias,
            String bankName,
            String branchName,
            String ownerName,
            String code,
            RequestCallback requestCallback) {
        getBaseMapWithUid();
        baseMap.put("ownerIsUser", ownerIsUser);
        baseMap.put("collectionType", collectionType);
        baseMap.put("collectionAddr", collectionAddr);
        baseMap.put("alias", alias);
        baseMap.put("bankName", bankName);
        baseMap.put("branchName", branchName);
        baseMap.put("ownerName", ownerName);
        return new HttpRequest.Builder()
                .setRequestCode(0x123)
                .setRequestUrl(HttpUrl.getIntance().addPaymentAddress + "/" + code)
                .setShowDialog(true)
                .setDialog(viewDelegate.getNetConnectDialog())
                .setRequestName("添加收款地址")
                .setRequestMode(HttpRequest.RequestMode.POST)
                .setParameterMode(HttpRequest.ParameterMode.Json)
                .setRequestObj(baseMap)
                .setRequestCallback(requestCallback)
                .build()
                .RxSendRequest();

    }

    public Disposable checkPaymentAddress(
            String ownerIsUser,
            String collectionType,
            String collectionAddr,
            String alias,
            String bankName,
            String branchName,
            String ownerName,
            RequestCallback requestCallback) {
        getBaseMapWithUid();
        baseMap.put("ownerIsUser", ownerIsUser);
        baseMap.put("collectionType", collectionType);
        baseMap.put("collectionAddr", collectionAddr);
        baseMap.put("alias", alias);
        baseMap.put("bankName", bankName);
        baseMap.put("branchName", branchName);
        baseMap.put("ownerName", ownerName);
        return new HttpRequest.Builder()
                .setRequestCode(0x124)
                .setRequestUrl(HttpUrl.getIntance().checkPaymentAddress)
                .setShowDialog(true)
                .setDialog(viewDelegate.getNetConnectDialog())
                .setRequestName("校验收款地址")
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
            String ownerName,
            String code,
            RequestCallback requestCallback) {
        getBaseMapWithUid();
        baseMap.put("collectionType", "2");
        baseMap.put("collectionAddr", collectionAddr);
        baseMap.put("ownerName", ownerName);
        return new HttpRequest.Builder()
                .setRequestCode(0x123)
                .setRequestUrl(HttpUrl.getIntance().ic_addIntermediaryBeneBankInfo + "/" + code)
                .setShowDialog(true)
                .setDialog(viewDelegate.getNetConnectDialog())
                .setRequestName("中介添加支付宝地址")
                .setRequestMode(HttpRequest.RequestMode.POST)
                .setParameterMode(HttpRequest.ParameterMode.Json)
                .setRequestObj(baseMap)
                .setRequestCallback(requestCallback)
                .build()
                .RxSendRequest();
    }

    public Disposable ic_checkInteBankInfo(
            String collectionAddr,
            String ownerName,
            RequestCallback requestCallback) {
        getBaseMapWithUid();
        baseMap.put("collectionType", "2");
        baseMap.put("collectionAddr", collectionAddr);
        baseMap.put("ownerName", ownerName);
        return new HttpRequest.Builder()
                .setRequestCode(0x124)
                .setRequestUrl(HttpUrl.getIntance().ic_checkInteBankInfo)
                .setShowDialog(true)
                .setDialog(viewDelegate.getNetConnectDialog())
                .setRequestName("中介支付宝地址校验")
                .setRequestMode(HttpRequest.RequestMode.POST)
                .setParameterMode(HttpRequest.ParameterMode.Json)
                .setRequestObj(baseMap)
                .setRequestCallback(requestCallback)
                .build()
                .RxSendRequest();
    }

    public Disposable ic_addIntermediaryBeneBankInfo(
            String ownerIsUser,
            String collectionType,
            String collectionAddr,
            String alias,
            String bankName,
            String branchName,
            String ownerName,
            String code,
            RequestCallback requestCallback) {
        getBaseMapWithUid();
        baseMap.put("ownerIsUser", ownerIsUser);
        baseMap.put("collectionType", collectionType);
        baseMap.put("collectionAddr", collectionAddr);
        baseMap.put("alias", alias);
        baseMap.put("bankName", bankName);
        baseMap.put("branchName", branchName);
        baseMap.put("ownerName", ownerName);
        return new HttpRequest.Builder()
                .setRequestCode(0x123)
                .setRequestUrl(HttpUrl.getIntance().ic_addIntermediaryBeneBankInfo + "/" + code)
                .setShowDialog(true)
                .setDialog(viewDelegate.getNetConnectDialog())
                .setRequestName("中介添加银行地址")
                .setRequestMode(HttpRequest.RequestMode.POST)
                .setParameterMode(HttpRequest.ParameterMode.Json)
                .setRequestObj(baseMap)
                .setRequestCallback(requestCallback)
                .build()
                .RxSendRequest();

    }


    public Disposable ic_checkInteBankInfo(
            String ownerIsUser,
            String collectionType,
            String collectionAddr,
            String alias,
            String bankName,
            String branchName,
            String ownerName,
            RequestCallback requestCallback) {
        getBaseMapWithUid();
        baseMap.put("ownerIsUser", ownerIsUser);
        baseMap.put("collectionType", collectionType);
        baseMap.put("collectionAddr", collectionAddr);
        baseMap.put("alias", alias);
        baseMap.put("bankName", bankName);
        baseMap.put("branchName", branchName);
        baseMap.put("ownerName", ownerName);
        return new HttpRequest.Builder()
                .setRequestCode(0x124)
                .setRequestUrl(HttpUrl.getIntance().ic_checkInteBankInfo)
                .setShowDialog(true)
                .setDialog(viewDelegate.getNetConnectDialog())
                .setRequestName("中介银行地址校验")
                .setRequestMode(HttpRequest.RequestMode.POST)
                .setParameterMode(HttpRequest.ParameterMode.Json)
                .setRequestObj(baseMap)
                .setRequestCallback(requestCallback)
                .build()
                .RxSendRequest();

    }


}