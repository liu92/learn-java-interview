package com.learn.interview.chapter15;

import java.util.Random;

/**
 * -Xms10m -Xmx10m -XX:+PrintGCDetails -XX:+PrintCommandLineFlags  -XX:+UseG1GC
 * @ClassName: G1Demo
 * @Description:
 * @Author: lin
 * @Date: 2020/9/22 10:00
 * History:
 * @<version> 1.0
 */
public class G1Demo {
    public static void main(String[] args) {
        try{
            String str = "lin";
            while (true){
                str += str + new Random().nextInt(77777)+new Random().nextInt(88888);
                str.intern();
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
