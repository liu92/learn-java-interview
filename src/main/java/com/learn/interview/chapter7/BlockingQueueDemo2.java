package com.learn.interview.chapter7;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName: BlockingQueueDemo2
 * @Description:
 * @Author: lin
 * @Date: 2020/9/10 11:17
 * History:
 * @<version> 1.0
 */
public class BlockingQueueDemo2 {
    public static void main(String[] args) throws InterruptedException {
        BlockingQueue<String> bkQueue = new ArrayBlockingQueue<>(3);
        System.out.println(bkQueue.offer("a", 2L, TimeUnit.SECONDS));
        System.out.println(bkQueue.offer("b", 2L, TimeUnit.SECONDS));
        System.out.println(bkQueue.offer("c", 2L, TimeUnit.SECONDS));

        System.out.println("===============================");
        System.out.println(bkQueue.poll(2L, TimeUnit.SECONDS));
        System.out.println(bkQueue.poll(2L, TimeUnit.SECONDS));
        System.out.println(bkQueue.poll(2L, TimeUnit.SECONDS));
        System.out.println(bkQueue.poll(2L, TimeUnit.SECONDS));
    }
}
