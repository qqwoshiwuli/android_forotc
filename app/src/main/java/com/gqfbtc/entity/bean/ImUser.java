package com.gqfbtc.entity.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

/**
 * Created by 郭青枫 on 2017/11/13.
 */
@Entity
public class ImUser {
    /**
     * code :
     * token : py278aoXZ8uDRNf3LYeDCIqJQbRh9DS2PibkB9guNVNlHnHujz3Ngk5O95WoWNI3W2f/UtJvXbI=
     * userId : 4u
     * errorMessage :
     * url :
     */
    @Id
    private Long id;
    private String code;
    private String token;
    private String userId;
    private String errorMessage;
    private String url;

    @Generated(hash = 474714816)
    public ImUser(Long id, String code, String token, String userId, String errorMessage,
                  String url) {
        this.id = id;
        this.code = code;
        this.token = token;
        this.userId = userId;
        this.errorMessage = errorMessage;
        this.url = url;
    }

    @Generated(hash = 1371478464)
    public ImUser() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
