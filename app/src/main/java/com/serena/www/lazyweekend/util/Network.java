package com.serena.www.lazyweekend.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.serena.www.lazyweekend.contenst.Constant;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author Yuan
 * @time 2016/7/26  1:07
 * @desc ${TODD}
 */
public class Network {
    public static final int DEFAULT_TIMEOUT = 5;

    public Retrofit mRetrofit;
    public ApiService mService;

    //私有化构造方法
    private Network() {

        OkHttpClient okHttpClient = new OkHttpClient().newBuilder()
                .readTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                .connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                .build();

        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ").serializeNulls().create();

        mRetrofit = new Retrofit.Builder()
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .baseUrl(Constant.BASE_URL)
                .build();

        mService = mRetrofit.create(ApiService.class);
    }

    //在访问时创建单例(静态内部类式的单例)
    private static class SingletonHolder {
        private static final Network INSTANCE = new Network();
    }

    //获取单例
    public static Network getInstance() {
        return SingletonHolder.INSTANCE;
    }
}
