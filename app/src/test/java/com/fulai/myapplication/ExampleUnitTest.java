package com.fulai.myapplication;

import org.junit.Test;

import java.util.HashMap;

import static org.junit.Assert.assertEquals;

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
    public void testHashMap() {
        HashMap<String, String> hashMap = new HashMap<>();
        String s1 = hashMap.put("1", "one");
        System.out.println(s1);
        String s = hashMap.put("1", "first");
        System.out.println(s);
    }



}