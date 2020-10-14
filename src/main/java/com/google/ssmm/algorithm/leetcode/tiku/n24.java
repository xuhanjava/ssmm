package com.google.ssmm.algorithm.leetcode.tiku;

public class n24 {
    public static ListNode swapPairs(ListNode head) {
        if(head == null || head.next == null){
            return head;
        }
        ListNode begin = new ListNode();
        ListNode begin1 = new ListNode();
        begin1.next = head.next; //todo xuhan 这个地方的问题就是没有理解指针的含义；这题的反思就是我每次遍历都必须找到上一次遍历的最后一个
        while(true){             //节点，要不然用一个新的指针的话，其实数据还是不怎么变的；引用必须指向节点的时候 head.next = xxx 才会修改，要不然直接head = xxx
                                 // 之前的值是不会有任何变化的
            if (begin == null || head == null){
                break;
            }
            if(head.next == null){
                break;
            }
            begin.next = head.next;
            ListNode third = begin.next.next;
            begin.next.next = head;
            head.next = third;
            begin = head;
            head = third;
            begin.next = third;
        }

        return begin1.next;
    }

    public static void main(String[] args) {

        ListNode third =  new ListNode(3,null);

        ListNode second = new ListNode(2,third);

        ListNode first =  new ListNode(1,second);

        ListNode listNode = swapPairs(first);
        System.out.println(listNode);

    }

    public static class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }
}
