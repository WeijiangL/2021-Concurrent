package com.example.gc;

import com.example.list.ArrayListDemo;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class OOMDemo {

    public static void main(String[] args) {
        //myStackOverFlow();
//        myHeapSpace();
//        myGCOverHead();
        myUnableCreatNewThread();
    }

    private static void myUnableCreatNewThread() {
        for (int i = 1 ; ; i++) {
            new Thread (() -> {
                try {
                    TimeUnit.SECONDS.sleep(Integer.MAX_VALUE);
                } catch (Throwable e) {
                    e.printStackTrace();
                    try {
                        throw e;
                    } catch (InterruptedException e1) {
                        e1.printStackTrace();
                    }
                }
            }, String.valueOf(i)).start();
        }
    }

    private static void myGCOverHead() {
        int i = 0;
        List<String> list = new ArrayList();

        try {

            while (true) {
                list.add(String.valueOf( ++ i).intern());
            }

        }catch (Throwable e){
            throw e;
        }
    }

    private static void myHeapSpace() {
        byte[] bytes = new byte[50 * 1024 * 1024];
     }

    private static void myStackOverFlow() {
        myStackOverFlow();
    }
}
