package com.gqfbtc.entity.bean;

/**
 * Created by 郭青枫 on 2017/11/14.
 */

public class Intermediary {
    /**
     * intermediaryId : 15
     * name : Hu
     * avatar : 4ddccf815101c80e7f90913251ec4f10
     * grade : 3.36
     * dealCount : 5300
     * dealQuantity : 2365.25
     * dealType : 3,1
     */

    private String intermediaryId;
    private String name;
    private String avatar;
    private String grade;
    private String dealCount;
    private String dealQuantity;
    private String dealType;
    boolean alipay  ;
    boolean     card;


    public boolean isAlipay() {
        return alipay;
    }

    public void setAlipay(boolean alipay) {
        this.alipay = alipay;
    }

    public boolean isCard() {
        return card;
    }

    public void setCard(boolean card) {
        this.card = card;
    }

    public String getIntermediaryId() {
        return intermediaryId;
    }

    public void setIntermediaryId(String intermediaryId) {
        this.intermediaryId = intermediaryId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getDealCount() {
        return dealCount;
    }

    public void setDealCount(String dealCount) {
        this.dealCount = dealCount;
    }

    public String getDealQuantity() {
        return dealQuantity;
    }

    public void setDealQuantity(String dealQuantity) {
        this.dealQuantity = dealQuantity;
    }

    public String getDealType() {
        return dealType;
    }

    public void setDealType(String dealType) {
        this.dealType = dealType;
    }
}
