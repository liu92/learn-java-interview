package com.learn.interview.chapter1;

/**
 * @ClassName: Test1
 * @Description:
 * @Author: lin
 * @Date: 2020/9/7 17:06
 * History:
 * @<version> 1.0
 */
public class Test1 {

    public static void main(String[] args) {
        System.out.println("=====");
//        add();

        increment();
        System.out.println("********");
    }

    volatile static   int number =0;
    static  int count = 0;
//    public static synchronized  void add(){
//        number ++ ;
//    }

    public static   void increment(){
        count ++ ;
    }

}