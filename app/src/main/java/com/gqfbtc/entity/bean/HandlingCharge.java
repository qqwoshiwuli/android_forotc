package com.gqfbtc.entity.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Created by 郭青枫 on 2017/11/14.
 */

public class HandlingCharge implements Parcelable {
    /**
     * id : null
     * code : null
     * adId : 3
     * intermediaryId : 1
     * dealOwnerId : 10
     * dealIsSafe : null
     * dealPrice : 1000   //单价
     * dealQuantity : 5
     * adPoundage : null
     * dealPoundage : 25  //用户的手续费
     * intermediaryPoundage : null
     * adIsDone : null
     * dealIsDone : null
     * intermediaryIsDone : null
     * userCollectionCashAddr : null
     * intermediaryCollectionCashAddr : null
     * dealCollectionCoinAddr : null
     * randomcoin : null
     * dealStatus : null
     * createTime : null
     * updateTime : null
     * userPoundageVo : 25.00CNY
     * pountAgeRateVo : 0.05 //费率
     * amountVo : 5000CNY    //交易总金额
     * quantityVo : 5BTC       //交易数量
     * priceVo : 1000CNY       //交易单价
     */

    private String id;
    private String code;
    private String adId;
    private String intermediaryId;
    private String dealOwnerId;
    private String dealIsSafe;
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
    private String createTime;
    private String updateTime;
    private String userPoundageVo;
    private String pountAgeRateVo;
    private String amountVo;
    private String quantityVo;
    private String priceVo;
    private String time;
    private List<PaymentBTCETHAddress> dealCollectionCoinAddrList;
    private List<PaymentBTCETHAddress> userCollectionCashAddrList;

    public String getId() {
        return id;
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

    public String getDealIsSafe() {
        return dealIsSafe;
    }

    public void setDealIsSafe(String dealIsSafe) {
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

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
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

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public List<PaymentBTCETHAddress> getDealCollectionCoinAddrList() {
        return dealCollectionCoinAddrList;
    }

    public void setDealCollectionCoinAddrList(List<PaymentBTCETHAddress> dealCollectionCoinAddrList) {
        this.dealCollectionCoinAddrList = dealCollectionCoinAddrList;
    }

    public List<PaymentBTCETHAddress> getUserCollectionCashAddrList() {
        return userCollectionCashAddrList;
    }

    public void setUserCollectionCashAddrList(List<PaymentBTCETHAddress> userCollectionCashAddrList) {
        this.userCollectionCashAddrList = userCollectionCashAddrList;
    }

    public static Creator<HandlingCharge> getCREATOR() {
        return CREATOR;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeString(this.code);
        dest.writeString(this.adId);
        dest.writeString(this.intermediaryId);
        dest.writeString(this.dealOwnerId);
        dest.writeString(this.dealIsSafe);
        dest.writeString(this.dealPrice);
        dest.writeString(this.dealQuantity);
        dest.writeString(this.adPoundage);
        dest.writeString(this.dealPoundage);
        dest.writeString(this.intermediaryPoundage);
        dest.writeString(this.adIsDone);
        dest.writeString(this.dealIsDone);
        dest.writeString(this.intermediaryIsDone);
        dest.writeString(this.userCollectionCashAddr);
        dest.writeString(this.intermediaryCollectionCashAddr);
        dest.writeString(this.dealCollectionCoinAddr);
        dest.writeString(this.randomcoin);
        dest.writeString(this.dealStatus);
        dest.writeString(this.createTime);
        dest.writeString(this.updateTime);
        dest.writeString(this.userPoundageVo);
        dest.writeString(this.pountAgeRateVo);
        dest.writeString(this.amountVo);
        dest.writeString(this.quantityVo);
        dest.writeString(this.priceVo);
        dest.writeString(this.time);
        dest.writeTypedList(this.dealCollectionCoinAddrList);
        dest.writeTypedList(this.userCollectionCashAddrList);
    }

    public HandlingCharge() {
    }

    protected HandlingCharge(Parcel in) {
        this.id = in.readString();
        this.code = in.readString();
        this.adId = in.readString();
        this.intermediaryId = in.readString();
        this.dealOwnerId = in.readString();
        this.dealIsSafe = in.readString();
        this.dealPrice = in.readString();
        this.dealQuantity = in.readString();
        this.adPoundage = in.readString();
        this.dealPoundage = in.readString();
        this.intermediaryPoundage = in.readString();
        this.adIsDone = in.readString();
        this.dealIsDone = in.readString();
        this.intermediaryIsDone = in.readString();
        this.userCollectionCashAddr = in.readString();
        this.intermediaryCollectionCashAddr = in.readString();
        this.dealCollectionCoinAddr = in.readString();
        this.randomcoin = in.readString();
        this.dealStatus = in.readString();
        this.createTime = in.readString();
        this.updateTime = in.readString();
        this.userPoundageVo = in.readString();
        this.pountAgeRateVo = in.readString();
        this.amountVo = in.readString();
        this.quantityVo = in.readString();
        this.priceVo = in.readString();
        this.time = in.readString();
        this.dealCollectionCoinAddrList = in.createTypedArrayList(PaymentBTCETHAddress.CREATOR);
        this.userCollectionCashAddrList = in.createTypedArrayList(PaymentBTCETHAddress.CREATOR);
    }

    public static final Parcelable.Creator<HandlingCharge> CREATOR = new Parcelable.Creator<HandlingCharge>() {
        @Override
        public HandlingCharge createFromParcel(Parcel source) {
            return new HandlingCharge(source);
        }

        @Override
        public HandlingCharge[] newArray(int size) {
            return new HandlingCharge[size];
        }
    };
}
