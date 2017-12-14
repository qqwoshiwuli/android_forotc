package com.gqfbtc.entity.bean;

/**
 * Created by 郭青枫 on 2017/11/14.
 */

public class AdvertisingMessage {
    /**
     * adMessageId : 9
     * nickName : 皑皑
     * avatar : 277.png
     * createTime : 1510297168000
     * content : "我非常热爱****"
     */

    private int adMessageId;
    private String nickName;
    private String avatar;
    private long createTime;
    private String content;

    public int getAdMessageId() {
        return adMessageId;
    }

    public void setAdMessageId(int adMessageId) {
        this.adMessageId = adMessageId;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
