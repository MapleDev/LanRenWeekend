package com.serena.www.lazyweekend.util;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * @author Serena
 * @time 2016/6/23  19:16
 * @desc SharePreference的数据存储的工具类
 */
public class SharePreferenceUtil {

    public static final String FILENAME = "netDownload";

    public static void putString(Context context, String title, String content) {
        SharedPreferences sp = context.getSharedPreferences(FILENAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = sp.edit();
        edit.putString(title, content);
        edit.commit();
    }

    public static String getString(Context context, String title) {
        SharedPreferences sp = context.getSharedPreferences(FILENAME, Context.MODE_PRIVATE);
        return sp.getString(title, "");
    }

    public static void putInt(Context context, String title, int content) {
        SharedPreferences sp = context.getSharedPreferences(FILENAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = sp.edit();
        edit.putInt(title, content);
        edit.commit();
    }

    public static int getInt(Context context, String title) {
        SharedPreferences sp = context.getSharedPreferences(FILENAME, Context.MODE_PRIVATE);
        return sp.getInt(title, 0);
    }

    public static void putLong(Context context, String title, long content) {
        SharedPreferences sp = context.getSharedPreferences(FILENAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = sp.edit();
        edit.putLong(title, content);
        edit.commit();
    }

    public static Long getLong(Context context, String title) {
        SharedPreferences sp = context.getSharedPreferences(FILENAME, Context.MODE_PRIVATE);
        return sp.getLong(title, 0);
    }

    public static void putBoolean(Context context, String title, boolean content) {
        SharedPreferences sp = context.getSharedPreferences(FILENAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = sp.edit();
        edit.putBoolean(title, content);
        edit.commit();
    }

    public static boolean getBoolean(Context context, String title) {
        SharedPreferences sp = context.getSharedPreferences(FILENAME, Context.MODE_PRIVATE);
        return sp.getBoolean(title, false);
    }

    public static void clear(Context context) {
        SharedPreferences sp = context.getSharedPreferences(FILENAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.clear();
        editor.apply();
    }
}
