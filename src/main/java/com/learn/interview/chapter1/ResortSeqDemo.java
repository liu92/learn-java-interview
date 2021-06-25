package com.learn.interview.chapter1;

/**
 * @ClassName: ResortSeqDemo
 * @Description:
 * @Author: lin
 * @Date: 2020/9/7 23:00
 * History:
 * @<version> 1.0
 */
public class ResortSeqDemo {
    int a= 0;
    boolean flag = false;

    public void method01() {
        a = 1;
        flag = true;
    }

    public void method02() {
        if(flag) {
            a = a + 5;
            System.out.println("reValue:" + a);
        }
    }
}
