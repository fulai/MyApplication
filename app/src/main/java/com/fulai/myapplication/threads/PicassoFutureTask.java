package com.fulai.myapplication.threads;

import android.support.annotation.NonNull;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

/**
 * Created by Dengmao on 17/8/9.
 */

public class PicassoFutureTask extends FutureTask<String> {

    public PicassoFutureTask(@NonNull Callable<String> callable) {
        super(callable);
    }

    public PicassoFutureTask(@NonNull Runnable runnable, String result) {
        super(runnable, result);
    }
}
