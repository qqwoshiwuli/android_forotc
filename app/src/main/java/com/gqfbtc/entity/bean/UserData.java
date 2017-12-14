package com.gqfbtc.entity.bean;

/**
 * Created by 郭青枫 on 2017/11/15.
 */

public class UserData {
    /**
     * id : 4
     * name :
     * nickName : 多撒好的集散
     * password : 68b1cea99091b9edbe1ad587e9f7a436
     * email : 504114410@qq.com
     * phone : 15896559159
     * createTime : 1510196255000
     * updateTime : 1510310584000
     * avatar : 691.png
     * canSendAd : true
     * canTrade : true
     * canWithdraw : true
     * isAuth : false
     * countSumStr : 交易数量：2次
     * scoreStr : 好评：0分
     */

    private int id;
    private String name;
    private String nickName;
    private String password;
    private String email;
    private String phone;
    private long createTime;
    private long updateTime;
    private String avatar;
    private boolean canSendAd;
    private boolean canTrade;
    private boolean canWithdraw;
    private boolean isAuth;
    private String countSumStr;
    private String scoreStr;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
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

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public boolean isCanSendAd() {
        return canSendAd;
    }

    public void setCanSendAd(boolean canSendAd) {
        this.canSendAd = canSendAd;
    }

    public boolean isCanTrade() {
        return canTrade;
    }

    public void setCanTrade(boolean canTrade) {
        this.canTrade = canTrade;
    }

    public boolean isCanWithdraw() {
        return canWithdraw;
    }

    public void setCanWithdraw(boolean canWithdraw) {
        this.canWithdraw = canWithdraw;
    }

    public boolean isIsAuth() {
        return isAuth;
    }

    public void setIsAuth(boolean isAuth) {
        this.isAuth = isAuth;
    }

    public String getCountSumStr() {
        return countSumStr;
    }

    public void setCountSumStr(String countSumStr) {
        this.countSumStr = countSumStr;
    }

    public String getScoreStr() {
        return scoreStr;
    }

    public void setScoreStr(String scoreStr) {
        this.scoreStr = scoreStr;
    }
}
