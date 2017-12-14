package com.gqfbtc.entity.bean;

import java.math.BigDecimal;

/**
 * Created by 郭青枫 on 2017/11/15.
 */

public class AssetInformation {
    /**
     * id : 8
     * accCode : 23123
     * currency : 1
     * ownerId : 10
     * amount : 3000
     * frozenAmount : 46.99999
     * ownerIsUser : true
     * createTime : 1510542065000
     * updateTime : 1510738288000
     */

    private String id;
    private String accCode;
    private String currency;
    private String ownerId;
    private String withdrawFee;
    private BigDecimal amount;
    private BigDecimal frozenAmount;
    private boolean ownerIsUser;
    private long createTime;
    private long updateTime;

    public String getWithdrawFee() {
        return withdrawFee;
    }

    public void setWithdrawFee(String withdrawFee) {
        this.withdrawFee = withdrawFee;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAccCode() {
        return accCode;
    }

    public void setAccCode(String accCode) {
        this.accCode = accCode;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getFrozenAmount() {
        return frozenAmount;
    }

    public void setFrozenAmount(BigDecimal frozenAmount) {
        this.frozenAmount = frozenAmount;
    }

    public boolean isOwnerIsUser() {
        return ownerIsUser;
    }

    public void setOwnerIsUser(boolean ownerIsUser) {
        this.ownerIsUser = ownerIsUser;
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
}
