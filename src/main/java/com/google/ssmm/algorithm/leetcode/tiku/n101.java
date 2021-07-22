package com.google.ssmm.algorithm.leetcode.tiku;

public class n101 {

    public boolean isSymmetric11(TreeNode root) {
        return check1(root, root);
    }

    public boolean check1(TreeNode p, TreeNode q) {
        if (p == null && q == null) {
            return true;
        }
        if (p == null || q == null) {
            return false;
        }
        return p.val == q.val && check(p.left, q.right) && check(p.right, q.left);
    }


    //[2,3,3,4,5,5,4,null,null,8,9,9,8]
    public boolean isSymmetric(TreeNode root) {
        if (root == null) {
            return true;
        }
        TreeNode left = root.left;
        TreeNode right = root.right;
        if (left == null && right == null) {
            return true;
        } else if (left == null && right != null) {
            return false;
        } else if (left != null && right == null) {
            return false;
        }
        if (left.val != right.val) {
            return false;
        }
        TreeNode temp = right.left;
        right.left = right.right;
        right.right = temp;
        return isTrue(left, right);
    }

    public boolean isTrue(TreeNode left, TreeNode right) {
        if (left == null && right == null) {
            return true;
        } else if (left == null && right != null) {
            return false;
        } else if (left != null && right == null) {
            return false;
        }

        if (left.val != right.val) {
            return false;
        }
        if (!isTrue(left.left, right.left)) {
            return false;
        }
        if (!isTrue(left.right, right.right)) {
            return false;
        }
        return true;
    }

    public boolean isSymmetric1(TreeNode root) {
        return check(root, root);
    }

    public boolean check(TreeNode p, TreeNode q) {
        if (p == null && q == null) {
            return true;
        }
        if (p == null || q == null) {
            return false;
        }
        return p.val == q.val && check(p.left, q.right) && check(p.right, q.left);
    }

}
