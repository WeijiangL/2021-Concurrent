package com.example.threads;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

/*/

 */
public class CallableDemo implements Callable<Integer> {

        @Override
        public Integer call() throws Exception {
            System.out.println(Thread.currentThread().getName() + "\t enter call...");
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return 20;
        }

        public static void main(String[] args) throws ExecutionException, InterruptedException {
            // API调用
            FutureTask<Integer> futureTask = new FutureTask<>(new CallableDemo());
            new Thread (futureTask, "AAA").start();
            Integer result2 = futureTask.get();

            System.out.println("111111111111111");
        /*
            1.Callable是带返回值和可以抛出异常的多线程创建方式，可以精确知道是哪个线程有错误
            2.多个线程使用同一个FutureTask对象，call方法只会被第一个线程所调用
            3.值得注意的是，一般来说，使用Callable可以让它执行一些耗时操作，也就是说当调度到这个线程时，让他
                先干着，自己先去干别的事情，可以把获取call方法返回值的语句放到比较后面的位置
         */

            // main线程
            int result1 = 10;

//        Integer result2 = futureTask.get();
            System.out.println(Thread.currentThread().getName() + "\t " + (result1 + result2));


        }
}
