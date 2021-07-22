package com.google.ssmm.algorithm.leetcode.tiku;

public class n20 {
        public static boolean isValid(String s) {
        if(s == null || s.length() == 0){
            return true;
        }
        if(s.length() == 1){
            return false;
        }
        char[]array = s.toCharArray();
        char [] stack = new char[s.length()];
        int begin = 0;
        for (int i=0; i< s.length();i++){
            if (begin == 0){
                stack[begin++] = array[i];
                continue;
            }
            switch (array[i]){
                case ')':
                    if(stack[begin-1] == '('){
                        begin --;
                    }else{
                        stack[begin++] = ')';
                    }
                    break;
                case ']':
                    if(stack[begin-1] == '['){
                        begin --;
                    }else{
                        stack[begin++] = ']';
                    }
                    break;
                case '}':
                    if(stack[begin-1] == '{'){
                        begin --;
                    }else{
                        stack[begin++] = '}';
                    }
                    break;
                default:
                    stack[begin++] = array[i];
            }
        }
        return begin == 0;
    }

    public static void main(String[] args) {
        String s = "(){}";
        System.out.println(isValid(s));
    }
}
