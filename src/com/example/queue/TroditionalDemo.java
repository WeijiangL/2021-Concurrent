package com.example.queue;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

// 传统版生产者消费者模式 两个线程，一个变量num = 0 ， 一个对其+1，一个对其-1，来五次
public class TroditionalDemo {

    // 变量
    int num;
    // 可重入锁
    Lock lock = new ReentrantLock();
    // Condition，控制线程的等待和唤醒
    Condition condition = lock.newCondition();

    // 高内聚，即资源类有自己的方法
    public void increment() {
        try{
            lock.lock();

            // 先判断num的值，如果不为0，则代表不能生产了
            while(num != 0) {
                // 如果不为0，进来进入等待状态
                condition.await();
            }
            num ++;
            System.out.println(Thread.currentThread().getName() + "\t " + num);
            // 操作完后唤醒等待的线程，说该-1了
            condition.signalAll();
        }catch (Exception e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }

    public void decrement() {
        try{
            lock.lock();

            // 先判断num的值，如果不为0，则代表不能生产了
            while(num != 1) {
                // 如果不为1，进来进入等待状态
                condition.await();
            }
            num --;
            System.out.println(Thread.currentThread().getName() + "\t " + num);
            // 操作完后唤醒等待的线程，说该+1了
            condition.signalAll();
        }catch (Exception e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {

        TroditionalDemo troditionalDemo = new TroditionalDemo();

        new Thread (() -> {
            for (int i = 0; i < 5; i++) {
                troditionalDemo.increment();
            }
        } , "AAA").start();

        new Thread (() -> {
            for (int i = 0; i < 5; i++) {
                troditionalDemo.decrement();
            }
        } , "BBB").start();
    }
}
