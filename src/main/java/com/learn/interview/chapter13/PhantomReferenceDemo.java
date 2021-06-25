package com.learn.interview.chapter13;

import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;

/**
 * @ClassName: PhantomReferenceDemo
 * @Description: 虚引用
 * @Author: lin
 * @Date: 2020/9/17 10:33
 * History:
 * @<version> 1.0
 */
public class PhantomReferenceDemo {
    public static void main(String[] args) {
        Object object = new Object();
        //引用队列
        ReferenceQueue<Object> referenceQueue = new ReferenceQueue<>();
        //虚引用它来监控这个object对象的回收信息。也就是说在GC之前没有放在队列中，在GC之后会放在这个队列中
        // 这个有点类似于SpringAop的后置通知，在最终被处理完之前，还是其它要做的没有。 也就是后置通知
        PhantomReference<Object> phantomReference = new PhantomReference<>(object, referenceQueue);
        System.out.println(object);
        //PhantomReference的get方法总是返回null
        System.out.println(phantomReference.get());
        //队列消费
        System.out.println(referenceQueue.poll());
        System.out.println("===================================");

        object = null;
        System.gc();
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(object);
        //PhantomReference的get方法总是返回null
        System.out.println(phantomReference.get());
        //队列消费
        System.out.println(referenceQueue.poll());
    }
}
