package com.redis.spring;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.*;

public class HashRedis {
    public static void main(String[] args) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
        RedisTemplate redisTemplate = applicationContext.getBean(RedisTemplate.class);

        String key = "hash";
        Map<String,String> map = new HashMap<>();
        map.put("f1","val1");
        map.put("f2","val2");
        //相当于 hmset 命令
        redisTemplate.opsForHash().putAll(key,map);
        //相当于hset 命令
        redisTemplate.opsForHash().put(key,"f3","2");
        printValueForHash(redisTemplate,key,"f3");
        // hexists key filed 命令
        boolean exists = redisTemplate.opsForHash().hasKey(key,"f3");
        System.out.println(exists);
        //hgetall 命令
        Map entries = redisTemplate.opsForHash().entries(key);
        System.out.println(entries);
        //hincrby 命令
        redisTemplate.opsForHash().increment(key,"f3",4);
        printValueForHash(redisTemplate,key,"f3");
        // hincrbyfloat 命令
        redisTemplate.opsForHash().increment(key,"f3",0.88);
        printValueForHash(redisTemplate,key,"f3");
        // hvals 命令
        List valueList = redisTemplate.opsForHash().values(key);
        System.out.println(valueList);
        // hkeys 命令
        Set keysSet = redisTemplate.opsForHash().keys(key);
        List<String> fieldList = new ArrayList<>();
        fieldList.add("f1");
        fieldList.add("f2");
        // hmget 命令
        List  valueList2 = redisTemplate.opsForHash().multiGet(key,fieldList);
        System.out.println(valueList2);
        //hsetnx 命令
        boolean success = redisTemplate.opsForHash().putIfAbsent(key,"f4","val4");
        System.out.println(success);
        // hdel 命令
        Long result = redisTemplate.opsForHash().delete(key,"f1","f2");
        System.out.println(result);

    }

    public static void printValueForHash(RedisTemplate redisTemplate,String key,String hashKey){
        System.out.println(redisTemplate.opsForHash().get(key,hashKey));
    }
}
