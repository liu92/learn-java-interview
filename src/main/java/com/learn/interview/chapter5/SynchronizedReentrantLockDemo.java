package com.learn.interview.chapter5;

/**
 * @ClassName: SynchronizedReentrantLockDemo
 * @Description:
 * @Author: lin
 * @Date: 2020/9/9 15:09
 * History:
 * @<version> 1.0
 */
public class SynchronizedReentrantLockDemo {

    public static void main(String[] args) {
        Phone phone = new Phone();

        // 两个线程操作资源列
        new Thread(() -> {
            try {
                phone.sendSMS();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, "t1").start();

        new Thread(() -> {
            try {
                phone.sendSMS();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, "t2").start();
    }
}

/**
 * 资源类
 */
class Phone {

    /**
     * 发送短信
     * @throws Exception
     */
    public synchronized void sendSMS() throws Exception{
        System.out.println(Thread.currentThread().getName() + "\t invoked sendSMS()");

        // 在同步方法中，调用另外一个同步方法
        sendEmail();
    }

    /**
     * 发邮件
     * @throws Exception
     */
    public synchronized void sendEmail() throws Exception{
        System.out.println(Thread.currentThread().getName() + "\t ########invoked sendEmail()");
    }
}