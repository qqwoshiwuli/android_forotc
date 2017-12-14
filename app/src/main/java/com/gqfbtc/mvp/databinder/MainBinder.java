package com.gqfbtc.mvp.databinder;

import com.fivefivelike.mybaselibrary.http.HttpRequest;
import com.fivefivelike.mybaselibrary.http.RequestCallback;
import com.gqfbtc.http.HttpUrl;
import com.gqfbtc.mvp.delegate.MainDelegate;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import io.reactivex.disposables.Disposable;

/**
 * Created by 郭青枫 on 2017/10/14.
 */

public class MainBinder extends IMBinder<MainDelegate> {
    public MainBinder(MainDelegate viewDelegate) {
        super(viewDelegate);
    }

    public Disposable imtoken(
            RequestCallback requestCallback) {
        getBaseMapWithUid();
        return new HttpRequest.Builder()
                .setRequestCode(0x124)
                .setRequestUrl(HttpUrl.getIntance().imtoken)
                .setRequestName("获取imtoken")
                .setRequestObj(baseMap)
                .setRequestMode(HttpRequest.RequestMode.POST)
                .setParameterMode(HttpRequest.ParameterMode.KeyValue)
                .setRequestCallback(requestCallback)
                .build()
                .RxSendRequest();

    }

    public Disposable ic_imtoken(
            RequestCallback requestCallback) {
        getBaseMapWithUid();
        return new HttpRequest.Builder()
                .setRequestCode(0x124)
                .setRequestUrl(HttpUrl.getIntance().ic_imtoken)
                .setRequestName("获取imtoken")
                .setRequestObj(baseMap)
                .setRequestMode(HttpRequest.RequestMode.POST)
                .setParameterMode(HttpRequest.ParameterMode.KeyValue)
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
                .setRequestCode(0x125)
                .setRequestUrl(isUser ? HttpUrl.getIntance().userCenter : HttpUrl.getIntance().ic_userCenter)
                .setRequestName("个人中心")
                .setRequestMode(HttpRequest.RequestMode.GET)
                .setParameterMode(HttpRequest.ParameterMode.KeyValue)
                .setRequestObj(baseMap)
                .setRequestCallback(requestCallback)
                .build()
                .RxSendRequest();
    }

    public Disposable getAPPVersion(
            RequestCallback requestCallback
    ) {
        getBaseMap();
        return new HttpRequest.Builder()
                .setRequestCode(0x126)
                .setRequestUrl(HttpUrl.getIntance().getAPPVersion)
                .setRequestName("版本更新")
                .setRequestMode(HttpRequest.RequestMode.GET)
                .setParameterMode(HttpRequest.ParameterMode.KeyValue)
                .setRequestObj(baseMap)
                .setRequestCallback(requestCallback)
                .build()
                .RxSendRequest();
    }

    public Disposable batch(
            File path1,
            File path2,
            File path3,
            RequestCallback requestCallback) {
        getBaseMapWithUid();
        List<File> files = new ArrayList<>();
        files.add(path1);
        files.add(path2);
        files.add(path3);
        return new HttpRequest.Builder()
                .setRequestCode(0x127)
                .setRequestUrl(HttpUrl.getIntance().batch)
                .setShowDialog(false)
                .setDialog(viewDelegate.getNetConnectDialog())
                .setRequestName("实名认证")
                .setRequestMode(HttpRequest.RequestMode.POST)
                .setParameterMode(HttpRequest.ParameterMode.KeyValue)
                .setRequestObj(baseMap)
                .setFileList(files)
                .setRequestCallback(requestCallback)
                .build()
                .RxSendRequest();

    }
}