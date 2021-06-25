package com.learn.interview.juc.chapter5;

/**
 * @ClassName learn-java-interview
 * @Description 描述
 * @Date 2020/12/24 5:22 下午
 * @Author lin
 * @Version 1.0
 */
public class SempleDemo {
    public static void main(String[] args) {

        for (int i = 0; i < 3; i++) {
            new Thread(()->{
                System.out.println(Thread.currentThread().getName() + "----线程");
            },String.valueOf(i) ).start();
        }

    }
}
