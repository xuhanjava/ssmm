package com.google.ssmm.algorithm.leetcode.tiku;

public class n617 {
    public static TreeNode mergeTrees(TreeNode t1, TreeNode t2) {
        if(t1 == null & t2 == null){
            return null;
        }
        TreeNode result = new TreeNode(0);
        digui(t1,t2,result);
        return result;

    }

    public static void digui(TreeNode t1, TreeNode t2,TreeNode result){
        if(t1 == null && t2 == null){
            return;
        }

        if(t1 != null && t2 != null){
            result.val = t1.val + t2.val;
            if(t1.left != null || t2.left != null){
                TreeNode temp = new TreeNode(0);
                result.left = temp;
                digui(t1.left,t2.left,temp);
            }
            if(t1.right != null || t2.right != null){
                TreeNode temp = new TreeNode(0);
                result.right = temp;
                digui(t1.right,t2.right,temp);
            }
            return;
        }
        if(t1 == null){
            result.val = t2.val;
            result.left = t2.left;
            result.right = t2.left;
            return ;
        }
        result.val = t1.val;
        result.left = t1.left;
        result.right = t1.left;
        return ;

    }

    //[1,3,2,5]
    //[2,1,3,null,4,null,7]
    public static void main(String[] args) {
        TreeNode t3 = new TreeNode(3);
        TreeNode t2 = new TreeNode(2);
        TreeNode t1 = new TreeNode(1);
        t1.left = t2;
        t1.right = t3;

        TreeNode t31 = new TreeNode(30);
        TreeNode t21 = new TreeNode(20);
        TreeNode t11 = new TreeNode(10);
        t11.left = t21;
        t11.right = t31;
        TreeNode treeNode = mergeTrees(t1, t11);
        System.out.println(treeNode);
    }
}
