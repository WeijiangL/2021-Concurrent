package com.example.jvm;

import sun.util.resources.sl.CalendarData_sl;

import java.util.concurrent.TimeUnit;

public class JavaPDemo {

    int a;
    int b;

    public void add() {
        a = 1;
        b = 2;
        System.out.println(a + b);
    }

    public static void main(String[] args) {
        JavaPDemo person = new JavaPDemo();
        for(int i = 0 ; i < 15 ; i ++) {
            System.gc();
        }
        JavaPDemo person1 = new JavaPDemo();
        person.add();
        try {
            TimeUnit.SECONDS.sleep(Integer.MAX_VALUE);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
    