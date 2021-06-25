package com.learn.interview.chapter7;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 使用阻塞队列来实现生产者消费者模式
 * @ClassName: ProdConsumerBlockingQueueDemo
 * @Description:
 * @Author: lin
 * @Date: 2020/9/12 10:29
 * History:
 * @<version> 1.0
 */
public class ProdConsumerBlockingQueueDemo {
    public static void main(String[] args) {
        //这里调用资源，然后传入阻塞队列的实现类，这样就不要固定写死一个阻塞队列了
        MySource mySource = new MySource(new ArrayBlockingQueue<>(10));

        new Thread(()->{
            System.out.println(Thread.currentThread().getName() + "\t 生产线程启动");
            try {
                mySource.myProd();
            } catch (Exception e) {
                e.printStackTrace();
            }
        },"prod").start();


        new Thread(()->{
            System.out.println(Thread.currentThread().getName() + "\t 消费线程启动");
            System.out.println("");
            System.out.println("");
            try {
                mySource.myConsumer();
                System.out.println("");
                System.out.println("");
            } catch (Exception e) {
                e.printStackTrace();
            }
        },"consumer").start();


        // 5秒后，停止生产和消费
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("");
        System.out.println("");
        System.out.println("5秒中后，生产和消费线程停止，线程结束");
        mySource.stop();
    }
}

/**
 * 阻塞队列来操作资源类
 */

class MySource{

    /**
     * 使用volatile来控制变量，保证多线程情况下的可见性，
     * 及时通知, 默认开启 ， 这个值来控制是生产者生产，还是消费者消费
     */
    private volatile  boolean FLAG = true;
    private AtomicInteger atomicInteger = new AtomicInteger();

    BlockingQueue<String> blockingQueue = null;

    /**
     * 在写通用架构时，要考虑架构的 通顺，适配和通用。
     *
     *传接口，不许传入具体类。如传入具体类，那么在很多的操作要进行阻塞队列的操作。
     *那么就要定义很多方法。但是如果定义方法传入的是接口，那么在实际调用的时候只需要传入接口的实现类
     * 这样就可以将代码的耦合度降低
     * @param blockingQueue
     */
    public  MySource(BlockingQueue<String> blockingQueue){
        //采用依赖注入里面的，构造注入方法传入
        this.blockingQueue = blockingQueue;
    }


    /**
     * 生产者
     * @throws Exception
     */
    public  void myProd() throws Exception {
        String data = null;
        boolean retValue ;
        //这里如果是true,则表示可以生产
        while (FLAG){
            //生产商品
            data = atomicInteger.incrementAndGet() + "";
            //然后将生产的商品加入到队列中
            //如果在2s种内不能加入到队列中，则返回false
            retValue = blockingQueue.offer(data, 2L, TimeUnit.SECONDS);

            if(retValue){
                //放入队列成功
                System.out.println(Thread.currentThread().getName() + "\t 插入队列:" + data  + "成功" );
            }else {
                System.out.println(Thread.currentThread().getName() + "\t 插入队列:" + data  + "失败" );
            }
            //上面一直生产，1s种一次
            TimeUnit.SECONDS.sleep(1);
        }
        System.out.println(Thread.currentThread().getName() + "\t 停止生产，表示FLAG=false，生产结束 ");
    }


    /**
     * 消费者
     * @throws Exception
     */
    public  void myConsumer() throws Exception {
        String result;
        //这里如果是true,则表示可以生产
        while (FLAG){
            //从队列中取出数据
            //如果在2s种内没有从队列中取出数据，则返回null
            result = blockingQueue.poll(2L, TimeUnit.SECONDS);

            if(result == null || result.equalsIgnoreCase("")){
                FLAG = false;
                //放入队列成功
                System.out.println(Thread.currentThread().getName() + "\t 超过2s没有从消费者队列取出数据，消费者退出！");
                System.out.println();
                System.out.println();
                //退出
                return;
            }
            System.out.println(Thread.currentThread().getName() + "\t 消费队列："+ result + "成功");
        }

    }


    /**
     * 停止生产
     */
    public void  stop(){
        this.FLAG =false;
    }
}