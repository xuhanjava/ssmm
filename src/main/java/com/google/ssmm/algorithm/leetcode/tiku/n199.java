package com.google.ssmm.algorithm.leetcode.tiku;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class n199 {
    public static List<Integer> rightSideView(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if(root == null){
            return result;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while(!queue.isEmpty()){
            TreeNode poll = queue.poll();
            result.add(poll.val);
            int count = 0;
            if(poll.right != null){
                queue.add(poll.right);
                count ++;
            }
            if(poll.left != null){
                queue.add(poll.left);
                count ++;
            }
            int len = queue.size();
            for(int i=0;i<len-count;i++){
                TreeNode poll1 = queue.poll();
                if(poll1.right != null){
                    queue.add(poll1.right);
                }
                if(poll1.left != null){
                    queue.add(poll1.left);
                }
            }

        }
        return result;
    }

    public static void main(String[] args) {
        TreeNode node6 = new TreeNode(6);
        TreeNode node5 = new TreeNode(5);
        TreeNode node3 = new TreeNode(3);
        TreeNode node2 = new TreeNode(2);
        node2.right = node6;
        node2.left = node5;
        TreeNode node1 = new TreeNode(1);
        node1.left = node2;
        node1.right = node3;
        rightSideView(node1);

    }
}
