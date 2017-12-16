package com.gqfbtc.entity.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by 郭青枫 on 2017/11/21.
 */

public class CheckFrozen implements Parcelable {
    /**
     * safeModel : 该模式出售需要先将出售资产的5%充值到平台钱包，且广告卖出额至少为1BTC。您当前钱包可用资产为10000.000000BTC，最多可挂单出售200000.000000BTC。
     * normalModel : 该模式发布出售广告需要先将相应资产充值到平台前台，您当前钱包可用资产为10000.000000BTC，最多可挂单出售10000.000000BTC。
     * canTrade : true
     */

    private String safeModel;
    private String normalModel;
    private boolean canTrade;
    private String floatPrice;
    private String floatPercent;
    private String marketPrice;

    //页面上问号的点击提示语
    //大宗交易时的提示语
    private String prompt;
    //求购币种提示
    private String cointypePro;
    //买入或卖出量
    private String quantityAdPro;
    //最低买入或最低卖出
    private String thresholdPro;
    //开放时间
    private String timePro;
    //收款帐号
    private String bankInfoPro;


    public String getSafeModel() {
        return safeModel;
    }

    public void setSafeModel(String safeModel) {
        this.safeModel = safeModel;
    }

    public String getNormalModel() {
        return normalModel;
    }

    public void setNormalModel(String normalModel) {
        this.normalModel = normalModel;
    }

    public boolean isCanTrade() {
        return canTrade;
    }

    public void setCanTrade(boolean canTrade) {
        this.canTrade = canTrade;
    }

    public String getFloatPrice() {
        return floatPrice;
    }

    public void setFloatPrice(String floatPrice) {
        this.floatPrice = floatPrice;
    }

    public String getFloatPercent() {
        return floatPercent;
    }

    public void setFloatPercent(String floatPercent) {
        this.floatPercent = floatPercent;
    }

    public String getMarketPrice() {
        return marketPrice;
    }

    public void setMarketPrice(String marketPrice) {
        this.marketPrice = marketPrice;
    }

    public String getPrompt() {
        return prompt;
    }

    public void setPrompt(String prompt) {
        this.prompt = prompt;
    }

    public String getCointypePro() {
        return cointypePro;
    }

    public void setCointypePro(String cointypePro) {
        this.cointypePro = cointypePro;
    }

    public String getQuantityAdPro() {
        return quantityAdPro;
    }

    public void setQuantityAdPro(String quantityAdPro) {
        this.quantityAdPro = quantityAdPro;
    }

    public String getThresholdPro() {
        return thresholdPro;
    }

    public void setThresholdPro(String thresholdPro) {
        this.thresholdPro = thresholdPro;
    }

    public String getTimePro() {
        return timePro;
    }

    public void setTimePro(String timePro) {
        this.timePro = timePro;
    }

    public String getBankInfoPro() {
        return bankInfoPro;
    }

    public void setBankInfoPro(String bankInfoPro) {
        this.bankInfoPro = bankInfoPro;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.safeModel);
        dest.writeString(this.normalModel);
        dest.writeByte(this.canTrade ? (byte) 1 : (byte) 0);
        dest.writeString(this.floatPrice);
        dest.writeString(this.floatPercent);
        dest.writeString(this.marketPrice);
        dest.writeString(this.prompt);
        dest.writeString(this.cointypePro);
        dest.writeString(this.quantityAdPro);
        dest.writeString(this.thresholdPro);
        dest.writeString(this.timePro);
        dest.writeString(this.bankInfoPro);
    }

    public CheckFrozen() {
    }

    protected CheckFrozen(Parcel in) {
        this.safeModel = in.readString();
        this.normalModel = in.readString();
        this.canTrade = in.readByte() != 0;
        this.floatPrice = in.readString();
        this.floatPercent = in.readString();
        this.marketPrice = in.readString();
        this.prompt = in.readString();
        this.cointypePro = in.readString();
        this.quantityAdPro = in.readString();
        this.thresholdPro = in.readString();
        this.timePro = in.readString();
        this.bankInfoPro = in.readString();
    }

    public static final Parcelable.Creator<CheckFrozen> CREATOR = new Parcelable.Creator<CheckFrozen>() {
        @Override
        public CheckFrozen createFromParcel(Parcel source) {
            return new CheckFrozen(source);
        }

        @Override
        public CheckFrozen[] newArray(int size) {
            return new CheckFrozen[size];
        }
    };
}
