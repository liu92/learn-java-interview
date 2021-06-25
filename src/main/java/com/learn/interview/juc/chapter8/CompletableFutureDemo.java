package com.learn.interview.juc.chapter8;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * @ClassName learn-java-interview
 * @Description 描述
 * @Date 2020/12/28 2:29 下午
 * @Author lin
 * @Version 1.0
 */
public class CompletableFutureDemo {
    public static void main(String[] args) {

        //异步调用 runAsync 没有返回值
//        CompletableFuture<Void> completableFuture = CompletableFuture.runAsync(() -> {
//            System.out.println(Thread.currentThread().getName() + " 没有返回......");
//        });
//        try {
//            completableFuture.get();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        } catch (ExecutionException e) {
//            e.printStackTrace();
//        }

        //异步调用 supplyAsync有返回值， 供给型函数没有参数但是有返回值
        CompletableFuture<Integer> supplyAsync = CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread().getName() + "\t completableFuture2");
            int a = 10/0;
            return 10;
        });
        //两个参数, BiConsumer中 accept方法两个参数
        try {
            System.out.println(supplyAsync.whenComplete((t, u) -> {
                //如果上面到异步处理没有 出现问题那么就进入这里 进行处理，若出现错误那么就进到下面的方法
                //参数t是上面异步执行没有出错 返回的值
                System.out.println("******* 参数t: " + t);
                //参数u是 上面异步执行出现错误 返回异常信息
                //如果出现异常: java.util.concurrent.CompletionException: java.lang.ArithmeticException: / by zero
                // 并且 也会返回下面的 exceptionally中的返回值
                System.out.println("******* 参数u: " + u);
            }).exceptionally(f -> {
                //function 函数式接口，有一个输入参数，那么就有一个输出
                System.out.println("******** exception" + f.getMessage());
                return 404;
            }).get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}
