package com.example.threads;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MyThreadPoolDemo {


        // 线程池，非常重要
        /*
            意义
             1.线程池的工作主要是控制线程的运行数量
             2.线程复用，控制最大并发数，管理线程
             3.降低资源消耗，重复利用已创建的线程降低线程创建和销毁的资源消耗
             4.提高响应速度，
             5.提高线程的可管理性，线程时稀缺资源，如果无限制的创建，会消耗系统资源，还会降低系统运行速度，线程池可以统一
                分配调度监控
         */
        /*
            用法
            1.Excuetor Excuetors 创建线程不能new，要用Excuetors工具类
            2.常用的有三大线程池类
                a.Executors.newFixedThreadPool(5);
                b.Executors.newSingleThreadExecutor();
                c.Executors.newCachedThreadPool();
            3.线程池用完一定要关
         */
        public static void main(String[] args) {
            // 查看电脑核数
            System.out.println(Runtime.getRuntime().availableProcessors());

            // 创建一个定长的线程池，可控制线程的最大并发数，超出的线程会在队列中等待
            ExecutorService executorService = Executors.newFixedThreadPool(5);

            // 创建一个单线程化的线程池，只会用唯一的工作线程来执行任务
            ExecutorService executorService1 = Executors.newSingleThreadExecutor();

            // 创建一个可缓存化的线程池，如果线程池长度超过处理需要，则回收多余线程
            ExecutorService executorService2 = Executors.newCachedThreadPool();

            // 1.10个顾客来银行办理业务，固定开放5个窗口，最后执行的结果一定是这5个窗口随机执行完10个顾客的流程
            try{
                    for (int i = 1; i <= 10; i++) {
                        executorService2.execute(() -> {
                            System.out.println(Thread.currentThread().getName() + "\t" + "办理业务");
                        });
                    }
            }catch (Exception e){
                e.printStackTrace();
            }finally {
                executorService.shutdown();
            }
        }
}
