package com.google.ssmm.algorithm.leetcode.tiku;

import java.util.*;

public class n103 {
    public static List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        LinkedList<TreeNode> queue = new LinkedList<TreeNode>();
        List<List<Integer>> result = new ArrayList<>();
        if(root == null){
            return result;
        }
        queue.add(root);
        int direct = 1;
        while (!queue.isEmpty()) {
            int size = queue.size();
            List<Integer> tempList = new ArrayList<>(size);
            List<TreeNode> temoNodeList= new ArrayList<>(size);
            for (int i = 0; i < size; i++) {
                TreeNode poll = queue.poll();
                if(direct == 1){
                    if (poll.left != null) {
                        temoNodeList.add(poll.left);
                    }
                    if (poll.right != null) {
                        temoNodeList.add(poll.right);
                    }
                }else{
                    if (poll.right != null) {
                        temoNodeList.add(poll.right);
                    }
                    if (poll.left != null) {
                        temoNodeList.add(poll.left);
                    }

                }
                tempList.add(poll.val);
            }
            direct = -direct;
            result.add(tempList);
            Collections.reverse(temoNodeList);
            queue.addAll(temoNodeList);
        }
        return result;
    }

    public static void main(String[] args) {
        //[1,2,3,4,null,null,5]
        TreeNode treeNode5 = new TreeNode(5);
        TreeNode treeNode4 = new TreeNode(4);
        TreeNode treeNode3 = new TreeNode(3);
        TreeNode treeNode2 = new TreeNode(2);
        treeNode2.left = treeNode4;
        treeNode3.right = treeNode5;
        TreeNode treeNode1 = new TreeNode(1);
        treeNode1.left = treeNode2;
        treeNode1.right= treeNode3;
        System.out.println(zigzagLevelOrder(treeNode1));
    }
}
