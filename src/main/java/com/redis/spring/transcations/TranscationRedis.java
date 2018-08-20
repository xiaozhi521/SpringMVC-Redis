package com.redis.spring.transcations;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SessionCallback;

import java.util.List;

/**
 *  Redis 事务可以一次执行多个命令， 并且带有以下两个重要的保证：
 *  事务是一个单独的隔离操作：事务中的所有命令都会序列化、按顺序地执行。事务在执行的过程中，不会被其他客户端发送来的命令请求所打断。
 *  事务是一个原子操作：事务中的命令要么全部被执行，要么全部都不执行。
 *  一个事务从开始到执行会经历以下三个阶段：
 *      开始事务。
 *      命令入队。
 *      执行事务。
 */
public class TranscationRedis {
    public static void main(String[] args) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
        final RedisTemplate redisTemplate = applicationContext.getBean(RedisTemplate.class);
        SessionCallback sessionCallback = new SessionCallback() {
            public Object execute(RedisOperations ops) throws DataAccessException {
                //1、开启事务
                ops.multi();
                //2、命令入队
                ops.boundValueOps("key1").set("value1");
                //由于命令只是进入队列，而没有被执行，所以此处采用 get 命令，而 value 返回为 null
                String value = (String) ops.boundValueOps("key1").get();
                System.out.println("事务执行过程中，命令入队列，而没有被执行，所以value为空；value = " + value);
                //此时list会保存之前进入队列的所有命令的结果
                //3、执行事务
                List list = ops.exec();
                //事物结束，获取 value1
                value = (String) redisTemplate.opsForValue().get("key1");
                return value;
            }
        };
        //执行Redis 命令
        String value = (String) redisTemplate.execute(sessionCallback);
        System.out.println(value);
    }
}
