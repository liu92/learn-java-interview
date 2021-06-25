package com.learn.interview.chapter14;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName: GCOverheadLimitDemo
 * @Description: 测试GC overhead limit效果
 * @Author: lin
 * @Date: 2020/9/17 14:58
 * History:
 * @<version> 1.0
 */
public class GcOverheadLimitDemo {
    public static void main(String[] args) {
        int i = 0;
        List<String> list = new ArrayList<>();
        try {
            while(true) {
                list.add(String.valueOf(++i).intern());
            }
        } catch (Throwable e) {
            System.out.println("***************i:" + i);
            //Exception in thread "main" java.lang.OutOfMemoryError: GC overhead limit exceeded
            e.printStackTrace();
            throw e;
        }
    }
}
