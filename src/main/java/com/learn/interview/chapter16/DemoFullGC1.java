package com.learn.interview.chapter16;

/**
 * -XX:NewSize=10485760 -XX:MaxNewSize=10485760 -XX:InitialHeapSize=20971520
 * -XX:MaxHeapSize=20971520 -XX:SurvivorRatio=8  -XX:MaxTenuringThreshold=15
 * -XX:PretenureSizeThreshold=10485760 -XX:+UseParNewGC -XX:+UseConcMarkSweepGC
 * -XX:+PrintGCDetails -XX:+PrintGCTimeStamps
 *
 * -XX:PretenureSizeThreshold: 设置大对象阈值为3MB，也就是超过3MB，就直接进入老年代
 * 测试 Full GC
 *
 *  可能老年代可用空间小于了历次Young GC后升入老年代的对象的平均大小，就会在Young GC之前，提前触发Full GC
 * @ClassName learn-spring-cloud
 * @Description 描述
 * @Date 2021/2/2 4:17 下午
 * @Author lin
 * @Version 1.0
 */
public class DemoFullGC1 {

    public static void main(String[] args) {

        byte[] array1 = new byte[3 * 1024 * 1024];

        array1 = new byte[2 * 1024 * 1024];
        array1 = new byte[2 * 1024 * 1024];
        array1 = null;

        byte[] array2 = new byte[2 * 1024 * 1024];
        byte[] array3 = new byte[2 * 1024 * 1024];

//        byte[] array3 = new byte[128 * 1024];
//        byte[] array6 = new byte[2 * 1024 * 1024];
    }
}
