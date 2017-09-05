package com.fulai.myapplication;

import org.junit.Test;

import io.reactivex.Observable;
import io.reactivex.functions.Consumer;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() throws Exception {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void doNextOperate() {
        //doOnNext发送之前做一些操作
        Observable.just("1").doOnNext(new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {
                System.out.println("doOnNext" + s);
            }
        }).subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {
                System.out.println("Observe" + s);
            }
        });
    }
}