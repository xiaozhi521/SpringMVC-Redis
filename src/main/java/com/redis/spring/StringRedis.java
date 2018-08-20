package com.redis.spring;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.redis.core.RedisTemplate;

public class StringRedis {
    public static void main(String[] args) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
        RedisTemplate redisTemplate = applicationContext.getBean(RedisTemplate.class);
        //设值
        redisTemplate.opsForValue().set("key1","value1");
        redisTemplate.opsForValue().set("key2","value2");
        //通过key取值
        String key1 = (String) redisTemplate.opsForValue().get("key1");
        System.out.println(key1);
        //通过key删除值
        redisTemplate.delete("key1");
        //求长度
        Long length = redisTemplate.opsForValue().size("key2");
        System.out.println("key2长度：" + length);
        //设置新值并返回旧址
        String oldValue = (String) redisTemplate.opsForValue().getAndSet("key2","new Value2");
        System.out.println("key2旧值：" + oldValue);
        //通过key取值
        String value2 = (String) redisTemplate.opsForValue().get("key2");
        System.out.println("value2的值：" + value2);
        //求子串
        String rangeValue2 = redisTemplate.opsForValue().get("key2",0,3);
        System.out.println("value2的子串值：" + rangeValue2);

        //追加字符串到末尾，返回新长度
        int newLen = redisTemplate.opsForValue().append("key2","_app-edis");
        System.out.println("value2新长度：" + newLen);

        //通过key取值
        String valueAppend = (String) redisTemplate.opsForValue().get("key2");
        System.out.println("value2的值：" + valueAppend);

    }
}
