package com.google.ssmm.algorithm.leetcode.digui;

import com.google.ssmm.algorithm.leetcode.tiku.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

public class n111 {

    public static int minDepth(TreeNode root) {
        if(root ==null){
            return 0;
        }
        return digui(root, 1);
    }

    public static int guangduSearch(TreeNode root){
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        int level = 0;
        while(!queue.isEmpty()){
            int whileSize = queue.size();
            level++;
            for(int i=0;i<whileSize;i++){
                TreeNode tempNode = queue.poll();
                if(tempNode.right == null && tempNode.left == null){
                    return level;
                }
                if(tempNode.left != null){
                    queue.add(tempNode.left);
                }
                if(tempNode.right != null){
                    queue.add(tempNode.right);
                }
            }

        }
        return level;
    }

    public static int digui(TreeNode root, int level) {
        if (root.left == null && root.right == null) {
            return level;
        }
        int left = Integer.MAX_VALUE, right = Integer.MAX_VALUE;
        if (root.left != null) {
            left = digui(root.left, level + 1);
        }
        if (root.right != null) {
            right = digui(root.right, level + 1);
        }
        return Math.min(left, right);
    }

    public static void main(String[] args) {
        TreeNode node6 = new TreeNode(6);
        TreeNode node5 = new TreeNode(5);
        node5.right = node6;
        TreeNode node4 = new TreeNode(4);
        node4.right = node5;
        TreeNode node3 = new TreeNode(3);
        node3.right = node4;
        TreeNode node2 = new TreeNode(2);
        node2.right = node3;
        int v = guangduSearch(node2);
        System.out.println(v);
    }

}
