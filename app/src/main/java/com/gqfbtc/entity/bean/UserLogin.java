package com.gqfbtc.entity.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by 郭青枫 on 2017/11/11.
 */
@Entity
public class UserLogin {
    /**
     * id : 11
     * name :
     * nickName :
     * password : 1f15d3ed58959c98c5139c7cd39f7ecd
     * email : 504114410@qq.com
     * phone : 15072311111
     * createTime : 1510320313000
     * updateTime : 1510368417000
     * avatar : 215.png
     * canSendAd : false
     * canTrade : false
     * canWithdraw : true
     * sponsor :
     * isAuth : false
     */

    @Id
    private long id;
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
    private String sponsor;
    private boolean isAuth;
    private String imToken;
    /**
     * canAssure : true
     * discount : 5
     * feeRate : 2
     * onLine : false
     */

    private boolean canAssure;
    private int discount;
    private int feeRate;
    private boolean onLine;


    @Generated(hash = 2055751242)
    public UserLogin(long id, String name, String nickName, String password,
            String email, String phone, long createTime, long updateTime,
            String avatar, boolean canSendAd, boolean canTrade, boolean canWithdraw,
            String sponsor, boolean isAuth, String imToken, boolean canAssure,
            int discount, int feeRate, boolean onLine) {
        this.id = id;
        this.name = name;
        this.nickName = nickName;
        this.password = password;
        this.email = email;
        this.phone = phone;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.avatar = avatar;
        this.canSendAd = canSendAd;
        this.canTrade = canTrade;
        this.canWithdraw = canWithdraw;
        this.sponsor = sponsor;
        this.isAuth = isAuth;
        this.imToken = imToken;
        this.canAssure = canAssure;
        this.discount = discount;
        this.feeRate = feeRate;
        this.onLine = onLine;
    }

    @Generated(hash = 180802810)
    public UserLogin() {
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
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

    public String getSponsor() {
        return sponsor;
    }

    public void setSponsor(String sponsor) {
        this.sponsor = sponsor;
    }

    public boolean isAuth() {
        return isAuth;
    }

    public void setAuth(boolean auth) {
        isAuth = auth;
    }

    public String getImToken() {
        return imToken;
    }

    public void setImToken(String imToken) {
        this.imToken = imToken;
    }

    public boolean isCanAssure() {
        return canAssure;
    }

    public void setCanAssure(boolean canAssure) {
        this.canAssure = canAssure;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    public int getFeeRate() {
        return feeRate;
    }

    public void setFeeRate(int feeRate) {
        this.feeRate = feeRate;
    }

    public boolean isOnLine() {
        return onLine;
    }

    public void setOnLine(boolean onLine) {
        this.onLine = onLine;
    }

    public boolean getCanSendAd() {
        return this.canSendAd;
    }

    public boolean getCanTrade() {
        return this.canTrade;
    }

    public boolean getCanWithdraw() {
        return this.canWithdraw;
    }

    public boolean getIsAuth() {
        return this.isAuth;
    }

    public void setIsAuth(boolean isAuth) {
        this.isAuth = isAuth;
    }

    public boolean getCanAssure() {
        return this.canAssure;
    }

    public boolean getOnLine() {
        return this.onLine;
    }
}
