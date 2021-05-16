package com.example.volatiled;

// 双端检锁机制并不能保证线程一定安全，原因是因为有指令重排的存在
// instance = new SingletonDemo() 可分为以下三步
// memory = allocate(); 分配内存空间
// instance(memory); 初始化对象
// intance = memory; 将刚刚分配的内存空间指向刚初始化的对象
// 其中2和3没有依赖关系，所以说他俩是有可能会由编译器指令重排的，就会造成，给分配好的地址指给instance时，instance虽然不为null，但此时instance还未初始化，就直接返回了，会出问题

public class SingletonDemo {

    private static volatile SingletonDemo instance = null;

    private SingletonDemo() {
        System.out.println(Thread.currentThread().getName() + "\t: ");
    }

    public static SingletonDemo getInstance(){

        if (instance ==null) {
            synchronized (SingletonDemo.class){
                if (instance == null) {
                    instance = new SingletonDemo();
                }
            }
        }

        return instance;
    }

    public static void main(String[] args) {

        for (int i = 1 ; i <= 10; i++) {
            new Thread (() -> {
                SingletonDemo.getInstance();
            }, String.valueOf(i)).start();
        }

    }
}
