package com.gqfbtc.entity.bean;

/**
 * Created by 郭青枫 on 2017/11/15.
 */

public class TransactAppraise {
    /**
     * dealId : 2
     * firstId : 7
     * firstName : 买家
     * firstScore : 0
     * secondId : 15
     * secondName : 中介
     * secondScore : 0
     */

    private String dealId;
    private String firstId;
    private String firstName;
    private String firstScore;
    private String secondId;
    private String secondName;
    private String secondScore;
    private String title;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDealId() {
        return dealId;
    }

    public void setDealId(String dealId) {
        this.dealId = dealId;
    }

    public String getFirstId() {
        return firstId;
    }

    public void setFirstId(String firstId) {
        this.firstId = firstId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getFirstScore() {
        return firstScore;
    }

    public void setFirstScore(String firstScore) {
        this.firstScore = firstScore;
    }

    public String getSecondId() {
        return secondId;
    }

    public void setSecondId(String secondId) {
        this.secondId = secondId;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public String getSecondScore() {
        return secondScore;
    }

    public void setSecondScore(String secondScore) {
        this.secondScore = secondScore;
    }
}
