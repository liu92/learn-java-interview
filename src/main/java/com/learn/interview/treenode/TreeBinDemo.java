package com.learn.interview.treenode;

/**
 * @ClassName learn-spring-cloud
 * @Description 描述
 * @Date 2020/12/7 11:15 上午
 * @Author lin
 * @Version 1.0
 */
public class TreeBinDemo<K,V>  extends NodeDemo<K,V>{
    TreeNodeDemo<K,V> root;
    volatile TreeNodeDemo<K,V> first;
    volatile Thread waiter;
    volatile int lockState;

    TreeBinDemo(TreeNodeDemo<K,V> b) {
        super(-2, null, null, null);
        this.first = b;
        TreeNodeDemo<K,V> r = null;
        for (TreeNodeDemo<K,V> x = b, next; x != null; x = next) {
            next = (TreeNodeDemo<K,V>)x.next;
        }

    }
}
