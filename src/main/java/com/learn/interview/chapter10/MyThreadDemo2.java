package com.learn.interview.chapter10;

import java.util.concurrent.*;

/**
 * @ClassName: MyThreadDemo2
 * @Description:
 * @Author: lin
 * @Date: 2020/9/14 16:30
 * History:
 * @<version> 1.0
 */
public class MyThreadDemo2 {
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
                new ThreadPoolExecutor.DiscardPolicy());

        try {
            int t = 9;
            //模拟10个用户来办理业务，每个用户就是一个来自外部请求线程
            for (int i = 1; i <= t; i++) {
                final  int tem = i;
                pool.execute(()->{
                    System.out.println(Thread.currentThread().getName()+ "\t 给用户:" + tem + " 办理业务");
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            pool.shutdown();
        }


        System.out.println(Runtime.getRuntime().availableProcessors());
    }
}
