package com.learn.interview.chapter2;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @ClassName: AbaDemo
 * @Description: CAS产生的ABA问题
 * @Author: lin
 * @Date: 2020/9/8 16:02
 * History:
 * @<version> 1.0
 */
public class AbaDemo {
    static AtomicReference<Integer> atomicReference = new AtomicReference<>(100);
    public static void main(String[] args) {
       new Thread(()->{
           atomicReference.compareAndSet(100, 101);
           atomicReference.compareAndSet(101, 100);
       },"t1").start();

       new Thread(()->{
           try {
               // 睡眠一秒，保证t1线程，完成了ABA操作
               TimeUnit.SECONDS.sleep(1);
           } catch (InterruptedException e) {
               e.printStackTrace();
           }
           // 把100 改成 101 然后在改成100，也就是ABA
           System.out.println(atomicReference.compareAndSet(100, 2020) + "\t" + atomicReference.get());
       }, "t2").start();
    }
}
