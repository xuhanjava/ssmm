package com.google.ssmm.algorithm.leetcode.tiku;

import java.util.ArrayList;
import java.util.List;

public class n257 {
    public static List<String> binaryTreePaths(TreeNode root) {
        List<String> result = new ArrayList<>();
        if(root == null){
            return result;
        }
        List<StringBuilder> dfs = dfs(root);
        for(StringBuilder sb : dfs){
            result.add(sb.toString());
        }
        return result;
    }

    public static List<StringBuilder> dfs (TreeNode root){
        if(root.left == null && root.right == null){
            List<StringBuilder> temp = new ArrayList<>();
            StringBuilder sb = new StringBuilder();
            sb.append(root.val);
            temp.add(sb);
            return temp;
        }
        List<StringBuilder> result = new ArrayList<>();
        if(root.left != null){
            List<StringBuilder> dfs = dfs(root.left);
            for(StringBuilder s:dfs){
                s.insert(0,"->");
                s.insert(0,root.val);
            }
            result.addAll(dfs);
        }
        if(root.right != null){
            List<StringBuilder> dfs = dfs(root.right);
            for(StringBuilder s:dfs){
                s.insert(0,"->");
                s.insert(0,root.val);
            }
            result.addAll(dfs);
        }

        return result;
    }

    public static void main(String[] args) {
        TreeNode f3 = new TreeNode(33);
        TreeNode f2 = new TreeNode( -33);
        TreeNode f1 = new TreeNode(31);
        f1.left= f2;
        f2.left = f3;
        List<String> strings = binaryTreePaths(f1);
        System.out.println(strings);
    }
}
