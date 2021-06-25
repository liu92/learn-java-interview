package com.learn.interview.chapter15;

import java.util.Random;

/**
 * 新生代
 * 1、-Xms10m -Xmx10m -XX:+PrintGCDetails -XX:+PrintCommandLineFlags  -XX:+UseSerialGC (DefNew+Tenured)
 *
 * 2、-Xms10m -Xmx10m -XX:+PrintGCDetails -XX:+PrintCommandLineFlags  -XX:+UseParNewGC (ParNew+Tenured)
 *
 *
 * 3、-Xms10m -Xmx10m -XX:+PrintGCDetails -XX:+PrintCommandLineFlags  -XX:+UseParallelGC (PSYoungGen+ParOldGen)
 *
 * 4、老年代
 * 4.1、-Xms10m -Xmx10m -XX:+PrintGCDetails -XX:+PrintCommandLineFlags  -XX:+UseParallelOldGC (PSYoungGen+ParOldGen)
 * 4.2 不加就是默认的UseParallelGC
 * -Xms10m -Xmx10m -XX:+PrintGCDetails -XX:+PrintCommandLineFlags
 *
 * 5、cms垃圾收集器
 * -Xms10m -Xmx10m -XX:+PrintGCDetails -XX:+PrintCommandLineFlags  -XX:+UseConcMarkSweepGC
 * (par new generation + concurrent mark sweep +Serial Old )
 * ParNew(young 区用）+ CMS（Old 区用） + Serial Old 的收集器组合，Serial Old将作为CMS出错的后备收集器
 *
 * 6、G1垃圾收集器
 * -Xms10m -Xmx10m -XX:+PrintGCDetails -XX:+PrintCommandLineFlags  -XX:+UseG1GC
 *
 * 7、(理论上知道即可，实际中已经被优化掉，没有了)
 * -Xms10m -Xmx10m -XX:+PrintGCDetails -XX:+PrintCommandLineFlags  -XX:+UseSerialOldGC
 *
 * 下面是故意繁琐配置，主要是为了学习，一般生产不这么配置。
 * 下面是故意繁琐配置，主要是为了学习，一般生产不这么配置。
 * 下面是故意繁琐配置，主要是为了学习，一般生产不这么配置。
 *
 * -Xms10m -Xmx10m -XX:+PrintGCDetails -XX:+PrintCommandLineFlags  -XX:+UseParallelGC  -XX:+UseParallelOldGC (PSYoungGen+ )
 *  这种配置和配置一个参数的效果一样
 *
 * -Xms10m -Xmx10m -XX:+PrintGCDetails -XX:+PrintCommandLineFlags  -XX:+UseParNewGC  -XX:+UseConcMarkSweepGC (Par New  generation+ concurrent mark sweep)
 * 这种配置也是一样的，和配置一个参数的效果一样
 *
 *
 *
 * @ClassName: GcDemo
 * @Description:
 * @Author: lin
 * @Date: 2020/9/21 15:01
 * History:
 * @<version> 1.0
 */
public class GcDemo {
    public static void main(String[] args) {
        System.out.println("*************GcDemo Hello");
        try{
           String str = "lin";
           while (true){
               str += str + new Random().nextInt(77777777)+new Random().nextInt(888888888);
               str.intern();
           }
        }catch(Exception e){
            e.printStackTrace();
        }
    }




}
