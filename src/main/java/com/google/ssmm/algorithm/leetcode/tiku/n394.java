package com.google.ssmm.algorithm.leetcode.tiku;

import java.util.LinkedList;

public class n394 {
    //ab2[ab]
    public String decodeString(String s) {
        LinkedList<String> dequeue = new LinkedList<>();
        if (s == null || s.length() == 0) {
            return s;
        }
        char[] chars = s.toCharArray();
        for (char c : chars) {
            if ( "]".equals("" + c)) {
                LinkedList<String> list = new LinkedList<>();
                while(true){
                    String s1 = dequeue.removeLast();
                    if(!"[".equals(s1)){
                        list.add(s1);
                    }else{
                        int count = getCount(dequeue);
                        StringBuilder sb = new StringBuilder();
                        while(!list.isEmpty()){
                            sb.append(list.removeLast());
                        }
                        String value = sb.toString();
                        for(int i=1;i<count;i++){
                            sb.append(value);
                        }
                        dequeue.add(sb.toString());
                        break;
                    }
                }

            }else{
                dequeue.addLast(""+c);
            }
        }
        StringBuilder sb = new StringBuilder();
        for(String s12: dequeue){
            sb.append(s12);
        }
        return sb.toString();
    }

    private int getCount(LinkedList<String> dequeue) {
        StringBuilder sb = new StringBuilder();
        while(!dequeue.isEmpty()){
            String s2 = dequeue.removeLast();
            if(Character.isDigit(s2.toCharArray()[0])){
                sb.append(s2);
            }else{
                dequeue.add(s2);
                break;
            }
        }
        sb.reverse();
        return Integer.parseInt(sb.toString());
    }

    public static void main(String[] args) {
//        String s = decodeString("a10[b]");
//        System.out.println(s);
    }
}
