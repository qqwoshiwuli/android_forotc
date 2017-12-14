package com.gqfbtc.entity.bean;

/**
 * Created by 郭青枫 on 2017/11/15.
 */

public class OrderListItem {
    /**
     * id : 11
     * code : 22200003
     * adId : 3
     * intermediaryId : 1
     * dealOwnerId : 10
     * dealIsSafe : true
     * dealPrice : 5000
     * dealQuantity : 1
     * adPoundage : 25
     * dealPoundage : 25
     * intermediaryPoundage : 0
     * dealStatus : 0
     * createTime : 1510653028000
     */


    private String id;
    private String code;
    private String adId;
    private String intermediaryId;
    private String dealOwnerId;
    private boolean dealIsSafe;
    private String dealPrice;
    private String dealQuantity;
    private String adPoundage;
    private String dealPoundage;
    private String intermediaryPoundage;
    private String adIsDone;
    private String dealIsDone;
    private String intermediaryIsDone;
    private String userCollectionCashAddr;
    private String intermediaryCollectionCashAddr;
    private String dealCollectionCoinAddr;
    private String randomcoin;
    private String dealStatus;
    private long createTime;
    private long updateTime;
    private String userPoundageVo;
    private String pountAgeRateVo;
    private String amountVo;
    private String quantityVo;
    private String priceVo;
    private String dealPriceStr;//成交单价 字符串
    private String dealMoneyStr;//交易金额 字符串
    private String title; //状态1、2、3转译的文字
    private String payType; //列表 最右边 显示购买还是出售
    private Boolean isComplete;
    private Boolean isCancel;


    public Boolean getCancel() {
        return isCancel;
    }

    public void setCancel(Boolean cancel) {
        isCancel = cancel;
    }

    public Boolean getComplete() {
        return isComplete;
    }

    public void setComplete(Boolean complete) {
        isComplete = complete;
    }

    public String getId() {
        return id;
    }

    public String getDealPriceStr() {
        return dealPriceStr;
    }

    public void setDealPriceStr(String dealPriceStr) {
        this.dealPriceStr = dealPriceStr;
    }

    public String getDealMoneyStr() {
        return dealMoneyStr;
    }

    public void setDealMoneyStr(String dealMoneyStr) {
        this.dealMoneyStr = dealMoneyStr;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPayType() {
        return payType;
    }

    public void setPayType(String payType) {
        this.payType = payType;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getAdId() {
        return adId;
    }

    public void setAdId(String adId) {
        this.adId = adId;
    }

    public String getIntermediaryId() {
        return intermediaryId;
    }

    public void setIntermediaryId(String intermediaryId) {
        this.intermediaryId = intermediaryId;
    }

    public String getDealOwnerId() {
        return dealOwnerId;
    }

    public void setDealOwnerId(String dealOwnerId) {
        this.dealOwnerId = dealOwnerId;
    }

    public boolean isDealIsSafe() {
        return dealIsSafe;
    }

    public void setDealIsSafe(boolean dealIsSafe) {
        this.dealIsSafe = dealIsSafe;
    }

    public String getDealPrice() {
        return dealPrice;
    }

    public void setDealPrice(String dealPrice) {
        this.dealPrice = dealPrice;
    }

    public String getDealQuantity() {
        return dealQuantity;
    }

    public void setDealQuantity(String dealQuantity) {
        this.dealQuantity = dealQuantity;
    }

    public String getAdPoundage() {
        return adPoundage;
    }

    public void setAdPoundage(String adPoundage) {
        this.adPoundage = adPoundage;
    }

    public String getDealPoundage() {
        return dealPoundage;
    }

    public void setDealPoundage(String dealPoundage) {
        this.dealPoundage = dealPoundage;
    }

    public String getIntermediaryPoundage() {
        return intermediaryPoundage;
    }

    public void setIntermediaryPoundage(String intermediaryPoundage) {
        this.intermediaryPoundage = intermediaryPoundage;
    }

    public String getAdIsDone() {
        return adIsDone;
    }

    public void setAdIsDone(String adIsDone) {
        this.adIsDone = adIsDone;
    }

    public String getDealIsDone() {
        return dealIsDone;
    }

    public void setDealIsDone(String dealIsDone) {
        this.dealIsDone = dealIsDone;
    }

    public String getIntermediaryIsDone() {
        return intermediaryIsDone;
    }

    public void setIntermediaryIsDone(String intermediaryIsDone) {
        this.intermediaryIsDone = intermediaryIsDone;
    }

    public String getUserCollectionCashAddr() {
        return userCollectionCashAddr;
    }

    public void setUserCollectionCashAddr(String userCollectionCashAddr) {
        this.userCollectionCashAddr = userCollectionCashAddr;
    }

    public String getIntermediaryCollectionCashAddr() {
        return intermediaryCollectionCashAddr;
    }

    public void setIntermediaryCollectionCashAddr(String intermediaryCollectionCashAddr) {
        this.intermediaryCollectionCashAddr = intermediaryCollectionCashAddr;
    }

    public String getDealCollectionCoinAddr() {
        return dealCollectionCoinAddr;
    }

    public void setDealCollectionCoinAddr(String dealCollectionCoinAddr) {
        this.dealCollectionCoinAddr = dealCollectionCoinAddr;
    }

    public String getRandomcoin() {
        return randomcoin;
    }

    public void setRandomcoin(String randomcoin) {
        this.randomcoin = randomcoin;
    }

    public String getDealStatus() {
        return dealStatus;
    }

    public void setDealStatus(String dealStatus) {
        this.dealStatus = dealStatus;
    }

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    public long getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(long updateTime) {
        this.updateTime = updateTime;
    }

    public String getUserPoundageVo() {
        return userPoundageVo;
    }

    public void setUserPoundageVo(String userPoundageVo) {
        this.userPoundageVo = userPoundageVo;
    }

    public String getPountAgeRateVo() {
        return pountAgeRateVo;
    }

    public void setPountAgeRateVo(String pountAgeRateVo) {
        this.pountAgeRateVo = pountAgeRateVo;
    }

    public String getAmountVo() {
        return amountVo;
    }

    public void setAmountVo(String amountVo) {
        this.amountVo = amountVo;
    }

    public String getQuantityVo() {
        return quantityVo;
    }

    public void setQuantityVo(String quantityVo) {
        this.quantityVo = quantityVo;
    }

    public String getPriceVo() {
        return priceVo;
    }

    public void setPriceVo(String priceVo) {
        this.priceVo = priceVo;
    }
}
