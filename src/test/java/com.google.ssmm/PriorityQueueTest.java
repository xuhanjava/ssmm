package com.google.ssmm;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * 优先级队列测试
 * 优先级队列不允许使用 null 元素。NullpointException.依靠自然顺序的优先级队列还不允许插入不可比较的对象（这样做可能导致 ClassCastException）
 * 此对象不是线程安全的。LinkedBlockingQueue是线程安全的。
 * Created by xuhan on 17-2-6.
 */
public class PriorityQueueTest {
    private String name;
    private int population;
    public PriorityQueueTest(String name, int population)
    {
        this.name = name;
        this.population = population;
    }
    public String getName()
    {
        return this.name;
    }

    public int getPopulation()
    {
        return this.population;
    }
    public String toString()
    {
        return getName() + " - " + getPopulation();
    }
    public static void main(String args[])
    {
        Comparator<PriorityQueueTest> OrderIsdn =  new Comparator<PriorityQueueTest>(){
            public int compare(PriorityQueueTest o1, PriorityQueueTest o2) {
                // TODO Auto-generated method stub
                int numbera = o1.getPopulation();
                int numberb = o2.getPopulation();
                if(numberb > numbera)
                {
                    return 1;
                }
                else if(numberb<numbera)
                {
                    return -1;
                }
                else
                {
                    return 0;
                }

            }



        };
        Queue<PriorityQueueTest> priorityQueue =  new PriorityQueue<PriorityQueueTest>(11,OrderIsdn);

        PriorityQueueTest t1 = new PriorityQueueTest("t1",1);
        PriorityQueueTest t3 = new PriorityQueueTest("t3",3);
        PriorityQueueTest t2 = new PriorityQueueTest("t2",2);
        PriorityQueueTest t4 = new PriorityQueueTest("t4",0);
        priorityQueue.add(t1);
        priorityQueue.add(t3);
        priorityQueue.add(t2);
        priorityQueue.add(t4);
        System.out.println(priorityQueue.poll().toString());
    }
}  


