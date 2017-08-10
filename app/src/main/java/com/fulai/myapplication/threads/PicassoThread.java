package com.fulai.myapplication.threads;

/**
 * Created by Dengmao on 17/8/9.
 */

public class PicassoThread extends Thread {

    public PicassoThread(Runnable r) {
        super(r);
    }

    @Override
    public void run() {
        System.out.println("PicassoThread run");
        super.run();
    }
}
