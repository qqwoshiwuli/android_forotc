package com.gqfbtc.entity.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by 郭青枫 on 2017/11/16.
 */

public class ShareBean implements Parcelable {
    /**
     * countNum : 0
     * shareText : 分享地址为:http://localhost:8080/share/
     * shareLink : http://localhost:8080/share/
     * sumAmount : null
     */

    private String countNum;
    private String shareText;
    private String shareLink;
    private String imgUrl;
    private String title;
    private String sumAmount;

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCountNum() {
        return countNum;
    }

    public void setCountNum(String countNum) {
        this.countNum = countNum;
    }

    public String getShareText() {
        return shareText;
    }

    public void setShareText(String shareText) {
        this.shareText = shareText;
    }

    public String getShareLink() {
        return shareLink;
    }

    public void setShareLink(String shareLink) {
        this.shareLink = shareLink;
    }

    public String getSumAmount() {
        return sumAmount;
    }

    public void setSumAmount(String sumAmount) {
        this.sumAmount = sumAmount;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.countNum);
        dest.writeString(this.shareText);
        dest.writeString(this.shareLink);
        dest.writeString(this.imgUrl);
        dest.writeString(this.title);
        dest.writeString(this.sumAmount);
    }

    public ShareBean() {
    }

    protected ShareBean(Parcel in) {
        this.countNum = in.readString();
        this.shareText = in.readString();
        this.shareLink = in.readString();
        this.imgUrl = in.readString();
        this.title = in.readString();
        this.sumAmount = in.readString();
    }

    public static final Parcelable.Creator<ShareBean> CREATOR = new Parcelable.Creator<ShareBean>() {
        @Override
        public ShareBean createFromParcel(Parcel source) {
            return new ShareBean(source);
        }

        @Override
        public ShareBean[] newArray(int size) {
            return new ShareBean[size];
        }
    };
}
