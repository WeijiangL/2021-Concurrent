package com.example.lock;

// 读写锁

/*
    独占锁：写锁
    共享锁：读锁
    互斥锁：读写 写写 写读

    读操作可以多个线程同时进行，但是写操作一次只能有一次，并且写操作是原子性和独占性的，一个线程如果想去写一个共享资源，那么在写的时候，别的线程就不能再对该资源有读或写的操作
 */

import java.util.HashMap;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

// 手写一个缓存，需要添加，获取，删除三大操作
class MyCache {
    // 用一个HashMap来模拟缓存数据的存储
    HashMap<Integer , Object> hashMap = new HashMap();
    ReadWriteLock readWriteLock = new ReentrantReadWriteLock();

/*
    // 写
    public void set(Integer key , Object value) {
        System.out.println(Thread.currentThread().getName() + "\t 开始写：");
        hashMap.put(key , value);
        System.out.println(Thread.currentThread().getName() + "\t 写完了");
    }

    // 读
    public void get(Integer key) {
        System.out.println(Thread.currentThread().getName() + "\t 开始读：");
        hashMap.get(key);
        System.out.println(Thread.currentThread().getName() + "\t 读完了");
    }
*/

    // 写
    public void set(Integer key , Object value) {
        readWriteLock.writeLock().lock();
        try {
            System.out.println(Thread.currentThread().getName() + "\t 开始写：");
            hashMap.put(key , value);
            System.out.println(Thread.currentThread().getName() + "\t 写完了");
        } finally {
            readWriteLock.writeLock().unlock();
        }


    }

    // 读
    public void get(Integer key) {
        readWriteLock.readLock().lock();
        try {
            System.out.println(Thread.currentThread().getName() + "\t 开始读：");
            hashMap.get(key);
            System.out.println(Thread.currentThread().getName() + "\t 读完了");
        } finally {
            readWriteLock.readLock().unlock();
        }

    }
}

public class ReadWriteLockDemo {

    public static void main(String[] args) {
        MyCache myCache = new MyCache();

        // 创建5个线程去读或写，读可以读个线程同时，写一次只能有一个线程
        for (int i = 1 ; i <= 5; i++) {
            final int temp = i;
            new Thread (() -> {
                myCache.set(temp , Thread.currentThread().getName());
                try {
                    TimeUnit.SECONDS.sleep(3);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                myCache.get(temp);
            }, String.valueOf(i)).start();
        }
    }
}
