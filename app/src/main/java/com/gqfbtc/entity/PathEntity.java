package com.gqfbtc.entity;

/**
 * Created by 郭青枫 on 2017/10/18.
 */

public class PathEntity {
    /**
     * id : 19
     * path : http://iwiller.365use.net/upload/task_album/11/20171011135929_575.jpg
     * small_path : http://iwiller.365use.net/upload/task_album/11/small_20171011135929_575.jpg
     */

    private String id;
    private String path;
    private String small_path;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getSmall_path() {
        return small_path;
    }

    public void setSmall_path(String small_path) {
        this.small_path = small_path;
    }
}
