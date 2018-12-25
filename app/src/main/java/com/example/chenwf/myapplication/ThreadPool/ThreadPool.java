/*题目：（主要考察编码规范性）
多线程技术主要解决处理器单元内多个线程执行的问题，它可以显著减少处理器单元的闲置时间，增加处理器单元的吞吐能力，
现需要实现线程池模块，要求如下：
1. 线程池管理器（ThreadPool）：用于创建并管理线程池，包括 创建线程池，销毁线程池，添加新任务；
2. 工作线程（PoolWorker）：线程池中线程，在没有任务时处于等待状态，可以循环的执行任务；
3. 任务接口（Task）：每个任务必须实现的接口，以供工作线程调度任务的执行，它主要规定了任务的入口，任务执行完后的收尾工作，任务的执行状态等；
4. 任务队列（taskQueue）：用于存放没有处理的任务

要求：
1.	可以使用C/C++/JS/OC等语言实现
2.	不得使用各编程语言已有的函数库
3.	JS的代码在node.js环境下测试
4.	所有的接口函数必须提供完整的单元测试*/

package com.example.chenwf.myapplication.ThreadPool;


import java.util.LinkedList;
import java.util.List;

/**
 * @author chenwf
 * @date 2018/12/19
 * @company Fotile智能厨电研究院
 */
public class ThreadPool {
    private static int i = 0;

    public static void main(String[] args) {
        for (i = 0; i < 50; i++) {
            final int j = i;
            ThreadPool.getInstance().execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(4000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("Running: " + System.currentTimeMillis() + " runnable: " + j);
                }
            });
        }
    }

    private static int DefaultCoreNum = 3;
    private int workNum = DefaultCoreNum;

    private PoolWorker[] poolWorkers;


    private List<Runnable> taskQueue = new LinkedList<>();

    private static class threadPoolHolder {
        private final static ThreadPool instance = new ThreadPool();
    }

    public static ThreadPool getInstance() {
        return threadPoolHolder.instance;
    }

    private ThreadPool() {
        this(DefaultCoreNum);
    }

    private ThreadPool(int coreNum) {
        if (coreNum <= 0) {
            coreNum = DefaultCoreNum;
        }
        workNum = coreNum;
        poolWorkers = new PoolWorker[coreNum];
    }

    /*添加任务*/
    public void execute(Runnable task) {
        synchronized (taskQueue) {
            taskQueue.add(task);
            taskQueue.notify();
            checkRunningWorker();
        }
    }

    /**
     * 检查正在运行的线程
     */
    private void checkRunningWorker() {
        for (int i = 0; i < workNum; i++) {
            if (null == poolWorkers[i]) {
                /* 未使用，直接创建线程*/
                poolWorkers[i] = new PoolWorker(i);
                poolWorkers[i].start();
                break;
            }
            if (null == poolWorkers[i].runnable) {
                /* 存在空闲线程*/
                break;
            }
            if (!poolWorkers[i].running) {
                /* 存在已经关闭的线程，置空并重新创建线程*/
                poolWorkers[i] = null;
                poolWorkers[i] = new PoolWorker(i);
                poolWorkers[i].start();
                break;
            }
        }

    }

    /*实际运行*/
    private class PoolWorker extends Thread {
        private boolean running = true;
        public Runnable runnable = new Runnable() {
            @Override
            public void run() {

            }
        };
        private long lastRunningTime;
        private String id = "";

        public PoolWorker(int id) {
            this.id = "Thread " + id;
        }

        @Override
        public void run() {
            while (running) {
                synchronized (taskQueue) {
                    while (running && taskQueue.isEmpty()) {
                        try {
                            /*100毫秒拉取一个任务*/
                            runnable = null;
                            taskQueue.wait(10);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                    if (!taskQueue.isEmpty()) {
                        runnable = taskQueue.remove(0);
                    }
                }
                if (null != runnable) {
                    lastRunningTime = System.currentTimeMillis();
                    runnable.run();
                    System.out.println("finish runnable on " + id);
                    System.out.println();
                    runnable = null;
                } else {
                    if (System.currentTimeMillis() - lastRunningTime > 10 * 1000) {
                        // 1分钟没有新任务，关闭该线程
                        System.out.println("close " + id);
                        running = false;
                        interrupt();
                    }
                }


            }
        }

        public void stopThread() {
            running = false;
        }
    }
}
