package com.learn.interview.chapter3;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * 多线程情况下测试ArrayList不安全情况
 * @ClassName: ArrayListNotSafeDemo
 * @Description:
 * @Author: lin
 * @Date: 2020/9/8 23:22
 * History:
 * @<version> 1.0
 */
public class ArrayListNotSafeDemo {
    public static void main(String[] args) {

        List<String> list = new CopyOnWriteArrayList<>();

        for (int i = 0; i < 30; i++) {
            new Thread(() -> {
                list.add(UUID.randomUUID().toString().substring(0, 8));
                System.out.println(list);
            }, String.valueOf(i)).start();
        }
    }
    /**在出现问题后，要分析问题，并总结问题。
     *
     * 1、故障现象
     *    java.util.ConcurrentModificationException
     *
     * 2、导致原因
     *
     * 3、解决方案
     *   3.1 new Vector()；
     *   3.2 Collections.synchronizedList(new ArrayList<>());
     *   3.3 new CopyOnWriteArrayList<>();
     *
     * 4、优化建议(同样的错误不犯第二次)
     */
}
