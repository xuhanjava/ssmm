package com.google.ssmm.algorithm.leetcode.tiku;

import java.util.HashMap;
import java.util.Map;

public class n106 {
    public static TreeNode buildTree(int[] inorder, int[] postorder) {
        Map<Integer, Integer> map = new HashMap<>(inorder.length);
        for (int i = 0; i < inorder.length; i++) {
            map.put(inorder[i], i);
        }
        return getTree(map, inorder, postorder, 0, inorder.length - 1, 0, postorder.length - 1);
    }

    private static TreeNode getTree(Map<Integer, Integer> map, int[] inorder, int[] postorder, int inBegin, int inEnd, int postBegin, int postEnd) {
        if(inBegin >inEnd){
            return null;
        }
        int root = postorder[postEnd];
        int leftSize = map.get(root) - inBegin;
        int leftInBegin = inBegin;
        int leftInEnd = inBegin + leftSize -1;
        int leftPostBegin = postBegin;
        int leftPostEnd = postBegin + leftSize-1;
        TreeNode rootNode = new TreeNode(root);

        TreeNode left = getTree(map, inorder, postorder, leftInBegin, leftInEnd, leftPostBegin, leftPostEnd);
        rootNode.left = left;

        int rightInBegin = inBegin + leftSize + 1;
        int rightInENd = inEnd;
        int rightPostBegin =postBegin+ leftSize;
        int rightPostEnd = postEnd-1;

        TreeNode right = getTree(map, inorder, postorder, rightInBegin, rightInENd, rightPostBegin, rightPostEnd);
        rootNode.right = right;
        return rootNode;
    }

    public static void main(String[] args) {
//        inorder = [9,3,15,20,7]
//        后序遍历 postorder = [9,15,7,20,3]

        int[] first = {9,3,15,20,7}; //2～4
        int[] second = {9,15,7,20,3}; //1~3
        TreeNode treeNode = buildTree(first, second);
        System.out.println(treeNode);
    }


}
