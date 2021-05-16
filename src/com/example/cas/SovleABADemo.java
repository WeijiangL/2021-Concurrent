package com.example.cas;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicStampedReference;

public class SovleABADemo {

    private static AtomicInteger ai = new AtomicInteger(100);
    private static AtomicStampedReference<Integer> atomicStampedReference = new AtomicStampedReference<>(100 , 1);

    public static void main(String[] args) {

        //createABA();

        solveABA();
    }

    private static void solveABA() {

        new Thread (() -> {
            int stamp = atomicStampedReference.getStamp();
            System.out.println(stamp);
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            atomicStampedReference.compareAndSet(100 , 101 , stamp , stamp +1);
            atomicStampedReference.compareAndSet(101 , 100 , atomicStampedReference.getStamp() , atomicStampedReference.getStamp() +1);
            System.out.println(atomicStampedReference.getStamp() + "-----3 " + atomicStampedReference.getReference());
        } , "AAA").start();

        new Thread (() -> {
            int stamp =  atomicStampedReference.getStamp();
            System.out.println(stamp + "BBB");
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(atomicStampedReference.compareAndSet(100, 2019, atomicStampedReference.getStamp(), atomicStampedReference.getStamp() + 1));
            System.out.println(atomicStampedReference.getReference());
        } , "BBB").start();
    }

    private static void createABA() {
        // 产生一个ABA问题
        new Thread (() -> {
            ai.compareAndSet(100 , 101);
            ai.compareAndSet(101 , 100);
            System.out.println(ai.get());
        } , "AAA").start();

        new Thread (() -> {
            try {
                TimeUnit.SECONDS.sleep(3);
                System.out.println(ai.compareAndSet(100, 2019));
                System.out.println(ai.get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } , "BBB").start();
    }
}
