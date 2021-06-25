package com.learn.interview.chapter14;

/**
 * 测试堆内存不够,然后报错异常
 * @ClassName: JavaHeapSpaceDemo
 * @Description:
 * @Author: lin
 * @Date: 2020/9/17 14:26
 * History:
 * @<version> 1.0
 */
public class JavaHeapSpaceDemo {
    public static void main(String[] args) {
        // 堆空间的大小 -Xms10m -Xmx10m
        // 创建一个 80M的字节数组
        byte [] bytes = new byte[80 * 1024 * 1024];
    }
}
