package com.google.ssmm.algorithm.leetcode;

/**
 * 合并 k 个排序链表，返回合并后的排序链表。请分析和描述算法的复杂度。
 *
 * 示例:
 *
 * 输入:
 * [
 *   1->4->5,
 *   1->3->4,
 *   2->6
 * ]
 * 输出: 1->1->2->3->4->4->5->6
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/merge-k-sorted-lists
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
class MergeKLists {
    public static ListNode mergeKLists(ListNode[] lists) {
        if (lists.length == 0){
            return null;
        }

        ListNode node = new ListNode(0);
        ListNode head = node;
        int endSizeList = 0;
        while(endSizeList < lists.length){
            int minIndex = 0;
            for(int index= 0;index<lists.length;index++){
                if(lists[minIndex] == null){
                    if(lists[index] == null){
                        continue;
                    }
                    minIndex = index;
                }
                if(lists[index] == null){
                    continue;
                }

                if( lists[minIndex].val > lists[index].val){
                    minIndex = index;
                }
            }
            node.next = lists[minIndex];
            node = node.next;
            if (lists[minIndex].next == null){
                endSizeList ++;
            }
            lists[minIndex] = lists[minIndex].next;
        }

        return head.next;
    }
}