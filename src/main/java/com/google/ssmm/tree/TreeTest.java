package com.google.ssmm.tree;

import com.google.ssmm.entity.TreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 *
 */

public class TreeTest {
    public static void main(String[] args) {
        BST bst = new BST();
        Random random = new Random();//默认构造方法
        for (int i=0;i<35;i++){
            bst.addTreeNode(new Integer(random.nextInt(5)));
        }
        bst.display(bst.getHead());
        System.out.println("________________________________________________________________________________");
        List<TreeNode> result = new ArrayList<>();
        bst.find(3,result);
        result.stream().forEach(item->{
            System.out.println(item);
        });
        System.out.println("************************************************************************************");
        bst.deleteTreeNode(3);
        bst.display(bst.getHead());

    }
}

//3<<<87a8a46a-6226-487f-8cf3-2f4c0c98e927
//3<<<579ac656-acd4-41e8-9816-8b91df5f3a3f
//3<<<248e8350-7224-4d34-ae93-81bfea5adc0c
//3<<<97a9a2af-0670-4061-bc4b-9663d49869b9
//3<<<8f3310a9-a395-461f-a137-f2fe686ebee9
