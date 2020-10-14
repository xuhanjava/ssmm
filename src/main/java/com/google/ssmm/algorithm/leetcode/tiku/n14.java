package com.google.ssmm.algorithm.leetcode.tiku;

public class n14 {
        public static String longestCommonPrefix(String[] strs) {
            if(strs == null || strs.length == 0){
                return "";
            }
            int length = strs.length;
            int index =0;
            while(true){
                for (int i=0;i<length;i++){
                    if(strs[i].length() <=index){
                        return strs[0].substring(0,index);
                    }
                    if(i == 0){

                    }else if(strs[i-1].charAt(index) != strs[i].charAt(index)){
                        return strs[0].substring(0,index);
                    }
                }
                index++;
            }
        }

        public static void main(String[] args) {
            String [] array = {"",""}  ;
            System.out.println(longestCommonPrefix(array));
           // System.out.println("123".substring(0,3));
        }
}
