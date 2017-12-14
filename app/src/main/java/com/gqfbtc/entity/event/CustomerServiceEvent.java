package com.gqfbtc.entity.event;

/**
 * Created by 郭青枫 on 2017/11/16.
 */

public class CustomerServiceEvent {

    String title;
    String msg;

    public CustomerServiceEvent(String title,
                                String msg) {
        this.title = title;
        this.msg = msg;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
