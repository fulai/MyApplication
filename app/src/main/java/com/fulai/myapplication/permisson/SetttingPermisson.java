package com.fulai.myapplication.permisson;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;

/**
 * Created by Dengmao on 17/8/10.
 */

public class SetttingPermisson {

    public static boolean hasAccessNetWorkPermisson(Context context) {
        String name = Manifest.permission.ACCESS_NETWORK_STATE;
        return context.checkCallingOrSelfPermission(name) == PackageManager.PERMISSION_GRANTED;
    }
}
