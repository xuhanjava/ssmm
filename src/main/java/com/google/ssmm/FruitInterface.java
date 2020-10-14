package com.google.ssmm;

public interface FruitInterface {
    static void dispalay(){
       System.out.println(123);
   }

   default void d(){
       System.out.println(123);
   }
}
