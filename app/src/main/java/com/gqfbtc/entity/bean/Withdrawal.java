package com.gqfbtc.entity.bean;

/**
 * Created by 郭青枫 on 2017/11/15.
 */

public class Withdrawal {
    /**
     * id : 4
     * accountId : 3
     * depositCoinAddr : 1F5XZBMxwb84VWjbMAfB1uJai6KTt4tzNf
     * type : 1
     * quantity : 5
     * poundage : 0.005
     * level : 1
     * remark : 备注
     * status : 0
     * createTime : 1510748617000
     * updateTime : 1510750020000
     */

    private int id;
    private int accountId;
    private String depositCoinAddr;
    private String type;
    private String quantity;
    private double poundage;
    private String level;
    private String remark;
    private String status;
    private long createTime;
    private long updateTime;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public String getDepositCoinAddr() {
        return depositCoinAddr;
    }

    public void setDepositCoinAddr(String depositCoinAddr) {
        this.depositCoinAddr = depositCoinAddr;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public double getPoundage() {
        return poundage;
    }

    public void setPoundage(double poundage) {
        this.poundage = poundage;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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
