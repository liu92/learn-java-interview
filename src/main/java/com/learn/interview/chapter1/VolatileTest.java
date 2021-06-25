package com.learn.interview.chapter1;

/**
 * -XX:+TraceClassLoading 参数是将运行jdk, sun报都打印出来
 *
 * -server -XX:+UnlockDiagnosticVMOptions -XX:+TraceClassLoading  -XX:+PrintAssembly -XX:+LogCompilation -XX:LogFile=live.log
 * @ClassName: VolatileTest
 * @Description:
 * @Author: lin
 * @Date: 2020/9/24 13:50
 * History:
 * @<version> 1.0
 */
public class VolatileTest {

    private volatile int volatileCount = 0;
    private int count = 0;

    public static void main(String[] args) {

        VolatileTest volatileTest = new VolatileTest();

        volatileTest.increase();
        volatileTest.decrease();

    }

    private void decrease() {
        count--;
        System.out.println("没有volatile关键字--");
    }

    private void increase() {
        volatileCount++;
        System.out.println("有volatile关键字++");
    }

}
