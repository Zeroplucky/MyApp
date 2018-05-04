package com.example.utils;

import android.text.TextUtils;
import android.util.Log;

/**
 * Created by Administrator on 2017/12/28.
 */

public class Logger {

    static final String LOG_TAG = "xxx";

    static boolean DEBUG_TAG = false;

    public static void enable() {
        DEBUG_TAG = true;
    }

    public static void disable() {
        DEBUG_TAG = false;
    }

    public static boolean getDebugMode() {
        return DEBUG_TAG;
    }

    public static void i(String tag, String log) {
        if (DEBUG_TAG && log != null) {
            if (!TextUtils.isEmpty(log))
                Log.i(tag, log);
        }
    }

    public static void i(String log) {
        i(LOG_TAG, log);
    }

    public static void d(String tag, String log) {
        if (DEBUG_TAG && log != null) {
            if (!TextUtils.isEmpty(log))
                Log.d(tag, log);
        }
    }

    public static void d(String log) {
        d(LOG_TAG, log);
    }

    public static void w(String tag, String log) {
        if (DEBUG_TAG && log != null) {
            if (!TextUtils.isEmpty(log))
                Log.w(tag, log);
        }
    }

    public static void w(String log) {
        w(LOG_TAG, log);
    }

    public static void e(String log) {
        if (DEBUG_TAG) {
            if (!TextUtils.isEmpty(log))
                Log.e(LOG_TAG, log);
        }
    }

    public static void e(String Tag, String log) {
        if (DEBUG_TAG) {
            if (!TextUtils.isEmpty(log))
                Log.e(Tag, log);
        }
    }

    public static void e(String log, Exception e) {
        if (DEBUG_TAG) {
            if (!TextUtils.isEmpty(log))
                Log.e(LOG_TAG, log);
            e.printStackTrace();
        }
    }


}
