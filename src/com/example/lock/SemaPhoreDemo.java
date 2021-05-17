package com.example.lock;

        import java.util.concurrent.Semaphore;
        import java.util.concurrent.TimeUnit;

// SemaPhore
// 多个线程抢多个资源
public class SemaPhoreDemo {
    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(3) ;

        for (int i = 1 ; i <= 6; i++) {
            final int temp = i;
            new Thread (() -> {
                try {
                    semaphore.acquire();
                    System.out.println(Thread.currentThread().getName() + "\t " + temp + "抢到车位");
                    TimeUnit.SECONDS.sleep(3);
                    System.out.println(Thread.currentThread().getName() + "\t 3s后，" + temp + "离开车位");
                } catch (Exception e) {
                    e.printStackTrace();
                }finally {
                    semaphore.release();
                }
            }, String.valueOf(i)).start();
        }
    }

}
