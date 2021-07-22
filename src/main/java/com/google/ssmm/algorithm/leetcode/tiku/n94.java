package com.google.ssmm.algorithm.leetcode.tiku;

import java.util.ArrayList;
import java.util.List;

public class n94 {
    class Solution {
        public List<Integer> inorderTraversal(TreeNode root) {
            List<Integer> result = new ArrayList<>();
            put(root,result);
            return result;
        }

        public void put(TreeNode node,List<Integer> result){
            if(node == null){
                return;
            }
            put(node.left,result);
            result.add(node.val);
            put(node.right,result);
        }
    }
}
