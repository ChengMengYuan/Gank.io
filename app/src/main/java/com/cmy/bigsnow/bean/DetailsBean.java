package com.cmy.bigsnow.bean;

import java.io.Serializable;
import java.util.Date;

/**
 * @Author : mengyuan.cheng
 * @Version : 2017/7/31
 * @E-mail : mengyuan.cheng.mier@gmail.com
 * @Description :
 */

public class DetailsBean implements Serializable {
    private static final long serialVersionUID = 1111111111111111111L;
    /**
     * ID
     */
    private String Id;
    /**
     * 创建时间
     */
    private Date createdat;
    /**
     * 说明
     */
    private String desc;
    /**
     * 公开时间
     */
    private Date publishedat;
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

    public void setId(String Id) {
        this.Id = Id;
    }

    public String getId() {
        return Id;
    }

    public void setCreatedat(Date createdat) {
        this.createdat = createdat;
    }

    public Date getCreatedat() {
        return createdat;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getDesc() {
        return desc;
    }

    public void setPublishedat(Date publishedat) {
        this.publishedat = publishedat;
    }

    public Date getPublishedat() {
        return publishedat;
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

}
