package com.example.queue;

// 约瑟夫问题
// 创建环形单向链表
class CircleSingleLinkedList {

    // 创建一个头节点first
    Boy first = null;

    /*
        思路
        1.先创建第一个节点，让第一个节点形成环形
        2.每创建一个节点，就把该节点加入到环形链表中
     */
    public void add(int nums) {

        // 对nums判定边界
        if (nums < 1) {
            System.out.println("nums不正确");
        }

        // 创建一个辅助指针，帮助创建
        Boy curBoy = null;
        for (int i = 1; i <= nums; i++) {
            // 创建每次需要加入的新的节点
            Boy boy = new Boy(i);
            if(i == 1) {// 表示只有一个节点
                // 让第一个节点形成环形
                first = boy;
                first.next = first;
                // 将curBoy指向first
                curBoy = first;
            } else {
                // 将新加的节点和前面的节点形成环形
                curBoy.next = boy;
                boy.next = first;
                // 将辅助指针指向新的节点
                curBoy = boy;
            }
        }
    }

    /*
        思路
        1.需要辅助指针帮忙遍历
        2.判断有没有到链表的最后一个节点的条件是：curBoy.next = first
     */
    public void list() {
        // 判断链表是否为null
        if(first == null) {
            System.out.println("链表为null");
            return;
        }

        // 创建辅助指针
        Boy curBoy = first;
        while(true) {
            System.out.println(curBoy);
            if(curBoy.next == first) {// 表示遍历到最后一个节点了
                break;
            }
            curBoy = curBoy.next;
        }
    }

    // 约瑟夫问题
    /*
        概述：比如n=5(环形链表有5个人)，k=1(表示从第一个人开始数)，m=2(表示从第一个人开始数两下)，也就是数到2，让2离开这个环形链表
     */
    /*
        思路
        1.创建一个辅助节点helper，指向first节点的前一个节点，因为涉及到删除节点，必须要往前定义一个指针
        2.通过遍历，让helper和first移动k-1步
        3.通过遍历，让helper和first移动m-1步
        4.删除first指向的节点
          first =first.next
          help.next = first
          这个出圈顺序是：2 4 1 5 3
     */
    public void sovleJosephu(int n , int k , int m) {
        // 对参数进行数据校验
        if (first == null || k <= 0 || k > n) {
            System.out.println("参数不正确，请重新输入");
        }

        /*
            1.创建一个辅助节点helper，指向first节点的前一个节点，因为涉及到删除节点，必须要往前定义一个指针
         */
        Boy helper = first;
        //想要让helper指向first的前一个节点，需要通过遍历
        while(true) {
            if(helper.next == first) {// 表示helper的下一个节点是first，退出循环
                break;
            }
            helper = helper.next;
        }

        /*
            2.通过遍历，让helper和first移动k-1步(开始报数前)
         */
        for (int i = 0; i < k - 1; i++) {
            helper = helper.next;
            first = first.next;
        }

        /*
            3.通过遍历，让helper和first移动m-1步（开始报数后），循环操作，直到圈中只有一个节点
         */
        while (true) {
            if (first == helper) {// 说明圈中只有一个节点了
                break;
            }
            for (int i = 0; i < m - 1; i++) { // first指向要删除的节点，helper指向要删除的前一个节点
                helper = helper.next;
                first = first.next;
            }
         /*
            4.删除first指向的节点
         */
            System.out.println("删除的节点为：" + first.no);
            first =first.next;
            helper.next = first;

        }
        System.out.println("圈中还剩下的节点为：" + first.no);
    }
}

public class Josephu {
    public static void main(String[] args) {
        CircleSingleLinkedList circleSingleLinkedList = new CircleSingleLinkedList();
        circleSingleLinkedList.add(5);
//        circleSingleLinkedList.list();
        circleSingleLinkedList.sovleJosephu(5 , 1 , 2);

    }
}

// 节点类
class Boy {
    int no;
    Boy next;

    public Boy(int no) {
        this.no = no;
    }

    @Override
    public String toString() {
        return "Boy{" +
                "no=" + no +
                '}';
    }
}

