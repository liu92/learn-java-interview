package com.learn.interview.chapter16;

/**
 * 测试 对象进入老年代
 * @ClassName learn-spring-cloud
 * @Description 描述
 * @Date 2021/2/2 4:17 下午
 * @Author lin
 * @Version 1.0
 */
public class DemoOldGeneration {

    public static void main(String[] args) {
        byte[] array1 = new byte[2 * 1024 * 1024];
        array1 = new byte[2 * 1024 * 1024];
        array1 = new byte[2 * 1024 * 1024];
        array1 = null;
        byte[] array2 = new byte[128* 1024];

        byte[] array3 = new byte[2 * 1024 * 1024];
        array3 = new byte[2 * 1024 * 1024];
        array3 = new byte[2 * 1024 * 1024];
        array3 = new byte[128 * 1024];
        array3 = null;

        byte[] array4 = new byte[2 * 1024 * 1024];
    }
}
