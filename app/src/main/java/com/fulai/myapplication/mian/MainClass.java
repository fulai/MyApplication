package com.fulai.myapplication.mian;

import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;
import java.util.WeakHashMap;

/**
 * Created by Dengmao on 17/8/7.
 */

public class MainClass {
    public static void main(String[] args) {
        //WeakHashMap<String, String> weakHashMap = new WeakHashMap<>();
//        ReferenceQueue<Object> referenceQueue = new ReferenceQueue<>();
//        String string = "weakReference";
//        WeakReference<String> weakReference = new WeakReference<String>(string, referenceQueue);
//        WeakReference<String> temp = (WeakReference<String>) referenceQueue.poll();
//        if (temp != null) {
//            String str = temp.get();
//            System.out.println(str);
//        }
//        System.gc();
//        temp = (WeakReference<String>) referenceQueue.poll();
//        if (temp != null) {
//            String str = temp.get();
//            System.out.println(str);
//        }
        int a = 1 << 0;
        int b = 1 << 1;
        int c = 1 << 2;
        int d = 1 << 3;
        int temp = 7;
        if ((temp & d) == 0) {
            System.out.println("true");
        } else {
            System.out.println("false");
        }

    }
}
