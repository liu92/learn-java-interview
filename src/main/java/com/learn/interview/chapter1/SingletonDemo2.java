package com.learn.interview.chapter1;

/**
 * @ClassName: SingletonDemo2
 * @Description:
 * @Author: lin
 * @Date: 2020/9/8 9:43
 * History:
 * @<version> 1.0
 */
public class SingletonDemo2 {

    private SingletonDemo2(){
        System.out.println(Thread.currentThread().getName() + "\t 我是构造方法SingletonDemo2");
    }

    /**
     * 静态内部类
     */
    public  static  class InstanceHolder{
        //这对像是类对象，所以只在jvm中只有一个
         private static final SingletonDemo2 INSTANCE = new SingletonDemo2();
    }

    public static SingletonDemo2 getInstance(){
        return  InstanceHolder.INSTANCE;
    }

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                SingletonDemo2.getInstance();
            }, String.valueOf(i)).start();
        }
    }
}
