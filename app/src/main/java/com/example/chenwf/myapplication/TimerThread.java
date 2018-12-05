package com.example.chenwf.myapplication;


/**
 * @author chenwf
 * @date 2018/12/5
 * @company Fotile智能厨电研究院
 */
class TimerThread extends Thread {
    boolean newTasksMayBeScheduled = true;
    private TaskQueue queue;
    private long frequency;

    TimerThread(TaskQueue var1) {
        this.queue = var1;
        frequency = Integer.MAX_VALUE;
    }

    public void setFrequency(long frequency) {
        this.frequency = frequency;
    }

    public void run() {
        boolean var9 = false;

        try {
            var9 = true;
            this.mainLoop();
            var9 = false;
        } finally {
            if (var9) {
                TaskQueue var4 = this.queue;
                synchronized (this.queue) {
                    this.newTasksMayBeScheduled = false;
                    this.queue.clear();
                }
            }
        }

        TaskQueue var1 = this.queue;
        synchronized (this.queue) {
            this.newTasksMayBeScheduled = false;
            this.queue.clear();
        }
    }

    private void mainLoop() {
        while (true) {
            while (true) {
                try {
                    TaskQueue var3 = this.queue;
                    TimerTask var1;
                    boolean var2;
                    synchronized (this.queue) {
                        if(frequency <= 0){
                            //关闭计时器
                            newTasksMayBeScheduled = false;
                            this.queue.clear();
                            this.queue.notify();
                            return;
                        }
                        while (this.queue.isEmpty() && this.newTasksMayBeScheduled) {
                            this.queue.wait();
                        }

                        if (this.queue.isEmpty()) {
                            return;
                        }

                        var1 = this.queue.getMin();
                        Object var8 = var1.lock;
                        long var4;
                        long var6;
                        synchronized (var1.lock) {
                            if (var1.state == 3) {
                                this.queue.removeMin();
                                continue;
                            }

                            var4 = System.currentTimeMillis();
                            var6 = var1.nextExecutionTime;
                            if (var2 = var6 <= var4) {
                                if (var1.period == 0L) {
                                    this.queue.removeMin();
                                    var1.state = 2;
                                } else {
                                    this.queue.rescheduleMin(var1.period < 0L ? var4 - var1.period : var6 + var1.period);
                                }
                            }
                        }

                        if (!var2) {
                            this.queue.wait(var6 - var4);
                        }
                    }

                    if (var2) {
                        System.out.println(frequency--%2==0?"偶数":"奇数");
                        var1.run();
                    }
                } catch (InterruptedException var13) {
                    ;
                }
            }
        }
    }
}
