package com.fivefivelike.mybaselibrary.entity;


/**
 * Created by 郭青枫 on 2016/11/10.
 */
public class ToolbarBuilder {
    private String title;//标题
    private String subTitle;//右边文字
    private String backTxt = "返回";//右边文字
    private boolean isShowBack = true;//是否显示返回按钮
    private int mRightImg1;//右边第一个图标按钮
    private int mRightImg2;//右边第二个图标按钮
    private int mRightImg3;//右边第二个图标按钮
    private int mToolbarBackColor;//toolbar背景颜色
    private boolean isTitleShow = true;//是否显示标题
    private int layoutBarBack;
    private int statusBack;
    private int navigationIcon;
    private boolean isCancelDialogAndFinalActivity = true;

    public boolean isCancelDialogAndFinalActivity() {
        return isCancelDialogAndFinalActivity;
    }

    public ToolbarBuilder setCancelDialogAndFinalActivity(boolean cancelDialogAndFinalActivity) {
        isCancelDialogAndFinalActivity = cancelDialogAndFinalActivity;
        return this;
    }

    public String getBackTxt() {
        return backTxt;
    }

    public void setBackTxt(String backTxt) {
        this.backTxt = backTxt;
    }

    public int getNavigationIcon() {
        return navigationIcon;
    }

    public ToolbarBuilder setNavigationIcon(int navigationIcon) {
        this.navigationIcon = navigationIcon;
        return this;
    }

    public int getStatusBack() {
        return statusBack;
    }

    public ToolbarBuilder setStatusBack(int statusBack) {
        this.statusBack = statusBack;
        return this;
    }

    public int getLayoutBarBack() {
        return layoutBarBack;
    }

    public ToolbarBuilder setLayoutBarBack(int layoutBarBack) {
        this.layoutBarBack = layoutBarBack;
        return this;
    }

    public boolean isTitleShow() {
        return isTitleShow;
    }

    public ToolbarBuilder setTitleShow(boolean titleShow) {
        isTitleShow = titleShow;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public ToolbarBuilder setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getSubTitle() {
        return subTitle;
    }

    public ToolbarBuilder setSubTitle(String subTitle) {
        this.subTitle = subTitle;
        return this;
    }

    public boolean isShowBack() {
        return isShowBack;
    }

    public ToolbarBuilder setShowBack(boolean showBack) {
        isShowBack = showBack;
        return this;
    }

    public int getmRightImg1() {
        return mRightImg1;
    }

    public ToolbarBuilder setmRightImg1(int mRightImg1) {
        this.mRightImg1 = mRightImg1;
        return this;
    }

    public int getmToolbarBackColor() {
        return mToolbarBackColor;
    }

    public ToolbarBuilder setmToolbarBackColor(int mToolbarBackColor) {
        this.mToolbarBackColor = mToolbarBackColor;
        return this;
    }

    public int getmRightImg2() {
        return mRightImg2;
    }

    public ToolbarBuilder setmRightImg2(int mRightImg2) {
        this.mRightImg2 = mRightImg2;
        return this;
    }

    public int getmRightImg3() {
        return mRightImg3;
    }

    public ToolbarBuilder setmRightImg3(int mRightImg3) {
        this.mRightImg3 = mRightImg3;
        return this;
    }
}
