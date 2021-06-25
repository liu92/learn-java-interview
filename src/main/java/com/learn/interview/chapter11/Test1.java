package com.learn.interview.chapter11;


/**
 * @ClassName: Test1
 * @Description:
 * @Author: lin
 * @Date: 2020/9/15 17:08
 * History:
 * @<version> 1.0
 */
public class Test1 {
    /**
     *  jdk1.8
     * java heap中类静态变量属性引用的对象
     */
    private static Test2 t2 ;

    /**
     *  jdk1.8
     * java heap中类静态变量属性引用的对象
     */
    private static final Test2 TEST = new Test2() ;

    public static void main(String[] args) {
        //虚拟机栈(栈帧中的局部变量)中引用的对象
      Per per = new Per();


    }

    static class  Per{
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
