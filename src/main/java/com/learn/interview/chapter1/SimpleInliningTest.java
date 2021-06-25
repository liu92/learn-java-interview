package com.learn.interview.chapter1;

/**
 * @ClassName: SimpleInliningTest
 * @Description:
 * @Author: lin
 * @Date: 2020/9/24 15:02
 * History:
 * @<version> 1.0
 */
public class SimpleInliningTest {
    private int count = 0;

    public static void main(String[] args) throws Exception {
        SimpleInliningTest t = new SimpleInliningTest();
        int sum = 0;
        for (int i = 0; i < 1000000; i++) {
            sum += t.m();
        }
        System.out.println(sum);
    }

    public int m() {
        int i = count;
        i = m2(i);
        i += count;
        i *= count;
        i++;
        return i;
    }

    public int m2(int i) {
        if (i % 10 == 0) {
            i += 1;
        } else if (i % 10 == 1) {
            i += 2;
        } else if (i % 10 == 2) {
            i += 3;
        }
        return i;
    }
}
