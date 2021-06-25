package com.learn.interview.chapter4;

/**值传递和引用传递
 * @ClassName: TransferValueDemo
 * @Description:
 * @Author: lin
 * @Date: 2020/9/9 11:07
 * History:
 * @<version> 1.0
 */
public class TransferValueDemo {

    public static void main(String[] args) {
        TransferValueDemo test = new TransferValueDemo();

        // 定义基本数据类型
        /**
         * 在虚拟机栈中局部变量表存放着编译器的各种基础数据类、对象引用(reference类型，它不同于对象本身，
         * 可能是一个指向
         * 对象起始地址的引用指针，也可能是一个代表对象的句柄或其它与对象相关的位置)
         * 和returnAddress类型(指向一条字节码治理)
         *
         * 从描述可知局部表明表存放着各种基础数据类型，那么对于代码中的a,在执行changeValue1方法时。传递的时这个变量的
         * 副本，然后对这个对传入的变量副本进行了修改，而在main中打印的原来的a其值没有改变，所以这里打印的还是原来的值。
         */
        int age = 20;
        test.changeValue1(age);
        System.out.println("age ----" + age);

        // 实例化person类
        /**
         * 对于传递引用类型时，执行changeValue2，这个时候传递的时对象Person的一个引用，而这个引用指向的是内存地址。
         * 所以当在执行changeValue2会改变内存中的Person的值，属于引用传递，两个指针都是指向同一个地址
         *
         * person是引用在栈
         * 实例对象在堆
         *
         * 只要引用改变，但是这个引用对应的内存地址不会变
         */
        Person person = new Person("abc");
        test.changeValue2(person);
        System.out.println("personName-----" + person.getPersonName());

        // String
        /**
         * 当我们执行changeValue3的时候，会重新新建一个xxx，并没有销毁abc，然后指向xxx，
         * 然后最后我们输出的是main中的引用，还是指向的abc，因此最后输出结果还是abc
         */
        String str = "abc";
        test.changeValue3(str);
        System.out.println("string-----" + str);

        /**
         * Task :TransferValueDemo.main()
         * age ----20
         * personName-----XXXX
         * string-----abc
         */
    }

    public void changeValue1(int age) {
        age = 30;
    }

    public void changeValue2(Person person) {
        person.setPersonName("XXXX");
    }
    public void changeValue3(String str) {
        str = "XXX";
    }
}

class Person {
    private Integer id;
    private String personName;

    public Person(String personName) {
        this.personName = personName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }
}