package com.google.ssmm.entity;

import java.util.UUID;

public class TreeNode<K extends Comparable> {
    private K value;
    private TreeNode<K> left;
    private TreeNode<K> right;
    private String key;

    public TreeNode(K value) {
        this.key = UUID.randomUUID().toString();
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public K getValue() {
        return value;
    }

    public void setValue(K value) {
        this.value = value;
    }

    public TreeNode<K> getLeft() {
        return left;
    }

    public void setLeft(TreeNode<K> left) {
        this.left = left;
    }

    public TreeNode<K> getRight() {
        return right;
    }

    public void setRight(TreeNode<K> right) {
        this.right = right;
    }

    @Override
    public String toString() {
        return "TreeNode{" +
                "key='" + key + '\'' +
                '}';
    }
}

//3<<<54da50a1-7f5f-47e4-80a6-9d44d02790d6
//3<<<9f7a07b2-fa23-4b45-b9e5-a9812e835980
//3<<<bf5bfb9c-4e5d-4030-aed6-61b68f034e16
//3<<<37d017b6-2938-41bb-95d5-34a41eae4e18
//3<<<7517d9d5-f478-4079-8818-c02c63f4ea5d
