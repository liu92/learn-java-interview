package com.learn.interview.chapter1;

import java.util.concurrent.TimeUnit;

/**
 * Volatile Java虚拟机提供的轻量级同步机制
 *
 *  如果要测试 汇编指令那么需要加入vm参数
 *   -server -Xcomp -XX:+UnlockDiagnosticVMOptions -XX:+PrintAssembly -XX:CompileCommand=compileonly
 *
 * 可见性（及时通知）
 * 不保证原子性
 * 禁止指令重排
 * @ClassName: VolatileDemo
 * @Description:
 * @Author: lin
 * @Date: 2020/9/7 15:06
 * History:
 * @<version> 1.0
 */
public class VolatileDemo {

    public static void main(String[] args) {
        MyData myData = new MyData();

        new Thread(()->{
            System.out.println(Thread.currentThread().getName() + "\t come in");

            // 线程睡眠3秒，假设在进行运算
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            // 修改number的值
            myData.addTo60();

            // 输出修改后的值
            System.out.println(Thread.currentThread().getName() + "\t update number value:" + myData.number);

        }, "AAA").start();

        while(myData.number == 0) {
            // main线程就一直在这里等待循环，直到number的值不等于零
        }

        // 按道理这个值是不可能打印出来的，因为主线程运行的时候，number的值为0，所以一直在循环
        // 如果能输出这句话，说明AAA线程在睡眠3秒后，更新的number的值，重新写入到主内存，并被main线程感知到了
        System.out.println(Thread.currentThread().getName() + "\t mission is over");
    }
}


class MyData {

//    int number = 0;
    /**
     * volatile 修饰的关键字，是为了增加 主线程和线程之间的可见性，只要有一个线程修改了内存中的值，其它线程也能马上感知
     */
    volatile  int number =0;


    public void addTo60() {
        this.number = 60;
    }
}