package com.learn.interview.juc.chapter3;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * 题目:举例说明集合类不是安全的
 * 1:故障现象(在多线程下使用ArrayList)
 *   java.util.ConcurrentModificationException
 * 2:导致原因
 *  ArrayList在多线程情况下是 不安全的
 *
 * 3:解决方案
 *
 *   3.1、Vector,
 *   3.2、Collections.synchronizedList(new ArrayList<>())
 *   3.3、CopyOnWriteArrayList<>()  这个是线程安全的。使用ReentrantLock来进行加锁控制
 *
 * 4:优化建议
 *   不能说加锁，因为在JUC中已提供类这类的解决方案。这个JUC 读作GUC 这是行话
 *
 *
 * 5、CopyOnWriteArrayList 写时复制
 *   CopyOnWriteArrayList容器即写时复制的容器，在一个容器添加元素时候，不直接往当前容器Object[]中添加，
 *   而是先将当前容器Object[]进入copy,复制出一个新的容器Object[] newElements, 并将新的数组容量+1，
 *   然后往新的容器newElements中添加元素，添加元素之后，再将原来的容器的引用指向新的容器setArray(newElements).
 *   这样做的好处是对CopyOnWrite 容器进行并发的读。而不需要加锁，因为当前容器不好添加任何元素，所以CopyOnWriteArrayList
 *   容器也是一种读写分离的思想，读和写不同容器。
 *
 *
 * @ClassName learn-java-interview
 * @Description 描述
 * @Date 2020/12/24 10:14 上午
 * @Author lin
 * @Version 1.0
 */
public class NoSafeDemo {

    public static void main(String[] args) {
        //线程不安全
//        List<String> list = new ArrayList<>();
        //线程安全，但是vecor效率不好
//        List<String> list = new Vector<>();
        // 不推荐使用这个
//        List<String> list = Collections.synchronizedList(new ArrayList<>());

        List<String> list = new CopyOnWriteArrayList<>();

        for (int i = 0; i < 30; i++) {
            new Thread(()->{
                list.add(UUID.randomUUID().toString().substring(0,8));
                System.out.println(list);
            },String.valueOf(i)).start();

        }

//        list.forEach(System.out::println);

    /**
     * 1、HashSet, 这个也不是线程安全的
     * 在多线程情况下 也会出现
     * java.util.ConcurrentModificationException
     *
     * 2、出现原因
     *  多线程情况多线程竞争一个资源，而hashSet是不安全的。所以会造成并发异常
     *
     * 3、解决方法
     *  使用: Collections.synchronizedSet()可以解决，但是这个工具类中的方法也是加了synchronizde关键字
     *  使用  CopyOnWriteArraySet<>();这个写时复制来解决
     *
     *
     */
     Set<String> strings =new CopyOnWriteArraySet<>();  //Collections.synchronizedSet(new HashSet<>());  //new HashSet<>();


    /**
     * 1、HashMap，这个也是线程不安全类
     *  java.util.ConcurrentModificationException
     *
     * 2、原因
     *  在多线程情况下，多个线程竞争一个资源，那么就会造成并发异常
     *
     * 3、解决方案
     *  使用:
     *   3.1、Collections.synchronizedMap(new HashMap<>());
     *   3.2、new ConcurrentHashMap 并发类
     *
     *  注意:hashMap中 使用juc包下面解决 并发问题的类 不和 ArrayList、HashSet一样。
     *  它使用的是一个 新的的类 ConcurrentHashMap
     */
     Map<String, String> map =new ConcurrentHashMap<>(); //Collections.synchronizedMap(new HashMap<>()); //new HashMap<>();

    }
}
