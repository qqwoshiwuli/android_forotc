package com.gqfbtc.entity.event;

/**
 * Created by 郭青枫 on 2017/11/16.
 */

public class TransactEvent {

    String id;
    String userId;
    String code;
    String msg;

    public TransactEvent(String userId,String groupName, String msg) {
        this.userId=userId;
        String[] info = groupName.split("_");
        this.msg = msg;
        if (info != null) {
            if (info.length > 0) {
                id = info[0];
                if (info.length > 1) {
                    code = info[1];
                }
            }
        }
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
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
}
