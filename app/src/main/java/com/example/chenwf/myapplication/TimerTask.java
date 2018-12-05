package com.example.chenwf.myapplication;

/**
 * @author chenwf
 * @date 2018/12/5
 * @company Fotile智能厨电研究院
 */
public abstract class TimerTask implements Runnable {
    final Object lock = new Object();
    int state = 0;
    long period = 0L;
    long nextExecutionTime;
}
