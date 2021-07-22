package com.google.ssmm.algorithm.leetcode.tiku;

public class n86 {
    //给定一个链表和一个特定值 x，对链表进行分隔，使得所有小于 x 的节点都在大于或等于 x 的节点之前。
    //
    //你应当保留两个分区中每个节点的初始相对位置。
    //
    // 
    //
    //示例:
    //
    //输入: head = 1->4->3->2->5->2, x = 3
    //输出: 1->2->2->4->3->5
    //
    public static ListNode partition(ListNode head, int x) {
        ListNode little = new ListNode(0);
        ListNode littleHead = little;
        ListNode big = new ListNode(0);
        ListNode bigHead = big;
        while (head != null){
            ListNode temp = new ListNode(head.val);
            if(head.val < x){
                little.next = temp;
                little = little.next;
            }else{
                big.next = temp;
                big = big.next;
            }
            head = head.next;
        }
        if (littleHead.next != null){
            little.next = bigHead.next;
            return littleHead.next;
        }else{
            return bigHead.next;
        }
    }

    public static void main(String[] args) {
        ListNode f6 = new ListNode(2);
        ListNode f5 = new ListNode(5,f6);
        ListNode f4 = new ListNode(2,f5);
        ListNode f3 = new ListNode(3,f4);
        ListNode f2 = new ListNode(4,f3);
        ListNode f1 = new ListNode(1,f2);
        ListNode partition = partition(f1, 3);
        System.out.println(partition);
    }
}
