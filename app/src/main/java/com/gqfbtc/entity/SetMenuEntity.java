package com.gqfbtc.entity;

/**
 * Created by Administrator on 2017/8/23.
 */

public class SetMenuEntity {

    String title = "";
    String content = "";

    int leftImgId = 0;
    int contentColorId=0;

    boolean isHavaContent = false;
    boolean isHavaCheck = false;
    boolean isHaveImg = false;
    boolean isHaveLine = false;
    boolean isHaveRightImg = true;

    boolean isCheck = false;

    String userImgPath = "";

    Class activityClass = null;


    String sutitle = "";

    public SetMenuEntity setContentColorId(int contentColorId) {
        this.contentColorId = contentColorId;
        return this;
    }

    public int getContentColorId() {
        return contentColorId;
    }

    public String getSutitle() {
        return sutitle;
    }

    public SetMenuEntity setSutitle(String sutitle) {
        this.sutitle = sutitle;
        return this;
    }

    public SetMenuEntity setCheck(boolean check) {
        isCheck = check;
        return this;
    }

    public boolean isCheck() {
        return isCheck;
    }

    public Class getActivityClass() {
        return activityClass;
    }

    public SetMenuEntity setActivityClass(Class activityClass) {
        this.activityClass = activityClass;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public int getLeftImgId() {
        return leftImgId;
    }


    public boolean isHavaContent() {
        return isHavaContent;
    }

    public boolean isHavaCheck() {
        return isHavaCheck;
    }

    public boolean isHaveImg() {
        return isHaveImg;
    }

    public boolean isHaveLine() {
        return isHaveLine;
    }

    public boolean isHaveRightImg() {
        return isHaveRightImg;
    }

    public String getUserImgPath() {
        return userImgPath;
    }


    public SetMenuEntity setTitle(String title) {
        this.title = title;
        return this;
    }

    public SetMenuEntity setContent(String content) {
        this.content = content;
        return this;
    }

    public SetMenuEntity setLeftImgId(int leftImgId) {
        this.leftImgId = leftImgId;
        return this;
    }

    public SetMenuEntity setHavaContent(boolean havaContent) {
        isHavaContent = havaContent;
        return this;
    }

    public SetMenuEntity setHavaCheck(boolean havaCheck) {
        isHavaCheck = havaCheck;
        return this;
    }

    public SetMenuEntity setHaveImg(boolean haveImg) {
        isHaveImg = haveImg;
        return this;
    }

    public SetMenuEntity setHaveLine(boolean haveLine) {
        isHaveLine = haveLine;
        return this;
    }

    public SetMenuEntity setHaveRightImg(boolean haveRightImg) {
        isHaveRightImg = haveRightImg;
        return this;
    }

    public SetMenuEntity setUserImgPath(String userImgPath) {
        this.userImgPath = userImgPath;
        return this;
    }


}
