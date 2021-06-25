package com.learn.interview.chapter13;

import java.lang.ref.SoftReference;

/**
 * @ClassName: SoftReferenceDemo
 * @Description: 软引用
 * @Author: lin
 * @Date: 2020/9/16 22:42
 * History:
 * @<version> 1.0
 */
public class SoftReferenceDemo {

    public static void main(String[] args) {
        //softRefMemoryEnough();
        softRefMemoryNoEnough();
    }

    /**
     * 内存够用的时候
     */
    public static   void softRefMemoryEnough(){
        // 创建一个强应用
        Object o1 = new Object();
        // 创建一个软引用
        SoftReference<Object> softReference = new SoftReference<>(o1);
        System.out.println("*****内存够用时**********o1: "+o1);
        System.out.println("***内存够用时******softReference: " + softReference.get());

        o1 = null;
        // 手动GC
        System.gc();

        System.out.println("*******内存够用时,进行手动GC后***************o1: "+ o1);
        System.out.println("***内存够用时手动GC后软引用*******softReference: " + softReference.get());
    }


    /**
     * JVM配置，故意产生大对象并配置小的内存，让它的内存不够用了导致OOM，看软引用的回收情况
     * -Xms10m -Xmx10m -XX:+PrintGCDetails
     */
    public static void softRefMemoryNoEnough(){
        System.out.println("========================");
        // 创建一个强应用
        Object o1 = new Object();
        // 创建一个软引用
        SoftReference<Object> softReference = new SoftReference<>(o1);
        System.out.println("*********内存不够用时***********o1: "+o1);
        System.out.println("****内存不够用时*****softReference: "+ softReference.get());

        o1 = null;

        // 模拟OOM自动GC
        try {
            // 创建30M的大对象
            byte[] bytes = new byte[30 * 1024 * 1024];
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.out.println("*********内存不够用,并且进行手动GC*************o1: "+ o1);
            System.out.println("******内存不够用,并且进行手动GC*****softReference: "+ softReference.get());
        }
    }
}
