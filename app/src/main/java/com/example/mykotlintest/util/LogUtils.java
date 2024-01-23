package com.example.mykotlintest.util;

import android.util.Log;

import com.example.mykotlintest.Constant;
import com.google.gson.Gson;


/**
 * 日志类
 */
public class LogUtils {

//    private static boolean isDebuggable() {
//        return BuildConfig.DEBUG;
//    }


    public static void d(String tag ,String message){
        if (!Constant.debugLog)
            return;

        Log.d(tag, message);


    }


    public static void i(String tag ,String message){
        if (!Constant.debugLog)
            return;

        Log.i(tag, message);
    }

    public static void e(String tag ,String message){
        if (!Constant.debugLog)
            return;

        Log.e(tag, message);
    }
}
