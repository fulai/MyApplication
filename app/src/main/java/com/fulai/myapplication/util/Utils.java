package com.fulai.myapplication.util;

import android.content.Context;

/**
 * Created by Dengmao on 17/8/10.
 */

public class Utils {

    public static <T> T getService(Context context, String name) {
        return (T) context.getSystemService(name);
    }

}
