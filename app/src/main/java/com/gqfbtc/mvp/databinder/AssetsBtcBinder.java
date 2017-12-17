package com.gqfbtc.mvp.databinder;

import com.fivefivelike.mybaselibrary.base.BaseDataBind;
import com.fivefivelike.mybaselibrary.http.HttpRequest;
import com.fivefivelike.mybaselibrary.http.RequestCallback;
import com.gqfbtc.http.HttpUrl;
import com.gqfbtc.mvp.delegate.AssetsBtcDelegate;
import com.yanzhenjie.nohttp.rest.CacheMode;

import io.reactivex.disposables.Disposable;

/**
 * Created by 郭青枫 on 2017/10/14.
 */

public class AssetsBtcBinder extends BaseDataBind<AssetsBtcDelegate> {
    public AssetsBtcBinder(AssetsBtcDelegate viewDelegate) {
        super(viewDelegate);
    }

    //http://116.62.120.179:82/idx/acc/1
    public Disposable acc(
            RequestCallback requestCallback) {
        getBaseMapWithUid();
        return new HttpRequest.Builder()
                .setRequestCode(0x123)
                .setRequestUrl(HttpUrl.getIntance().acc + "/1")
                .setRequestName("btc资产")
                .setRequestMode(HttpRequest.RequestMode.GET)
                .setParameterMode(HttpRequest.ParameterMode.KeyValue)
                .setRequestObj(baseMap)
                .setCacheMode(CacheMode.ONLY_REQUEST_NETWORK)
                .setRequestCallback(requestCallback)
                .build()
                .RxSendRequest();

    }

}