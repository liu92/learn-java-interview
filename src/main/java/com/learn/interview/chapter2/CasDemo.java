package com.learn.interview.chapter2;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @ClassName: CasDemo
 * @Description:
 * @Author: lin
 * @Date: 2020/9/8 10:00
 * History:
 * @<version> 1.0
 */
public class CasDemo {
    public static void main(String[] args) {
        //这个初始值是5，也就是主物理内存中的是5，拷贝到main线程的工作内存中去做修改处理。
        //希望将主物理内存的5，改为2020
        AtomicInteger atomicInteger = new AtomicInteger(5);
        //期望值5，如果初始值是5，那么线程会将这个值快照和主物理内存的值进行比较
        // 在将工作内存的值写回到主物理内存时 希望主物理内存的值，不被其它线程修改过，就是说明
        // 没有其它线程与其争抢，那么就将值修改位2020
        System.out.println(atomicInteger.compareAndSet(5, 2020) + "\t current data: " + atomicInteger.get());
        System.out.println(atomicInteger.compareAndSet(5, 1024) + "\t current data: " + atomicInteger.get());
    }
}
