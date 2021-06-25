package com.learn.interview.juc.chapter2;

import java.util.concurrent.TimeUnit;

/**
 * 题目：多线程8锁
 * 1、标准访问(也就是phone类中两个方法都是同步方法)，请问先打印邮件还是短信？
 *   执行结果
 *   -----SendEmail
 *   -----SendEms
 *
 *  答：同步方法锁定的是当前这个对象， 一段时间内只允许有一个线程进入 资源类中访问其中的一个方法，
 *   不可能多个线程同时进入。
 *
 *
 * 2、使用同一个资源类，邮件方法暂停4秒钟，请问先打印邮件还是短信？
 *  打印结果:
 *    -----SendEmail
 *    -----SendEms
 *  答:因为两个线程都是用的同一个资源类，所以在同一个时间段内，
 *   只能有一个线程使用这个资源类，然后去调用同步方法
 *
 *
 * 3、也是同一个资源类，新增一个普通方法hello()，请问先打印邮件还是hello?
 *   打印结果:
 *    ------hello
 *    ------sendEmail
 * 答: 普通方法没有加synchronized，不会和另外一个线程争抢。
 *
 *
 * 4、两部手机(也就是两个phone实例对象)，请问先打印邮件还是短信？
 *   打印结果:
 *   ------SendEms
 *   ------sendEmail
 *   答:因为是两个实例对象，锁不一样所以不会去争抢。
 *
 *
 * 5、两个静态同步方法，同一部手机(实例对象)，请问先打印邮件还是短信？
 *  打印:
 *  -----SendEmail
 *  -----SendEms
 *  答:静态同步方法就是锁的这个类，并且是同一个实例对象。所以在同一时间内只有一个线程获取到资源锁。
 *  在线程A获取到资源锁后调用同步方法 先等待4s钟，执行完后是否锁，其他线程才能获取到这个资源类到锁。
 *
 *
 * 6、两个静态同步方法，两部手机(实例对象)，请问先打印邮件还是短信？
 *  打印:
 *  -----SendEmail
 *  -----SendEms
 *  答:虽然是两个静态同步方法，并且是两个实例对象，但是静态方法 锁定得是这个资源类( xx.class)，而不是一个个具体到实例对象
 *  所以在执行得时候，在一段时间内也只有一个线程能访问这个资源类。另外到线程只能等待释放锁之后才能获取。
 *
 * 7、一个普通同步方法(一个是具体对象)，一个静态同步方法(一个是模板)，1部手机(实例对象)，请问先打印邮件还是短信？
 *  打印:
 *  ------sendSms
 *  ------sendEmail
 *  答:因为一个是具体实例对象，一个是这个类模板，两个是不同得东西，所以不会发生争抢。
 *
 *
 * 8、一个普通同步方法，一个静态同步方法，2部手机(实例对象)，请问先打印邮件还是短信？
 * 打印:
 *   ------sendSms
 *   ------sendEmail
 *
 *  答: 同样 因为一个是具体实例对象，一个是这个类模板， 然后通过两个实例来调用，两个是不同得东西，所以不会发生争抢。
 *
 *
 * 9、一个普通方法(一个是具体对象)，一个静态同步方法(一个是模板)，1部手机(实例对象)，请问先打印邮件还是短信？
 *  打印:
 *  ------hello
 *  ------sendEmail
 *
 *
 * @ClassName learn-java-interview
 * @Description 描述
 * @Date 2020/12/23 10:01 下午
 * @Author lin
 * @Version 1.0
 */
public class ThreadLock {
    public static void main(String[] args) throws InterruptedException {
        Phone phone = new Phone();
        Phone phone2 = new Phone();
        new Thread(()->{
            try {
                phone.sendEmail();
            } catch (Exception e) {
                e.printStackTrace();
            }
        },"A").start();

        //这里先睡100毫秒，让其线程A先于B 执行
        Thread.sleep(100);

        new Thread(()->{
            try {
                //1、2、4、5
//                phone.sendSms();
                //3、7
                phone2.hello();
                //6
//                phone2.sendSms();
            } catch (Exception e) {
                e.printStackTrace();
            }
        },"B").start();
    }

}


/**
 * 一个对象里面如果有多个synchronized方法，某一个时刻内，只要一个线程调用其中的一个synchronized方法了，
 * 其它的线程都只能等待，换句话说，就是某一个时刻内，只能有唯一一个线程去访问这些synchronized方法，
 * 其锁定的是当前对象this，被锁定后，其它线程都不能进入当前对象的其它的synchronized方法。
 *
 *
 * 所有的非静态同步方法用的都是同一把锁--实例对象本身
 * synchronized实现同步得基础：java中的每一个对象都可以作为锁。
 * 具体表现为以下3种形式：
 *   对普通同步方法，锁的是当前实例对象。
 *   对静态同步方法，锁的是当前类的class对象。
 *   对同步方法块，锁的是synchonized括号里面配置的对象。
 *
 * 当一个线程尝试访问同步代码块时，它首先必须得到锁，退出或抛出异常时必须释放锁。
 * 也就是说如果一个实例对象的非静态同步方法获取锁后，该实例对象的其它非静态同步方法必须等待获取锁的方法释放锁后才能获取锁，
 * 可是别的实例对象的非静态同步方法因为跟该实例对象的非静态同步方法用的是不同的锁，
 * 所以无须等待该实例对象已获取锁的非静态同步方法释放锁就可以获取它们自己的锁。
 *
 * 所有的静态同步方法用的都是同一把锁-----类对象本身，
 * 这两把锁(this/class)是两个不同的对象，所以静态同步方法与非静态同步方法之间是不会有竟态条件的。
 * 但是一旦一个静态同步方法获取锁后，其它的静态同步方法都必须等待该方法释放锁后才能获取锁，
 * 而不管是不是同一个实例对象的静态同步方法之间，还是不同的实例对象的静态同步方法之间，只要它们同一个类的实例对象。
 *
 */
class Phone{

    /**
     * 第一种情况： 如果 在方法这个资源类是，使用的是同一个对象，那么在访问的时候
     * 一段时间内只有1个线程能 进入资源类中 调用一个同步方法。
     * @throws Exceptioncon
     */
//    public synchronized void sendEmail()throws  Exception{
//      System.out.println("------sendEmail");
//    }
//
//    public synchronized void sendSms(){
//        System.out.println("------sendSms");
//    }

    /**
     * 线程A先于B执行。
     * 第二种情况： sendEmail发送方法暂停4s，那么在访问的时候如果使用的是"同一个实例对象"。
     * 那么在一段时间内线程调用sendEmail获取到实例对象到锁后 要睡4s钟。然后释放锁之后。
     * 其他线程才能 获取到 这个资源类的锁，才能去调用sendSms方法
     * @throws Exception
     */
    public static synchronized void sendEmail()throws  Exception{
        try {
            TimeUnit.SECONDS.sleep(4);
            System.out.println("------sendEmail");
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }

    public static synchronized void sendSms(){
        System.out.println("------sendSms");
    }





    public void hello(){
        System.out.println("------hello");
    }
}