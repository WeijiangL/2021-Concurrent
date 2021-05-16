package com.example.cas;


import java.util.concurrent.atomic.AtomicReference;

class User {
    int age;
    String name;

    public User(int age, String name) {
        this.age = age;
        this.name = name;
    }

    @Override
    public String toString() {
        return "User{" +
                "age=" + age +
                ", name='" + name + '\'' +
                '}';
    }
}

public class AtomicRefrenceDemo {

    public static void main(String[] args) {
        User user1 = new User(21 , "user1");
        User user2 = new User(22 , "user2");

        AtomicReference<User> atomicReference = new AtomicReference<>();
        atomicReference.set(user1);

        System.out.println(atomicReference.compareAndSet(user1, user2) + "\t " + atomicReference.get().toString());
        System.out.println(atomicReference.compareAndSet(user1, user2) + "\t " + atomicReference.get().toString());
    }
}
