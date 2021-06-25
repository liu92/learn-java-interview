package com.learn.interview.chapter14;

/**
 * 直接内存，也就是不再java虚拟机堆内存中。
 *  Direct buffer memory
 * Netty + NIO：这是由于NIO引起的
 *
 *
 *
 * @ClassName: DirectBufferMemoryDemo
 * @Description:
 * @Author: lin
 * @Date: 2020/9/17 15:36
 * History:
 * @<version> 1.0
 */
public class DirectBufferMemoryDemo1 {
    public static void main(String[] args) {
        System.out.println("配置的maxDirectMemory:" +
                (sun.misc.VM.maxDirectMemory() /(double) 1024/1024) + "MB");
    }
}
