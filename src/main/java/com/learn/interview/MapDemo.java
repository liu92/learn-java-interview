package com.learn.interview;

import java.util.Hashtable;
import java.util.Map;

/**
 * @ClassName learn-java-interview
 * @Description 描述
 * @Date 2020/11/18 4:24 下午
 * @Author lin
 * @Version 1.0
 */
public class MapDemo {

    /**
     * @param args
     * @name:
     * @description: TODO
     * @return: void
     * @date:
     * @author:
     */
    public static void main(String[] args) {
//        Map<Integer, Integer> map = new HashMap<>(100);
//
//        map.put(1, 2);
//        System.out.println("map =====" + map);
////        System.out.println("位运算===="+ tableSizeFor(100));

        Map<Integer, Integer> map1 = new Hashtable<>();
        map1.put(1, 1);
        map1.put(2, 2);
        map1.put(3, 3);

        for (int i = map1.size(); i --> 0; ) {

            System.out.println("map1 =====" + i);
        }

    }


    static final int tableSizeFor(int cap) {
        int n = cap - 1; //99
        n |= n >>> 1;  // 99>>1 =49 , 99|49=115
        n |= n >>> 2;  // 115>>2 = 28 , 115|28 =127
        n |= n >>> 4; // 127>>4 = 7, 127|7=127
        n |= n >>> 8; // 127>>8 = 127, 127|127 =127
        n |= n >>> 16; // 127>>16= 127
        //n = 127+1 =128
        return (n < 0) ? 1 : (n >= 1 << 30) ? 1 << 30 : n + 1;
    }






}
