package com.gqfbtc.entity.bean;

/**
 * Created by 郭青枫 on 2017/11/16.
 */

public class MyAdvertising {
    /**
     * id : 3
     * code : 00001926875
     * currency : 1
     * owerId : 4
     * isSale : true
     * isSafe : false
     * status : 0
     * isFixed : true
     * price : 50000.25//单价
     * quantity : 5//数量
     * lockedAmount : 1
     * succeedAmount : 0
     * deposit : 5
     * remark : 我是备注信息
     * startTime : {"hour":10,"minute":0,"second":0,"nano":0}
     * endTime : {"hour":22,"minute":0,"second":0,"nano":0}
     * threshold : 5000 //最低
     * floatPercent : 0
     * createTime : 1510214237000
     * updateTime : 1510486545000
     * collectionCashAddr : 3
     * collectionCoinAddr : 33
     * ceil : 333
     * floor : 3333
     * tagsKa : false
     * tagsExpress : false
     */

    private String id;
    private String code;
    private String currency;
    private String owerId;
    private boolean isSale;
    private boolean isSafe;
    private String status;
    private boolean isFixed;
    private String price;
    private String quantity;
    private String lockedAmount;
    private String succeedAmount;
    private String deposit;
    private String remark;
    private StartTimeBean startTime;
    private EndTimeBean endTime;
    private String threshold;
    private String floatPercent;
    private long createTime;
    private long updateTime;
    private String collectionCashAddr;
    private String collectionCoinAddr;
    private String ceil;
    private String floor;
    private String time;
    private boolean tagsKa;
    private boolean tagsExpress;

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getOwerId() {
        return owerId;
    }

    public void setOwerId(String owerId) {
        this.owerId = owerId;
    }

    public boolean isSale() {
        return isSale;
    }

    public void setSale(boolean sale) {
        isSale = sale;
    }

    public boolean isSafe() {
        return isSafe;
    }

    public void setSafe(boolean safe) {
        isSafe = safe;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public boolean isFixed() {
        return isFixed;
    }

    public void setFixed(boolean fixed) {
        isFixed = fixed;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getLockedAmount() {
        return lockedAmount;
    }

    public void setLockedAmount(String lockedAmount) {
        this.lockedAmount = lockedAmount;
    }

    public String getSucceedAmount() {
        return succeedAmount;
    }

    public void setSucceedAmount(String succeedAmount) {
        this.succeedAmount = succeedAmount;
    }

    public String getDeposit() {
        return deposit;
    }

    public void setDeposit(String deposit) {
        this.deposit = deposit;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public StartTimeBean getStartTime() {
        return startTime;
    }

    public void setStartTime(StartTimeBean startTime) {
        this.startTime = startTime;
    }

    public EndTimeBean getEndTime() {
        return endTime;
    }

    public void setEndTime(EndTimeBean endTime) {
        this.endTime = endTime;
    }

    public String getThreshold() {
        return threshold;
    }

    public void setThreshold(String threshold) {
        this.threshold = threshold;
    }

    public String getFloatPercent() {
        return floatPercent;
    }

    public void setFloatPercent(String floatPercent) {
        this.floatPercent = floatPercent;
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

    public String getCollectionCashAddr() {
        return collectionCashAddr;
    }

    public void setCollectionCashAddr(String collectionCashAddr) {
        this.collectionCashAddr = collectionCashAddr;
    }

    public String getCollectionCoinAddr() {
        return collectionCoinAddr;
    }

    public void setCollectionCoinAddr(String collectionCoinAddr) {
        this.collectionCoinAddr = collectionCoinAddr;
    }

    public String getCeil() {
        return ceil;
    }

    public void setCeil(String ceil) {
        this.ceil = ceil;
    }

    public String getFloor() {
        return floor;
    }

    public void setFloor(String floor) {
        this.floor = floor;
    }

    public boolean isTagsKa() {
        return tagsKa;
    }

    public void setTagsKa(boolean tagsKa) {
        this.tagsKa = tagsKa;
    }

    public boolean isTagsExpress() {
        return tagsExpress;
    }

    public void setTagsExpress(boolean tagsExpress) {
        this.tagsExpress = tagsExpress;
    }

    public static class StartTimeBean {
        /**
         * hour : 10
         * minute : 0
         * second : 0
         * nano : 0
         */

        private int hour;
        private int minute;
        private int second;
        private int nano;

        public int getHour() {
            return hour;
        }

        public void setHour(int hour) {
            this.hour = hour;
        }

        public int getMinute() {
            return minute;
        }

        public void setMinute(int minute) {
            this.minute = minute;
        }

        public int getSecond() {
            return second;
        }

        public void setSecond(int second) {
            this.second = second;
        }

        public int getNano() {
            return nano;
        }

        public void setNano(int nano) {
            this.nano = nano;
        }
    }

    public static class EndTimeBean {
        /**
         * hour : 22
         * minute : 0
         * second : 0
         * nano : 0
         */

        private int hour;
        private int minute;
        private int second;
        private int nano;

        public int getHour() {
            return hour;
        }

        public void setHour(int hour) {
            this.hour = hour;
        }

        public int getMinute() {
            return minute;
        }

        public void setMinute(int minute) {
            this.minute = minute;
        }

        public int getSecond() {
            return second;
        }

        public void setSecond(int second) {
            this.second = second;
        }

        public int getNano() {
            return nano;
        }

        public void setNano(int nano) {
            this.nano = nano;
        }
    }
}
