package com.miqt.pluginlib.tools;

import android.os.SystemClock;
import android.util.Log;

import com.miqt.pluginlib.annotation.IgnoreMethodHook;

import java.util.Stack;

@IgnoreMethodHook
public class MethodHookPrint implements IMethodHookHandler {
    private final ThreadLocal<Stack<Long>> local = new ThreadLocal<>();

    @Override
    public void onMethodEnter(Object thisObj, String className, String methodName, String argsType, String returnType, Object... args) {
        Stack<Long> queue = local.get();
        if (queue == null) {
            queue = new Stack<>();
            local.set(queue);
        }
        queue.push(SystemClock.elapsedRealtime());
    }

    @Override
    public void onMethodReturn(Object returnObj, Object thisObj, String className, String methodName, String argsType, String returnType, Object... args) {
        Stack<Long> queue = local.get();
        assert queue != null;
        Long time = queue.pop();
        if (time != null) {
            long duc = SystemClock.elapsedRealtime() - time;
            String value = (thisObj == null ? className : className + "@" + Integer.toHexString(System.identityHashCode(thisObj))) +
                    "." + methodName +
                    "():[" + duc + "ms]";

            if (duc >= 1000) {
                Log.e("MethodHookHandler", value);
            } else if (duc >= 600) {
                Log.w("MethodHookHandler", value);
            } else if (duc >= 300) {
                Log.d("MethodHookHandler", value);
            } else if (duc >= 50) {
                Log.i("MethodHookHandler", value);
            }
        }
    }
}
