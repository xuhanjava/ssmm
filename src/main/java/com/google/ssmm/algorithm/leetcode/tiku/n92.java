package com.google.ssmm.algorithm.leetcode.tiku;

public class n92 {
    //[1,2,3,4,5]
    //2
    //4
    public ListNode reverseBetween(ListNode head, int m, int n) {
        ListNode prev =new ListNode(0);
        ListNode cur = head;
        ListNode a=new ListNode(0);
        ListNode b = new ListNode(0);

        for(int i=1;i<=n;i++){
            if(i == m){
                a = prev;
                b = cur;
            }
            ListNode temp = cur.next;
            if(i>= m){
                cur.next = prev;
            }
            prev = cur;
            cur = temp;
        }
        a.next = prev;
        b.next = cur;
        if(m == 1){
            return prev;
        }

        return head;
    }

    public static void main(String[] args) {

    }
}
