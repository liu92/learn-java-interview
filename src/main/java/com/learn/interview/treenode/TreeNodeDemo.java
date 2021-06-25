package com.learn.interview.treenode;

/**
 * @ClassName learn-spring-cloud
 * @Description 描述
 * @Date 2020/12/7 10:49 上午
 * @Author lin
 * @Version 1.0
 */
public class TreeNodeDemo<K,V>  extends NodeDemo<K,V>{

    TreeNodeDemo<K,V> parent;  // red-black tree links
    TreeNodeDemo<K,V> left;
    TreeNodeDemo<K,V> right;
    TreeNodeDemo<K,V> prev;    // needed to unlink next upon deletion
    boolean red;



    TreeNodeDemo(int hash, K key, V val, NodeDemo<K, V> next, TreeNodeDemo<K,V> parent) {
        super(hash, key, val, next);
        this.parent = parent;
    }


}
