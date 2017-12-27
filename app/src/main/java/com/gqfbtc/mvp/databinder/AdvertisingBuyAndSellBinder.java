package com.gqfbtc.mvp.databinder;

import com.fivefivelike.mybaselibrary.base.BaseDataBind;
import com.fivefivelike.mybaselibrary.http.HttpRequest;
import com.fivefivelike.mybaselibrary.http.RequestCallback;
import com.gqfbtc.http.HttpUrl;
import com.gqfbtc.mvp.delegate.AdvertisingBuyAndSellDelegate;

import io.reactivex.disposables.Disposable;

/**
 * Created by 郭青枫 on 2017/10/14.
 */

public class AdvertisingBuyAndSellBinder extends BaseDataBind<AdvertisingBuyAndSellDelegate> {
    public AdvertisingBuyAndSellBinder(AdvertisingBuyAndSellDelegate viewDelegate) {
        super(viewDelegate);
    }

    //普通买(固定模式)
    public Disposable saveAd1(
            String currency,
            String price,//单价
            String quantity,//数量
            String remark,//备注
            String startTime,
            String endTime,
            String threshold,//最小购买金额(默认100)
            RequestCallback requestCallback) {
        getBaseMapWithUid();
        baseMap.put("currency", currency);
        baseMap.put("isSafe", false);
        baseMap.put("isSale", false);
        baseMap.put("isFixed", true);
        baseMap.put("price", price);
        baseMap.put("remark", remark);
        baseMap.put("quantity", quantity);
        baseMap.put("start", startTime);
        baseMap.put("end", endTime);
        baseMap.put("threshold", threshold);
        return new HttpRequest.Builder()
                .setRequestCode(0x123)
                .setRequestUrl(HttpUrl.getIntance().saveAd)
                .setShowDialog(true)
                .setDialog(viewDelegate.getNetConnectDialog())
                .setRequestName("广告")
                .setRequestMode(HttpRequest.RequestMode.POST)
                .setParameterMode(HttpRequest.ParameterMode.Json)
                .setRequestObj(baseMap)
                .setRequestCallback(requestCallback)
                .build()
                .RxSendRequest();

    }

    //普通买(溢价模式）
    public Disposable saveAd2(
            String currency,
            String floatPercent,//浮动率
            String price,//单价
            String quantity,//数量
            String remark,//备注
            String startTime,
            String endTime,
            String threshold,//最小购买金额(默认100)
            String ceil,//价格上线
            RequestCallback requestCallback) {
        getBaseMapWithUid();
        baseMap.put("currency", currency);
        baseMap.put("isSafe", false);
        baseMap.put("isSale", false);
        baseMap.put("isFixed", false);
        baseMap.put("floatPercent", floatPercent);
        baseMap.put("price", price);
        baseMap.put("quantity", quantity);
        baseMap.put("remark", remark);
        baseMap.put("start", startTime);
        baseMap.put("end", endTime);
        baseMap.put("threshold", threshold);
        baseMap.put("ceil", ceil);
        return new HttpRequest.Builder()
                .setRequestCode(0x123)
                .setRequestUrl(HttpUrl.getIntance().saveAd)
                .setShowDialog(true)
                .setDialog(viewDelegate.getNetConnectDialog())
                .setRequestName("广告")
                .setRequestMode(HttpRequest.RequestMode.POST)
                .setParameterMode(HttpRequest.ParameterMode.Json)
                .setRequestObj(baseMap)
                .setRequestCallback(requestCallback)
                .build()
                .RxSendRequest();

    }

    //安全买(固定模式)
    public Disposable saveAd3(
            String currency,
            String price,//单价
            String quantity,//数量
            String remark,//备注
            String startTime,
            String endTime,
            String threshold,//最小购买金额(默认100)
            String collectionCoinAddr,//买方收币地址(可多选)
            RequestCallback requestCallback) {
        getBaseMapWithUid();
        baseMap.put("currency", currency);
        baseMap.put("isSafe", true);
        baseMap.put("isSale", false);
        baseMap.put("isFixed", true);
        baseMap.put("price", price);
        baseMap.put("quantity", quantity);
        baseMap.put("remark", remark);
        baseMap.put("start", startTime);
        baseMap.put("end", endTime);
        baseMap.put("threshold", threshold);
        baseMap.put("collectionCoinAddr", collectionCoinAddr);
        return new HttpRequest.Builder()
                .setRequestCode(0x123)
                .setRequestUrl(HttpUrl.getIntance().saveAd)
                .setShowDialog(true)
                .setDialog(viewDelegate.getNetConnectDialog())
                .setRequestName("广告")
                .setRequestMode(HttpRequest.RequestMode.POST)
                .setParameterMode(HttpRequest.ParameterMode.Json)
                .setRequestObj(baseMap)
                .setRequestCallback(requestCallback)
                .build()
                .RxSendRequest();
    }

