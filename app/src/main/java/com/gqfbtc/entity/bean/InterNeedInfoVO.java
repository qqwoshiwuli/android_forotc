package com.gqfbtc.entity.bean;

import java.util.List;

/**
 * Created by 郭青枫 on 2017/12/4 0004.
 */

public class InterNeedInfoVO {
    /** 卖方 用户 */
    private UserLogin sellUser;
    /** 买方 用户 */
    private UserLogin buyUser;
    /** 卖家 收款地址 */
    private List<PaymentBTCETHAddress> sellCashAddrList;
    /** 买家 平台收币地址 */
    private PaymentBTCETHAddress buyPlatformCoinAddr;
    /** 买家 个人收币地址 */
    private List<PaymentBTCETHAddress> buyCoinAddrList;

    public UserLogin getSellUser() {
        return sellUser;
    }

    public void setSellUser(UserLogin sellUser) {
        this.sellUser = sellUser;
    }

    public UserLogin getBuyUser() {
        return buyUser;
    }

    public void setBuyUser(UserLogin buyUser) {
        this.buyUser = buyUser;
    }

    public List<PaymentBTCETHAddress> getSellCashAddrList() {
        return sellCashAddrList;
    }

    public void setSellCashAddrList(List<PaymentBTCETHAddress> sellCashAddrList) {
        this.sellCashAddrList = sellCashAddrList;
    }

    public PaymentBTCETHAddress getBuyPlatformCoinAddr() {
        return buyPlatformCoinAddr;
    }

    public void setBuyPlatformCoinAddr(PaymentBTCETHAddress buyPlatformCoinAddr) {
        this.buyPlatformCoinAddr = buyPlatformCoinAddr;
    }

    public List<PaymentBTCETHAddress> getBuyCoinAddrList() {
        return buyCoinAddrList;
    }

    public void setBuyCoinAddrList(List<PaymentBTCETHAddress> buyCoinAddrList) {
        this.buyCoinAddrList = buyCoinAddrList;
    }
}
