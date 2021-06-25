package com.learn.interview.chapter5;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @ClassName: ReentrantLockDemo
 * @Description:
 * @Author: lin
 * @Date: 2020/9/9 15:09
 * History:
 * @<version> 1.0
 */
public class ReentrantLockDemo {

    public static void main(String[] args) {
        Phone1 phone = new Phone1();

        /**
         * 因为Phone实现了Runnable接口
         */
        Thread t3 = new Thread(phone, "t3");
        Thread t4 = new Thread(phone, "t4");
        t3.start();
        t4.start();
    }
}

/**
 * 资源类
 */
class Phone1 implements Runnable{
    Lock lock = new ReentrantLock();

    /**
     * 调用set方法的时候，能否访问另外一个加锁的set方法
     * @throws Exception
     */
    public  void getLock(){
       lock.lock();
        try {
            System.out.println(Thread.currentThread().getName() + "\t get Lock");
            setLock();
        }catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
            lock.unlock();
        }
    }

    /**
     * 发邮件
     */
    public synchronized void setLock(){
        lock.lock();
        try {
            System.out.println(Thread.currentThread().getName() + "\t #########set Lock");
        }catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    @Override
    public void run() {
        getLock();
    }
}