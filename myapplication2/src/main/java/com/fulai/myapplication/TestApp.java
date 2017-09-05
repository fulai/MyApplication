package com.fulai.myapplication;

import android.app.Application;

import com.fulai.myapplication.service.AppCrashHandler;

/**
 * Created by Dengmao on 17/9/2.
 */

public class TestApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
//        CrashHandler crashHandler = CrashHandler.getInstance();
//        crashHandler.init(this);
        AppCrashHandler appCrashHandler = AppCrashHandler.getInstance();
        appCrashHandler.init(this);
    }
}
