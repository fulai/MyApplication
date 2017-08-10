package com.fulai.myapplication.util;

import android.os.Process;
import android.support.annotation.NonNull;

import java.util.concurrent.ThreadFactory;

/**
 * Created by Dengmao on 17/8/10.
 */

public class PicassoThreadFactory implements ThreadFactory {
    @Override
    public Thread newThread(@NonNull Runnable runnable) {
        return new PicassoThread(runnable);
    }

    private static class PicassoThread extends Thread {
        public PicassoThread(Runnable runnable) {
            super(runnable);
        }

        @Override
        public void run() {
            Process.setThreadPriority(Process.THREAD_PRIORITY_BACKGROUND);
            super.run();
        }
    }
}
