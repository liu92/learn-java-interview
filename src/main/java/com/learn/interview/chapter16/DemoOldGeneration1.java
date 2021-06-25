package com.learn.interview.chapter16;

/**
 * -XX:NewSize=41943043 -XX:MaxNewSize=41943043 -XX:InitialHeapSize=83886086
 * -XX:MaxHeapSize=83886086 -XX:SurvivorRatio=8 -XX:MaxTenuringThreshold=15
 * -XX:PretenureSizeThreshold=41943043 -XX:+UseParNewGC -XX:+UseConcMarkSweepGC
 * -XX:+PrintGCDetails -XX:+PrintGCTimeStamps
 * 测试 对象进入老年代
 *
 * @ClassName learn-spring-cloud
 * @Description 描述
 * @Date 2021/2/2 4:17 下午
 * @Author lin
 * @Version 1.0
 */
public class DemoOldGeneration1 {

    public static void main(String[] args) {

        int count = 16;
        for (int i = 0; i <= count; i++) {
            byte[] array1 = new byte[10 * 1024 * 1024];
            array1 = new byte[10 * 1024 * 1024];
            array1 = new byte[10 * 1024 * 1024];
            array1 = new byte[10 * 1024];
            array1 = null;
        }
    }
}
