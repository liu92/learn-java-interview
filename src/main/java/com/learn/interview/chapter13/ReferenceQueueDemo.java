package com.learn.interview.chapter13;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;

/**
 * 引用队列
 * @ClassName: ReferenceQueueDemo
 * @Description:
 * @Author: lin
 * @Date: 2020/9/17 10:04
 * History:
 * @<version> 1.0
 */
public class ReferenceQueueDemo {
    public static void main(String[] args) {
        Object object = new Object();
        ReferenceQueue<Object> referenceQueue = new ReferenceQueue<>();
        //这里使用弱引用另外的一个构造方法
        WeakReference<Object> weakReference = new WeakReference<>(object, referenceQueue);
        System.out.println("=============GC前===============");
        System.out.println(object);
        System.out.println(weakReference.get());
        //队列消费
        System.out.println(referenceQueue.poll());

        object = null;
        System.gc();
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("=============GC后===============");
        System.out.println(object);
        System.out.println(weakReference.get());
        System.out.println(referenceQueue.poll());
    }
}
