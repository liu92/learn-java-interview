package com.learn.interview.chapter6;

import java.util.concurrent.CountDownLatch;

/**
 * 让一些线程阻塞直到另一些线程完成一系列操作才被唤醒
 *
 * CountDownLatch z主要有两个方法，当一个或多个线程调用await方法时，这些线程阻塞。
 * 其它线程调用countDown()方法将计数器减1(调用countDown方法当线程不会阻塞)，
 * 当计数器的值为0时，因为调用await方法阻塞的线程会被唤醒，然后继续执行
 *
 * @ClassName: CountDownLatchDemo
 * @Description:
 * @Author: lin
 * @Date: 2020/9/10 9:00
 * History:
 * @<version> 1.0
 */
public class CountDownLatchDemo {
    private static CountDownLatch countDownLatch = new CountDownLatch(6);
    public static void main(String[] args) {
        int count = 6;
        for (int i = 1; i <=count; i++) {
            new Thread(()->{
                System.out.println(Thread.currentThread().getName() + "\t 上完自习，离开教室");
                //调用方法, 该方法将计算器值递减直至0 为止。
                countDownLatch.countDown();
            },String.valueOf(i)).start();
        }
        //在上述的6个线程，也就是同学都离开了教室，那么班长才能最后走的。
        //而这里的main线程就相当于是这个 班长。所有我们需要等待上述的操作完成后，才能执行下面的操作。
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + "\t 班长最后关门");

    }
}
