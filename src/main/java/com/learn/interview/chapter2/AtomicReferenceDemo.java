package com.learn.interview.chapter2;

import java.util.concurrent.atomic.AtomicReference;

/**
 * @ClassName: AtomicReferenceDemo
 * @Description:
 * @Author: lin
 * @Date: 2020/9/8 15:22
 * History:
 * @<version> 1.0
 */
public class AtomicReferenceDemo {
    public static void main(String[] args) {
        User u1 = new User("z3", 20);
        User u2 = new User("li4", 22);
        // 创建原子引用包装类
        AtomicReference<User> atomicReference = new AtomicReference<>();
        //将主物理内存的共享变量，设置为u1;
        atomicReference.set(u1);

        // 比较并交换，如果现在主物理内存的值为u1，那么交换成u2
        System.out.println(atomicReference.compareAndSet(u1, u2) + "\t" + atomicReference.get().toString());
        // 比较并交换，现在主物理内存的值是u2了，但是预期为u1，因此交换失败
        System.out.println(atomicReference.compareAndSet(u1, u2) + "\t" + atomicReference.get().toString());
    }
}

class User{
    String userName;
    int age;


    public User(String userName, int age) {
        this.userName = userName;
        this.age = age;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
    @Override
    public String toString() {
        return "User{" +
                "userName='" + userName + '\'' +
                ", age=" + age +
                '}';
    }
}