package com.cmy.bigsnow.http;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @Author : mengyuan.cheng
 * @Version : 2017/8/7
 * @E-mail : mengyuan.cheng.mier@gmail.com
 * @Description : 服务工厂类
 */

public class ServiceFactory {
    private final Gson mGson;
    private OkHttpClient mOkHttpClient;

    private ServiceFactory() {
        mGson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd hh:mm:ss")
                .create();
        mOkHttpClient = OkHttpProvider.getDefaultOkHttpClient();

    }

    public static ServiceFactory getInstance() {
        return SingletonHolder.INSTANCE;
    }

    private static class SingletonHolder {
        private static final ServiceFactory INSTANCE = new ServiceFactory();
    }

    public <S> S createService(Class<S> serviceClass) {
        String baseUrl = "http://gank.io/api/";
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(mOkHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        return retrofit.create(serviceClass);
    }
}
