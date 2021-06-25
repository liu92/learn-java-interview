package com.learn.interview.chapter3;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @ClassName: MapNotSafeDemo
 * @Description:
 * @Author: lin
 * @Date: 2020/9/9 10:54
 * History:
 * @<version> 1.0
 */
public class MapNotSafeDemo {
    public static void main(String[] args) {
        Map<String, String> map = new HashMap<>();
        for (int i = 0; i < 30; i++) {
            new Thread(() -> {
                map.put(Thread.currentThread().getName(), UUID.randomUUID().toString().substring(0, 8));
                System.out.println(map);
            }, String.valueOf(i)).start();
        }
    }
}
