package com.learn.interview.chapter13;

import java.util.HashMap;
import java.util.Map;
import java.util.WeakHashMap;

/**
 * WeakHashMap使用
 * @ClassName: WeakHashMapDemo
 * @Description:
 * @Author: lin
 * @Date: 2020/9/17 9:28
 * History:
 * @<version> 1.0
 */
public class WeakHashMapDemo {
    public static void main(String[] args) {
//        myHashMap();
        System.out.println("========================");
        myWeakHashMap();
    }


    private static void myHashMap(){
        Map<Integer, String> map = new HashMap<>(16);
        Integer key = new Integer(1);
        String value = "hashMap";

        map.put(key, value);
        System.out.println(map);
        key = null;

        System.gc();
        System.out.println(map +"\t" + map.size());
    }

    private static void myWeakHashMap(){
        Map<Integer, String> map = new WeakHashMap<>(16);
        Integer key = new Integer(2);
        String value = "weakHashMap";

        map.put(key, value);
        System.out.println(map);
        key = null;

        System.gc();
        System.out.println(map+"\t" + map.size());
    }
}
