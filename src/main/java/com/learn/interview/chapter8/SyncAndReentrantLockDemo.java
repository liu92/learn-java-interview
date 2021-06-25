package com.learn.interview.chapter8;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 顺序唤醒
 * 使用ReentrantLock来实现分组唤醒需要唤醒的线程，可以精确唤醒，而不是像synchronized那样，
 * 要么随机，要么全部唤醒
 * @ClassName: SyncAndReentrantLockDemo
 * @Description:
 * @Author: lin
 * @Date: 2020/9/11 17:16
 * History:
 * @<version> 1.0
 */
public class SyncAndReentrantLockDemo {
    public static void main(String[] args) {
      //  当A线程执行完后，B线程才能执行，然后B线程执行完成后，C线程才执行
      //  记住锁的三步方法： 判断 干活 唤醒

      ShareResource shareResource = new ShareResource();

      //三个线程,每个线程执行10遍
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                shareResource.print5();
            }
        }, "A").start();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                shareResource.print10();
            }
        }, "B").start();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                shareResource.print15();
            }
        }, "C").start();
    }
}

class ShareResource{

    /**
     * 当t=1时，是线程A
     * 当t=2时，是线程B
     * 当t=3时，是线程C
     */
    private int t = 1;
    /**
     * 创建一个重入锁
     */
    private Lock lock = new ReentrantLock();
    /**
     * 然后定义三个条件，也可以称为锁的钥匙，通过它就可以获取到锁，进入到方法里面
     */
    private Condition c1 = lock.newCondition();
    private Condition c2 = lock.newCondition();
    private Condition c3 = lock.newCondition();

    /**
     * 打印5次
     */
    public void  print5(){
        lock.lock();
        try{
            //判断
          while (t !=1){
             c1.await();
          }
          //打印五次
          int c = 5;
          for (int i = 1; i <=c ; i++) {
              System.out.println(Thread.currentThread().getName() +  "\t" + i);
          }
          //打印完了之后先修改标识
            t=2;
          //然后通知线程2，去打印
           c2.signal();
        }catch(Exception e){
           e.printStackTrace();
        }finally{
          lock.unlock();
        }

         
    }

    /**
     * 线程2打印10次
     */
    public void  print10(){
        lock.lock();
        try{
            //判断
            while (t !=2){
                c2.await();
            }
            //打印10次
            int c = 10;
            for (int i = 1; i <=c ; i++) {
                System.out.println(Thread.currentThread().getName() +  "\t" + i);
            }
            //打印完了之后先修改标识
            t=3;
            //然后通知线程2，去打印
            c3.signal();
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            lock.unlock();
        }
    }

    /**
     * 线程3打印15次
     */
    public void  print15(){
        lock.lock();
        try{
            //判断
            while (t !=3){
                c3.await();
            }
            //打印15次
            int c = 15;
            for (int i = 1; i <=c ; i++) {
                System.out.println(Thread.currentThread().getName() +  "\t" + i);
            }
            //在线程3打印完了之后，将标识修改回1，然后又开始从线程1开始打印
            t = 1;
            //然后通知线程1，去打印
            c1.signal();
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            lock.unlock();
        }
    }
}