    //安全买(溢价模式）
    public Disposable saveAd4(
            String currency,
            String floatPercent,//浮动率
            String price,//单价
            String quantity,//数量
            String remark,//备注
            String startTime,
            String endTime,
            String threshold,//最小购买金额(默认100)
            String ceil,//价格上线
            String collectionCoinAddr,//买方收币地址(可多选)
            RequestCallback requestCallback) {
        getBaseMapWithUid();
        baseMap.put("currency", currency);
        baseMap.put("isSafe", true);
        baseMap.put("isSale", false);
        baseMap.put("isFixed", false);
        baseMap.put("floatPercent", floatPercent);
        baseMap.put("price", price);
        baseMap.put("quantity", quantity);
        baseMap.put("remark", remark);
        baseMap.put("start", startTime);
        baseMap.put("end", endTime);
        baseMap.put("threshold", threshold);
        baseMap.put("ceil", ceil);
        baseMap.put("collectionCoinAddr", collectionCoinAddr);
        return new HttpRequest.Builder()
                .setRequestCode(0x123)
                .setRequestUrl(HttpUrl.getIntance().saveAd)
                .setShowDialog(true)
                .setDialog(viewDelegate.getNetConnectDialog())
                .setRequestName("广告")
                .setRequestMode(HttpRequest.RequestMode.POST)
                .setParameterMode(HttpRequest.ParameterMode.Json)
                .setRequestObj(baseMap)
                .setRequestCallback(requestCallback)
                .build()
                .RxSendRequest();

    }

    //普通卖(固定模式)  设置默认时间
    public Disposable saveAd5(
            String currency,
            String price,//单价
            String quantity,//数量
            String remark,//备注
            String threshold,//最小购买金额(默认100)
            String collectionCashAddr,//卖方收款地址(可多选)
            RequestCallback requestCallback) {
        getBaseMapWithUid();
        baseMap.put("currency", currency);
        baseMap.put("isSafe", false);
        baseMap.put("isSale", true);
        baseMap.put("isFixed", true);
        baseMap.put("price", price);
        baseMap.put("quantity", quantity);
        baseMap.put("remark", remark);
        baseMap.put("start", "每日" + "_" + "10:00");
        baseMap.put("end", "每日" + "_" + "23:00");
        baseMap.put("threshold", threshold);
        baseMap.put("collectionCashAddr", collectionCashAddr);
        return new HttpRequest.Builder()
                .setRequestCode(0x123)
                .setRequestUrl(HttpUrl.getIntance().saveAd)
                .setShowDialog(true)
                .setDialog(viewDelegate.getNetConnectDialog())
                .setRequestName("广告")
                .setRequestMode(HttpRequest.RequestMode.POST)
                .setParameterMode(HttpRequest.ParameterMode.Json)
                .setRequestObj(baseMap)
                .setRequestCallback(requestCallback)
                .build()
                .RxSendRequest();
    }

    //普通卖(溢价模式)
    public Disposable saveAd6(
            String currency,
            String floatPercent,//浮动率
            String price,//单价
            String quantity,//数量
            String remark,//备注
            String threshold,//最小购买金额(默认100)
            String floor,//价格下限
            String collectionCashAddr,//卖方收款地址(可多选)
            RequestCallback requestCallback) {
        getBaseMapWithUid();
        baseMap.put("currency", currency);
        baseMap.put("isSafe", false);
        baseMap.put("isSale", true);
        baseMap.put("isFixed", false);
        baseMap.put("floatPercent", floatPercent);
        baseMap.put("price", price);
        baseMap.put("quantity", quantity);
        baseMap.put("remark", remark);
        baseMap.put("start", "每日_10:00");
        baseMap.put("end", "每日_23:00");
        baseMap.put("threshold", threshold);
        baseMap.put("collectionCashAddr", collectionCashAddr);
        baseMap.put("floor", floor);
        return new HttpRequest.Builder()
                .setRequestCode(0x123)
                .setRequestUrl(HttpUrl.getIntance().saveAd)
                .setShowDialog(true)
                .setDialog(viewDelegate.getNetConnectDialog())
                .setRequestName("广告")
                .setRequestMode(HttpRequest.RequestMode.POST)
                .setParameterMode(HttpRequest.ParameterMode.Json)
                .setRequestObj(baseMap)
                .setRequestCallback(requestCallback)
                .build()
                .RxSendRequest();
    }

