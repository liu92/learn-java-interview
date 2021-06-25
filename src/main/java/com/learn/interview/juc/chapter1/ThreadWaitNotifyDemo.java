package com.learn.interview.juc.chapter1;

/**
 *  资源类
 */
class AirConditioner {

    private int number= 0;

    public synchronized void increment() throws InterruptedException {
        //1.判断
        while (number != 0){
            this.wait();
        }
        //2、增加
        number++;
        System.out.println(Thread.currentThread().getName() + "\t" + number);
        //3、通知
        this.notifyAll();
    }

    public synchronized  void decrment() throws InterruptedException {
        while (number == 0){
            this.wait();
        }

        number--;
        System.out.println(Thread.currentThread().getName() + "\t" + number);
        this.notifyAll();
    }
}

/**
 * 两个线程，可以操作初始值为零的一个变量。
 * 实现一个线程对该变量加1，一个线程对该变量减去1
 * 1、高内聚，低耦合前提下，线程操作资源类
 * 2、判断/处理问题/通知
 * 3、多线程交互中，必须要防止多线程对虚假唤醒，也即(判断只能用while，不能用if)
 *
 * @ClassName learn-java-interview
 * @Description 描述
 * @Date 2020/12/23 10:15 上午
 * @Author lin
 * @Version 1.0
 */
public class ThreadWaitNotifyDemo{

    public static void main(String[] args) {
        AirConditioner airConditioner = new AirConditioner();

        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                try {
                    Thread.sleep(200);
                    airConditioner.increment();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"A").start();

        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                try {
                    Thread.sleep(300);
                    airConditioner.decrment();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"B").start();

        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                try {
                    Thread.sleep(400);
                    airConditioner.increment();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"C").start();

        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                try {
                    Thread.sleep(500);
                    airConditioner.decrment();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"D").start();
    }
}
