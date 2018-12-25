package com.example.chenwf.myapplication;

import com.example.chenwf.myapplication.ThreadPool.ThreadPool;

import org.junit.Test;

/**
 * @author chenwf
 * @date 2018/12/25
 * @company Fotile智能厨电研究院
 */
public class ThreadPoolTest {
    private static int i = 0;

    @Test
    public void TestBubbleSort() {
        for (i = 0; i < 10; i++) {
            ThreadPool.getInstance().execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(System.currentTimeMillis() + " runable: " + i);
                }
            });
        }
    }

}
