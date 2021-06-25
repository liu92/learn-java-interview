package com.learn.interview.chapter10;

import java.util.concurrent.*;

/**
 * @ClassName: MyThreadPoolDemo1
 * @Description:
 * @Author: lin
 * @Date: 2020/9/14 13:29
 * History:
 * @<version> 1.0
 */
public class MyThreadPoolDemo1 {


    public static void main(String[] args) {
        // 手动创建线程池
        final int corePoolSize = 2;
        final int maximumPoolSize = 5;
        final long keepAliveTime = 1L;

        ExecutorService pool = new ThreadPoolExecutor(
                corePoolSize, maximumPoolSize,
                keepAliveTime, TimeUnit.SECONDS,
                //等候区
                new LinkedBlockingQueue<Runnable>(3),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.AbortPolicy());
        try {
            int t = 6;
            //模拟6个顾客来办理业务，受理窗口max只有5个
            for (int i = 1; i <= t; i++) {
                final  int tem = i;
                pool.execute(()->{
                    System.out.println(Thread.currentThread().getName()+ " 号窗口，" + "服务客户" + tem);
                    try {
                        TimeUnit.SECONDS.sleep(4);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            pool.shutdown();
        }


        /**
         * 如果队列满了且正在运行的线程数小于maximumPoolSize,那么会非核心线程数，然后立即执行任务。
         * 从下面看到，3，4，5号客户是在等待区里面，而客户2是后面来的，但是这个时候比等待区的先执行。
         * pool-1-thread-1 号窗口，服务客户1
         * pool-1-thread-3 号窗口，服务客户6
         *
         * pool-1-thread-2 号窗口，服务客户2
         * pool-1-thread-3 号窗口，服务客户3
         * pool-1-thread-1 号窗口，服务客户5
         * pool-1-thread-2 号窗口，服务客户4
         */
    }

}
