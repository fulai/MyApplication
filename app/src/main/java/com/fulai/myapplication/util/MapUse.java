package com.fulai.myapplication.util;

import android.support.v4.util.ArrayMap;
import android.util.SparseArray;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * Created by Dengmao on 17/8/10.
 */

public class MapUse {
    public void hashMapUse() {
        Map<String, String> map = new HashMap<>();
        map.put("1", "value1");
        map.put("2", "value2");
        map.get("1");
        Set<Map.Entry<String, String>> entries = map.entrySet();
        Iterator<?> iterator = entries.iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, String> entry = (Map.Entry<String, String>) iterator.next();
            String key = entry.getKey();
            String value = entry.getValue();
        }
    }

    public static void arrayMapUse() {
        Map<String, String> map = new ArrayMap<>();
        map.put("1", "value1");
        map.put("2", "value2");
        map.get("1");
        Set<Map.Entry<String, String>> entries = map.entrySet();
        Iterator<?> iterator = entries.iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, String> entry = (Map.Entry<String, String>) iterator.next();
            String key = entry.getKey();
            String value = entry.getValue();
        }
    }

    public static void main(String[] args) {
        //sparseArrayUse();
        arrayMapUse();
    }

    public static void sparseArrayUse() {
        SparseArray<String> sparseArray = new SparseArray<>();
        sparseArray.put(1, "value1");
        sparseArray.put(2, "value2");
        sparseArray.get(1);
        int size = sparseArray.size();
        for (int i = 0; i < size; i++) {
            int i1 = sparseArray.keyAt(i);
            String value = sparseArray.valueAt(i);
            System.out.println(i1);
            System.out.println(value);
        }
    }


}
