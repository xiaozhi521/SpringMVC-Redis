package com.redis.spring.transcations;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SessionCallback;

import java.util.HashMap;
import java.util.Map;

public class TranscationRedisHash {
    public static void main(String[] args) {
       ApplicationContext applicationContext =  new ClassPathXmlApplicationContext("applicationContext.xml");

       RedisTemplate redisTemplate = applicationContext.getBean(RedisTemplate.class);

       SessionCallback sessionCallback = new SessionCallback() {
           @Override
           public Object execute(RedisOperations redisOperations) throws DataAccessException {
               redisOperations.multi();
               Map map = new HashMap();
               map.put("age","15");
               map.put("name","mqf");
               redisOperations.boundHashOps("map").putAll(map);
               redisOperations.exec();
               Map mapValue = redisOperations.boundHashOps("map").entries();
               return mapValue;
           }
       };
       Map map = (Map) redisTemplate.execute(sessionCallback);
        System.out.println(map);
    }
}
