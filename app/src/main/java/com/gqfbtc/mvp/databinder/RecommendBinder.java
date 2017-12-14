package com.gqfbtc.mvp.databinder;

import com.fivefivelike.mybaselibrary.base.BaseDataBind;
import com.fivefivelike.mybaselibrary.http.HttpRequest;
import com.fivefivelike.mybaselibrary.http.RequestCallback;
import com.gqfbtc.http.HttpUrl;
import com.gqfbtc.mvp.delegate.RecommendDelegate;

import io.reactivex.disposables.Disposable;

/**
 * Created by 郭青枫 on 2017/11/16.
 */

public class RecommendBinder extends BaseDataBind<RecommendDelegate> {

    public RecommendBinder(RecommendDelegate viewDelegate) {
        super(viewDelegate);
    }

    public Disposable getSharePanelInfo(
            boolean isShow,
            RequestCallback requestCallback) {
        getBaseMapWithUid();
        return new HttpRequest.Builder()
                .setRequestCode(0x123)
                .setRequestUrl(HttpUrl.getIntance().getSharePanelInfo)
                .setShowDialog(isShow)
                .setDialog(viewDelegate.getNetConnectDialog())
                .setRequestName("获取分享的页面数据")
                .setRequestMode(HttpRequest.RequestMode.GET)
                .setParameterMode(HttpRequest.ParameterMode.KeyValue)
                .setRequestObj(baseMap)
                .setRequestCallback(requestCallback)
                .build()
                .RxSendRequest();
    }


}