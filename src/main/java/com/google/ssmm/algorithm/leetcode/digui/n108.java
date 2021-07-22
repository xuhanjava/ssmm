package com.google.ssmm.algorithm.leetcode.digui;

import com.google.ssmm.algorithm.leetcode.tiku.TreeNode;

public class n108 {
    public TreeNode sortedArrayToBST(int[] nums) {
        return digui(nums,0,nums.length-1);
    }

    public TreeNode digui(int[] nums,int begin,int end){
        if(begin > end){
            return null;
        }
        int rootIndex = (end + begin)/2;
        TreeNode treeNode = new TreeNode(nums[rootIndex]);
        treeNode.left = digui(nums,begin,rootIndex -1);
        treeNode.right = digui(nums,rootIndex +1,end);
        return treeNode;
    }
}
