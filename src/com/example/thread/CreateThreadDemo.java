package com.example.thread;

import java.util.concurrent.Callable;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

public class CreateThreadDemo {
    // 只有两种方式新建一个线程

    public static void main(String[] args) {

        UseThread useThread = new UseThread();
        useThread.start();

        UseRun useRun = new UseRun();
        Thread thread = new Thread(useRun);
        thread.start();

        UseCall useCall = new UseCall();
        FutureTask<String> futureTask = new FutureTask<>(useCall);
        Thread thread1 = new Thread(futureTask);
        thread1.start();
    }
}

//方法1
class UseThread extends Thread{
    @Override
    public void run() {
        super.run();
        System.out.println("使用继承Thread创建线程");
    }
}

//方法2
class UseRun implements Runnable {

    @Override
    public void run() {
        System.out.println("使用实现Runnable接口");
    }
}

// ”方法3“

class UseCall implements Callable<String> {

    @Override
    public String call() throws Exception {
        return "使用实现Callable接口创建线程";
    }
}

/**
 *  注意：方法3虽然也能成功创建线程，但是在Thread.java的源码中明确写道，只有两种方法创建线程
 *  There are two ways to create a new thread of execution.
 *  Callable本质上是把自己交给FutureTask类去处理
 *  继承关系：FutureTask -> RunnableFuture -> Runnable
 *  所以本质上实现Callable接口去创建线程，也是实现了Runnable接口去创建线程
 */