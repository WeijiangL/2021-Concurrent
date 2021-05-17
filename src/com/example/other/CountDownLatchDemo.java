package com.example.other;

import java.util.concurrent.CountDownLatch;

// CountDownLatch
// 让一些线程阻塞，直到另一些线程完成一系列操作后才被唤醒
public class CountDownLatchDemo {

    private static CountDownLatch countDownLatch = new CountDownLatch(6);

    public static void main(String[] args) throws InterruptedException {
        // normalCountDownLatch();

        for (int i = 1 ; i <= 6 ; i++) {
            new Thread (() -> {
                System.out.println(Thread.currentThread().getName() + "\t 灭亡");
                countDownLatch.countDown();
            }, CountDownLatchEnum.foreach(i).getMessage()).start();
        }
        countDownLatch.await();
        System.out.println(Thread.currentThread().getName() + "\t 秦灭六国");
    }

    private static void normalCountDownLatch() throws InterruptedException {
        for (int i = 1 ; i <= 60; i++) {
            new Thread (() -> {
                System.out.println(Thread.currentThread().getName() + "\t 开始吃饭");
                countDownLatch.countDown();
            }, String.valueOf(i)).start();
        }
        countDownLatch.await();
        System.out.println(Thread.currentThread().getName() + "\t 上课");
    }
}
