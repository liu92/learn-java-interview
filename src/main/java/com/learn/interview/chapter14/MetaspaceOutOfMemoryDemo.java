package com.learn.interview.chapter14;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * 模拟测试Metaspace 内存溢出
 * 设置jvm参数： -XX:MetaspaceSize=8m -XX:MaxMetaspaceSize=8m
 *
 * 在java8版本使用Metaspace来代替永久代
 *
 * Metaspace是方法区HotSpot中的实现，它与持久代最大的区别在于：Metaspace并不在虚拟内存中，而是使用本地内存，也即在java8中，
 * class metadata（the virtual machines internal presentation of Java class），
 * 被存储在叫做Metaspace的native memory
 *
 * 永久代（java8后背元空间Metaspace取代了）存放了以下信息：
 *  虚拟机加载的类信息
 *  常量池
 *  静态变量
 *  即时编译后的代码
 *
 * @ClassName: MetaspaceOutOfMemoryDemo
 * @Description: 测试Metaspace元空间 的内存溢出
 * @Author: lin
 * @Date: 2020/9/18 8:58
 * History:
 * @<version> 1.0
 */
public class MetaspaceOutOfMemoryDemo {
    /**
     * 创建静态类
     */
    static  class OomTest{}

    public static void main(String[] args) {
        // 模拟计数多少次以后发生异常
        int i =0;
        try{
           while (true){
               i++;
               //使用spring的动态字节码计技术
               Enhancer enhancer = new Enhancer();
               enhancer.setSuperclass(OomTest.class);
               enhancer.setUseCache(false);
               enhancer.setCallback(new MethodInterceptor() {
                   @Override
                   public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
                       //通过反射技术，不停的产生OomTest。
                       return methodProxy.invokeSuper(o, args);
                   }
               });
               //通过字节码技术，不停的创建静态类OomTest，因为是静态内部类的所以不停的往Metaspace中加载
               enhancer.create();
           }
        }catch(Exception e){
            System.out.println("发生异常的次数:" + i);
            e.printStackTrace();
        }finally{

        }
    }
}
