package com.gqfbtc.entity.bean;

import java.io.Serializable;

/**
 * Created by 郭青枫 on 2017/7/18.
 */

public class ShareEntity implements Serializable {
    /**
     * title : 周二研发学习会：持续能将“平凡”变为“非凡”
     * content :
     * pic : http://app.xkf2017.com/upload/news/430/smaller_20170718132033_981.png
     * url : http://app.xkf2017.com/views/?id=430&uid=5
     */

    private String title;
    private String content;
    private String pic;
    private String url;
    private String platform;

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
