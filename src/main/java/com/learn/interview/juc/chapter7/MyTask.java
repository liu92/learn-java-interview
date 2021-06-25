package com.learn.interview.juc.chapter7;

import java.util.concurrent.RecursiveTask;

/**
 *  //接口要被实现，抽象类要被继承
 * @ClassName learn-java-interview
 * @Description 描述
 * @Date 2020/12/28 11:01 上午
 * @Author lin
 * @Version 1.0
 */
public class MyTask extends RecursiveTask<Integer> {

    private static final long serialVersionUID = 1227231946214836330L;

    private static final  Integer ADJUST_VALUE = 10;
    private int begin;
    private int end;
    private int result;

    public MyTask() {
    }

    public MyTask(int begin, int end) {
        this.begin = begin;
        this.end = end;
    }

    @Override
    protected Integer compute() {
        if((end - begin) <= ADJUST_VALUE){
            for (int i = begin; i <= end; i++) {
                 result = result + i;
            }
        }else {
            int middle = (end + begin)/2;
            //第一个任务从0到 middle
            MyTask  task1 =  new MyTask(begin, middle);
            //第二个任务从middle+1到 end
            MyTask  task2 =  new MyTask(middle+1, end);
            //调用fork还是会 回来调用 compute, 因为 RecursiveTask是递归任务
            task1.fork();
            task2.fork();
            result = task1.join() + task2.join();
        }
        return result;
    }
}
