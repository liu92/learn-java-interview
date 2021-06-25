package com.learn.interview.chapter6;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * 信号量主要用于两个方面，一个用于多个资源共享的互斥使用，另一个用于并发线程数的控制。
 * @ClassName: SemaphoreDemo
 * @Description:
 * @Author: lin
 * @Date: 2020/9/10 10:08
 * History:
 * @<version> 1.0
 */
public class SemaphoreDemo {
    public static void main(String[] args) {
        //模拟3个车位, 这个Semaphore，有伸缩性，如果这个车位被其它车辆抢到了，那么其它的车量就等待。
        //当有车量走了之后，这个车位数量又恢复了。
        Semaphore semaphore = new Semaphore(3);
        int t = 6;
        //有6部车进来抢车位
        for (int i = 1; i <=t ; i++) {
            new Thread(()->{
                try {
                    //占用一个车位
                    semaphore.acquire();
                    System.out.println(Thread.currentThread().getName() + "\t 抢到车位");
                    TimeUnit.SECONDS.sleep(3);
                    System.out.println(Thread.currentThread().getName() + "\t 停车3s后离开车位");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    //在车离开之后，就要释放这个锁，也就是这个被抢到的车位，释放出来，给其他车辆使用
                    semaphore.release();
                }
            },String.valueOf(i)).start();
        }
    }
}
