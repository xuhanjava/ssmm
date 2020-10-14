package com.google.ssmm.algorithm.leetcode;

class MergeTwoLists {
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (l1 == null){
            return l2;
        }
        if (l2 == null){
            return l1;
        }
        ListNode node = new ListNode(0);
        ListNode head = node;
        while(l1 != null && l2 != null){
            if (l1.val < l2.val){
                node.next = (new ListNode(l1.val));
                l1 = l1.next;
                node = node.next;
            }else{
                node.next = (new ListNode(l2.val));
                l2 = l2.next;
                node = node.next;
            }
        }

        if (l1 != null){
            node.next = l1;
        }else{
            node.next  = l2;
        }

       return head.next;
    }
}