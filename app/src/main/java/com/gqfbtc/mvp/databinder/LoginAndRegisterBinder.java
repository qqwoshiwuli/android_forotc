package com.gqfbtc.mvp.databinder;

import com.fivefivelike.mybaselibrary.base.BaseDataBind;
import com.fivefivelike.mybaselibrary.http.HttpRequest;
import com.fivefivelike.mybaselibrary.http.RequestCallback;
import com.gqfbtc.http.HttpUrl;
import com.gqfbtc.mvp.delegate.LoginAndRegisterDelegate;
import com.yanzhenjie.nohttp.rest.CacheMode;

import io.reactivex.disposables.Disposable;

/**
 * Created by 郭青枫 on 2017/10/14.
 */

public class LoginAndRegisterBinder extends BaseDataBind<LoginAndRegisterDelegate> {
    public LoginAndRegisterBinder(LoginAndRegisterDelegate viewDelegate) {
        super(viewDelegate);
    }

    public Disposable doLogin(
            String phone,
            String password,
            RequestCallback requestCallback) {
        getBaseMapWithUid();
        baseMap.put("phone", phone);
        baseMap.put("password", password);
        return new HttpRequest.Builder()
                .setRequestCode(0x123)
                .setRequestUrl(HttpUrl.getIntance().doLogin)
                .setShowDialog(true)
                .setDialog(viewDelegate.getNetConnectDialog("登录中..."))
                .setRequestName("登陆")
                .setRequestMode(HttpRequest.RequestMode.POST)
                .setParameterMode(HttpRequest.ParameterMode.KeyValue)
                .setRequestObj(baseMap)
                .setRequestCallback(requestCallback)
                .build()
                .RxSendRequest();

    }

    public Disposable imtoken(
            RequestCallback requestCallback) {
        getBaseMapWithUid();
        return new HttpRequest.Builder()
                .setRequestCode(0x124)
                .setRequestUrl(HttpUrl.getIntance().imtoken)
                .setShowDialog(true)
                .setDialog(viewDelegate.getNetConnectDialog())
                .setRequestName("获取imtoken")
                .setRequestObj(baseMap)
                .setRequestMode(HttpRequest.RequestMode.POST)
                .setParameterMode(HttpRequest.ParameterMode.KeyValue)
                .setRequestCallback(requestCallback)
                .build()
                .RxSendRequest();

    }

    public Disposable ic_doLogin(
            String email,
            String password,
            RequestCallback requestCallback) {
        getBaseMapWithUid();
        baseMap.put("email", email);
        baseMap.put("password", password);
        return new HttpRequest.Builder()
                .setRequestCode(0x123)
                .setRequestUrl(HttpUrl.getIntance().ic_doLogin)
                .setShowDialog(true)
                .setDialog(viewDelegate.getNetConnectDialog("中介登录中..."))
                .setRequestName("中介登陆")
                .setRequestMode(HttpRequest.RequestMode.POST)
                .setParameterMode(HttpRequest.ParameterMode.KeyValue)
                .setRequestObj(baseMap)
                .setRequestCallback(requestCallback)
                .build()
                .RxSendRequest();

    }

    /**
     * {
     * "code": "0000",
     * "msg": "OK",
     * "data": {
     * "id": 4,
     * "name": "",
     * "nickName": null,
     * "password": "3d24b838770ee90773804e8599e549ff",
     * "email": "504114410@qq.com",
     * "phone": "15670702651",
     * "createTime": 1510056753679,
     * "updateTime": null,
     * "avatar": null,
     * "canSendAd": true,
     * "canTrade": true,
     * "canWithdraw": true,
     * "sponsor": null
     * }
     * }
     */
    public Disposable signup(
            String phone,
            String password,
            String email,
            String msgCode,
            RequestCallback requestCallback) {
        getBaseMapWithUid();
        baseMap.put("phone", phone);
        baseMap.put("password", password);
        baseMap.put("email", email);
        baseMap.put("msgCode", msgCode);
        return new HttpRequest.Builder()
                .setRequestCode(0x123)
                .setRequestUrl(HttpUrl.getIntance().signup)
                .setShowDialog(true)
                .setDialog(viewDelegate.getNetConnectDialog("注册中..."))
                .setRequestName("注册")
                .setRequestMode(HttpRequest.RequestMode.POST)
                .setParameterMode(HttpRequest.ParameterMode.Json)
                .setRequestObj(baseMap)
                .setRequestCallback(requestCallback)
                .build()
                .RxSendRequest();

    }

    public Disposable getMsgCode(
            String phone,
            RequestCallback requestCallback) {
        getBaseMapWithUid();
        baseMap.put("phone", phone);
        return new HttpRequest.Builder()
                .setRequestCode(0x124)
                .setRequestUrl(HttpUrl.getIntance().getMsgCode)
                .setShowDialog(true)
                .setCacheMode(CacheMode.ONLY_REQUEST_NETWORK)
                .setDialog(viewDelegate.getNetConnectDialog())
                .setRequestName("验证码")
                .setRequestObj(baseMap)
                .setRequestMode(HttpRequest.RequestMode.GET)
                .setParameterMode(HttpRequest.ParameterMode.KeyValue)
                .setRequestCallback(requestCallback)
                .build()
                .RxSendRequest();

    }

    public Disposable editUserNickName(
            String userId,
            String nickName,
            RequestCallback requestCallback) {
        getBaseMapWithUid();
        baseMap.put("userId", userId);
        baseMap.put("nickName", nickName);
        return new HttpRequest.Builder()
                .setRequestCode(0x123)
                .setRequestUrl(HttpUrl.getIntance().editUserNickName)
                .setShowDialog(true)
                .setDialog(viewDelegate.getNetConnectDialog())
                .setRequestName("设置用户昵称")
                .setRequestObj(baseMap)
                .setRequestMode(HttpRequest.RequestMode.POST)
                .setParameterMode(HttpRequest.ParameterMode.KeyValue)
                .setRequestCallback(requestCallback)
                .build()
                .RxSendRequest();

    }

    public Disposable doFindUser(
            String phone,
            String msgCode,
            RequestCallback requestCallback) {
        getBaseMapWithUid();
        baseMap.put("phone", phone);
        baseMap.put("msgCode", msgCode);
        return new HttpRequest.Builder()
                .setRequestCode(0x125)
                .setRequestUrl(HttpUrl.getIntance().doFindUser)
                .setShowDialog(true)
                .setDialog(viewDelegate.getNetConnectDialog())
                .setRequestName("找回密码验证")
                .setRequestMode(HttpRequest.RequestMode.GET)
                .setParameterMode(HttpRequest.ParameterMode.KeyValue)
                .setRequestObj(baseMap)
                .setRequestCallback(requestCallback)
                .build()
                .RxSendRequest();

    }

    public Disposable editNewPassword(
            String phone,
            String password,
            RequestCallback requestCallback) {
        getBaseMapWithUid();
        baseMap.put("phone", phone);
        baseMap.put("password", password);
        return new HttpRequest.Builder()
                .setRequestCode(0x126)
                .setRequestUrl(HttpUrl.getIntance().editNewPassword)
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

}