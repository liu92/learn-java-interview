package com.learn.interview.chapter9;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

/**
 * Callable的使用
 * @ClassName: CallableDemo
 * @Description:
 * @Author: lin
 * @Date: 2020/9/14 9:18
 * History:
 * @<version> 1.0
 */
public class CallableDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {

        //两个线程 ， 一个main线程，一个futureTask

        //main线程执行，另一个线程futureTask 去执行互相不影响。
        /**
         * 如果是同一个futureTask实例，然后使用两个线程计算，那么这个打印结果也只会有一次。
         * 因为这个调用只会调用1次， 内部缓存。其结果已经计算出来了，不会再去计算了。直接拿过来用
         */
        FutureTask<Integer> futureTask = new FutureTask<Integer>(new MyThread());
        Thread thread = new Thread(futureTask, "t1");
        FutureTask<Integer> futureTask1 = new FutureTask<Integer>(new MyThread());
        Thread thread1 = new Thread(futureTask1, "t2");
        thread.start();
        thread1.start();

        //如果在计算还没有计算完时，就要去获取结果，那么就会阻塞。比如这个线程需要2s去计算。
        // 不应该阻塞main线程。
//        int result = futureTask.get();
        System.out.println(Thread.currentThread().getName() + "*********************");
        int t  = 100;

        //2、通过者判断来确定计算是否完成，如果没有完成则循环，这个和自旋类型。
        //while (!futureTask.isDone()){
        //
        //}

        //要求获得Callable线程的计算结果，如果计算没有完成，去获取计算结果，就会导致阻塞，直到计算完成。
        int result = futureTask.get();

        System.out.println("**********返回结果："+(t + result));
    }
}

class  MyThread implements Callable<Integer>{

    @Override
    public Integer call() throws Exception {
        System.out.println(Thread.currentThread().getName()+ "  come in Callable");
        TimeUnit.SECONDS.sleep(2);
        return 1024;
    }
}
