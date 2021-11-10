package com.example.aqs;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.ReentrantLock;

// 这个类主要是用来看看Lock的源码，看看它是怎么通过继承AQS来实现同步的
public class AQSDemo {
    public static void main(String[] args) {
        ReentrantLock reentrantLock = new ReentrantLock();
//        new CountDownLatch();
    }

}
