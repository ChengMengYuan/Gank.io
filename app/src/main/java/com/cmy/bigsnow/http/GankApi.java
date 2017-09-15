package com.cmy.bigsnow.http;

import com.cmy.bigsnow.app.index.bean.CallBack;
import com.cmy.bigsnow.app.index.bean.CategoryData;
import com.cmy.bigsnow.app.index.bean.DailyList;
import com.cmy.bigsnow.bean.SearchResult;

import java.util.List;

import io.reactivex.Single;
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
    @GET("history/content/{count}/{pageIndex}")
    Single<CallBack> getWeekData(
            @Path("count") int count,
            @Path("pageIndex") int pageIndex
    );
    //    Observable<CallBack> getWeekData();


    /***
     * 根据类别查询干货 http://gank.io/api/data/Android/10/1
     *
     * @param type
     * @param pageIndex
     * @return
     */
    @GET("data/{type}/{count}/{pageIndex}")
    Single<CategoryData> getCommonDate(
            @Path("type") String type,
            @Path("count") int count,
            @Path("pageIndex") int pageIndex
    );

    /**
     * 获取某天的干货
     *
     * @param date http://gank.io/api/day/2015/08/06
     * @return
     */
    @GET("day/{date}")
    Single<DailyList> getRecentlyGanHuo(@Path("date") String date);

    /**
     * 搜索
     *
     * @param keyword
     * @param pageIndex
     * @return
     */
    @GET("search/query/{keyword}/category/{category}/count/10/page/{pageIndex}")
    Single<List<SearchResult>> search(
            @Path("category") String category,
            @Path("keyword") String keyword,
            @Path("pageIndex") int pageIndex
    );

}
