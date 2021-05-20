package com.example.queue;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

// Conditon的用法：即Lock的好处，可以精确唤醒某个线程
/*
    A打印5次，接着B打印10次，接着C打印15次
    来10轮
 */
public class ConditionDemo {

    // 标志位，作为判断是A B C 中哪个线程要运行
    private int number = 1;
    private Lock lock = new ReentrantLock();
    private Condition c1 = lock.newCondition();
    private Condition c2 = lock.newCondition();
    private Condition c3 = lock.newCondition();


    public void print5() {
        try{
            lock.lock();
            // 判断 为了防止虚假唤醒，一定要用while循环
            while(number != 1) {// 如果number != 1，表示现在还轮不到A线程干活，A等着
                c1.await();
            }
            // 干活
            for (int i = 0; i < 5; i++) {
                System.out.println(Thread.currentThread().getName() + "\t " + i);
            }
            number = 2;
            // 通知
            c2.signal(); // A线程干完活了，该叫B线程干活了
        }catch (Exception e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }

    public void print10() {
        try{
            lock.lock();
            while(number != 2) {
                c2.await();
            }
            for (int i = 0; i < 10; i++) {
                System.out.println(Thread.currentThread().getName() + "\t " + i);
            }

            number = 3;
            c3.signal();
        }catch (Exception e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }

    public void print15() {
        try{
            lock.lock();
            while (number != 3) {
                c3.await();
            }
            for (int i = 0; i < 15; i++) {
                System.out.println(Thread.currentThread().getName() + "\t " + i);
            }
            number = 1;
            c1.signal();
        }catch (Exception e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {

        ConditionDemo conditionDemo = new ConditionDemo();

        new Thread (() -> {
            for (int i = 0; i < 10; i++) {
                conditionDemo.print5();
            }
        } , "AAA").start();

        new Thread (() -> {
            for (int i = 0; i < 10; i++) {
                conditionDemo.print10();
            }
        } , "BBB").start();

        new Thread (() -> {
            for (int i = 0; i < 10; i++) {
                conditionDemo.print15();
            }
        } , "CCC").start();
    }
}
