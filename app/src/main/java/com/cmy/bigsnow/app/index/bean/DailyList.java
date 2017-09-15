package com.cmy.bigsnow.app.index.bean;

import java.util.List;

/**
 * @Author : mengyuan.cheng
 * @Version : 2017/8/7
 * @E-mail : mengyuan.cheng.mier@gmail.com
 * @Description :
 */

public class DailyList {
    private List<CategoryData> Android;
    private List<CategoryData> iOS;
    private List<CategoryData> 休息视频;
    private List<CategoryData> 拓展资源;
    private List<CategoryData> 瞎推荐;
    private List<CategoryData> 福利;
    private List<CategoryData> App;
    private List<CategoryData> 前端;

    public List<CategoryData> getAndroid() {
        return Android;
    }

    public void setAndroid(List<CategoryData> Android) {
        this.Android = Android;
    }

    public List<CategoryData> getIOS() {
        return iOS;
    }

    public void setIOS(List<CategoryData> iOS) {
        this.iOS = iOS;
    }

    public List<CategoryData> get休息视频() {
        return 休息视频;
    }

    public void set休息视频(List<CategoryData> 休息视频) {
        this.休息视频 = 休息视频;
    }

    public List<CategoryData> getGanHuoDataBean() {
        return 拓展资源;
    }

    public void set拓展资源(List<CategoryData> 拓展资源) {
        this.拓展资源 = 拓展资源;
    }

    public List<CategoryData> get瞎推荐() {
        return 瞎推荐;
    }

    public void set瞎推荐(List<CategoryData> 瞎推荐) {
        this.瞎推荐 = 瞎推荐;
    }

    public List<CategoryData> get福利() {
        return 福利;
    }

    public void set福利(List<CategoryData> 福利) {
        this.福利 = 福利;
    }

    public List<CategoryData> get前端() {
        return 前端;
    }

    public void set前端(List<CategoryData> 前端) {
        this.前端 = 前端;
    }

    public List<CategoryData> getApp() {
        return App;
    }

    public void setApp(List<CategoryData> app) {
        App = app;
    }
}
