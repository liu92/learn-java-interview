package com.learn.interview.chapter1;

/**
 * volatile在单例模式下的使用
 * @ClassName: SingletonDemo1
 * @Description:
 * @Author: lin
 * @Date: 2020/9/8 8:20
 * History:
 * @<version> 1.0
 */
public class SingletonDemo1 {

    private static volatile SingletonDemo1 instance = null;

    private SingletonDemo1(){
        System.out.println(Thread.currentThread().getName() + "\t 我是构造方法SingletonDemo");
    }

    public static  SingletonDemo1 getInstance(){
        if(null == instance){
            // 同步代码段的时候，进行检测
            synchronized (SingletonDemo1.class){
                if(null == instance){
                    instance = new SingletonDemo1();
                }
            }
        }
        return  instance;
    }

    public static void main(String[] args) {
        // 这里的 == 是比较内存地址
//        System.out.println(SingletonDemo.getInstance() == SingletonDemo.getInstance());
//        System.out.println(SingletonDemo.getInstance() == SingletonDemo.getInstance());
//        System.out.println(SingletonDemo.getInstance() == SingletonDemo.getInstance());
//        System.out.println(SingletonDemo.getInstance() == SingletonDemo.getInstance());

        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                SingletonDemo1.getInstance();
            }, String.valueOf(i)).start();
        }
    }
}
