package com.learn.interview.chapter6;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * 和CountDownLatch相反，CountDownLatch是做加减法，当技术值减为0 的时候就执行
 * CyclicBarrier 是做加法，也就是当有线程来之后需要等待其它线程，当全部线程都到了之后才开始工作。
 *  比如说开会，一共有7个人开会，但是人没有到齐，那么这个会议就不能开始
 * @ClassName: CyclicBarrierDemo
 * @Description:
 * @Author: lin
 * @Date: 2020/9/10 9:43
 * History:
 * @<version> 1.0
 */
public class CyclicBarrierDemo {
    public static void main(String[] args) {
        /**
         * 定义一个循环屏障，参数1：需要累加的值，参数2 需要执行的方法
         */
        CyclicBarrier cyclicBarrier = new CyclicBarrier(7, ()->{
            System.out.println("召唤神龙");
        });

        int t=7;
        for (int i = 1; i <=t ; i++) {
            final int tempInt = i;
            new Thread(()->{
                //这里开始收集
                System.out.println(Thread.currentThread().getName() + "\t 收集到 第" + tempInt + "颗龙珠");
                //但是如果龙珠没有收集完成，那么就要等待
                try {
                    cyclicBarrier.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            },String.valueOf(i)).start();

        }
    }
}