    //安全卖(固定模式)
    public Disposable saveAd7(
            String currency,
            String price,//单价
            String quantity,//数量
            String remark,//备注
            String startTime,
            String endTime,
            String threshold,//最小购买金额(默认100)
            String collectionCashAddr,//卖方收款地址(可多选)
            RequestCallback requestCallback) {
        getBaseMapWithUid();
        baseMap.put("currency", currency);
        baseMap.put("isSafe", true);
        baseMap.put("isSale", true);
        baseMap.put("isFixed", true);
        baseMap.put("price", price);
        baseMap.put("quantity", quantity);
        baseMap.put("remark", remark);
        baseMap.put("start", startTime);
        baseMap.put("end", endTime);
        baseMap.put("threshold", threshold);
        baseMap.put("collectionCashAddr", collectionCashAddr);
        return new HttpRequest.Builder()
                .setRequestCode(0x123)
                .setRequestUrl(HttpUrl.getIntance().saveAd)
                .setShowDialog(true)
                .setDialog(viewDelegate.getNetConnectDialog())
                .setRequestName("广告")
                .setRequestMode(HttpRequest.RequestMode.POST)
                .setParameterMode(HttpRequest.ParameterMode.Json)
                .setRequestObj(baseMap)
                .setRequestCallback(requestCallback)
                .build()
                .RxSendRequest();

    }

    //安全卖(溢价模式)
    public Disposable saveAd8(
            String currency,
            String floatPercent,//浮动率
            String price,//单价
            String quantity,//数量
            String remark,//备注
            String startTime,
            String endTime,
            String threshold,//最小购买金额(默认100)
            String floor,//价格下限
            String collectionCashAddr,//卖方收款地址(可多选)
            RequestCallback requestCallback) {
        getBaseMapWithUid();
        baseMap.put("currency", currency);
        baseMap.put("isSafe", true);
        baseMap.put("isSale", true);
        baseMap.put("isFixed", false);
        baseMap.put("floatPercent", floatPercent);
        baseMap.put("price", price);
        baseMap.put("quantity", quantity);
        baseMap.put("remark", remark);
        baseMap.put("start", startTime);
        baseMap.put("end", endTime);
        baseMap.put("threshold", threshold);
        baseMap.put("floor", floor);
        baseMap.put("collectionCashAddr", collectionCashAddr);
        return new HttpRequest.Builder()
                .setRequestCode(0x123)
                .setRequestUrl(HttpUrl.getIntance().saveAd)
                .setShowDialog(true)
                .setDialog(viewDelegate.getNetConnectDialog())
                .setRequestName("广告")
                .setRequestMode(HttpRequest.RequestMode.POST)
                .setParameterMode(HttpRequest.ParameterMode.Json)
                .setRequestObj(baseMap)
                .setRequestCallback(requestCallback)
                .build()
                .RxSendRequest();

    }

    public Disposable getPaymentAddressList(
            RequestCallback requestCallback) {
        getBaseMapWithUid();
        return new HttpRequest.Builder()
                .setRequestCode(0x124)
                .setRequestUrl(HttpUrl.getIntance().getPaymentAddressList)
                .setRequestName("获得收款地址列表")
                .setRequestObj(baseMap)
                .setShowDialog(true)
                .setDialog(viewDelegate.getNetConnectDialog())
                .setRequestCallback(requestCallback)
                .setRequestMode(HttpRequest.RequestMode.GET)
                .setParameterMode(HttpRequest.ParameterMode.KeyValue)
                .build()
                .RxSendRequest();
    }

    public Disposable getCoinAddressList(
            String conllectionType,
            RequestCallback requestCallback) {
        getBaseMapWithUid();
        baseMap.put("conllectionType", conllectionType);
        return new HttpRequest.Builder()
                .setRequestCode(0x124)
                .setRequestUrl(HttpUrl.getIntance().getCoinAddressList)
                .setRequestName("获得收币地址列表")
                .setShowDialog(true)
                .setDialog(viewDelegate.getNetConnectDialog())
                .setRequestMode(HttpRequest.RequestMode.GET)
                .setParameterMode(HttpRequest.ParameterMode.KeyValue)
                .setRequestObj(baseMap)
                .setRequestCallback(requestCallback)
                .build()
                .RxSendRequest();
    }


}