package com.learn.interview.chapter13;

/**
 * 强引用示例
 * @ClassName: StrongReferenceDemo
 * @Description:
 * @Author: lin
 * @Date: 2020/9/16 22:26
 * History:
 * @<version> 1.0
 */
public class StrongReferenceDemo {
    public static void main(String[] args) {
        // 这样定义的默认就是强应用
        Object o1 = new Object();
        // 使用第二个引用，指向刚刚创建的Object对象
        System.out.println("对象引用哦o1= " + o1);
        Object o2 =  o1;
        // 置空
        o1 = null;
        // 垃圾回收
        System.gc();

        System.out.println("对象引用哦o2= " + o2);
    }
}
