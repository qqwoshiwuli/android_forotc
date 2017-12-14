package com.gqfbtc.entity.bean;

/**
 * Created by 郭青枫 on 2017/11/15.
 */

public class TopUp {
    /**
     * id : 9
     * currency : 1
     * createTime : 1510747184000
     * updateTime : 1510748868000
     */

    private String id;
    private String currency;
    private long createTime;
    private long updateTime;
    /**
     * id : 4
     * address : 333sadasdasdasdasd
     * txHash : 24154
     * validCount : 4
     * state : 1
     * quantity : 5
     * isUser : true
     * currency : 1
     */

    private String address;
    private String txHash;
    private String validCount;
    private String state;
    private String quantity;
    private boolean isUser;
    private String status;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTxHash() {
        return txHash;
    }

    public void setTxHash(String txHash) {
        this.txHash = txHash;
    }

    public String getValidCount() {
        return validCount;
    }

    public void setValidCount(String validCount) {
        this.validCount = validCount;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public boolean isUser() {
        return isUser;
    }

    public void setUser(boolean user) {
        isUser = user;
    }
}
