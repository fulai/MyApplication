package com.fulai.myapplication.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.fulai.myapplication.util.Utils;
import com.fulai.myapplication.permisson.SetttingPermisson;

import static android.content.Context.CONNECTIVITY_SERVICE;
import static android.content.Intent.ACTION_AIRPLANE_MODE_CHANGED;
import static android.net.ConnectivityManager.CONNECTIVITY_ACTION;

/**
 * Created by Dengmao on 17/8/10.
 */

public class NetWorkBroadcastReceiver extends BroadcastReceiver {
    private Context mContext;

    public NetWorkBroadcastReceiver(Context mContext) {
        this.mContext = mContext;
    }

    public void register() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(ACTION_AIRPLANE_MODE_CHANGED);
        if (SetttingPermisson.hasAccessNetWorkPermisson(mContext)) {
            intentFilter.addAction(CONNECTIVITY_ACTION);
        }
        mContext.registerReceiver(this, intentFilter);
    }

    public void unRegister() {
        mContext.unregisterReceiver(this);
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent == null) {
            return;
        }
        String action = intent.getAction();
        if (ACTION_AIRPLANE_MODE_CHANGED.equals(action)) {
            //飞行模式
        } else if (CONNECTIVITY_ACTION.equals(action)) {
            //网络发生变化
            ConnectivityManager cm = Utils.getService(mContext, CONNECTIVITY_SERVICE);
            NetworkInfo activeNetworkInfo = cm.getActiveNetworkInfo();
        }
    }
}
