package com.cmy.bigsnow.utils;

import android.app.Activity;
import android.app.Application;
import android.content.Context;

import com.cmy.bigsnow.greendao.GreenDaoManager;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ：     cmy
 * @version :     2017/7/19.
 * @e-mil ：      mengyuan.cheng.mier@gmail.com
 * @Description : 将所有的activity添加到List中,方便退出时统一管理
 */
public class ActivityUtil extends Application {
    public static List<Integer> BGResources = new ArrayList<Integer>();
    public static List<Object> activitys = new ArrayList<>();
    private static ActivityUtil instance;
    private static Context mContext;

    /**
     * 获取单例模式中唯一的MyApplication实例
     */
    public static ActivityUtil getInstance() {
        if (instance == null) {
            instance = new ActivityUtil();
        }
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = getApplicationContext();
        //初始化图片加载框架
        Fresco.initialize(mContext);
        //初始化Log
        Logger.addLogAdapter(new AndroidLogAdapter());
        //初始化数据库
        GreenDaoManager.getInstance();

    }

    public static Context getContext() {
        return mContext;
    }

    /**
     * 添加activity到容器中
     *
     * @param activity
     */
    public void addActivity(Activity activity) {
        if (!activitys.contains(activity)) {
            activitys.contains(activity);
        }
    }

    /**
     * 退出App时调用该方法
     * 遍历所有activity并且finish。
     */
    public void destory() {
        for (Object activity : activitys) {
            ((Activity) activity).finish();
        }
        System.exit(0);
    }
}
