package com.learn.interview.chapter17;

/**
 *  注意:这里的是字节
 * -XX:NewSize=104857600  -XX:MaxNewSize=104857600 -XX:InitialHeapSize=209715200
 * -XX:MaxHeapSize=209715200 -XX:SurvivorRatio=8  -XX:MaxTenuringThreshold=15
 * -XX:PretenureSizeThreshold=20971520
 * -XX:+UseParNewGC -XX:+UseConcMarkSweepGC -XX:+PrintGCDetails -XX:+PrintGCTimeStamps
 * @ClassName learn-spring-cloud
 * @Description 描述
 * @Date 2021/2/4 9:51 上午
 * @Author lin
 * @Version 1.0
 */
public class DemoFullGC2 {

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
