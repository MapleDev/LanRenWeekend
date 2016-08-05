package com.serena.www.lazyweekend.util;

import android.app.Activity;
import android.widget.Toast;

/**
 * @author MxBox
 * @time 2016/6/5  0:59
 * @desc 主线程和子线程都能显示的Toast
 */
public class ToastUtils {
    public static void show(final Activity activity,final String text){
        if("main".equalsIgnoreCase(Thread.currentThread().getName())){
            Toast.makeText(activity,text,Toast.LENGTH_SHORT).show();
        }else {
            activity.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(activity,text,Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}
