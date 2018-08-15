package com.redis.spring;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.RedisTemplate;

/**
 *  超时命令
 *  persist key: 持久化key，取消超时时间。当过期时间移除成功时，返回 1 。 如果 key 不存在或 key 没有设置过期时间，返回 0
 *  ttl key: 查看key的超时时间，以秒计算，-1代表没有超时时间 -2代表已超时
 *  expire key seconds: 设置超时时间戳，以秒为单位。设置成功返回 1 。 当 key 不存在或者不能为 key 设置过期时间时返回 0
 *  pttl key: 查看key的超时时间戳，用毫秒计算
 *  pexpire key: 设置键值超时的时间，以毫秒为单位
 *  pexpireat key stamptimes: 设置超时时间点，以毫秒为单位的uninx时间戳
 */
public class OverTimeRedis {
    public static void main(String[] args) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
        RedisTemplate redisTemplate = applicationContext.getBean(RedisTemplate.class);
//        redisTemplate.execute();
    }
}
