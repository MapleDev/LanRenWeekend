package com.serena.www.lazyweekend.util;

import android.text.TextUtils;

import com.google.gson.Gson;

/**
 * @author Serena
 * @time 2016/6/23  13:09
 * @desc Json解析的工具类
 */
public class JsonUtil {

    public static <T> T parse(String json,Class<T> tClass){
        try {
            if(TextUtils.isEmpty(json)) {
                return null;
            }
            Gson gson = new Gson();
            T t = gson.fromJson(json, tClass);
            return t;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
