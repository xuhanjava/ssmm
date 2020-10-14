package com.google.ssmm.tree;

import com.google.ssmm.entity.TreeNode;

import java.util.ArrayList;
import java.util.List;


//有重复元素（单链表实现、不够优雅）
public class BST<K extends Comparable> {
    private TreeNode head;

    public TreeNode getHead() {
        return head;
    }

    public int deleteTreeNode(K value){
        List<TreeNode> treeNodes = new ArrayList<>();
        find(value, treeNodes);
        //注意这里是反向删
        for(int i = treeNodes.size()-1;i>=0;i--){
            deleteSingleNode(treeNodes.get(i));
        }
        return treeNodes.size();
    }

    //1.没有子节点
    //2.一个子节点
    //3.两个子节点 a4fc
    //todo xuhan 如何找到前序节点(我这里删除只是把value设置为null)
    private boolean deleteSingleNode(TreeNode<K> node){
        if(isEmptyNode(node.getLeft())  && isEmptyNode(node.getLeft())){
            node.setValue(null);
            return true;
        }
        if(!isEmptyNode(node.getLeft()) && !isEmptyNode(node.getRight())){
            TreeNode<K> temp = node.getRight();
            while(!isEmptyNode(temp)){
                if (isEmptyNode(temp.getLeft())){
                    node.setValue(temp.getValue());
                    return true;
                }
                temp = temp.getLeft();
            }
            return false;
        }
        if(!isEmptyNode(node.getLeft())){
            node.setValue(node.getLeft().getValue());
        }
        if(!isEmptyNode(node.getRight())){
            node.setValue(node.getRight().getValue());
        }
        return false;
    }

    public boolean isEmptyNode(TreeNode node){
        return node == null || node.getValue() == null;
    }

    public boolean addTreeNode(K value) {
        if (isEmptyNode(head)) {
            head = new TreeNode(value);
            return true;
        }
        TreeNode temp = head;
        while (true) {
            if (temp.getValue().compareTo(value) > 0) {
                if (isEmptyNode(temp.getLeft())) {
                    temp.setLeft(new TreeNode(value));
                    return true;
                }
                temp = temp.getLeft();
            } else {
                if (isEmptyNode(temp.getRight())) {
                    temp.setRight(new TreeNode(value));
                    return true;
                }
                temp = temp.getRight();
            }
        }
    }


    //如果有重复元素会怎么样
    public void find(K value,List<TreeNode> resultList) {
        if (isEmptyNode(head)) {
            return;
        }
        TreeNode temp = head;
        while (true) {
            if (isEmptyNode(temp)) {
                return;
            }
            if (temp.getValue().compareTo(value) == 0) {
                resultList.add(temp);
                temp = temp.getRight();
            } else if (temp.getValue().compareTo(value) < 0) {
                temp = temp.getRight();
            } else {
                temp = temp.getLeft();
            }
        }
    }


    //中序遍历（就从小到大遍历）
    public void display(TreeNode node) {
        if (isEmptyNode(node)) {
            return;
        }
        display(node.getLeft());
        System.out.println(node.getValue() + "<<<" + node.getKey());
        display(node.getRight());
    }
}
