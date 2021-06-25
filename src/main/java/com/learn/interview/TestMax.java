package com.learn.interview;

import java.util.Iterator;
import java.util.LinkedList;

/**
 * @ClassName: TestMax
 * @Description:
 * @Author: lin
 * @Date: 2020/9/29 15:30
 * History:
 * @<version> 1.0
 */
public class TestMax {
    public static void main(String[] args) {
//        int max = Math.max(10, 5);
//        System.out.println("max==============="+max);

        LinkedList<Integer> list = new LinkedList<>();
        list.add(2);
        list.add(3);
        list.add(5);
        list.add(1);

        list.addFirst(4);




        int count = 0;
        Iterator<Integer> iterator = list.iterator();
        while (iterator.hasNext()){
            Integer next = iterator.next();
            iterator.remove();
            System.out.println("当前值:" + next + ",执行次数" + ++count);
        }



    }
}
