package com.gqfbtc.entity.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by 郭青枫 on 2017/11/20.
 */

public class AppVersion implements Parcelable {
    /**
     * versionContent : 1231654654
     * versionTitle : 111
     * isNeedFresh : false
     * downloadAddr : http://1
     * versionCode : 1.0.0
     * downloadAddrForotc : http://flr.im/forotc
     */

    private String versionContent;
    private String versionTitle;
    private boolean isNeedFresh;
    private String downloadAddr;
    private String versionCode;
    private String downloadAddrForotc;

    public String getVersionContent() {
        return versionContent;
    }

    public void setVersionContent(String versionContent) {
        this.versionContent = versionContent;
    }

    public String getVersionTitle() {
        return versionTitle;
    }

    public void setVersionTitle(String versionTitle) {
        this.versionTitle = versionTitle;
    }

    public boolean isIsNeedFresh() {
        return isNeedFresh;
    }

    public void setIsNeedFresh(boolean isNeedFresh) {
        this.isNeedFresh = isNeedFresh;
    }

    public String getDownloadAddr() {
        return downloadAddr;
    }

    public void setDownloadAddr(String downloadAddr) {
        this.downloadAddr = downloadAddr;
    }

    public String getVersionCode() {
        return versionCode;
    }

    public void setVersionCode(String versionCode) {
        this.versionCode = versionCode;
    }

    public String getDownloadAddrForotc() {
        return downloadAddrForotc;
    }

    public void setDownloadAddrForotc(String downloadAddrForotc) {
        this.downloadAddrForotc = downloadAddrForotc;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.versionContent);
        dest.writeString(this.versionTitle);
        dest.writeByte(this.isNeedFresh ? (byte) 1 : (byte) 0);
        dest.writeString(this.downloadAddr);
        dest.writeString(this.versionCode);
        dest.writeString(this.downloadAddrForotc);
    }

    public AppVersion() {
    }

    protected AppVersion(Parcel in) {
        this.versionContent = in.readString();
        this.versionTitle = in.readString();
        this.isNeedFresh = in.readByte() != 0;
        this.downloadAddr = in.readString();
        this.versionCode = in.readString();
        this.downloadAddrForotc = in.readString();
    }

    public static final Parcelable.Creator<AppVersion> CREATOR = new Parcelable.Creator<AppVersion>() {
        @Override
        public AppVersion createFromParcel(Parcel source) {
            return new AppVersion(source);
        }

        @Override
        public AppVersion[] newArray(int size) {
            return new AppVersion[size];
        }
    };
}
