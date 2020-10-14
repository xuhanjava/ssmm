package com.google.ssmm.algorithm.leetcode.tiku;

public class Two {
    //给出两个 非空 的链表用来表示两个非负的整数。其中，它们各自的位数是按照 逆序 的方式存储的，并且它们的每个节点只能存储 一位 数字。
    //
    //如果，我们将这两个数相加起来，则会返回一个新的链表来表示它们的和。
    //
    //您可以假设除了数字 0 之外，这两个数都不会以 0 开头。
    //
    //示例：
    //
    //输入：(2 -> 4 -> 3) + (5 -> 6 -> 4)
    //输出：7 -> 0 -> 8
    //原因：342 + 465 = 807
    //
    //来源：力扣（LeetCode）
    //链接：https://leetcode-cn.com/problems/add-two-numbers
    //著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        if (l1 == null) {
            return l2;
        }
        if (l2 == null) {
            return l1;
        }

        //比较大小
        ListNode result = new ListNode(0);
        ListNode begin = result;
        int prev = 0;
        while (l1 != null && l2 != null) {
            int tempValue = l1.val + l2.val + prev;
            prev = tempValue / 10;
            ListNode temp  = new ListNode(tempValue % 10);
            result.next = temp;
            result = temp;
            l1 = l1.next;
            l2 = l2.next;
        }
        if(l1 == null){
            while(l2 != null){
                int tempValue = l2.val + prev;
                prev = tempValue / 10;
                ListNode temp  = new ListNode(tempValue % 10);
                result.next = temp;
                result = temp;
                l2 = l2.next;
            }
        }
        if(l2 == null){
            while(l1 != null){
                int tempValue = l1.val + prev;
                prev = tempValue / 10;
                ListNode temp  = new ListNode(tempValue % 10);
                result.next = temp;
                result = temp;
                l1 = l1.next;
            }
        }
        if (prev >0){
            result.next = new ListNode(prev);
        }
        return begin.next;

    }

    static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }


    }

    //    // 输入：(2 -> 4 -> 3) + (5 -> 6 -> 4)
    //    // 输出：7 -> 0 -> 8
    //    // 原因：342 + 465 = 807
    //    //
    public static void main(String[] args) {
        ListNode l1 = new ListNode(2);
        l1.next = new ListNode(4);
        l1.next.next = new ListNode(3);

        ListNode l2 = new ListNode(5);
        l2.next = new ListNode(6);
        l2.next.next = new ListNode(4);
        ListNode listNode = addTwoNumbers(l1, l2);
        System.out.println(listNode);
    }
}
