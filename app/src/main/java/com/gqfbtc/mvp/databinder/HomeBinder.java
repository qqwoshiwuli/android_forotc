package com.gqfbtc.mvp.databinder;

import com.fivefivelike.mybaselibrary.base.BaseDataBind;
import com.fivefivelike.mybaselibrary.http.HttpRequest;
import com.fivefivelike.mybaselibrary.http.RequestCallback;
import com.gqfbtc.http.HttpUrl;
import com.gqfbtc.mvp.delegate.HomeDelegate;

import io.reactivex.disposables.Disposable;

/**
 * Created by 郭青枫 on 2017/10/14.
 */

public class HomeBinder extends BaseDataBind<HomeDelegate> {
    public HomeBinder(HomeDelegate viewDelegate) {
        super(viewDelegate);
    }

    public Disposable ad(
            String type,
            RequestCallback requestCallback) {
        getBaseMap();
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("/" + type);
        stringBuffer.append("/" + "list");
        stringBuffer.append("/" + viewDelegate.page);
        return new HttpRequest.Builder()
                .setRequestCode(0x124)
                .setRequestUrl(HttpUrl.getIntance().ad + stringBuffer.toString())
                .setRequestName("首页btc广告")
                .setRequestMode(HttpRequest.RequestMode.GET)
                .setParameterMode(HttpRequest.ParameterMode.Rest)
                .setRequestObj(baseMap)
                .setRequestCallback(requestCallback)
                .build()
                .RxSendRequest();

    }

    public Disposable adUcx(
            String type,
            RequestCallback requestCallback) {
        getBaseMap();
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("/" + type);
        stringBuffer.append("/" + "list");
        stringBuffer.append("/" + viewDelegate.page);
        return new HttpRequest.Builder()
                .setRequestCode(0x124)
                .setRequestUrl(HttpUrl.getIntance().adUcx + stringBuffer.toString())
                .setRequestName("首页ucx广告")
                .setRequestMode(HttpRequest.RequestMode.GET)
                .setParameterMode(HttpRequest.ParameterMode.Rest)
                .setRequestObj(baseMap)
                .setRequestCallback(requestCallback)
                .build()
                .RxSendRequest();

    }

    public Disposable listCarouselFigure(
            RequestCallback requestCallback) {
        getBaseMap();
        return new HttpRequest.Builder()
                .setRequestCode(0x125)
                .setRequestUrl(HttpUrl.getIntance().listCarouselFigure)
                .setRequestName("首页轮播")
                .setRequestMode(HttpRequest.RequestMode.GET)
                .setParameterMode(HttpRequest.ParameterMode.KeyValue)
                .setRequestObj(baseMap)
                .setRequestCallback(requestCallback)
                .build()
                .RxSendRequest();

    }
}