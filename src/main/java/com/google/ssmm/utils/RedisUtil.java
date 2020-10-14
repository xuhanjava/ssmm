//package com.google.ssmm.utils;
//
//import org.redisson.Redisson;
//import org.redisson.api.RedissonClient;
//import org.redisson.api.RedissonReactiveClient;
//import org.redisson.api.RedissonRxClient;
//import org.redisson.config.Config;
//import redis.clients.jedis.Jedis;
//
//import java.io.File;
//import java.io.IOException;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
///**
// * redisson
// */
//public class RedisUtil {
//    public static void main(String[] args) throws IOException {
//        Jedis jedis = null;
//        try {
//            jedis = JedisPoolManager.getMgr().getResource();
////        jedis.auth("hello");
//
//            //simple key-value
//            jedis.set("redis", "myredis");
//            System.out.println(jedis.get("redis"));
//
//            jedis.append("redis", "yourredis");
//            jedis.append("mq", "RabbitMQ");
//
//            //incr
//            String pv = jedis.set("pv", "0");
//            System.out.println("pv:"+pv);
//            jedis.incr("pv");
//            jedis.incrBy("pv", 10);
//            System.out.println("pv:"+pv);
//
//            //mset
//            jedis.mset("firstName", "ricky", "lastName", "Fung");
//            System.out.println(jedis.mget("firstName", "lastName"));
//
//            //map
//            Map<String,String> cityMap =  new HashMap<String,String>();
//            cityMap.put("beijing", "1");
//            cityMap.put("shanghai", "2");
//
//            jedis.hmset("city", cityMap);
//            System.out.println(jedis.hget("city", "beijing"));
//            System.out.println(jedis.hlen("city"));
//            System.out.println(jedis.hmget("city", "beijing","shanghai"));
//
//            //list
//            jedis.lpush("hobbies", "reading");
//            jedis.lpush("hobbies", "basketball");
//            jedis.lpush("hobbies", "shopping");
//
//            List<String> hobbies = jedis.lrange("hobbies", 0, -1);
//            System.out.println("hobbies:"+hobbies);
//
//            jedis.del("hobbies");
//    }
//
//    public void testRedisson() throws IOException {
//        // 1. Create config object
//        Config config = new Config();
//        config.useClusterServers()
//                // use "rediss://" for SSL connection
//                .addNodeAddress("redis://127.0.0.1:7181");
//
//        // or read config from file
//        config = Config.fromYAML(new File("config-file.yaml"));
//        // 2. Create Redisson instance
//
//        // Sync and Async API
//        RedissonClient redisson = Redisson.create(config);
//        redisson.getLock("123");
//
//        // Reactive API
//        RedissonReactiveClient redissonReactive = Redisson.createReactive(config);
//
//        // RxJava2 API
//        RedissonRxClient redissonRx = Redisson.createRx(config);
//    }
//}
