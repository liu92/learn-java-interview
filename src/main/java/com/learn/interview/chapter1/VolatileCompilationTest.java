package com.learn.interview.chapter1;

/**
 * 测试 汇编指令那么需要加入vm参数
 * -server -Xcomp -XX:+UnlockDiagnosticVMOptions -XX:+PrintAssembly -XX:CompileCommand=compileonly
 *
 * 同时在jdk中 加入
 * /Library/Java/JavaVirtualMachines/jdk1.8.0_271.jdk/Contents/Home/jre/lib/hsdis-amd64.dylib
 *
 *
 * @ClassName learn-java-interview
 * @Description 描述
 * @Date 2021/3/17 10:32 上午
 * @Author lin
 * @Version 1.0
 */
public class VolatileCompilationTest {

   static  volatile  boolean flag = false;

    public static void main(String[] args) throws InterruptedException {
        new Thread(()->{
            System.out.println("waiting...........");
            while (!flag){

            }
            System.out.println("...........ok");
        }).start();
        Thread.sleep(2000);

        new  Thread(()->doData()).start();

    }

    public  static  void doData(){
        System.out.println(".....doData......start");
        flag = true;
        System.out.println(".....doData......end");
    }
}
