package com.example.chenwf.myapplication;
/*
* 题目：（主要考察编码规范性）
Timer,定时器，功能是在指定的时间间隔内反复触发事件，现需要实现Timer模块，要求如下：
1. 定时器的精度为ms
2. 允许用户自定定时器重复的次数
3. 利用Hook模式，触发定时事件之后调用用户自定义事件处理函数
4. 利用Timer模块实现如下功能“当定时器发生的次数是偶数次，则向控制台打印‘偶数’，否则打印‘奇数’”
* */


import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author chenwf
 * @date 2018/12/5
 * @company Fotile智能厨电研究院
 */
public class Timer {

    //测试
    public static void main(String[] res) {
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                //do somethings
                System.out.println(System.currentTimeMillis());
            }
        }, 0, 1, 5);
    }

    private final TaskQueue queue;
    private final TimerThread thread;
    private static final AtomicInteger nextSerialNumber = new AtomicInteger(0);

    private static int serialNumber() {
        return nextSerialNumber.getAndIncrement();
    }

    public Timer() {
        this("Timer-" + serialNumber());
    }

    public Timer(String threadId) {
        this.queue = new TaskQueue();
        this.thread = new TimerThread(this.queue);
        this.thread.setName(threadId);
        this.thread.start();
    }

    //创建定时器
    public void schedule(TimerTask task, long delay, long period, long frequency) {
        if (delay < 0L) {
            throw new IllegalArgumentException("Negative delay.");
        } else if (period <= 0L) {
            throw new IllegalArgumentException("Non-positive period.");
        } else if (frequency <= 0L) {
            throw new IllegalArgumentException("Negative frequency.");
        } else {
            this.sched(task, System.currentTimeMillis() + delay, -period, frequency);
        }
    }

    private void sched(TimerTask task, long delay, long period, long frequency) {
        if (delay < 0L) {
            throw new IllegalArgumentException("Illegal execution time.");
        } else {
            if (Math.abs(period) > 4611686018427387903L) {
                period >>= 1;
            }
            thread.setFrequency(frequency);
            synchronized (this.queue) {
                if (!this.thread.newTasksMayBeScheduled) {
                    throw new IllegalStateException("Timer already cancelled.");
                } else {
                    synchronized (task.lock) {
                        if (task.state != 0) {
                            throw new IllegalStateException("Task already scheduled or cancelled");
                        }

                        task.nextExecutionTime = delay;
                        task.period = period;
                        task.state = 1;
                    }

                    this.queue.add(task);
                    if (this.queue.getMin() == task) {
                        this.queue.notify();
                    }

                }
            }
        }
    }

    //取消定时器
    public void cancel() {
        synchronized (this.queue) {
            this.thread.newTasksMayBeScheduled = false;
            this.queue.clear();
            this.queue.notify();
        }
    }
}
