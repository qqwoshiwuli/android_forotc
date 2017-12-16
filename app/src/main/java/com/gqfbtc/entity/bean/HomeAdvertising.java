package com.gqfbtc.entity.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by 郭青枫 on 2017/11/13.
 */

public class HomeAdvertising implements Parcelable {
    /**
     * tradeAmount : 200
     * ownScore : 4.9
     * tags_express : 1
     * adId : 20
     * tradeFloorAmount : 100
     * ownerAvatar : 245.png
     * price : 200
     * tags_KA : 1
     * isSale : false
     * ownerNickName : 我叫买买11
     */

    public static final String coin_type_btc = "1";
    public static final String coin_type_ucx = "3";


    private String tradeAmount;
    private String ownScore;
    private String tags_express;
    private String adId;
    private String tradeFloorAmount;
    private String ownerAvatar;
    private String price;
    private String tags_KA;
    private boolean isSale;
    private boolean tagsExpress;
    private boolean tagsKA;
    private String ownerNickName;


    /**
     * leftQuantity : 0.105429
     * adId : 302
     * tradeFloorAmount : 0
     * floatPercent : -5.09
     * floor : 100
     * isFixed : false
     */

    private String leftQuantity;//剩余数量
    private String currency;//1 btc 3 ucx
    private String floatPercent;
    private String floor;//最低交易量
    private boolean isFixed;

    private String respondTimeStr;//响应时间
    private String respondRatioStr;//响应概率
    private String succCountStr;//成功交易次数@轻疯 @韩十力


    public static String getCoin_type_btc() {
        return coin_type_btc;
    }

    public static String getCoin_type_ucx() {
        return coin_type_ucx;
    }

    public String getTradeAmount() {
        return tradeAmount;
    }

    public void setTradeAmount(String tradeAmount) {
        this.tradeAmount = tradeAmount;
    }

    public String getOwnScore() {
        return ownScore;
    }

    public void setOwnScore(String ownScore) {
        this.ownScore = ownScore;
    }

    public String getTags_express() {
        return tags_express;
    }

    public void setTags_express(String tags_express) {
        this.tags_express = tags_express;
    }

    public String getAdId() {
        return adId;
    }

    public void setAdId(String adId) {
        this.adId = adId;
    }

    public String getTradeFloorAmount() {
        return tradeFloorAmount;
    }

    public void setTradeFloorAmount(String tradeFloorAmount) {
        this.tradeFloorAmount = tradeFloorAmount;
    }

    public String getOwnerAvatar() {
        return ownerAvatar;
    }

    public void setOwnerAvatar(String ownerAvatar) {
        this.ownerAvatar = ownerAvatar;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getTags_KA() {
        return tags_KA;
    }

    public void setTags_KA(String tags_KA) {
        this.tags_KA = tags_KA;
    }

    public boolean isSale() {
        return isSale;
    }

    public void setSale(boolean sale) {
        isSale = sale;
    }

    public boolean isTagsExpress() {
        return tagsExpress;
    }

    public void setTagsExpress(boolean tagsExpress) {
        this.tagsExpress = tagsExpress;
    }

    public boolean isTagsKA() {
        return tagsKA;
    }

    public void setTagsKA(boolean tagsKA) {
        this.tagsKA = tagsKA;
    }

    public String getOwnerNickName() {
        return ownerNickName;
    }

    public void setOwnerNickName(String ownerNickName) {
        this.ownerNickName = ownerNickName;
    }

    public String getLeftQuantity() {
        return leftQuantity;
    }

    public void setLeftQuantity(String leftQuantity) {
        this.leftQuantity = leftQuantity;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getFloatPercent() {
        return floatPercent;
    }

    public void setFloatPercent(String floatPercent) {
        this.floatPercent = floatPercent;
    }

    public String getFloor() {
        return floor;
    }

    public void setFloor(String floor) {
        this.floor = floor;
    }

    public boolean isFixed() {
        return isFixed;
    }

    public void setFixed(boolean fixed) {
        isFixed = fixed;
    }

    public String getRespondTimeStr() {
        return respondTimeStr;
    }

    public void setRespondTimeStr(String respondTimeStr) {
        this.respondTimeStr = respondTimeStr;
    }

    public String getRespondRatioStr() {
        return respondRatioStr;
    }

    public void setRespondRatioStr(String respondRatioStr) {
        this.respondRatioStr = respondRatioStr;
    }

    public String getSuccCountStr() {
        return succCountStr;
    }

    public void setSuccCountStr(String succCountStr) {
        this.succCountStr = succCountStr;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.tradeAmount);
        dest.writeString(this.ownScore);
        dest.writeString(this.tags_express);
        dest.writeString(this.adId);
        dest.writeString(this.tradeFloorAmount);
        dest.writeString(this.ownerAvatar);
        dest.writeString(this.price);
        dest.writeString(this.tags_KA);
        dest.writeByte(this.isSale ? (byte) 1 : (byte) 0);
        dest.writeByte(this.tagsExpress ? (byte) 1 : (byte) 0);
        dest.writeByte(this.tagsKA ? (byte) 1 : (byte) 0);
        dest.writeString(this.ownerNickName);
        dest.writeString(this.leftQuantity);
        dest.writeString(this.currency);
        dest.writeString(this.floatPercent);
        dest.writeString(this.floor);
        dest.writeByte(this.isFixed ? (byte) 1 : (byte) 0);
        dest.writeString(this.respondTimeStr);
        dest.writeString(this.respondRatioStr);
        dest.writeString(this.succCountStr);
    }

    public HomeAdvertising() {
    }

    protected HomeAdvertising(Parcel in) {
        this.tradeAmount = in.readString();
        this.ownScore = in.readString();
        this.tags_express = in.readString();
        this.adId = in.readString();
        this.tradeFloorAmount = in.readString();
        this.ownerAvatar = in.readString();
        this.price = in.readString();
        this.tags_KA = in.readString();
        this.isSale = in.readByte() != 0;
        this.tagsExpress = in.readByte() != 0;
        this.tagsKA = in.readByte() != 0;
        this.ownerNickName = in.readString();
        this.leftQuantity = in.readString();
        this.currency = in.readString();
        this.floatPercent = in.readString();
        this.floor = in.readString();
        this.isFixed = in.readByte() != 0;
        this.respondTimeStr = in.readString();
        this.respondRatioStr = in.readString();
        this.succCountStr = in.readString();
    }

    public static final Parcelable.Creator<HomeAdvertising> CREATOR = new Parcelable.Creator<HomeAdvertising>() {
        @Override
        public HomeAdvertising createFromParcel(Parcel source) {
            return new HomeAdvertising(source);
        }

        @Override
        public HomeAdvertising[] newArray(int size) {
            return new HomeAdvertising[size];
        }
    };
}
