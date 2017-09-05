package com.fulai.myapplication.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

/**
 * Created by Dengmao on 17/8/14.
 */

public class HeartBeatService extends Service {
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public void onDestroy() {
        Intent intent = new Intent(this, MessageService.class);
        startService(intent);
        super.onDestroy();
    }
}
