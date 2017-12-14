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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.safeModel);
        dest.writeString(this.normalModel);
        dest.writeByte(this.canTrade ? (byte) 1 : (byte) 0);
    }

    public CheckFrozen() {
    }

    protected CheckFrozen(Parcel in) {
        this.safeModel = in.readString();
        this.normalModel = in.readString();
        this.canTrade = in.readByte() != 0;
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
