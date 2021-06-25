package com.learn.interview.chapter7;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName: SynchronousQueueDemo
 * @Description:
 * @Author: lin
 * @Date: 2020/9/10 23:18
 * History:
 * @<version> 1.0
 */
public class SynchronousQueueDemo {
    public static void main(String[] args) {
        BlockingQueue<String> bkQueue = new SynchronousQueue<>();

        //线程1，往队列中加入数据
        new Thread(()->{
            try {
                System.out.println(Thread.currentThread().getName() + "\t put 1" );
                bkQueue.put("1");

                //这里虽然打印了，但是如果这个队列中的元素没有被消费，这句也不会阻塞队列
                //因为这个队列不存储元素。
                System.out.println(Thread.currentThread().getName() + "\t put 2" );
                bkQueue.put("2");

                System.out.println(Thread.currentThread().getName() + "\t put 3" );
                bkQueue.put("3");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"AA").start();


        //线程2，从队列中取出元素
        new Thread(()->{
            try {
                //暂停5s再取数据
                TimeUnit.SECONDS.sleep(5);
                System.out.println(Thread.currentThread().getName() + "\t" + bkQueue.take());

                TimeUnit.SECONDS.sleep(5);
                System.out.println(Thread.currentThread().getName() + "\t " + bkQueue.take() );

                TimeUnit.SECONDS.sleep(5);
                System.out.println(Thread.currentThread().getName() + "\t " + bkQueue.take());

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"BB").start();
    }
}
