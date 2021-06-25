package com.learn.interview.chapter17;

/**
 *  注意:这里的是字节
 * -XX:NewSize=209715200  -XX:MaxNewSize=209715200 -XX:InitialHeapSize=314572800
 * -XX:MaxHeapSize=314572800 -XX:SurvivorRatio=2  -XX:MaxTenuringThreshold=15
 * -XX:PretenureSizeThreshold=20971520
 * -XX:+UseParNewGC -XX:+UseConcMarkSweepGC -XX:+PrintGCDetails -XX:+PrintGCTimeStamps
 *
 * 调整 参数
 * 堆大小调大为了300MB，年轻代给了200MB，
 * 同时“-XX:SurvivorRatio=2”表明，Eden:Survivor:Survivor的比例为2:1:1，
 * 所以Eden区是100MB，每个Survivor区是50MB，老年代也是100MB
 *  这样看到JVM的性能
 * @ClassName learn-spring-cloud
 * @Description 描述
 * @Date 2021/2/4 9:51 上午
 * @Author lin
 * @Version 1.0
 */
public class DemoFullGC3 {

    public static void main(String[] args) throws InterruptedException {
        Thread.sleep(10000);
      while (true){
          loadData();
      }
    }

    private static void loadData() throws InterruptedException {
        byte[] data = null;
        for (int i = 0; i < 4; i++) {
            data = new byte[10 * 1024 * 1024];
        }
        data = null;

        byte[] data1 = new byte[10 * 1024 * 1024];
        byte[] data2 = new byte[10 * 1024 * 1024];
        byte[] data3 = new byte[10 * 1024 * 1024];
        data3 = new byte[10 * 1024 * 1024];

        Thread.sleep(1000);
    }
}
