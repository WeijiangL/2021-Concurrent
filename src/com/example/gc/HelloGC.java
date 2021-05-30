package com.example.gc;

import java.lang.ref.SoftReference;

public class HelloGC {
    public static void main(String[] args) {
        System.out.println("*************Hello GC");
//        try {
//            TimeUnit.SECONDS.sleep(Integer.MAX_VALUE);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        //byte[] b = new byte[1024 *50 *1024]；

        // 强引用
//        Object o1 = new Object();
//        Object o2 = o1;
//        o1 = null;
//        System.gc();
//        System.out.println(o2);

        // 软引用
        Person person = new Person();
        SoftReference<Person> softReference = new SoftReference<Person>(person);
        person = null;
        System.gc();
        System.out.println("内存没满 person：" + softReference.get());
        System.out.println("内存没满 softReference：" + softReference.get());
        byte[] b = new byte[1024 *50 *1024];
        System.out.println("内存满了 person：" + softReference.get());
        System.out.println("内存满了 softReference：" + softReference.get());
    }
}
class Person{

}
