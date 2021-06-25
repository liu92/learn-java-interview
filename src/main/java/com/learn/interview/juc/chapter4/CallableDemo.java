package com.learn.interview.juc.chapter4;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

/**
 * 多线程中，第三种获取多线程的方式
 * @ClassName learn-java-interview
 * @Description 描述
 * @Date 2020/12/24 2:52 下午
 * @Author lin
 * @Version 1.0
 */
public class CallableDemo {
    public static void main(String[] args) {
        /**
         * java的多态性，通过一个中间类FutureTask，该类实现了RunnableFuture,
         * 而该类又是Runnable的子类，间接的FutureTask也实现类Runnable接口。
         * 通过这个类的构造方法包含 Runnable接口和Callable接口。那么在往thread传入
         * 接口的时候就也传入FutureTask。
         *
         * 注意:要明白这个设计思想，面向接口编程。
         * 比如传入一个接口A不满足 方法的要求，那么就传入C，这个同时和A,B有关系。那么就可以满足其要求
         * 这样可以做到横向的扩容
         *
         */

        /**
         * 如果是同一个futureTask实例，然后使用两个线程计算，那么这个打印结果也只会有一次。
         * 因为这个调用只会调用1次， 内部缓存。其结果已经计算出来了，不会再去计算了。直接拿过来用
         */
        FutureTask<Integer> futureTask = new FutureTask<>(new MyCallable());
        new Thread(futureTask, "A").start();
//        System.out.println(Thread.currentThread().getName()+ "......计算完成");

        try {
            //放在最后，等待计算完之后在取
            /**
             * 如果这个方法不是放在最后，那么就会等待线程计算完成后获取结果后，才会继续往下执行。
             * 示例：
             *  先将这个打印放在最后看看打印结果，可以看到主线程不受影响。
             *   .....come in
             *   main......计算完成
             *   1024
             *
             *  如果将这个方法放在打印之前，那么就必须等待获取到计算结果之后。才能继续执行
             *  打印结果: 可以看到这个主线程确实是等待 其它线程获取到计算结果之后 才继续执行。
             *   .....come in
             *   1024
             *   main......计算完成
             */
            System.out.println(futureTask.get());

            System.out.println(Thread.currentThread().getName()+ "......计算完成");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}


/**
 * 和runnable区别
 * 1、方法不同，一个是call，一个是runn
 * 2、callable中的call有异常抛出
 * 3、这个方法有返回值
 * 4、范型
 */
class MyCallable implements Callable<Integer>{
    @Override
    public Integer call() throws Exception {
        System.out.println(".....come in");
         try {
             TimeUnit.SECONDS.sleep(4);
          }catch (InterruptedException e){
             e.printStackTrace();
          }
        return 1024;
    }
}