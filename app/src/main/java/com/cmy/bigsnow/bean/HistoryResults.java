package com.cmy.bigsnow.bean;

import java.io.Serializable;

/**
 * @Author : mengyuan.cheng
 * @Version : 2017/7/31
 * @E-mail : mengyuan.cheng.mier@gmail.com
 * @Description :
 */

public class HistoryResults implements Serializable {
    private static final long serialVersionUID = 1111111111111111110L;
    private String _id;

    private String content;

    private String created_at;

    private String publishedAt;

    private String rand_id;

    private String title;

    private String updated_at;

    public void set_id(String _id) {
        this._id = _id;
    }

    public String get_id() {
        return this._id;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getContent() {
        return this.content;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getCreated_at() {
        return this.created_at;
    }

    public void setPublishedAt(String publishedAt) {
        this.publishedAt = publishedAt;
    }

    public String getPublishedAt() {
        return this.publishedAt;
    }

    public void setRand_id(String rand_id) {
        this.rand_id = rand_id;
    }

    public String getRand_id() {
        return this.rand_id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return this.title;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }

    public String getUpdated_at() {
        return this.updated_at;
    }
}
