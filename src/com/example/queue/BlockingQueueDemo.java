package com.example.queue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TimeUnit;

// 阻塞队列
/*
    特点：
    1. 如果阻塞队列为null，则从阻塞队列中取出元素的操作被阻塞
    2. 如果阻塞队列满了，则往阻塞队列中插入元素的操作被阻塞
    3. 和List是平级接口，也是继承自Collection接口
    4. 有很多实现类，ctrl + alt + B 查看
 */
/*
    API
    1. 抛出异常型
        a. add(e); 添加
        b. remove(); 移除
        c. element(); 查看第一个元素
        注：如果队列满了再插入会报队列满了的异常
        注：如果队列为空再取出会报队列没有这个元素的异常
    2. 特殊值型
        a. offer(e); 添加
        b. poll(); 移除
        c. peek(); 查看第一个元素
        注：如果队列满了再插入会返回false
        注：如果队列为空再取出会返回null
     3. 一直阻塞型
        a. put(e); 添加
        b. take(e); 移除
        注： 如果队列满了再插入会一直处于阻塞状态
        注： 如果队列为空再 取出会一直处于阻塞状态
     4. 超时退出型
        a. offer(e , time , unit)
        b. poll(time unit)
        注：如果队列满了会阻塞一段时间不让插入，超时后线程自动退出
 */
/*
    SynchronousQueue:
    每一个Put操作必须要等待一个take操作，否则不能继续插入，反之亦然
 */
public class BlockingQueueDemo {
    public static void main(String[] args) {
//        ArrayBlockingQueue<Integer> arrayBlockingQueue = new ArrayBlockingQueue<>(3);
        SynchronousQueue<Object> queue = new SynchronousQueue<>();

        new Thread (() -> {
            try {
                System.out.println(Thread.currentThread().getName() + "\t 插入" + "1");
                queue.put(1);
                System.out.println(Thread.currentThread().getName() + "\t 插入" + "2");
                queue.put(2);
                System.out.println(Thread.currentThread().getName() + "\t 插入" + "3");
                queue.put(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } , "AAA").start();

        new Thread (() -> {

            try {
                TimeUnit.SECONDS.sleep(3);
                System.out.println(Thread.currentThread().getName() + "\t 取出" + queue.take());
                TimeUnit.SECONDS.sleep(3);
                System.out.println(Thread.currentThread().getName() + "\t 取出" + queue.take());
                TimeUnit.SECONDS.sleep(3);
                System.out.println(Thread.currentThread().getName() + "\t 取出" + queue.take());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } , "BBB").start();
    }
}
