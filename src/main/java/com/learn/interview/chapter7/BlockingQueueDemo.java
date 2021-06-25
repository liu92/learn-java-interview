package com.learn.interview.chapter7;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * 1、在多线程领域:所谓阻塞 就是在某些情况下会挂起线程(即阻塞)，一旦条件满足，被挂起的线程又会被自动唤醒
 * 2、为什么需要BlockingQueue？ 因为BlockingQueue不需要我们去关系什么时候阻塞线程，什么时候唤醒线程，
 *   因为这一切BlockingQueue都给处理了
 *
 * 3、在Concurrent包发布之前，在多线程情况下，需要我们程序员去控制这些细节，尤其还要兼顾效率和线程安全，
 *  而这会给我们的程序带来不小的复杂度。
 *
 * ArrayBlockingQueue: 由数组结构构成的有界阻塞队列。
 * LinkedBlockingDeque: 由链表结构组成的有界阻塞队列。
 * PriorityBlockingQueue: 支持优先级排序的无界阻塞队列。
 * DelayQueue: 使用优先级队列实现的延迟无界阻塞队列
 * SynchronousQueue: 不存储元素的阻塞队列，也即单个元素的队列
 * LinkedTransferQueue:由链表组成的无界阻塞队列
 *LinkedBlockingDeque:由链表组成的双向阻塞队列
 *
 *
 * @ClassName: BlockingQueueDemo
 * @Description:
 * @Author: lin
 * @Date: 2020/9/10 11:17
 * History:
 * @<version> 1.0
 */
public class BlockingQueueDemo {
    public static void main(String[] args) throws InterruptedException {
        BlockingQueue<String> blockingQueue = new ArrayBlockingQueue<>(3);
        System.out.println(blockingQueue.add("a"));
        System.out.println(blockingQueue.add("b"));
        System.out.println(blockingQueue.add("c"));


        /**
         *  //1、当阻塞队列满时,再往队列里add插入元素会抛出
         *  java.lang.IllegalStateException: Queue full
         *  2、当阻塞队列空时，再从队列中remove 移除元素会抛出NoSuchElementException
         */

        System.out.println(blockingQueue.add("xxxx"));

//        System.out.println(blockingQueue.remove());
//        System.out.println(blockingQueue.remove());
//        System.out.println(blockingQueue.remove());
//
//        System.out.println(blockingQueue.remove());


        /**
         * 特殊值：
         * 插入时,成功返回ture 失败返回false
         * 移除方法时，成功返回出队列的元素，队列里没有就返回null
         */
//        System.out.println(blockingQueue.offer("a"));
//        System.out.println(blockingQueue.offer("b"));
//        System.out.println(blockingQueue.offer("c"));
//        System.out.println(blockingQueue.offer("xx"));

        /**
         * 值等待3s钟
         */
        //    blockingQueue.offer("a",3, TimeUnit.SECONDS);
//
        /**
         *使用put
         * 如果队列满了那么就会一直阻塞，知道队列中有空的位置
         *
         */
//          blockingQueue.put("aaa");
//        System.out.println();

        /**
         * 阻塞等待 如果队列中没有 那么就一直等待
         */
//         blockingQueue.take();



//        System.out.println(blockingQueue.poll());
//        System.out.println(blockingQueue.poll());
//        System.out.println(blockingQueue.poll());
//        System.out.println(blockingQueue.poll());

    }
}
