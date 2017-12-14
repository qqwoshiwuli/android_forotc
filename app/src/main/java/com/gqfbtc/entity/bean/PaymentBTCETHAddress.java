package com.gqfbtc.entity.bean;

import android.os.Parcel;
import android.os.Parcelable;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by 郭青枫 on 2017/11/7.
 */
@Entity
public class PaymentBTCETHAddress implements Parcelable {
    /**
     * id : 1
     * ownerId : 1
     * ownerIsUser : true
     * collectionType : 1
     * collectionAddr : djkjdfksj@qq.com
     * alias : 中行
     * bankName : 中国银行
     * branchName : 开元路支行
     * ownerName : 王楷
     * remark : null
     * createTime : 1509955239000
     * updateTime : 1509955239000
     * isDel : false
     */
    @Id
    private long id;
    private String ownerId;
    private boolean ownerIsUser;
    private String collectionType;
    private String collectionAddr;
    private String alias;
    private String bankName;
    private String branchName;
    private String ownerName;
    private String remark;
    private long createTime;
    private long updateTime;
    private boolean isDel;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId;
    }

    public boolean isOwnerIsUser() {
        return ownerIsUser;
    }

    public void setOwnerIsUser(boolean ownerIsUser) {
        this.ownerIsUser = ownerIsUser;
    }

    public String getCollectionType() {
        return collectionType;
    }

    public void setCollectionType(String collectionType) {
        this.collectionType = collectionType;
    }

    public String getCollectionAddr() {
        return collectionAddr;
    }

    public void setCollectionAddr(String collectionAddr) {
        this.collectionAddr = collectionAddr;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getBranchName() {
        return branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
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

    public boolean isDel() {
        return isDel;
    }

    public void setDel(boolean del) {
        isDel = del;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(this.id);
        dest.writeString(this.ownerId);
        dest.writeByte(this.ownerIsUser ? (byte) 1 : (byte) 0);
        dest.writeString(this.collectionType);
        dest.writeString(this.collectionAddr);
        dest.writeString(this.alias);
        dest.writeString(this.bankName);
        dest.writeString(this.branchName);
        dest.writeString(this.ownerName);
        dest.writeString(this.remark);
        dest.writeLong(this.createTime);
        dest.writeLong(this.updateTime);
        dest.writeByte(this.isDel ? (byte) 1 : (byte) 0);
    }

    public boolean getOwnerIsUser() {
        return this.ownerIsUser;
    }

    public boolean getIsDel() {
        return this.isDel;
    }

    public void setIsDel(boolean isDel) {
        this.isDel = isDel;
    }

    public PaymentBTCETHAddress() {
    }

    protected PaymentBTCETHAddress(Parcel in) {
        this.id = in.readLong();
        this.ownerId = in.readString();
        this.ownerIsUser = in.readByte() != 0;
        this.collectionType = in.readString();
        this.collectionAddr = in.readString();
        this.alias = in.readString();
        this.bankName = in.readString();
        this.branchName = in.readString();
        this.ownerName = in.readString();
        this.remark = in.readString();
        this.createTime = in.readLong();
        this.updateTime = in.readLong();
        this.isDel = in.readByte() != 0;
    }

    @Generated(hash = 1958820229)
    public PaymentBTCETHAddress(long id, String ownerId, boolean ownerIsUser, String collectionType, String collectionAddr,
            String alias, String bankName, String branchName, String ownerName, String remark, long createTime,
            long updateTime, boolean isDel) {
        this.id = id;
        this.ownerId = ownerId;
        this.ownerIsUser = ownerIsUser;
        this.collectionType = collectionType;
        this.collectionAddr = collectionAddr;
        this.alias = alias;
        this.bankName = bankName;
        this.branchName = branchName;
        this.ownerName = ownerName;
        this.remark = remark;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.isDel = isDel;
    }

    public static final Parcelable.Creator<PaymentBTCETHAddress> CREATOR = new Parcelable.Creator<PaymentBTCETHAddress>() {
        @Override
        public PaymentBTCETHAddress createFromParcel(Parcel source) {
            return new PaymentBTCETHAddress(source);
        }

        @Override
        public PaymentBTCETHAddress[] newArray(int size) {
            return new PaymentBTCETHAddress[size];
        }
    };
}
