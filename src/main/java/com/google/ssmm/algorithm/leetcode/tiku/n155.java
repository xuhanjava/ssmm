package com.google.ssmm.algorithm.leetcode.tiku;

import java.util.Deque;
import java.util.LinkedList;

public class n155 {
    Deque<Integer> xStack;
    Deque<Integer> minStack;

    public n155() {
        xStack = new LinkedList<Integer>();
        minStack = new LinkedList<Integer>();
        minStack.push(Integer.MAX_VALUE);
    }

    public void push(int x) {
        xStack.push(x);
        minStack.push(Math.min(minStack.peek(), x));
    }

    public void pop() {
        xStack.pop();
        minStack.pop();
    }

    public int top() {
        return xStack.peek();
    }

    public int getMin() {
        return minStack.peek();
    }

}
