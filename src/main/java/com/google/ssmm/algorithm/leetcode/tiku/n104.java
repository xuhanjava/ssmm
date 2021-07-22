package com.google.ssmm.algorithm.leetcode.tiku;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class n104 {
    public int maxDepth(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        if(root == null){
            return 0;
        }
        int result = 0;
        queue.add(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            result++;
            List<Integer> tempList = new ArrayList<>(size);
            for (int i = 0; i < size; i++) {
                TreeNode poll = queue.poll();
                if (poll.left != null) {
                    queue.add(poll.left);
                }
                if (poll.right != null) {
                    queue.add(poll.right);
                }
                tempList.add(poll.val);
            }
        }
        return result;
    }
}
