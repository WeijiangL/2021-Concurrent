package com.example.list;

import java.util.ArrayList;

public class ArrayListDemo {
    public static void main(String[] args) {
        ArrayList<String> arrayList = new ArrayList<>();

        for (int i = 1 ; i <= 2; i++) {
            new Thread (() -> {
                arrayList.add("a");
                System.out.println(arrayList);
            }, String.valueOf(i)).start();

        }
    }
}
