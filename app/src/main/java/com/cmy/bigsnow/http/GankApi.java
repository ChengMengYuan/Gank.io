package com.cmy.bigsnow.http;

import com.cmy.bigsnow.bean.CallBack;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * @Author : mengyuan.cheng
 * @Version : 2017/7/28
 * @E-mail : mengyuan.cheng.mier@gmail.com
 * @Description : 网络请求接口
 */

public interface GankApi {

    /**
     * 获取某几日干货网站数据:
     * http://gank.io/api/history/content/7/1
     * 7 代表 7 个数据，1 代表：取第一页数据
     *
     * @return
     */
    @GET("history/content/7/1")
    Call<CallBack> getWeekData();

    //http://gank.io/api/data/Android/10/1
    @GET("data/{type}/{count}/{pageIndex}")
    Call<CallBack> getCommonDate(@Path("type") String type,
                                 @Path("count") int count,
                                 @Path("pageIndex") int pageIndex
    );

}
