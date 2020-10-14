package com.google.ssmm.hystrix;

import com.netflix.hystrix.*;

import java.io.IOException;
import java.util.*;

public class CommandHelloWorld extends HystrixCommand<String> {

    private final String name;

    public CommandHelloWorld(String name) {
        super(Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("SystemX"))
                .andCommandKey(HystrixCommandKey.Factory.asKey("PrimaryCommand"))
                .andThreadPoolKey(HystrixThreadPoolKey.Factory.asKey("PrimaryCommand"))
                .andCommandPropertiesDefaults(
                        // we default to a 600ms timeout for primary
                        HystrixCommandProperties.Setter().withExecutionTimeoutInMilliseconds(600)));
        this.name = name;
    }

    @Override
    protected String run() throws InterruptedException {
        Thread.sleep(8000);
        // a real example would do work like a network call here
        return "Hello " + name + "!";
    }

    @Override
    protected String getFallback() {
        return "fallback";
    }

    public int[] twoSum(int[] nums, int target) {
        Map<Integer,Integer> map = new HashMap(nums.length);
        List<Integer> list = new ArrayList(nums.length);
        for (int i = 0;i<nums.length;i++){
            if (map.get(nums[i])!= null){
                list.add(i);
                list.add(nums[i]);
                continue;
            }
            map.put(target-nums[i],i);
        }
        int[] array = new int[list.size()];
        for (int i=0;i<list.size();i++){
            array[i] = list.get(i);
        }
        return array;
    }

    public static void main(String[] args) throws IOException {
        Collections.synchronizedMap(new HashMap(1));

//        Runtime runtime = Runtime.getRuntime();
//        String appleScriptCommand =   "display notification \"通知内容\" with title \"标题\" subtitle \"子标题\"";
//
//
//        String[] args1 = { "osascript", "-e", appleScriptCommand};
//        try
//        {
//            Process process = runtime.exec(args1);
//        }
//        catch (Exception e)
//        {
//            e.printStackTrace();
//        }
    }
}

