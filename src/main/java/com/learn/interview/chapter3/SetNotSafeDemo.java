package com.learn.interview.chapter3;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * 集合不安全Set
 * @ClassName: SetNotSafeDemo
 * @Description:
 * @Author: lin
 * @Date: 2020/9/9 10:03
 * History:
 * @<version> 1.0
 */
public class SetNotSafeDemo {
    public static void main(String[] args) {

        Set<String> set = new CopyOnWriteArraySet<>();

        for (int i = 0; i < 30; i++) {
            new Thread(() -> {
                set.add(UUID.randomUUID().toString().substring(0, 8));
                System.out.println(set);
            }, String.valueOf(i)).start();
        }

        new HashSet<>().add("a");
    }
    /**在出现问题后，要分析问题，并总结问题。
     *
     * 1、故障现象
     *    java.util.ConcurrentModificationException
     *
     * 2、导致原因
     *
     * 3、解决方案
     *   3.1  Collections.synchronizedSet(new HashSet<>());
     *   3.2  new CopyOnWriteArraySet<>();
     *
     * 4、优化建议(同样的错误不犯第二次)
     */
}
