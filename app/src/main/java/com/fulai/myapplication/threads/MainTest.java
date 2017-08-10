package com.fulai.myapplication.threads;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

/**
 * Created by Dengmao on 17/8/9.
 */

public class MainTest {
    public static void main(String[] args) {
        ExecutorService executor = Executors.newCachedThreadPool();
        FutureTask futureTask = new PicassoFutureTask(new Runnable() {
            @Override
            public void run() {
                System.out.println("run");
            }
        }, "good");
        Future<?> submit = executor.submit(futureTask);
        try {
            String string = (String) submit.get();
            System.out.println(string);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        futureTask = new PicassoFutureTask(new Callable<String>() {
            @Override
            public String call() throws Exception {
                return "call";
            }
        });
        submit = executor.submit(futureTask);

        try {
            String string = (String) submit.get();
            System.out.println(string);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        PicassoThread picassoThread = new PicassoThread(new Runnable() {
            @Override
            public void run() {
                System.out.println("out run");
            }
        });
        picassoThread.start();


    }
}
