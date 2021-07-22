package com.google.ssmm.algorithm.leetcode.tiku;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class LFUCache {
    int minFreq, capacity;
    Map<Integer, Node> keyMap = new HashMap<>();
    Map<Integer, LinkedList<Node>> freq = new HashMap<>();

    public LFUCache(int capacity) {
        this.minFreq = 0;
        this.capacity = capacity;
    }

    public Node get(int key) {
        if (!keyMap.containsKey(key)) {
            return null;
        }
        Node node = keyMap.get(key);
        LinkedList<Node> list = freq.get(node.freq);
        list.remove(node);
        if (list.size() == 0) {
            freq.remove(node.freq);
            if (node.freq == minFreq) {
                minFreq++;
            }
        }
        node.freq = node.freq + 1;
        LinkedList<Node> secList = freq.getOrDefault(node.freq + 1, new LinkedList<>());
        secList.add(node);
        freq.put(node.freq, secList);
        return node;
    }

    public void put(int key, int value) {
        if (keyMap.containsKey(key)) {
            Node Node = keyMap.get(key);
            LinkedList<Node> list = freq.get(key);
            list.remove(Node);
            if (list.size() == 0) {
                freq.remove(Node.freq);
                if (minFreq == Node.freq) {
                    minFreq++;
                }
            }
            Node.freq += 1;
            LinkedList<Node> secList = freq.getOrDefault(Node.freq, new LinkedList<>());
            secList.add(Node);
            freq.put(Node.freq, secList);
        } else {
            //缓存满了
            if (keyMap.size() == capacity) {
                //删除最后一个的
                LinkedList<Node> minList = freq.get(minFreq);
                Node node = minList.pollLast();
                keyMap.remove(node.key);
                if (minList.size() == 0) {
                    freq.remove(minFreq);
                }
            }
            Node Node = new Node( key, value,1);
            keyMap.put(key, Node);
            LinkedList<Node> list = freq.getOrDefault(1, new LinkedList<>());
            list.add(Node);
            freq.put(1, list);
            minFreq = 1;
        }
    }
}
