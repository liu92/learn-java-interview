package com.learn.interview.chapter7;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 使用Lock来进行锁的控制，使用condition来唤醒线程和阻塞线程
 * 创建一个简单的生产、消费者模式
 * @ClassName: ProdConsumerTraditionDemo
 * @Description:
 * @Author: lin
 * @Date: 2020/9/11 15:38
 * History:
 * @<version> 1.0
 */
public class ProdConsumerTraditionDemo {

    public static void main(String[] args) {
        ShareData data = new ShareData();
        int t = 5;
        for (int i = 0; i <t ; i++) {
            new Thread(()->{
                try {
                    data.increment();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            },"生产者线程：t" + i).start();
        }


        for (int i = 0; i <t ; i++) {
            new Thread(()->{
                try {
                    data.decrement();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            },"消费者线程：t" + i).start();
        }

    }
}

/**
 * 资源类
 */
class ShareData{
    private int count = 0;
    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();

    /**
     * 生产商品
     */
    public void increment() throws InterruptedException {
        lock.lock();
        try{
            //首先判断是否有商品，没有则生成商品
            //这个条件不满着阻塞，表示已经有了商品了，生产一个消费一个
            while (count != 0){
                // 在多线程情况下，要再次判断，否则出现虚假唤醒机制
                // 如果使用if判断，如果有两个线程一起进入，同时判断这个count都等于0
                // 然后进入下面的操作，就会造成生产者生产了多个的情况。
                condition.await();
            }
            //如果相等则表示，没有商品则生产商品, 注意多线程下，要用锁来控制线程安全问题
            // 所以要将这个全部放在lock控制的try代码快中
            count++;
            System.out.println(Thread.currentThread().getName() + "\t " + count);
            //生产商品后通知消费者，消费
            condition.signalAll();
        }catch(Exception e){
           e.printStackTrace();
        }finally{
          lock.unlock();
        }
    }

    /**
     * 消费商品
     */
    public void decrement() throws InterruptedException {
        lock.lock();
        try{
            //首先判断是否有商品，没有商品则等待
            while (count == 0){
                // 在多线程情况下，要再次判断，否则出现虚假唤醒机制
                // 如果使用if判断，如果有两个线程一起进入，同时判断这个count都不等于0
                // 那么在执行后续操作，就会造成商品被多次扣减的情况
                condition.await();
            }
            count--;
            System.out.println(Thread.currentThread().getName() + "\t " + count);
            //生产商品后通知消费者，消费
            condition.signalAll();
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            lock.unlock();
        }
    }

}
