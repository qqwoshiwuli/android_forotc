package com.gqfbtc.mvp.databinder;

import com.fivefivelike.mybaselibrary.base.BaseDataBind;
import com.fivefivelike.mybaselibrary.http.HttpRequest;
import com.fivefivelike.mybaselibrary.http.RequestCallback;
import com.gqfbtc.http.HttpUrl;
import com.gqfbtc.mvp.delegate.TransactAppraiseDelegate;

import io.reactivex.disposables.Disposable;

/**
 * Created by 郭青枫 on 2017/10/14.
 */

public class TransactAppraiseBinder extends BaseDataBind<TransactAppraiseDelegate> {
    public TransactAppraiseBinder(TransactAppraiseDelegate viewDelegate) {
        super(viewDelegate);
    }

    public Disposable goDealMark(
            String dealId,
            String interId,
            String dealUId,
            String adUId,
            RequestCallback requestCallback) {
        getBaseMapWithUid();
        baseMap.put("dealId", dealId);
        baseMap.put("interId", interId);
        baseMap.put("dealUId", dealUId);
        baseMap.put("adUId", adUId);
        return new HttpRequest.Builder()
                .setRequestCode(0x123)
                .setRequestUrl(HttpUrl.getIntance().goDealMark)
                .setShowDialog(true)
                .setDialog(viewDelegate.getNetConnectDialog())
                .setRequestName("评价页面数据")
                .setRequestMode(HttpRequest.RequestMode.POST)
                .setParameterMode(HttpRequest.ParameterMode.KeyValue)
                .setRequestObj(baseMap)
                .setRequestCallback(requestCallback)
                .build()
                .RxSendRequest();
    }

    public Disposable saveDealMark(
            String dealId,
            String firstId,
            String firstScore,
            String firstContent,
            String secondId,
            String secondScore,
            String secondContent,
            RequestCallback requestCallback) {
        getBaseMapWithUid();
        viewDelegate.commitEnableView(false);
        baseMap.put("dealId", dealId);
        baseMap.put("firstId", firstId);
        baseMap.put("firstScore", firstScore);
        baseMap.put("firstContent", firstContent);
        baseMap.put("secondId", secondId);
        baseMap.put("secondScore", secondScore);
        baseMap.put("secondContent", secondContent);
        return new HttpRequest.Builder()
                .setRequestCode(0x124)
                .setRequestUrl(HttpUrl.getIntance().saveDealMark)
                .setShowDialog(true)
                .setDialog(viewDelegate.getNetConnectDialog())
                .setRequestName("保存评价")
                .setRequestMode(HttpRequest.RequestMode.POST)
                .setParameterMode(HttpRequest.ParameterMode.Json)
                .setRequestObj(baseMap)
                .setRequestCallback(requestCallback)
                .build()
                .RxSendRequest();

    }


}