package com.learn.interview.chapter14;

import java.util.concurrent.TimeUnit;

/**
 * @ClassName: UnableCreateNewThreadDemo
 * @Description: 测试unable to create new native thread错误
 * @Author: lin
 * @Date: 2020/9/17 17:13
 * History:
 * @<version> 1.0
 */
public class UnableCreateNewThreadDemo {
    public static void main(String[] args) {
        //注意：这里没有设置当循环到多少时，退出循环，也就是说
        //这个一直进行循环操作。 只增不减
        for (int i = 0; ; i++) {
            System.out.println("************** i = " + i);
            new Thread(() -> {
                try {
                    //而这里在进来之后，进让线程永远在这里停着。
                    TimeUnit.SECONDS.sleep(Integer.MAX_VALUE);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }, String.valueOf(i)).start();
        }
    }
}
