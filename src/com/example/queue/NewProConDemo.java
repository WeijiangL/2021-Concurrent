package com.example.queue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

// 阻塞队列版生产者消费者
/*
    模拟一个场景
     生产线程：负责每次生产一个
     消费线程：负责每次消费一个
     标志位flag：一旦为false，生产线程和消费线程全部叫停
 */
public class NewProConDemo {
    // 定义标志位，控制两个线程是否工作
    boolean flag = true;
    // 定义阻塞队列，使用接口形式
    BlockingQueue<String> blockingQueue = null;
    // 定义操作的资源
    AtomicInteger atomicInteger = new AtomicInteger();


    public NewProConDemo(BlockingQueue<String> blockingQueue) {
        this.blockingQueue = blockingQueue;
    }

    // 主线程改变flag
    public void updateFlag() {
        this.flag = false;
    }

    // 生产者方法
    public void myProduct() throws InterruptedException {
        String data;
        boolean retValue;
        // 判断
        while (flag) {
            // 干活
            // 资源+1
            data = atomicInteger.incrementAndGet() + "";
            // 放入阻塞队列
            retValue = blockingQueue.offer(data, 2L, TimeUnit.SECONDS);
            // 对插入阻塞队列后的结果做出判断
            if (retValue) {
                // 插入成功
                System.out.println(Thread.currentThread().getName() + "\t 插入" + data + "成功");
            } else {
                // 插入失败
                System.out.println(Thread.currentThread().getName() + "\t 插入" + data + "失败");
            }
            //插入后暂停生产者，开始消费者
            Thread.sleep(1000);
        }

        // 通知
    }

    // 消费者方法
    public void myConsumer() throws InterruptedException {
        String result;
        // 判断
        while (flag) {
            // 干活
            // 返回从阻塞队列取出数据的结果，并进行判断
            result = blockingQueue.poll(2L, TimeUnit.SECONDS);
            if (result == null || result.equalsIgnoreCase("")) {
                flag = false;
                System.out.println(Thread.currentThread().getName() + "\t 超过2s没有消费，程序退出");
                return;
            }
            System.out.println(Thread.currentThread().getName() + "\t" + "消费" + result + "成功");
        }

        // 通知
    }

    public static void main(String[] args) throws InterruptedException {

        ArrayBlockingQueue arrayBlockingQueue = new ArrayBlockingQueue(3);
        NewProConDemo newProConDemo = new NewProConDemo(arrayBlockingQueue);

        new Thread(() -> {
            try {
                System.out.println(Thread.currentThread().getName() + "\t 生产者线程启动");
                newProConDemo.myProduct();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "AAA").start();

        new Thread(() -> {
            try {
                System.out.println(Thread.currentThread().getName() + "\t 消费者线程启动");
                newProConDemo.myConsumer();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "BBB").start();

        Thread.sleep(1000);
        System.out.println(Thread.currentThread().getName() + "\t 不允许再生产和消费了");
        newProConDemo.updateFlag();
    }
}
