package com.google.ssmm.algorithm.leetcode.tiku;

import java.util.HashMap;
import java.util.Map;

//[4,1,6,0,2,5,7,null,null,null,3,null,null,null,8]
//输出
//[25,6,21,0,5,5,15,null,null,null,3,null,null,null,8]
//预期结果
//[30,36,21,36,35,26,15,null,null,null,33,null,null,null,8]
public class n538 {
    int sum = 0;

    public TreeNode convertBST12(TreeNode root) {
        if (root != null) {
            convertBST12(root.right);
            sum += root.val;
            root.val = sum;
            convertBST12(root.left);
        }
        return root;
    }


    public TreeNode convertBST(TreeNode root) {
        Map<Integer, Integer> resultMap = new HashMap<>();
        dfs(root, resultMap);
        return root;
    }

    public void dfs(TreeNode node, Map<Integer, Integer> resultMap) {
        if(node == null){
            return;
        }
        node.val = length(node, resultMap);
        dfs(node.left,resultMap);
        dfs(node.right,resultMap);
    }


    public int length(TreeNode node, Map<Integer, Integer> resultMap) {
        if (node == null) {
            return 0;
        }
        int value;
        if(node.right == null){
            resultMap.put(node.val, node.val);
            return node.val;
        }
        if (resultMap.containsKey(node.right.val)) {
            value = resultMap.get(node.right.val) + node.val;
        } else {
            value = length(node.right, resultMap) + node.val;
        }
        resultMap.put(node.val, value);
        return value;
    }
}
