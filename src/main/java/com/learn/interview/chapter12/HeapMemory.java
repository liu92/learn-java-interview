package com.learn.interview.chapter12;

/**
 * @ClassName: HeapMemory
 * @Description:
 * @Author: lin
 * @Date: 2020/9/16 10:06
 * History:
 * @<version> 1.0
 */
public class HeapMemory {

    public static void main(String[] args) {
        System.out.println(Runtime.getRuntime().availableProcessors());
        //返回 Java虚拟机试图使用的最大内存量。物理内存的1/4（-Xmx）
        long maxMemory = Runtime.getRuntime().maxMemory() ;
        //返回 Java虚拟机中的内存总量(初始值)。物理内存的1/64（-Xms）
        long totalMemory = Runtime.getRuntime().totalMemory() ;
        System.out.println("MAX_MEMORY(-Xmx:MaxHeapSize) =" + maxMemory +"(字节)、" + (maxMemory / (double)1024 / 1024) + "MB");
        System.out.println("DEFAULT_MEMORY(-Xms:InitialHeapSize) = " + totalMemory + " (字节)、" + (totalMemory / (double)1024 / 1024) + "MB");
    }
}
