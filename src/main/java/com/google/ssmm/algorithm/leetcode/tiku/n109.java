package com.google.ssmm.algorithm.leetcode.tiku;

public class n109 {
    /**
     * Definition for singly-linked list.
     * public class ListNode {
     *     int val;
     *     ListNode next;
     *     ListNode() {}
     *     ListNode(int val) { this.val = val; }
     *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
     * }
     */
    /**
     * Definition for a binary tree node.
     * public class TreeNode {
     * int val;
     * TreeNode left;
     * TreeNode right;
     * TreeNode() {}
     * TreeNode(int val) { this.val = val; }
     * TreeNode(int val, TreeNode left, TreeNode right) {
     * this.val = val;
     * this.left = left;
     * this.right = right;
     * }
     * }
     */
    ListNode first = null;

    public TreeNode sortedListToBST(ListNode head) {
        first = head;
        int len = 0;
        ListNode slow = head;
        while (slow != null) {
            slow = slow.next;
            len++;
        }
        return binary(head, 0, len - 1);
    }

    public TreeNode binary(ListNode head, int start, int end) {
        if (start > end) {
            return null;
        }
        int middle = (start + end) / 2;
        TreeNode left = binary(head, start, middle - 1);
        TreeNode middleNode = new TreeNode(first.val);
        first = first.next;
        TreeNode right = binary(head, middle + 1, end);
        middleNode.left = left;
        middleNode.right = right;
        return middleNode;
    }

    class Solution {
        ListNode globalHead;

        public TreeNode sortedListToBST(ListNode head) {
            globalHead = head;
            int length = getLength(head);
            return buildTree(0, length - 1);
        }

        public int getLength(ListNode head) {
            int ret = 0;
            while (head != null) {
                ++ret;
                head = head.next;
            }
            return ret;
        }

        public TreeNode buildTree(int left, int right) {
            if (left > right) {
                return null;
            }
            int mid = (left + right + 1) / 2;
            TreeNode root = new TreeNode();
            root.left = buildTree(left, mid - 1);
            root.val = globalHead.val;
            globalHead = globalHead.next;
            root.right = buildTree(mid + 1, right);
            return root;
        }
    }
}
