package com.learn.interview.chapter14;

/**
 * 测试堆栈溢出，
 * @ClassName: StackOverflowErrorDemo
 * @Description:
 * @Author: lin
 * @Date: 2020/9/17 14:15
 * History:
 * @<version> 1.0
 */
public class StackOverflowErrorDemo {
    public static void main(String[] args) {
        stackOverflowError();
    }

    /**
     * 栈一般是512K，不断的深度调用，直到栈被撑破
     */
    public static void stackOverflowError(){
        stackOverflowError();
    }
}
