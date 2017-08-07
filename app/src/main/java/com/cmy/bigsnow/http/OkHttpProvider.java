package com.cmy.bigsnow.http;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by _SOLID
 * Date:2016/12/3
 * Time:13:44
 */

public class OkHttpProvider {

    private final static long DEFAULT_CONNECT_TIMEOUT = 5;
    private final static long DEFAULT_WRITE_TIMEOUT = 20;
    private final static long DEFAULT_READ_TIMEOUT = 10;

    public static OkHttpClient getDefaultOkHttpClient() {
        return getOkHttpClient();
    }

    public static OkHttpClient getOkHttpClient() {
        return getOkHttpClient(new FromNetWorkControlInterceptor());
    }

    private static OkHttpClient getOkHttpClient(Interceptor cacheControl) {
        //定制OkHttp
        OkHttpClient.Builder httpClientBuilder = new OkHttpClient.Builder();
        //设置超时时间
        httpClientBuilder.connectTimeout(DEFAULT_CONNECT_TIMEOUT, TimeUnit.SECONDS);
        httpClientBuilder.writeTimeout(DEFAULT_WRITE_TIMEOUT, TimeUnit.SECONDS);
        httpClientBuilder.readTimeout(DEFAULT_READ_TIMEOUT, TimeUnit.SECONDS);
        //设置缓存
//        File httpCacheDirectory = new File(SolidApplication.getInstance().getCacheDir(), "OkHttpCache");
        //        httpClientBuilder.cache(new Cache(httpCacheDirectory, 100 * 1024 * 1024));
        //设置拦截器
//        httpClientBuilder.addInterceptor(new UserAgentInterceptor("Android Device"));
//        httpClientBuilder.addInterceptor(new LoggingInterceptor());
        httpClientBuilder.addInterceptor(cacheControl);
        httpClientBuilder.addNetworkInterceptor(cacheControl);
        return httpClientBuilder.build();
    }



    /**
     * 强制从网络获取数据
     */
    private static class FromNetWorkControlInterceptor implements Interceptor {
        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request();
            request = request.newBuilder()
                    .cacheControl(CacheControl.FORCE_NETWORK)
                    .build();

            Response response = chain.proceed(request);

            return response;
        }
    }


}
