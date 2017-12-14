package com.gqfbtc.mvp.databinder;

import com.fivefivelike.mybaselibrary.base.BaseDataBind;
import com.fivefivelike.mybaselibrary.http.HttpRequest;
import com.fivefivelike.mybaselibrary.http.RequestCallback;
import com.gqfbtc.http.HttpUrl;
import com.gqfbtc.mvp.delegate.ChangeUserSetDelegate;

import io.reactivex.disposables.Disposable;

/**
 * Created by 郭青枫 on 2017/11/11.
 */

public class ChangeUserSetBinder extends BaseDataBind<ChangeUserSetDelegate> {
    public ChangeUserSetBinder(ChangeUserSetDelegate viewDelegate) {
        super(viewDelegate);
    }

    public Disposable editUserPassword(
            String oldPassword,
            String newPassword,
            RequestCallback requestCallback) {
        getBaseMapWithUid();
        baseMap.put("oldPassword", oldPassword);
        baseMap.put("newPassword", newPassword);
        return new HttpRequest.Builder()
                .setRequestCode(0x123)
                .setRequestUrl(HttpUrl.getIntance().editUserPassword)
                .setShowDialog(true)
                .setDialog(viewDelegate.getNetConnectDialog())
                .setRequestName("设置新密码")
                .setRequestMode(HttpRequest.RequestMode.POST)
                .setParameterMode(HttpRequest.ParameterMode.KeyValue)
                .setRequestObj(baseMap)
                .setRequestCallback(requestCallback)
                .build()
                .RxSendRequest();

    }

    public Disposable editUserEmail(
            String email,
            RequestCallback requestCallback) {
        getBaseMapWithUid();
        baseMap.put("email", email);
        return new HttpRequest.Builder()
                .setRequestCode(0x124)
                .setRequestUrl(HttpUrl.getIntance().editUserEmail)
                .setShowDialog(true)
                .setDialog(viewDelegate.getNetConnectDialog())
                .setRequestName("设置绑定邮箱")
                .setRequestMode(HttpRequest.RequestMode.POST)
                .setParameterMode(HttpRequest.ParameterMode.KeyValue)
                .setRequestObj(baseMap)
                .setRequestCallback(requestCallback)
                .build()
                .RxSendRequest();

    }
}
