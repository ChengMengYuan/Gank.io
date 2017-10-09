package com.cmy.bigsnow.app.index.bean;

import java.util.List;

/**
 * The type Daily list.
 *
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

    /**
     * Gets android.
     *
     * @return the android
     */
    public List<CategoryData> getAndroid() {
        return Android;
    }

    /**
     * Sets android.
     *
     * @param Android the android
     */
    public void setAndroid(List<CategoryData> Android) {
        this.Android = Android;
    }

    /**
     * Gets ios.
     *
     * @return the ios
     */
    public List<CategoryData> getIOS() {
        return iOS;
    }

    /**
     * Sets ios.
     *
     * @param iOS the os
     */
    public void setIOS(List<CategoryData> iOS) {
        this.iOS = iOS;
    }

    /**
     * Get 休息视频 list.
     *
     * @return the list
     */
    public List<CategoryData> get休息视频() {
        return 休息视频;
    }

    /**
     * Set 休息视频.
     *
     * @param 休息视频 the 休息视频
     */
    public void set休息视频(List<CategoryData> 休息视频) {
        this.休息视频 = 休息视频;
    }

    /**
     * Gets gan huo data bean.
     *
     * @return the gan huo data bean
     */
    public List<CategoryData> getGanHuoDataBean() {
        return 拓展资源;
    }

    /**
     * Set 拓展资源.
     *
     * @param 拓展资源 the 拓展资源
     */
    public void set拓展资源(List<CategoryData> 拓展资源) {
        this.拓展资源 = 拓展资源;
    }

    /**
     * Get 瞎推荐 list.
     *
     * @return the list
     */
    public List<CategoryData> get瞎推荐() {
        return 瞎推荐;
    }

    /**
     * Set 瞎推荐.
     *
     * @param 瞎推荐 the 瞎推荐
     */
    public void set瞎推荐(List<CategoryData> 瞎推荐) {
        this.瞎推荐 = 瞎推荐;
    }

    /**
     * Get 福利 list.
     *
     * @return the list
     */
    public List<CategoryData> get福利() {
        return 福利;
    }

    /**
     * Set 福利.
     *
     * @param 福利 the 福利
     */
    public void set福利(List<CategoryData> 福利) {
        this.福利 = 福利;
    }

    /**
     * Get 前端 list.
     *
     * @return the list
     */
    public List<CategoryData> get前端() {
        return 前端;
    }

    /**
     * Set 前端.
     *
     * @param 前端 the 前端
     */
    public void set前端(List<CategoryData> 前端) {
        this.前端 = 前端;
    }

    /**
     * Gets app.
     *
     * @return the app
     */
    public List<CategoryData> getApp() {
        return App;
    }

    /**
     * Sets app.
     *
     * @param app the app
     */
    public void setApp(List<CategoryData> app) {
        App = app;
    }
}
