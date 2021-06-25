package com.learn.interview.chapter1;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Volatile Java虚拟机提供的轻量级同步机制
 *
 * 可见性（及时通知）
 * 不保证原子性
 * 禁止指令重排
 * @ClassName: VolatileDemo
 * @Description:
 * @Author: lin
 * @Date: 2020/9/7 15:06
 * History:
 * @<version> 1.0
 */
public class VolatileDemo2 {

    public static void main(String[] args) {
        MyData2 myData = new MyData2();

        // 创建10个线程，线程里面进行1000次循环
        int threadCount =20 ;
        int processCount = 1000;
        for (int i = 0; i < threadCount; i++) {
            new Thread(() -> {
                // 里面
                for (int j = 0; j < processCount; j++) {
                    myData.addPlusPlus();
                    myData.addAtomic();
                }
            }, String.valueOf(i)).start();
        }

        // 需要等待上面20个线程都计算完成后，在用main线程取得最终的结果值
        // 这里判断线程数是否大于2，为什么是2？因为默认是有两个线程的，一个main线程，一个gc线程
        while(Thread.activeCount() > 2) {
            // yield表示不执行
            Thread.yield();
        }

        // 查看最终的值
        // 假设volatile保证原子性，那么输出的值应该为：  20 * 1000 = 20000
        System.out.println(Thread.currentThread().getName() + "\t finally number value: " + myData.number);
        System.out.println(Thread.currentThread().getName() + "\t finally atomicNumber value: " + myData.atomicInteger);
    }
}


class MyData2 {

//    int number = 0;
    /**
     * volatile 修饰的关键字，是为了增加 主线程和线程之间的可见性，只要有一个线程修改了内存中的值，其它线程也能马上感知
     */
    volatile  int number =0;


    public  void addTo60() {
        this.number = 60;
    }

    /**
     * 注意，此时number 前面是加了volatile修饰
     */
    public synchronized    void addPlusPlus() {
        number ++;
    }

    /**
     *  创建一个原子Integer包装类，默认为0
     */
    AtomicInteger atomicInteger = new AtomicInteger();

    public void addAtomic() {
        // 相当于 atomicInter ++
        atomicInteger.getAndIncrement();
    }
}