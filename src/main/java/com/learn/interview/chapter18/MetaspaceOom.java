package com.learn.interview.chapter18;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;

/**
 * 设置参数
 * -XX:MetaspaceSize=10m -XX:MaxMetaspaceSize=10m
 *
 * 使用Cglib 来动态创建类
 *
 *  测试Metaspace OutOfMemoryError
 * @ClassName learn-spring-cloud
 * @Description 描述
 * @Date 2021/2/5 4:48 下午
 * @Author lin
 * @Version 1.0
 *
 */
public class MetaspaceOom {

    public static void main(String[] args) {
        while (true) {
            Enhancer enhancer = new Enhancer();
            enhancer.setSuperclass(Car.class);
            enhancer.setUseCache(false);
            enhancer.setCallback((MethodInterceptor) (o, method, objects, methodProxy)->{
                    if (method.getName().equals("run")) {
                        System.out.println("启动之前，先进行检查。。。。。");
                        return methodProxy.invokeSuper(o, objects);
                    } else {
                        return methodProxy.invokeSuper(o, objects);
                    }
            });
        }
    }


    private static class Car {
        public  void  run(){
            System.out.println("汽车启动，开始运行。。。。。");
        }
    }
}
