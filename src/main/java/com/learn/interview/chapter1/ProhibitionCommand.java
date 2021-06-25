package com.learn.interview.chapter1;

/**
 * @ClassName: ProhibitionCommand
 * @Description: volatile禁止指令重排序
 * @Author: lin
 * @Date: 2020/9/7 22:39
 * History:
 * @<version> 1.0
 */
public class ProhibitionCommand {

    public static void main(String[] args) {
        prohibition();
    }
    public  static void  prohibition(){
        int x = 11;
        int y = 12;
        x = x + 5;
        y = x * x;
    }
}
