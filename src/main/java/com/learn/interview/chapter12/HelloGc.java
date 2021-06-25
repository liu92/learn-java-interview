package com.learn.interview.chapter12;

import java.util.concurrent.TimeUnit;

/**
 * 查看运行的Java程序，JVM参数是否开启，具体值为多少？
 * @ClassName: HelloGc
 * @Description:
 * @Author: lin
 * @Date: 2020/9/15 22:41
 * History:
 * @<version> 1.0
 */
public class HelloGc {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("hello GC");
        TimeUnit.SECONDS.sleep(20000000);
    }
}
