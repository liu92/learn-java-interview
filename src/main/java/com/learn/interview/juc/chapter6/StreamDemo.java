package com.learn.interview.juc.chapter6;


import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * 题目：请按照给出数据，找出同时满足以下条件的用户，也即以下条件全部满足
 *  偶数ID且年龄大于24， 且用户名转为大写 且 用户字母倒序， 然后只输出一个用户名字
 * @ClassName learn-spring-cloud
 * @Description 描述
 * @Date 2020/12/25 10:41 下午
 * @Author lin
 * @Version 1.0
 */
public class StreamDemo {
    public static void main(String[] args) {
        //链式编程+流式计算
        UserStream u1 = new UserStream(11, "a", 23);
        UserStream u2 = new UserStream(12, "b", 24);
        UserStream u3 = new UserStream(13, "c", 22);
        UserStream u4 = new UserStream(14, "d", 28);
        UserStream u5 = new UserStream(16, "e", 26);

        List<UserStream> list = Arrays.asList(u1,u2,u3,u4,u5);

//        Function functio = new Function() {
//            @Override
//            public Object apply(Object o) {
//                return null;
//            }
//        };

        //Function函数有返回值
        Function<String, Integer> function = String::length;
        System.out.println(function.apply("abc"));

//        Predicate<String> predicate = new Predicate<String>() {
//            @Override
//            public boolean test(String s) {
//                return false;
//            }
//        };
        Predicate<String> predicate = s->{return  s.isEmpty();};
        System.out.println(predicate.test("xis"));

//        Consumer<String> consumer = new Consumer<String>() {
//            @Override
//            public void accept(String s) {
//
//            }
//        };
        //没有返回值 那么就没有 return
        Consumer<String> consumer = s->{
            System.out.println(s);
        };


//        Supplier<String> supplier = new Supplier<String>() {
//            @Override
//            public String get() {
//                return null;
//            }
//        };
        //没有参数，那么就写一个(),但是有返回值
        Supplier<String> supplier = ()->{ return  "java20";};


        list.stream().filter(u -> {return  u.getId() % 2 ==0;})
                .filter(s-> {return  s.getAge()> 24; })
                .map(m -> {return  m.getUserName().toUpperCase();})
                .sorted((o1,o2)-> {return  o2.compareTo(o1);})
                .limit(1).forEach(System.out::println);

    }

    /**
     * 题目
     */

}

