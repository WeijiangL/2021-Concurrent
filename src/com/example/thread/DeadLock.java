package com.example.thread;

public class DeadLock {

    //定义两个锁
    private static String Lock1 = "AAA";
    private static String Lock2 = "BBB";

    // 两个争夺锁的方法
    public static void myThreadGetLock() throws InterruptedException {
        String threadName = Thread.currentThread().getName();
        synchronized (Lock2){
            System.out.println(threadName + " get Lock2");
            Thread.sleep(100);
            synchronized (Lock1){
                System.out.println(threadName +" get Lock1");
            }
        }
    }

    public static void mainGetLock() throws InterruptedException {
        String threadName = Thread.currentThread().getName();
        synchronized (Lock1){
            System.out.println(threadName + " get Lock1");
            Thread.sleep(100);
            synchronized (Lock2){
                System.out.println(threadName + " get Lock2");
            }
        }
    }

    // 在主线程中调用mainGetLock
    public static void main(String[] args) throws InterruptedException {
        MyThread myThread = new MyThread();
        myThread.start();
        mainGetLock();
    }

    // 在子线程中调用myThreadGetLock
    private static class MyThread extends Thread {
        @Override
        public void run() {
            super.run();
            try {
                myThreadGetLock();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

