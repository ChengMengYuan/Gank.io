package com.cmy.bigsnow.app.index.bean;

import java.io.Serializable;

/**
 * The type Daily results.
 *
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


    /**
     * Sets desc.
     *
     * @param desc the desc
     */
    public void setDesc(String desc) {
        this.desc = desc;
    }

    /**
     * Gets desc.
     *
     * @return the desc
     */
    public String getDesc() {
        return desc;
    }


    /**
     * Sets type.
     *
     * @param type the type
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * Gets type.
     *
     * @return the type
     */
    public String getType() {
        return type;
    }

    /**
     * Sets url.
     *
     * @param url the url
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * Gets url.
     *
     * @return the url
     */
    public String getUrl() {
        return url;
    }

    /**
     * Sets used.
     *
     * @param used the used
     */
    public void setUsed(boolean used) {
        this.used = used;
    }

    /**
     * Gets used.
     *
     * @return the used
     */
    public boolean getUsed() {
        return used;
    }

    /**
     * Sets who.
     *
     * @param who the who
     */
    public void setWho(String who) {
        this.who = who;
    }

    /**
     * Gets who.
     *
     * @return the who
     */
    public String getWho() {
        return who;
    }

    /**
     * Gets id.
     *
     * @return the id
     */
    public String get_id() {
        return _id;
    }

    /**
     * Sets id.
     *
     * @param _id the id
     */
    public void set_id(String _id) {
        this._id = _id;
    }

    /**
     * Gets created at.
     *
     * @return the created at
     */
    public String getCreatedAt() {
        return createdAt;
    }

    /**
     * Sets created at.
     *
     * @param createdAt the created at
     */
    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    /**
     * Gets published at.
     *
     * @return the published at
     */
    public String getPublishedAt() {
        return publishedAt;
    }

    /**
     * Sets published at.
     *
     * @param publishedAt the published at
     */
    public void setPublishedAt(String publishedAt) {
        this.publishedAt = publishedAt;
    }
}
