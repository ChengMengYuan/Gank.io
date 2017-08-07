package com.cmy.bigsnow.bean;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;

import java.io.Serializable;

/**
 * @Author : mengyuan.cheng
 * @Version : 2017/7/31
 * @E-mail : mengyuan.cheng.mier@gmail.com
 * @Description :
 */

public class ImageBean implements Serializable {
    private String url;
    private Bitmap bitmap;
    private Drawable drawable;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

    public Drawable getDrawable() {
        return drawable;
    }

    public void setDrawable(Drawable drawable) {
        this.drawable = drawable;
    }
}
