package com.example.gc;

import java.util.HashMap;
import java.util.WeakHashMap;

public class WeakHashMapDemp {
    public static void main(String[] args) {
        myHashMap();
        System.out.println("===============");
        myWeakHashMap();
    }

    private static void myHashMap() {
        HashMap<Integer, String> map = new HashMap<>();
        Integer key = new Integer(1);
        String value = "hashMap";

        map.put(key , value);
        System.out.println(map);

        key = null; // key只与1有关
        System.out.println(map);

        System.gc();
        System.out.println(map); // 强引用，GC不会回收
    }

    private static void myWeakHashMap() {
        WeakHashMap<Integer, String> map = new WeakHashMap<>();
        Integer key = new Integer(1);
        String value = "WeakHashMap";

        map.put(key , value);
        System.out.println(map);

        key = null; // key只与1有关
        System.out.println(map);

        System.gc();
        System.out.println(map); // 弱引用，GC会回收
    }
}
