package com.learn.interview.chapter10;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 线程池的优势：
 *  线程池做的工作是控制运行的线程数量，处理过程中将任务放入队列，然后在线程中创建后启动这些任务，如果线程
 *  数量超过了最大数量，超出数量的线程排队等候，等其它线程执行完毕后，再从队列中取出任务来执行。
 *
 *  它的主要特点:线程服用控制最大并发数，管理线程
 *  1、降低资源消耗。通过重复利用已创建的线程降低线程创建和销毁造成的销耗。
 *  2、提高响应速度。当任务到达时，任务可以不需要等待线程创建就能立即执行。
 *  3、提高线程的可管理性。线程是稀缺资源，如果无限制的创建，不仅会消耗系统资源，还会降低系统的稳定性，
 *  使用线程池可以进行统一的分配，调优和监控。
 *
 *
 *
 * @ClassName: MyThreadPoolDemo
 * @Description:
 * @Author: lin
 * @Date: 2020/9/14 13:29
 * History:
 * @<version> 1.0
 */
public class MyThreadPoolDemo {


    public static void main(String[] args) {
            // Array  Arrays(辅助工具类)
            // Collection Collections(辅助工具类)
            // Executor Executors(辅助工具类)

            // 一池5个处理线程（用池化技术，一定要记得关闭，举个例子：比如某个银行处理支行，
            //  这个支行相当于池子，这个里面的柜台是固定， 然后有10个用户到了这个银行，让这5个营业员办理业务）。
            ExecutorService threadPool = Executors.newFixedThreadPool(5);
            // 一池1个处理线程
            //ExecutorService threadPool = Executors.newSingleThreadExecutor();
            // 一池n个处理线程
//            ExecutorService threadPool = Executors.newCachedThreadPool();

            // 模拟10个用户来办理业务，每个用户就是一个来自外部请求线程
            try {

                // 循环十次，模拟业务办理，让5个线程处理这10个请求
                int t = 10;
                for (int i = 0; i < t; i++) {
                    final int tempInt = i;
                    threadPool.execute(() -> {
                        System.out.println(Thread.currentThread().getName() + "\t 给用户:" + tempInt + " 办理业务");
                    });
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                threadPool.shutdown();
            }



        }

}
