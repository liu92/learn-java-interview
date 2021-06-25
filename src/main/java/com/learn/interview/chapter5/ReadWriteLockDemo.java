package com.learn.interview.chapter5;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 读写锁
 * 多个线程 同时读一个资源类没有任何问题，所以为了满足并发量，读取共享资源应该可以同时进行
 * 但是，如果一个线程想去写共享资源，就不应该再有其它线程可以对该资源进行读或写
 *   小总结：
 *       读-读：能共存
 *       读-写：不能共存
 *       写-写：不能共存
 *
        写操作：原子+独占，整个过程必须保证是一个完整的统一体，中间不许被分割，被打断
 * @ClassName: ReadWriteLockDemo
 * @Description:
 * @Author: lin
 * @Date: 2020/9/9 22:31
 * History:
 * @<version> 1.0
 */
public class ReadWriteLockDemo {
    public static void main(String[] args) {
        MyCache myCache = new MyCache();

        int count = 5;
        // 线程操作资源类，5个线程写
        for (int i = 0; i < count; i++) {
            // lambda表达式内部必须是final
            final int tempInt = i;
              new Thread(()->{
                  myCache.put(tempInt +"", tempInt + "");
              }, String.valueOf(i)).start();
        }

        // 线程操作资源类，5个线程读
        for (int i = 0; i < count; i++) {
            // lambda表达式内部必须是final
            final int tempInt = i;
            new Thread(()->{
                myCache.get(tempInt +"");
            }, String.valueOf(i)).start();
        }
    }
}

/**
 * 资源类 ，可以理解为缓存
 */

 class  MyCache{
    /**
     * 这里加入volatile是因为在缓存中，我们要保证线程的可见性。能够及时的看到其前一个操作的结果。
     */
    private volatile Map<String, Object> map = new HashMap<>();
    private ReentrantReadWriteLock rwLock = new ReentrantReadWriteLock();

    /**
     * 定义写操作
     * @param key
     * @param value
     */
    public void put(String key, Object value){
        //使用读写锁
        rwLock.writeLock().lock();
        try{
            System.out.println(Thread.currentThread().getName() + "\t 正在写入：" + key);
            try {
                // 模拟网络拥堵，延迟0.3秒
                TimeUnit.MILLISECONDS.sleep(300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            map.put(key, value);
            System.out.println(Thread.currentThread().getName() + "\t 写入完成");
        }catch(Exception e){
           e.printStackTrace();
        }finally{
            //写锁，释放锁
           rwLock.writeLock().unlock();
        }


    }

    /**
     * 定义读操作 ，读操作可以同时多个线程读取，顺序没有影响
     * @param key
     */
    public void get(String key) {
        //读锁
        rwLock.readLock().lock();
        try{
            System.out.println(Thread.currentThread().getName() + "\t 正在读取:");
            try {
                // 模拟网络拥堵，延迟0.3秒
                TimeUnit.MILLISECONDS.sleep(300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Object value = map.get(key);
            System.out.println(Thread.currentThread().getName() + "\t 读取完成：" + value);
        }catch(Exception e){
           e.printStackTrace();
        }finally{
            //读锁，释放锁
          rwLock.readLock().unlock();
        }

    }
}