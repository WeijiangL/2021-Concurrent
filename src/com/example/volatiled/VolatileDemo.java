package com.example.volatiled;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

class MyData {

    volatile int a = 0;
    AtomicInteger ai = new AtomicInteger(0);
    AtomicInteger ai1 = new AtomicInteger(0);

    public void addTo10() {
        //this.a = 10;
         ai1.getAndAdd(10);
    }

    public void addPlusPlus() {
        ai.getAndIncrement();
    }
}


public class VolatileDemo {
    public static void main(String[] args) {

        // Volatile的可见性保证
        seeOkByVolatile();

        // Volatile的不保证原子性
        //noAtomicByVolatile();
    }

    // Volatile的不保证原子性
    private static void noAtomicByVolatile() {
        MyData myData = new MyData();

        // 创建20个线程执行1000次addPlusPlus方法，正确的结果肯定是20*1000=20000，但是由于不是原子操作，最后的结果会小于20000
        for (int i = 1 ; i <= 20; i++) {
            new Thread (() -> {
                for (int j = 0; j < 1000; j++) {
                    myData.addPlusPlus();
                }
            }, String.valueOf(i)).start();
        }

        // 等待上面的20个线程执行完毕，有两种方法，第一种方法是直接Thread.sleep，但这样会很耗时，推荐用下面的方法
        // 在一个Java程序中默认还有两个线程，主线程和gc线程，如果活跃的线程大于2，说明上面的20个线程还没有执行完
        while (Thread.activeCount() > 2) {
            Thread.yield();
        }

        System.out.println(Thread.currentThread().getName() + "- finally a's value is: " + myData.ai);
    }

    // Volatile的可见性保证
    private static void seeOkByVolatile() {
        MyData data = new MyData();

        // 子线程3s之后将a从0变为10
        new Thread(){
            @Override
            public void run() {
                super.run();
                try {
                    TimeUnit.SECONDS.sleep(3);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                data.addTo10();
                System.out.println(data.ai1);
            }
        }.start();

        // 如果没有可见性，主线程不会知道a从0变为10了，会一直在这里等待
        while (data.ai1.get() == 0){

        }

        System.out.println(Thread.currentThread().getName() + "main over");
    }
}
