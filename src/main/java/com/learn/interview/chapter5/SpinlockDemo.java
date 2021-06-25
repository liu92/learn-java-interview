package com.learn.interview.chapter5;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @ClassName: SpinlockDemo
 * @Description:
 * @Author: lin
 * @Date: 2020/9/9 16:44
 * History:
 * @<version> 1.0
 */
public class SpinlockDemo {
    //原子引用线程, 现在的泛型装的是Thread
    AtomicReference<Thread> atomicReference = new AtomicReference<>();

    public static void main(String[] args) {
        SpinlockDemo spinlockDemo = new SpinlockDemo();

        //线程1
        new Thread(()->{
            // 开始占有锁
            spinlockDemo.myLock();
            try {
                //这里睡5s的意思，持有锁5s，在等1s后其它线程启动后，也要去获得锁。
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            // 开始释放锁
            spinlockDemo.myUnLock();
        },"AA").start();

        //暂停1会儿，这里睡1s，是让上面先启动
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Thread(()->{
            spinlockDemo.myLock();
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            spinlockDemo.myUnLock();
        },"BB").start();
    }


    /**
     * 不使用synchronized和Lock
     */
    public void myLock(){
        Thread thread = Thread.currentThread();
        System.out.println(Thread.currentThread().getName() + "\t come in ");

        // 开始自旋，期望值是null，更新值是当前线程，如果是null，则更新为当前线程，否者自旋
        // 这里取反的意思就是 第一次进来这个比较交换条件符合然后取反就是跳出while循环。
        // 在线程AA第一次进入时，这里的比较并更新是符合的，跳出循环， 当线程BB也来获取这个锁时
        // 这个 比较提交是不正确的，那么就会进入自旋。一直去询问这个 期望值和内存值是一致。
        // 当一致时线程BB就会跳出循环(也就是不再自旋了)。
        //引用类型默认是null
        while (!atomicReference.compareAndSet(null, thread)){

        }
    }

    public void myUnLock(){
        // 获取当前进来的线程
        Thread thread = Thread.currentThread();
        //比较当前内存中的值和期望的值是否一样，如果一样那么将其改回为null
        atomicReference.compareAndSet(thread, null);
        System.out.println(Thread.currentThread().getName() + "\t invoked myUnlock()");
    }
}
