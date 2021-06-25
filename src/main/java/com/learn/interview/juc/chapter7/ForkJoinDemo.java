package com.learn.interview.juc.chapter7;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;

/**
 * @ClassName learn-java-interview
 * @Description 描述
 * @Date 2020/12/28 10:53 上午
 * @Author lin
 * @Version 1.0
 */
public class ForkJoinDemo {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
       MyTask myTask = new MyTask(0, 100);
        ForkJoinPool threadPool = new ForkJoinPool();
        ForkJoinTask<Integer> forkJoinTask= threadPool.submit(myTask);
        System.out.println(forkJoinTask.get());

        threadPool.shutdown();
    }

}
