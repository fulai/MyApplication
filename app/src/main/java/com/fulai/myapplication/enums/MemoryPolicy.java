package com.fulai.myapplication.enums;

/**
 * Created by Dengmao on 17/8/8.
 */

public enum MemoryPolicy {
    NO_CACHE(1 << 0),
    NO_STORE(1 << 1);


    static boolean shouldReadFromMemoryCache(int memoryPolicy) {
        return (memoryPolicy & NO_CACHE.index) == 0;
    }

    static boolean shouldWriteToMemoryCache(int memoryPolicy) {
        return (memoryPolicy & NO_STORE.index) == 0;
    }

    int index;

    MemoryPolicy(int index) {
        this.index = index;
    }
}
