package com.gqfbtc.entity.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Created by 郭青枫 on 2017/11/12.
 */

public class BeforeSaveAd implements Parcelable {


    /**
     * floatPrice : 17041.59073848  //显示单价
     * floatPercent : -0.5792   //浮动比例
     * marketPrice : 40498.07685  //市场价格，传参根据市场价格和浮动价格计算上传
     */

    private String floatPrice;
    private String floatPercent;
    private String marketPrice;
    private List<PaymentBTCETHAddress> beneBanks;


    public List<PaymentBTCETHAddress> getBeneBanks() {
        return beneBanks;
    }

    public void setBeneBanks(List<PaymentBTCETHAddress> beneBanks) {
        this.beneBanks = beneBanks;
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.floatPrice);
        dest.writeString(this.floatPercent);
        dest.writeString(this.marketPrice);
        dest.writeTypedList(this.beneBanks);
    }

    public BeforeSaveAd() {
    }

    protected BeforeSaveAd(Parcel in) {
        this.floatPrice = in.readString();
        this.floatPercent = in.readString();
        this.marketPrice = in.readString();
        this.beneBanks = in.createTypedArrayList(PaymentBTCETHAddress.CREATOR);
    }

    public static final Parcelable.Creator<BeforeSaveAd> CREATOR = new Parcelable.Creator<BeforeSaveAd>() {
        @Override
        public BeforeSaveAd createFromParcel(Parcel source) {
            return new BeforeSaveAd(source);
        }

        @Override
        public BeforeSaveAd[] newArray(int size) {
            return new BeforeSaveAd[size];
        }
    };
}
