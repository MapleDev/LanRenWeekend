package com.serena.www.lazyweekend.util;

import android.text.TextUtils;
import android.util.Log;

import com.serena.www.lazyweekend.home.bean.RResult;

/**
 * @author Serena
 * @time 2016/6/23  13:05
 * @desc ${TODD}
 */
public abstract class HttpResponse<T> {

    //定义的泛型类代表我们需要的数据类型
    /*Class<T> t = null;

    public HttpResponse(Class<T> t) {
        this.t = t;
    }*/

    //网络请求成功后回调的方法
    public abstract void onSuccess(T t);

    //网络请求失败后回调的方法
    public abstract void onError(String msg);

    public void parseContent(String json) {
        Log.d("ss", "json..." + json);
        if (TextUtils.isEmpty(json)) {
            onError("网络请求失败");
        }
        /*if (t == String.class) {
            onSuccess((T) json);
            return;
        }*/

        RResult rResult = JsonUtil.parse(json, RResult.class);
        if (rResult.getStatus() != 200) {
            onError("网络请求失败");
        } else {
            onSuccess((T) rResult.getResult().toString());
        }
    }

}
