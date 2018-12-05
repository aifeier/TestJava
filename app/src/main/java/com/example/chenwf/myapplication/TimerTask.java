package com.example.chenwf.myapplication;

/**
 * @author chenwf
 * @date 2018/12/5
 * @company Fotile智能厨电研究院
 */
public abstract class TimerTask implements Runnable {
    final Object lock = new Object();
    static final int VIRGIN = 0;
    static final int SCHEDULED = 1;
    static final int EXECUTED = 2;
    static final int CANCELLED = 3;
    int state = 0;
    long period = 0L;
    long nextExecutionTime;
}
