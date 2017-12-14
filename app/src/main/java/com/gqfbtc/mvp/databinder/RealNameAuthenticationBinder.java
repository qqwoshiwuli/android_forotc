package com.gqfbtc.mvp.databinder;

import com.fivefivelike.mybaselibrary.base.BaseDataBind;
import com.fivefivelike.mybaselibrary.http.HttpRequest;
import com.fivefivelike.mybaselibrary.http.RequestCallback;
import com.gqfbtc.http.HttpUrl;
import com.gqfbtc.mvp.delegate.RealNameAuthenticationDelegate;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import io.reactivex.disposables.Disposable;

/**
 * Created by 郭青枫 on 2017/10/14.
 */

public class RealNameAuthenticationBinder extends BaseDataBind<RealNameAuthenticationDelegate> {
    public RealNameAuthenticationBinder(RealNameAuthenticationDelegate viewDelegate) {
        super(viewDelegate);
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
                .setRequestCode(0x123)
                .setRequestUrl(HttpUrl.getIntance().batch)
                .setShowDialog(true)
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