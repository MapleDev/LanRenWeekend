package com.serena.www.lazyweekend.util;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * @author Serena
 * @time 2016/6/23  12:56
 * @desc Http请求的工具类
 */
public class HttpUtil {

    private static HttpUtil httpUtil;
    private OkHttpClient client;

    private HttpUtil() {
        client = new OkHttpClient.Builder()
                .connectTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(10, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .build();
    }

    public static HttpUtil getInstance() {
        if (httpUtil == null) {
            synchronized (HttpUtil.class){
                if(httpUtil==null) {
                    httpUtil = new HttpUtil();
                }
            }
        }
        return httpUtil;
    }

    public void doGet(String url , final HttpResponse response){
        Request request = new Request.Builder()
                .url(url)
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                response.onError("网络请求异常");
            }

            @Override
            public void onResponse(Call call, Response resp) throws IOException {
                if(!resp.isSuccessful()) {
                    response.onError("网络请求异常");
                }
                //根据响应获取请求体
                String content = resp.body().string();
                response.parseContent(content);
            }
        });
    }
}
