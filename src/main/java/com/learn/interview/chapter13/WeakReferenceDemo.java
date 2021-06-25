package com.learn.interview.chapter13;

import java.lang.ref.WeakReference;

/**
 * 弱引用
 * @ClassName: WeakReferenceDemo
 * @Description: 弱引用
 * @Author: lin
 * @Date: 2020/9/16 23:09
 * History:
 * @<version> 1.0
 */
public class WeakReferenceDemo {
    public static void main(String[] args) {
        Object o1 = new Object();
        WeakReference<Object> weakReference = new WeakReference<>(o1);
        System.out.println(o1);
        System.out.println(weakReference.get());
        o1 = null;
        System.gc();
        System.out.println("=============GC后===============");
        System.out.println(o1);
        System.out.println(weakReference.get());
    }
}
