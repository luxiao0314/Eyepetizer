package com.eyepetizer.android.util;

import android.os.SystemClock;
import android.util.Log;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class MethodHook {

    private static final Map<String, Long> times = new ConcurrentHashMap<>();

    private static Long startTime = 0L;

    public static void enter(Object thisObj, String className, String methodName, String argsType, String returnType, Object... args) {
        startTime = SystemClock.elapsedRealtime();
    }

    public static void exit(Object thisObj, String className, String methodName, String argsType, String returnType, Object... args) {

        long duc = SystemClock.elapsedRealtime() - startTime;

        String value = (thisObj == null ? className : className + "@" + Integer.toHexString(System.identityHashCode(thisObj))) + "." + methodName + "():[" + duc + "ms]";

        if (duc >= 1000) {
            Log.e("MethodHook", value);
        } else if (duc >= 600) {
            Log.w("MethodHook", value);
        } else if (duc >= 300) {
            Log.d("MethodHook", value);
        } else if (duc >= 50) {
            Log.i("MethodHook", value);
        }
    }

    public static void onClick(Object thisObj, String className, String methodName, String argsType, String returnType, Object... args) {

    }
}