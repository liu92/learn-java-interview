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
 * @ClassName learn-spring-cloud
 * @Description 描述
 * @Date 2021/2/2 4:17 下午
 * @Author lin
 * @Version 1.0
 */
public class DemoFullGC {

    public static void main(String[] args) {

        byte[] array1 = new byte[2 * 1024 * 1024];
        array1 = null;
        byte[] array2 = new byte[2 * 1024 * 1024];
        byte[] array3 = new byte[2 * 1024 * 1024];


        byte[] array4 = new byte[2 * 1024 * 1024];
        byte[] array5 = new byte[128 * 1024];
//        array2 = null;

        byte[] array6 = new byte[2 * 1024 * 1024];
    }
}
