package com.fulai.myapplication.service;

import android.content.Context;
import android.os.Process;

/**
 * Created by Dengmao on 17/9/2
 * 捕捉crash
 */

public class AppCrashHandler implements Thread.UncaughtExceptionHandler {
    private Thread.UncaughtExceptionHandler defaultUncaughtExceptionHandler;

    private static AppCrashHandler instance;
    private Context mContext;

    private AppCrashHandler() {
    }

    public static AppCrashHandler getInstance() {
        if (instance == null) {
            synchronized (AppCrashHandler.class) {
                if (instance == null) {
                    instance = new AppCrashHandler();
                }
            }
        }
        return instance;
    }


    public void init(Context context) {
        defaultUncaughtExceptionHandler = Thread.getDefaultUncaughtExceptionHandler();
        Thread.setDefaultUncaughtExceptionHandler(this);
        mContext = context;
    }

    @Override
    public void uncaughtException(Thread t, Throwable e) {

        //如果系统提供了默认的异常处理器，则交给系统去结束我们的程序，否则就由我们自己结束自己
        if (defaultUncaughtExceptionHandler != null) {
            defaultUncaughtExceptionHandler.uncaughtException(t, e);
        } else {
            Process.killProcess(Process.myPid());
        }
    }
}
