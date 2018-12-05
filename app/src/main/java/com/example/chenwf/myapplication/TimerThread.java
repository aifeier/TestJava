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

    TimerThread(TaskQueue queue) {
        this.queue = queue;
        frequency = Integer.MAX_VALUE;
    }

    public void setFrequency(long frequency) {
        this.frequency = frequency;
    }

    public void run() {
        boolean fail = false;

        try {
            fail = true;
            this.mainLoop();
            fail = false;
        } finally {
            if (fail) {
                TaskQueue queue = this.queue;
                synchronized (this.queue) {
                    this.newTasksMayBeScheduled = false;
                    this.queue.clear();
                }
            }
        }

        TaskQueue queue = this.queue;
        synchronized (this.queue) {
            this.newTasksMayBeScheduled = false;
            this.queue.clear();
        }
    }

    private void mainLoop() {
        while (true) {
            while (true) {
                try {
                    TaskQueue queue = this.queue;
                    TimerTask task;
                    boolean nextExecutionTime;
                    synchronized (this.queue) {
                        if (frequency <= 0) {
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

                        task = this.queue.getMin();
                        Object lock = task.lock;
                        long currentTimeMillis;
                        long currentNextExecutionTime;
                        synchronized (task.lock) {
                            if (task.state == 3) {
                                this.queue.removeMin();
                                continue;
                            }

                            currentTimeMillis = System.currentTimeMillis();
                            currentNextExecutionTime = task.nextExecutionTime;
                            if (nextExecutionTime = currentNextExecutionTime <= currentTimeMillis) {
                                if (task.period == 0L) {
                                    this.queue.removeMin();
                                    task.state = 2;
                                } else {
                                    this.queue.rescheduleMin(task.period < 0L ? currentTimeMillis - task.period : currentNextExecutionTime + task.period);
                                }
                            }
                        }

                        if (!nextExecutionTime) {
                            this.queue.wait(currentNextExecutionTime - currentTimeMillis);
                        }
                    }

                    if (nextExecutionTime) {
                        System.out.println(frequency-- % 2 == 0 ? "偶数" : "奇数");
                        task.run();
                    }
                } catch (InterruptedException e) {
                    ;
                }
            }
        }
    }
}
