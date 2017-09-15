package com.cmy.bigsnow.app.index.bean;

import java.io.Serializable;

/**
 * @Author : mengyuan.cheng
 * @Version : 2017/7/31
 * @E-mail : mengyuan.cheng.mier@gmail.com
 * @Description :
 */

public class DailyResults implements Serializable {
    private static final long serialVersionUID = 1111111111111111111L;
    /**
     * ID
     */
    private String _id;
    /**
     * 创建时间
     */
    private String createdAt;
    /**
     * 说明
     */
    private String desc;
    /**
     * 公开时间
     */
    private String publishedAt;
    /**
     * 类别
     */
    private String type;
    /**
     * 地址
     */
    private String url;
    /**
     * 是否使用
     */
    private boolean used;
    /**
     * 人员
     */
    private String who;


    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getDesc() {
        return desc;
    }


    public void setType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    public void setUsed(boolean used) {
        this.used = used;
    }

    public boolean getUsed() {
        return used;
    }

    public void setWho(String who) {
        this.who = who;
    }

    public String getWho() {
        return who;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getPublishedAt() {
        return publishedAt;
    }

    public void setPublishedAt(String publishedAt) {
        this.publishedAt = publishedAt;
    }
}
