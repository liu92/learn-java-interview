package com.learn.interview.juc.chapter1;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 *  资源类
 *
 *  Condition也称为条件队列或条件变量.
 *
 * Condition 是一种广义上的条件队列。他为线程提供了一种更为灵活的**等待 / 通知**模式，
 * 线程在调用 await 方法后执行**挂起**操作，
 * 直到线程等待的某个条件为真时才会被**唤醒**。Condition 必须要配合 Lock 一起使用，
 * 因为对共享状态变量的访问发生在多线程环境下。一个 Condition 的实例必须与一个 Lock 绑定，
 * 因此 Condition 一般都是作为 Lock 的内部实现。
 *
 * Condition的实现类是在AQS中的中 ConditionObject 内部类，
 * 这个类中拥有firstWaiter(头节点)和lastWaiter(尾节点)，当线程调用await()方法时，
 * 将会以当前线程构成一个节点(Node)，并将节点加入到该队列的尾部。
 *
 * AQS 等待队列与 Condition 队列是两个相互独立的队列
 *
 * #await() 就是在当前线程持有锁的基础上释放锁资源，并新建 Condition 节点加入到 Condition 的队列尾部，阻塞当前线程 。
 * #signal() 就是将 Condition 的头节点移动到 AQS 等待节点尾部，让其等待再次获取锁。
 */
class AirConditionerSignal {

    private int number= 0;
    private final Lock lock = new ReentrantLock();
    //定义两个condition，分别管两个不同的方法
    //就是说对一个资源不同的操作
    private final Condition notFull  = lock.newCondition();
    private final Condition notEmpty = lock.newCondition();


    public  void increment() {
        lock.lock();
        try {
           while (number != 0){
               notFull.await();
           }
          number++;
            System.out.println(Thread.currentThread().getName() + "\t" + number);
          notEmpty.signalAll();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
          lock.unlock();
        }
    }

    public   void decrment() throws InterruptedException {
        lock.lock();
        try {
            while (number == 0){
                notEmpty.wait();
            }
            number--;
            System.out.println(Thread.currentThread().getName() + "\t" + number);
            notFull.signalAll();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }

    }
}

/**
 * 使用lock来实现
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
public class ThreadAwaitSignalDemo{

    public static void main(String[] args) {
        AirConditionerSignal airConditioner = new AirConditionerSignal();

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
