package com.learn.interview;

/**
 * @ClassName learn-java-interview
 * @Description 描述
 * @Date 2020/12/22 8:37 上午
 * @Author lin
 * @Version 1.0
 */
public class Test {
    public static String  string(String ...strings){
        String string = null;
        for (String s : strings) {
            string +=s;
        }

        return string;


    }


    public static void main(String[] args) {
        System.out.println(string("a"));
        System.out.println(string("a","b"));
        System.out.println(string("a","b","c"));
        System.out.println(string("a","b","c","d"));
        System.out.println(string("a","b","c","d","e"));

    }
}
