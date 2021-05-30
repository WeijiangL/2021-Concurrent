package com.example.list;

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
}

public class Josephu {
    public static void main(String[] args) {
        CircleSingleLinkedList circleSingleLinkedList = new CircleSingleLinkedList();
        circleSingleLinkedList.add(5);
        circleSingleLinkedList.list();
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

