package com.example.chenwf.myapplication;

import java.util.Arrays;

/**
 * @author chenwf
 * @date 2018/12/5
 * @company Fotile智能厨电研究院
 */
class TaskQueue {
    private TimerTask[] queue = new TimerTask[128];
    private int size = 0;

    TaskQueue() {
    }

    int size() {
        return this.size;
    }

    //添加task
    void add(TimerTask task) {
        if (this.size + 1 == this.queue.length) {
            this.queue = (TimerTask[]) Arrays.copyOf(this.queue, 2 * this.queue.length);
        }

        this.queue[++this.size] = task;
        this.fixUp(this.size);
    }

    TimerTask getMin() {
        return this.queue[1];
    }

    TimerTask get(int index) {
        return this.queue[index];
    }

    void removeMin() {
        this.queue[1] = this.queue[this.size];
        this.queue[this.size--] = null;
        this.fixDown(1);
    }

    void rescheduleMin(long nextExecutionTime) {
        this.queue[1].nextExecutionTime = nextExecutionTime;
        this.fixDown(1);
    }

    boolean isEmpty() {
        return this.size == 0;
    }

    void clear() {
        for(int var1 = 1; var1 <= this.size; ++var1) {
            this.queue[var1] = null;
        }

        this.size = 0;
    }

    private void fixUp(int size) {
        while(true) {
            if (size > 1) {
                int next = size >> 1;
                if (this.queue[next].nextExecutionTime > this.queue[size].nextExecutionTime) {
                    TimerTask nextTask = this.queue[next];
                    this.queue[next] = this.queue[size];
                    this.queue[size] = nextTask;
                    size = next;
                    continue;
                }
            }

            return;
        }
    }

    private void fixDown(int index) {
        while(true) {
            int front;
            if ((front = index << 1) <= this.size && front > 0) {
                if (front < this.size && this.queue[front].nextExecutionTime > this.queue[front + 1].nextExecutionTime) {
                    ++front;
                }

                if (this.queue[index].nextExecutionTime > this.queue[front].nextExecutionTime) {
                    TimerTask frontTask = this.queue[front];
                    this.queue[front] = this.queue[index];
                    this.queue[index] = frontTask;
                    index = front;
                    continue;
                }
            }

            return;
        }
    }
}
