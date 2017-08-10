package com.fulai.myapplication.threads;

import android.os.Process;
import android.support.annotation.NonNull;

import java.util.concurrent.ThreadFactory;

import static android.os.Process.THREAD_PRIORITY_BACKGROUND;

/**
 * Created by Dengmao on 17/8/9.
 */

public class MyFactoryThread implements ThreadFactory {
    @Override
    public Thread newThread(@NonNull Runnable runnable) {
        return new MynewThread(runnable);
    }

    private static class MynewThread extends Thread {
        public MynewThread(Runnable runnable) {
            super(runnable);
        }

        @Override
        public void run() {
            Process.setThreadPriority(THREAD_PRIORITY_BACKGROUND);
            super.run();
        }
    }
}
