package com.example.lock;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

// CyclicBarrier
// 根据CyclicBarrier构造方法中定义的线程数量，阻塞调用CyclicBarrier.await方法的线程，直到调用次数 = 线程数量，才定义构造方法中定义的线程
public class CyclicBarrierDemo {

    public static void main(String[] args) {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(7);

        for (int i = 1 ; i <= 7; i++) {
            final int temp = i;
            new Thread (() -> {
                System.out.println(Thread.currentThread().getName() + "\t 第" + temp + "颗龙珠");
                try {
                    cyclicBarrier.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }, String.valueOf(i)).start();
        }

        new Thread (() -> {
            System.out.println(Thread.currentThread().getName() + "\t召唤神龙");
        } , "AAA").start();
    }
}
