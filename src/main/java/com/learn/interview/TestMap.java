package com.learn.interview;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @ClassName: TestMap
 * @Description:
 * @Author: lin
 * @Date: 2020/11/11 16:45
 * History:
 * @<version> 1.0
 */
public class TestMap<k,V> {
    public static void main(String[] args) {
        Map<String, String> map = new ConcurrentHashMap<>(16);
        int count = 20;
        for (int i = 0; i < count; i++) {
            map.put("ce=" + i, "测试哈哈"+i);
        }



        System.out.println("map === "+ map);

        System.out.println("get==" + map.get("ce="+0));

//        int[] t = new int[2];
//        for (int[] e = t; ;){
//            if(e[1]==0){
//                System.out.println("......");
//                break;
//            }
//
//        }


        System.out.println("-======"+Runtime.getRuntime().availableProcessors());


    }


}
