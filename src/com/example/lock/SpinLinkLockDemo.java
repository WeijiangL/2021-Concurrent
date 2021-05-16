package com.example.lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

// 自旋锁
// 指尝试获取锁的线程不会立即阻塞，而是采取循环的方式去尝试获取锁，这样做的好处减少切换线程上下文的消耗，缺点是CPU利用高
public class SpinLinkLockDemo {
    // 手写一个自旋锁

    // 首先需要一个原子引用类
    AtomicReference<Thread> atomicReference = new AtomicReference<>();

    //需要两个方法，一个加锁，一个解锁
    public void lock (){
        // 因为原子引用类定义的泛型是Thread类型，所以这里先获取当前线程对象
        Thread thread = Thread.currentThread();
        System.out.println(thread.getName() + "\t 执行lock");

        // 执行CAS ，如果CAS执行成功，则代表赋值成功跳出循环，如果执行失败，则表示当前期望值被别的线程改过了，需要进入循坏等待别的线程修改正确的期望值为止
        while (!atomicReference.compareAndSet(null , thread)) {

        }
    }

    public void unlock () {
        Thread thread = Thread.currentThread();
        atomicReference.compareAndSet(thread , null);
        System.out.println(thread.getName() + "\t 执行unlock完毕");
    }

    public static void main(String[] args) {

        SpinLinkLockDemo spinLinkLockDemo = new SpinLinkLockDemo();

        new Thread (() -> {
            // 加锁
            spinLinkLockDemo.lock();
            // 持有锁5s
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            // 解锁
            spinLinkLockDemo.unlock();
        } , "AAA").start();

        //主线程休眠1s，1s后B线程启动
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Thread (() -> {
            // 加锁
            spinLinkLockDemo.lock();
            // B线程加锁时，由于A线程先将原来为Null的thread改成了thread，所以B线程将一直循环等待，直到A线程5s后将thread重新修改回null
            spinLinkLockDemo.unlock();
        } , "BBB").start();
    }

}
