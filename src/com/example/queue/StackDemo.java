package com.example.queue;

/*
    用数组模拟
    先进后出
 */
public class StackDemo {

    // 栈顶
    int top;
    // 栈的大小
    int maxSize;
    // 栈的数据
    int [] stackArray;

    // 构造方法
    public StackDemo(int maxSize) {
        this.top = -1;
        this.maxSize = maxSize;
        stackArray = new int[this.maxSize];
    }

    // 判断是否满了
    public boolean isFull() {
        return top == (maxSize - 1);
    }

    // 判断是否为空
    public boolean isEmpty() {
        return top == -1;
    }

    //入栈
    public void push(int number) {
        if (isFull()) {
            System.out.println("栈满了...");
            return;
        }
        top ++;
        stackArray[top] = number;
    }

    // 出栈
    public int pop() {
        if (isEmpty()) {
            throw new RuntimeException("栈为null....");
        }
        int temp = stackArray[top];
        top --;
        return temp;
    }

    // 展示栈内所有元素
    public void list() {
        if (isEmpty()) {
            throw new RuntimeException("栈为null....");
        }
        for (int i = top; i >= 0; i--) {
            System.out.println(stackArray[i]);
        }
    }

    public static void main(String[] args) {
        StackDemo stackDemo = new StackDemo(5);
        System.out.println("================入栈==================");
        stackDemo.push(1);
        stackDemo.push(2);
        stackDemo.push(3);
        stackDemo.push(4);
        stackDemo.push(5);
        stackDemo.list();
        stackDemo.push(6);


        System.out.println("================出栈==================");
        System.out.println(stackDemo.pop());
        System.out.println(stackDemo.pop());
        System.out.println(stackDemo.pop());
        System.out.println(stackDemo.pop());
        System.out.println(stackDemo.pop());


    }
}
