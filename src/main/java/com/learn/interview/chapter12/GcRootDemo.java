package com.learn.interview.chapter12;

/**
 *  在Java中，可以作为GC Roots的对象有：
 *   1 虚拟机栈（栈帧中的局部变量区，也叫做局部变量表）中的引用对象
 *   2 方法区中的类静态属性引用的对象
 *   3 方法区中常量引用的对象
 *   4 本地方法栈中的JNI（Native方法）的引用对象
 *
 * @ClassName: GcRootDemo
 * @Description:
 * @Author: lin
 * @Date: 2020/9/15 22:20
 * History:
 * @<version> 1.0
 */
public class GcRootDemo {

    // 2.方法区中的类静态属性引用的对象
    // private static GCRootDemo2 t2;

    // 3.方法区中的常量引用，GC Roots 也会以这个为起点，进行遍历
    // private static final GCRootDemo3 t3 = new GCRootDemo3(8);

    public static void m1() {
        // 1.虚拟机栈（栈帧中的局部变量区，也叫做局部变量表）中的引用对象
        GcRootDemo t1 = new GcRootDemo();
        System.gc();
        System.out.println("第一次GC完成");
    }
    public static void main(String[] args) {
        m1();
    }
}
