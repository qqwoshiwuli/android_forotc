package com.gqfbtc.entity;

/**
 * Created by 郭青枫 on 2017/10/22.
 */

public class HomeRightPopuEntity {
    int id;
    String txt;

    public HomeRightPopuEntity(int id,
                               String txt) {
        this.id = id;
        this.txt = txt;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTxt() {
        return txt;
    }

    public void setTxt(String txt) {
        this.txt = txt;
    }
}
