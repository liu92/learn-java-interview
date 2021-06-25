package com.learn.interview.chapter11;

import java.util.concurrent.TimeUnit;

/**
 * 测试死锁
 * @ClassName: DeadLockDemo
 * @Description:
 * @Author: lin
 * @Date: 2020/9/15 8:57
 * History:
 * @<version> 1.0
 */
public class DeadLockDemo {

    public static void main(String[] args) {
        String lockA = "lockA";
        String lockB = "lockB";
        Cat cat  = new Cat(lockA, lockB);
        new Thread(()->{
            cat.getA();
        },"t1").start();

        new Thread(()->{
            cat.getB();
        },"t2").start();
    }
}


class Cat{
    private String lockA;
    private String lockB;

    public Cat(String lockA, String lockB) {
        this.lockA = lockA;
        this.lockB = lockB;
    }

    public void getA(){
        synchronized (lockA){
            System.out.println(Thread.currentThread().getName() + "\t 自己持有" + lockA + "\t 尝试获取：" + lockB);

            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            getB();
        }
    }

    public  void  getB(){
        synchronized (lockB){
            System.out.println(Thread.currentThread().getName() + "\t 自己持有" + lockB + "\t 尝试获取：" + lockA);

            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            getA();
        }
    }
}
