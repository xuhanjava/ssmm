package com.google.ssmm.algorithm.leetcode.tiku;

public class n112 {
    static boolean result = false;

    public static boolean hasPathSum(TreeNode root, int sum) {
        if(root == null){
            return false;
        }
        return digui(root,0,sum);
    }

    public static boolean digui(TreeNode root, int sum, int target) {
        if (root.left == null && root.right == null) {
            return sum +root.val == target;
        }
        if (root.left != null) {
            result = result || digui(root.left, root.val + sum, target);
        }
        if(result){
            return true;
        }
        if (root.right != null) {
            result = result || digui(root.right, root.val + sum, target);
        }
        if(result){
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        TreeNode treeNode2 = new TreeNode(2);
        TreeNode treeNode7 = new TreeNode(7);
        TreeNode treeNode11 = new TreeNode(11);
        TreeNode treeNode4 = new TreeNode(4);
        TreeNode treeNode5 = new TreeNode(5);
        treeNode11.right = treeNode2;
        treeNode11.left = treeNode7;
        treeNode4.left = treeNode11;
        treeNode5.left = treeNode4;
        boolean result = hasPathSum(treeNode5, 22);
        System.out.println(result);

    }
}
