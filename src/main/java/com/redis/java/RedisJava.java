package com.redis.java;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class RedisJava {
    public static void main(String[] args) {
        testRedisPerformance();
    }

    /**
     *  使用redis 连接池  testRedisPoolPerformance
     *  redis.clients.jedis.JedisPool
     *  redis.clients.jedis.JedisPoolConfig
     */
    public static Jedis getRedis(){
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        //设置最大的空闲数
        jedisPoolConfig.setMaxIdle(50);
        //设置最大等待毫秒
        jedisPoolConfig.setMaxTotal(100);
        //使用配置创建连接池
        JedisPool jedisPool = new JedisPool(jedisPoolConfig,"localhost");
        //从连接池中获取单个连接
        Jedis jedis = jedisPool.getResource();
        //需要密码
        jedis.auth("qwertyuiop@123");
        return jedis;
    }
    /**
     *  redis 性能测试 -- 简单的连接测试
     */
    public static void testRedisPerformance(){
        Jedis jedis = null;
        int i = 0;
        try{
            new Jedis("localhost");
            jedis.auth("qwertyuiop@123");
            //开始毫秒数
            long start = System.currentTimeMillis();
            while (true){

                long end = System.currentTimeMillis();
                //当大于等于1000毫秒，相当于1秒，结束
                if(end - start >= 1000){
                    break;
                }
                i++;
                jedis.set("test" + i, i + "");
                //设置超时时间
                jedis.pexpire("test" + i,10000L);
            }
        }finally {
            if(jedis != null){
                jedis.close();
            }
        }
        System.out.println("redis 每秒操作" + i + "次");
    }


    public static void connectRedis(){
        //连接本地的 Redis 服务
        Jedis jedis = new Jedis("127.0.0.1");
        jedis.auth("qwertyuiop@123");
        System.out.println("Connection to server sucessfully");
        //设置 redis 字符串数据
        jedis.set("w3ckey", "Redis tutorial");
        // 获取存储的数据并输出
        System.out.println("Stored string in redis:: "+ jedis.get("w3ckey"));
    }
}