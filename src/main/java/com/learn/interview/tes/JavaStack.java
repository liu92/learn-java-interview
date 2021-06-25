package com.learn.interview.tes;

/**
 *
 * @ClassName learn-java-interview
 * @Description 描述
 * @Date 2020/12/29 2:13 下午
 * @Author lin
 * @Version 1.0
 */
public class JavaStack {
    /**
     * main方法，每个方法都会在栈中创建一个栈帧，main方法调用m1方法，
     * 那么 main被压入栈底，m1方法在栈顶。执行的时候先打印 111，然后执行m1后 才会执行后面的代码
     * @param args
     */
    public static void main(String[] args) {
        System.out.println("1111");
        m1();
        System.out.println("33333");
    }

    public static void m1(){
        System.out.println("****m1");
    }
}
