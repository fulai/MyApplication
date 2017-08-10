package com.fulai.myapplication.util;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.annotation.NonNull;
import android.telephony.TelephonyManager;

import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by Dengmao on 17/8/10.
 */

public class PicassoExecutorService extends ThreadPoolExecutor {
    private static final int DEFAULT_THREAD_COUNT = 3;

    public PicassoExecutorService() {
        super(DEFAULT_THREAD_COUNT, DEFAULT_THREAD_COUNT, 0, TimeUnit.MILLISECONDS, new
                PriorityBlockingQueue<Runnable>(), new PicassoThreadFactory());
    }

    //Picasso的线程池，是根据网络情况进行创建
    public void adjustThreadCount(NetworkInfo info) {
        if (info == null || !info.isConnectedOrConnecting()) {
            setThreadCount(DEFAULT_THREAD_COUNT);
            return;
        }
        switch (info.getType()) {
            case ConnectivityManager.TYPE_WIFI:
            case ConnectivityManager.TYPE_WIMAX:
            case ConnectivityManager.TYPE_ETHERNET:
                setThreadCount(4);
                break;
            case ConnectivityManager.TYPE_MOBILE:
                switch (info.getSubtype()) {
                    case TelephonyManager.NETWORK_TYPE_LTE:  // 4G
                    case TelephonyManager.NETWORK_TYPE_HSPAP:
                    case TelephonyManager.NETWORK_TYPE_EHRPD:
                        setThreadCount(3);
                        break;
                    case TelephonyManager.NETWORK_TYPE_UMTS: // 3G
                    case TelephonyManager.NETWORK_TYPE_CDMA:
                    case TelephonyManager.NETWORK_TYPE_EVDO_0:
                    case TelephonyManager.NETWORK_TYPE_EVDO_A:
                    case TelephonyManager.NETWORK_TYPE_EVDO_B:
                        setThreadCount(2);
                        break;
                    case TelephonyManager.NETWORK_TYPE_GPRS: // 2G
                    case TelephonyManager.NETWORK_TYPE_EDGE:
                        setThreadCount(1);
                        break;
                    default:
                        setThreadCount(DEFAULT_THREAD_COUNT);
                }
                break;
            default:
                setThreadCount(DEFAULT_THREAD_COUNT);
        }

    }

    public void setThreadCount(int threadCount) {
        setCorePoolSize(threadCount);
        setMaximumPoolSize(threadCount);
    }

    public Future<?> submit(Runnable runnable) {
        PicassoFutureTask picassoFutureTask = new PicassoFutureTask(runnable);
        execute(runnable);
        return picassoFutureTask;
    }

    private static class PicassoFutureTask extends FutureTask<Void> {

        public PicassoFutureTask(@NonNull Runnable runnable) {
            super(runnable, null);
        }
    }


}
