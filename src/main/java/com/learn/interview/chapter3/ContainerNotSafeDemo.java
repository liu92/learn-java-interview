package com.learn.interview.chapter3;

import java.util.Arrays;
import java.util.List;

/**
 * @ClassName: ContainerNotSafeDemo
 * @Description:
 * @Author: lin
 * @Date: 2020/9/8 22:48
 * History:
 * @<version> 1.0
 */
public class ContainerNotSafeDemo {
    public static void main(String[] args) {
        List<String> list = Arrays.asList("a", "b", "c");
        list.forEach(System.out::print);
    }
}
