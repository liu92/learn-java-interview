package com.learn.interview.tes;

/**
 * @ClassName learn-java-interview
 * @Description 描述
 * @Date 2020/12/29 8:53 上午
 * @Author lin
 * @Version 1.0
 */
public class JvmNote {
    public static void main(String[] args) {
        MyObject myObject = new MyObject();
        //这个为null，这是因为BootStrap 根加载器是c++语言编写的。
        System.out.println(myObject.getClass().getClassLoader().getParent().getParent());
        // 获取这个加载器的 父类加载器， 扩展类加载器
        System.out.println(myObject.getClass().getClassLoader().getParent());
        //这个类是被那个 加载器加载的。 应用类加载器
        System.out.println(myObject.getClass().getClassLoader());
    }
}
