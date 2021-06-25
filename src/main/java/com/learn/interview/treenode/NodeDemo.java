package com.learn.interview.treenode;

import java.util.Map;

/**
 * @ClassName learn-spring-cloud
 * @Description 描述
 * @Date 2020/12/7 11:11 上午
 * @Author lin
 * @Version 1.0
 */
public class NodeDemo<K,V> implements Map.Entry<K,V> {
    final int hash;
    final K key;
    volatile V val;
    volatile NodeDemo<K,V> next;

    NodeDemo(int hash, K key, V val, NodeDemo<K,V> next) {
        this.hash = hash;
        this.key = key;
        this.val = val;
        this.next = next;
    }


    @Override
    public final K getKey()       { return key; }
    @Override
    public final V getValue()     { return val; }

    @Override
    public V setValue(V value) {
        return value;
    }

    @Override
    public final int hashCode()   { return key.hashCode() ^ val.hashCode(); }
    @Override
    public final String toString(){ return key + "=" + val; }
}
