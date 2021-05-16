package com.example.thread;


import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class TryLockToSovleDeadLock {

    //定义两个锁
    private static Lock Lock1 = new ReentrantLock();
    private static Lock Lock2 = new ReentrantLock();

    public static void mainGetLock() throws InterruptedException {
        String threadName = Thread.currentThread().getName();
        while(true) {
            if (Lock1.tryLock()){
                try{
                    System.out.println(threadName + " get Lock1");
                    if (Lock2.tryLock()) {
                        try {
                            System.out.println(threadName + " get Lock2");
                            break;
                        }finally {
                            Lock2.unlock();
                        }
                    }
                }finally {
                    Lock1.unlock();
                }
            }
            //Thread.sleep(100);
        }
    }

    public static void myThreadGetLock() throws InterruptedException {
        String threadName = Thread.currentThread().getName();
        while(true) {
            if (Lock2.tryLock()){
                try{
                    System.out.println(threadName + " get Lock2");
                    if (Lock1.tryLock()) {
                        try {
                            System.out.println(threadName + " get Lock1");
                            break;
                        }finally {
                            Lock1.unlock();
                        }
                    }
                }finally {
                    Lock2.unlock();
                }
            }
            //Thread.sleep(100);
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
