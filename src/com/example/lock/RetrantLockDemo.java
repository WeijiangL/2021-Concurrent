package com.example.lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


// 公平锁：每个线程会按照申请锁的顺序来获取锁，在获取锁的时候，会先查看维护锁的等待队列，如果队列为空或者自己就是队列的第一个，就占有锁，否则就按照先进先出的原则来获取锁。
// 非公平锁：多个线程获取锁的顺序并不是按照申请锁的顺序，一上来就尝试获取锁，如果获取不到，就按照公平锁的方式获取锁，这种锁的吞吐量大
public class RetrantLockDemo {
    // 可重入锁：一个线程在获得外层同步方法的锁的时候，会自动获得内层同步方法的锁synchornized和RetrantLock就是可重入锁

    static class Person{

        // synchornized
        public synchronized void setName(){
            System.out.println(Thread.currentThread().getName() + "\t " + "执行 setName()");
            setAge();
        }

        public synchronized void setAge(){
            System.out.println(Thread.currentThread().getName() + "\t " + "执行 setAge()");
        }

        // RetrantLock
        /*
            lock.lock() 可以有多个，但必须两两配对
         */
        Lock lock = new ReentrantLock();
        public void get(){
            lock.lock();
            System.out.println(Thread.currentThread().getName() + "\t " + "执行 get()");
            set();
            lock.unlock();
        }

        private void set() {
            lock.lock();
            System.out.println(Thread.currentThread().getName() + "\t " + "执行 set()");
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        Person person = new Person();
        new Thread (() -> {
            person.setName();
        } , "AAAA").start();

        new Thread (() -> {
            person.setName();
        } , "BBBB").start();

        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();

        new Thread (() -> {
            person.get();
        } , "CCCC").start();

        new Thread (() -> {
            person.get();
        } , "DDDD").start();
    }


}
